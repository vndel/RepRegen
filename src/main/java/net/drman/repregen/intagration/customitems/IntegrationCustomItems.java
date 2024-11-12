package net.drman.repregen.intagration.customitems;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;

public class IntegrationCustomItems extends Integration {
    private static IntegrationCustomItems i = new IntegrationCustomItems();
    public static IntegrationCustomItems get() {
        return i;
    }

    public IntegrationCustomItems() {
        this.setPluginName("CustomItems");
    }

    @Override
    public Engine getEngine() {
        return EngineCustomItems.get();
    }
}
