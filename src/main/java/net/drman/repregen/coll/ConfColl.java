package net.drman.repregen.coll;

import com.massivecraft.massivecore.MassiveCore;
import com.massivecraft.massivecore.store.Coll;
import net.drman.repregen.entity.Conf;

public class ConfColl extends Coll<Conf> {
    private static final ConfColl i = new ConfColl();
    public static ConfColl get() { return i; }

    @Override
    public void onTick() {
        super.onTick();
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
        if (!active)return;
        Conf.set(get(MassiveCore.INSTANCE, true));
    }
}
