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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.utils.web.properties.Configurations;

import java.io.ByteArrayInputStream;

import static ru.lanit.at.assertion.AssertErrorType.CRITICAL_ASSERT;
import static ru.lanit.at.assertion.AssertErrorType.SOFT_ASSERT;

//import ru.lanit.at.utils.web.properties.Configurations;

public class AllureLogger implements StepLifecycleListener, TestLifecycleListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(AllureLogger.class);

    private final Configurations conf = ConfigFactory.create(Configurations.class, System.getProperties(),
            System.getenv());

    @Override
    public void beforeStepStop(StepResult result) {
        boolean screenAfterStep = conf.screenAfterStep();
        if (screenAfterStep && !result.getStatus().equals(Status.SKIPPED)) {
            Allure.addAttachment(result.getName(),
                    new ByteArrayInputStream(((TakesScreenshot)
                            WebDriverRunner.getWebDriver())
                            .getScreenshotAs(OutputType.BYTES)));
        }
    }

    @Override
    public void afterStepStop(StepResult result) {
        if (!result.getStatus().equals(Status.SKIPPED)) {
            if (isHasInnerBrokenStep(result)) {
                LOGGER.info("Устанавливаем для шага: '" + result.getName() + "', статус=BROKEN");
                result.setStatus(Status.BROKEN);
            }
        }


        if (result.getDescription() != null && (result.getDescription().contains(SOFT_ASSERT.getName()) || result.getDescription().contains(CRITICAL_ASSERT.getName()))) {
            LOGGER.info("Устанавливаем для шага: '" + result.getName() + "', статус=BROKEN");
            result.setStatus(Status.BROKEN);
        }
    }

    private boolean isHasInnerBrokenStep(StepResult result) {
        for (StepResult r : result.getSteps()) {
            if (r.getStatus().equals(Status.BROKEN)) {
                return true;
            }
        }
        return false;
    }

}