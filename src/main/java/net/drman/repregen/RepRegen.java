package net.drman.repregen;

import com.massivecraft.massivecore.MassivePlugin;
import net.drman.repregen.coll.BPlayerColl;
import net.drman.repregen.coll.ConfColl;
import net.drman.repregen.coll.LangConfColl;
import net.drman.repregen.command.CmdBlock;
import net.drman.repregen.engine.BlockEngine;
import net.drman.repregen.intagration.aenchants.IntegrationAEnchants;
import net.drman.repregen.intagration.customitems.EngineCustomItems;
import net.drman.repregen.intagration.customitems.IntegrationCustomItems;
import net.drman.repregen.intagration.ultimateskills.IntegrationUltimateSkills;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

public final class RepRegen extends MassivePlugin {
    private static RepRegen i;
    public static RepRegen get() { return i; }

    public RepRegen() {i = this;}

    @Override
    public void onEnableInner() {
        this.activate(
                BPlayerColl.class,
                ConfColl.class,
                LangConfColl.class
        );

        this.activate(
                CmdBlock.class
        );

        this.activate(
                BlockEngine.class
        );

        this.activate(
                IntegrationAEnchants.class,
                IntegrationCustomItems.class,
                IntegrationUltimateSkills.class
        );
    }

    @Override
    public void onDisable() {
        BlockEngine.get().blocks.forEach(((location, object) -> {
            Block block = location.getBlock();
            block.setType(object.getBlockID().getMaterial());
            block.setData((byte)object.getBlockID().getData());
        }));
        EngineCustomItems.get().resetBlocks();
    }
}
