package ru.lanit.at.utils.allure;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.lanit.at.web.properties.WebConfigurations;

import java.io.ByteArrayInputStream;

public class AllureLogger implements StepLifecycleListener, TestLifecycleListener {

    private final WebConfigurations CFG = ConfigFactory.create(WebConfigurations.class,
            System.getProperties(),
            System.getenv());

    @Override
    public void beforeStepStop(StepResult result) {
        boolean screenAfterStep = Boolean.parseBoolean(
                System.getProperty("screenAfterStep",
                        String.valueOf(CFG.screenAfterStep())));
        if (screenAfterStep && !result.getStatus().equals(Status.SKIPPED)) {
            Allure.addAttachment(result.getName(),
                    new ByteArrayInputStream(((TakesScreenshot)
                            WebDriverRunner.getWebDriver())
                            .getScreenshotAs(OutputType.BYTES)));
        }
    }
}