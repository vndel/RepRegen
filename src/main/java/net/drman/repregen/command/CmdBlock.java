package net.drman.repregen.command;

import com.massivecraft.massivecore.util.MUtil;

import java.util.List;

public class CmdBlock extends BlockCommand {
    private static final CmdBlock i = new CmdBlock();
    public static CmdBlock get() {return i;}

    public CmdBlockAddPerm cmdBlockAddPerm = new CmdBlockAddPerm();
    public CmdBlockRemovePerm cmdBlockRemovePerm = new CmdBlockRemovePerm();

    @Override
    public List<String> getAliases() {
        return MUtil.list("blockengine", "bengine");
    }
}
