package ru.lanit.at.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Sleep {

    private final static Logger LOGGER = LoggerFactory.getLogger(Sleep.class);

    public static void pauseSec(double sec) {
        LOGGER.info("Ожидание {} секунд", sec);
        sleep(sec * 1000);
    }

    public static void pauseMs(double ms) {
        LOGGER.info("Ожидание {} миллисекунд", ms);
        sleep(ms);
    }

    private static void sleep(double ms) {
        try {
            TimeUnit.MILLISECONDS.sleep((long) ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
