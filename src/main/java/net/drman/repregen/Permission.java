package net.drman.repregen;

import com.massivecraft.massivecore.Identified;
import com.massivecraft.massivecore.util.PermissionUtil;
import org.bukkit.permissions.Permissible;

public enum Permission implements Identified {
    BASECOMMAND,
    ADDPERM,
    REMOVEPERM,
    CREATEBLOCK,
    EDITBLOCK,
    DETELEBLOCK,

    OVERRIDE
    ;

    private final String id;

    Permission() {
        this.id = PermissionUtil.createPermissionId(RepRegen.get(), this);
    }

    @Override
    public String getId() {
        return this.id;
    }

    public boolean has(Permissible permissible, boolean verbose) {
        return PermissionUtil.hasPermission(permissible, this, verbose);
    }

    public boolean has(Permissible permissible) {
        return PermissionUtil.hasPermission(permissible, this);
    }
}
