package ru.lanit.at.utils;

import java.util.concurrent.ThreadLocalRandom;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public class DataGenerator {

    /**
     * Генерирует набор русских, английских букв и цифр по маске
     * <p>
     * <br/> R - русская буква
     * <br/> D - цифра
     * <br/> E - английская буква
     *
     * @return - рандомная строка
     */
    public static String generateValueByMask(String mask) {
        StringBuilder result = new StringBuilder();
        char[] chars = mask.toCharArray();
        for (char aChar : chars) {
            switch (String.valueOf(aChar)) {
                case "R":
                    result.append(getRussianLetter());
                    break;
                case "D":
                    result.append(randomNumeric(1));
                    break;
                case "E":
                    result.append(randomAlphabetic(1).toLowerCase());
                    break;
                default:
                    result.append(aChar);
                    break;
            }
        }
        result.trimToSize();
        return result.toString();
    }

    /**
     * рандомная русская буква
     *
     * @return рандомная русская буква
     */
    private static String getRussianLetter() {
        int leftLimit = 1040;
        int rightLimit = leftLimit + 33;
        String res = "";
        int a = ThreadLocalRandom.current().nextInt(leftLimit, rightLimit + 1);
        char symbol = (char) a;
        res += symbol;
        return res;
    }
}
