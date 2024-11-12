package net.drman.repregen.command.Type;

import com.massivecraft.massivecore.command.type.Type;
import net.drman.repregen.coll.BPlayerColl;
import net.drman.repregen.entity.BPlayer;

public class TypeBPlayer {
    public static Type<BPlayer> get() {
        return BPlayerColl.get().getTypeEntity();
    }
}
