package properties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/chrome.properties",
        "classpath:config/${configName}.properties",
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

    @Key("webdriver.timeoutMs")
    @DefaultValue("10000")
    int webDriverTimeoutMs();

    @Key("hub.url")
    @DefaultValue("")
    String hubUrl();

    @Key("selenoid.enableVNC")
    @DefaultValue("false")
    boolean enableVnc();

    @Key("polling.timeoutMs")
    @DefaultValue("200")
    int pollingTimeoutMs();

    @Key("pages.package")
    @DefaultValue("pages")
    String pagesPackage();
}
