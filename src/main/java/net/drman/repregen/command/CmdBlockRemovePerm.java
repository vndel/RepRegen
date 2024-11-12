package net.drman.repregen.command;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Visibility;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.enumeration.TypeMaterial;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import net.drman.repregen.Permission;
import net.drman.repregen.Utils;
import net.drman.repregen.command.Type.TypeBPlayer;
import net.drman.repregen.entity.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class CmdBlockRemovePerm extends BlockCommand {
    public CmdBlockRemovePerm() {
        this.setDesc("Removes allowed blocks to a player.");

        this.setVisibility(Visibility.SECRET);
        this.addRequirements(RequirementHasPerm.get(Permission.REMOVEPERM));

        this.addParameter(TypeBPlayer.get(), "player");
        this.addParameter(TypeString.get(), "blockID");
    }

    @Override
    public void perform() throws MassiveException {
        BPlayer player = this.readArg();
        String id = this.readArg();
        if(player != null) {
            if(Conf.get().blocks.containsKey(id)) {
                if(!player.getAllowedBlocks().contains(id)) {
                    msg(ChatColor.translateAlternateColorCodes('&', LangConf.get().msgNoPermFound));
                    return;
                }
                player.removeBlock(id);
                msg(ChatColor.translateAlternateColorCodes('&', LangConf.get().msgRemovedPermission.replaceAll("\\{player}", player.getName())));
            } else {
                msg(ChatColor.translateAlternateColorCodes('&', LangConf.get().msgUnknownBlock));
                return;
            }
        } else {
            msg(ChatColor.translateAlternateColorCodes('&', LangConf.get().msgUnknownPlayer));
            return;
        }
    }
}
