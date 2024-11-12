package net.drman.repregen.entity;

import com.massivecraft.massivecore.store.Entity;

public class LangConf extends Entity<LangConf> {
    private static LangConf i = new LangConf();
    public static LangConf get() {
        return i;
    }
    public static void set(LangConf conf) {i = conf;}

    public String msgAddedBlock = "&c&lRepRegen &7// &aAdded block.";
    public String msgRemovedBlock = "&c&lRepRegen &7// &cRemoved block.";
    public String msgAddedPermission = "&c&lRepRegen &7// &aAdded permission to &e{player}&a.";
    public String msgRemovedPermission = "&c&lRepRegen &7// &cRemoved permission from &e{player}&c.";
    public String msgUnknownPlayer = "&c&lRepRegen &7// &cUnknown player.";
    public String msgUnknownBlock = "&c&lRepRegen &7// &cUnknown block";
    public String msgErrorBoolean = "&c&lRepRegen &7// &cPlease enter a boolean value (true/false)";
    public String msgErrorMaterial = "&c&lRepRegen &7// &cPlease enter a material value &e(https://helpch.at/docs/1.8/org/bukkit/Material.html)";
    public String msgErrorInteger = "&c&lRepRegen &7// &cPlease enter a whole number.";
    public String msgAlreadyHasPerm = "&c&lRepRegen &7// &cPlayer already has perm.";
    public String msgNoPermFound = "&c&lRepRegen &7// &cPlayer doesn't have perm.";
}
