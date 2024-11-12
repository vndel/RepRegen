package net.drman.repregen.coll;

import com.massivecraft.massivecore.store.SenderColl;
import net.drman.repregen.entity.BPlayer;

public class BPlayerColl extends SenderColl<BPlayer> {
    private static BPlayerColl i = new BPlayerColl();
    public static BPlayerColl get() { return i; }

    private BPlayerColl() {
        super("repregen_players");
    }
}
