package net.drman.repregen.intagration.ultimateskills;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;

public class IntegrationUltimateSkills extends Integration {
    private static IntegrationUltimateSkills i = new IntegrationUltimateSkills();
    public static IntegrationUltimateSkills get() {return i;}

    public IntegrationUltimateSkills() {
        this.setPluginName("UltimateCore-Skills");
    }

    @Override
    public Engine getEngine() {
        return EngineUltimateSkills.get();
    }
}
