package net.drman.repregen.intagration.aenchants;

import com.leonardobishop.quests.bukkit.BukkitQuestsPlugin;
import com.leonardobishop.quests.bukkit.tasktype.type.MiningCertainTaskType;
import com.massivecraft.massivecore.Engine;
import net.advancedplugins.ae.api.AEAPI;
import net.advancedplugins.ae.api.AEnchantmentType;
import net.advancedplugins.ae.api.CustomEffect;
import net.advancedplugins.ae.api.EffectActivationEvent;
import net.advancedplugins.ae.enchanthandler.enchanttypes.MINING;
import net.drman.repregen.RepRegen;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class EngineAEnchantments extends Engine {
    private static EngineAEnchantments i = new EngineAEnchantments();
    public static EngineAEnchantments get() {return i;}

    public void runBlockBreakEvent(BlockBreakEvent event) {
        if(!isActive()) {
            return;
        }
        MINING mining = new MINING();
        mining.onBlockBreak(event);
    }

    public boolean itemHasTelekinesis(ItemStack item) {
        if(!isActive()) {
            return false;
        }
        if(AEAPI.isAnEnchantment("telekinesis")) {
            return AEAPI.hasCustomEnchant("telekinesis", item);
        } else return false;
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
    }
}
