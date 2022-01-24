package ru.lanit.at.utils.web.properties;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/configuration.properties",
        "system:properties",
        "system:env"
})
public interface Configurations extends Config {


    @Key("stand")
    @DefaultValue("kpak")
    String getStand();


    @Key("screen_after_step")
    @DefaultValue("false")
    boolean screenAfterStep();
}
