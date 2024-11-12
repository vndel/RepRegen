package net.drman.repregen.command;

import com.massivecraft.massivecore.command.MassiveCommand;
import net.drman.repregen.Permission;
import net.drman.repregen.entity.BPlayer;

public class BlockCommand extends MassiveCommand {
    protected BPlayer senderBPlayer;

    public BlockCommand() {
        this.setDesc("View BlockEngine commands.");

        this.setSetupEnabled(true);
        this.setSetupPermClass(Permission.class);
    }

    @Override
    public void senderFields(boolean set) {
        if(this.senderIsConsole) {
            senderBPlayer = null;
        } else {
            this.senderBPlayer = set? BPlayer.get(this.sender):null;
        }
    }
}
