package net.drman.repregen.intagration.aenchants;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;

public class IntegrationAEnchants extends Integration {
    private static IntegrationAEnchants i = new IntegrationAEnchants();
    public static IntegrationAEnchants get() {
        return i;
    }

    public IntegrationAEnchants() {
        this.setPluginName("AdvancedEnchantments");
    }

    @Override
    public Engine getEngine() {
        return EngineAEnchantments.get();
    }

}
