package ru.lanit.at.utils.allure;


import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.utils.Sleep;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static io.qameta.allure.Allure.getLifecycle;

public class AllureHelper {
    private final static Logger LOGGER = LoggerFactory.getLogger(Sleep.class);
    private static ThreadLocal<AllureLifecycle> threadLocal = new ThreadLocal<>();

    /* Установка  AllureLifecycle для потока выполнения, используется при запуске фреймворка, как веб сервиса **/
    public static void setLifecycle(AllureLifecycle allureLifecycle) {
        threadLocal.set(allureLifecycle);
    }

    public static void attachScreenShot(String name, byte[] bytes) {
        get().addAttachment(name, "image/png", "png", bytes);
    }

    public static void attachTxt(String name, String text) {
        get().addAttachment(name, "text/plain", "txt", text.getBytes(StandardCharsets.UTF_8));
    }

    public static void attachPageSource(byte[] bytes) {
        get().addAttachment("Page source", "text/html", "html", bytes);
    }

    public static void setStepStatusBroken(String description) {
        get().updateStep(stepResult -> stepResult.setDescription(description));
    }

    public static void addInfoAllureStep(String stepName) {
        String uuid = UUID.randomUUID().toString();
        createAllureStep(uuid, stepName, Status.PASSED);
        LOGGER.info("выполняется шаг : \"" + stepName + "\"");
        stopAllureStep(uuid);
    }

    public static void addInfoAllureStep(String uuid, String stepName) {
        createAllureStep(uuid, stepName, Status.PASSED);
        LOGGER.info("выполняется шаг : \"" + stepName + "\"");
        stopAllureStep(uuid);
    }

    public static void addBrokenAllureStep(String stepName, String textMessage) {
        String uuid = UUID.randomUUID().toString();
        stepName = stepName + " [" + textMessage + "]";
        createAllureStep(uuid, stepName, Status.BROKEN);
        stopAllureStep(uuid);
    }


    public static void createAllureStep(String parentUuid, String uuid, String stepName, Status status) {
        LOGGER.info("Создание аллюр шага с именем :" + stepName);
        StepResult stepResult = new StepResult();
        stepResult.setName(stepName)
                .setStatus(status);
        get().startStep(parentUuid, uuid, stepResult);
    }

    public static void createAllureStep(String uuid, String stepName, Status status) {
        LOGGER.info("Создание аллюр шага с именем :" + stepName);
        StepResult stepResult = new StepResult();
        stepResult.setName(stepName)
                .setStatus(status);
        get().startStep(uuid, stepResult);
    }

    public static void stopAllureStep(String uuid) {
        get().stopStep(uuid);
    }


    private static AllureLifecycle get() {
        return getLifecycle();
    }
}