package ru.lanit.at.utils.web.properties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/${browser}.properties",
        "classpath:config/chrome.properties",
        "system:properties",
        "system:env"
})
public interface WebConfigurations extends Config {

    @Key("webdriver.browser.size")
    @DefaultValue("1920x1080")
    String webDriverBrowserSize();

    @Key("webdriver.version")
    @DefaultValue("")
    String webDriverVersion();

    @Key("webdriver.browser.name")
    @DefaultValue("")
    String webDriverBrowserName();

    @Key("webdriver.timeoutSeconds")
    @DefaultValue("10")
    int webDriverTimeoutSeconds();


    @Key("webdriver.pageLoadTimeoutSeconds")
    @DefaultValue("120")
    int pageLoadTimeoutSeconds();

    @Key("selenoid.enableVNC")
    @DefaultValue("false")
    boolean enableVnc();

    @Key("polling.timeoutMs")
    @DefaultValue("200")
    int pollingTimeoutMs();

    @Key("pages.package")
    @DefaultValue("ru.lanit.at.pages")
    String pagesPackage();

    @Key("site_url")
    @DefaultValue("")
    String site_url();


}
