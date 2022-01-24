package ru.lanit.at.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Sleep {

    private final static Logger LOGGER = LoggerFactory.getLogger(Sleep.class);

    public static void pauseSec(int sec) {
        LOGGER.info("Ожидание {} секунд", sec);
        sleep(sec * 1000);
    }

    public static void sleep(long ms) {
        try {
            LOGGER.info("Ожидание {} миллисекунд", ms);
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            LOGGER.warn("Interrupted!", e);
            Thread.currentThread().interrupt();
        }
    }
}
