package ru.lanit.at.utils;


import java.util.HashMap;
import java.util.Map;

/**
 * Класс для хранения переменных теста
 * Синтаксис %{var_name}%
 */
public class ContextHolder {

    private static final ThreadLocal<Map<String, Object>> THREAD = new ThreadLocal<>();

    private static Map<String, Object> getThread() {
        Map<String, Object> vault = THREAD.get();
        if (vault == null) {
            vault = new HashMap<>();
            THREAD.set(vault);
        }
        return vault;
    }


    public static Map<String, Object> asMap() {
        return getThread();
    }

    public static void put(String key, Object value) {
        getThread().put(key, value);
    }

    public static String replaceVarsIfPresent(String text) {
        if (text == null) {
            return "";
        } else {
            return VariableUtil.replaceVars(text, asMap());
        }
    }

    public static <T> T getValue(String key) {
        return (T) getThread().get(key);
    }

    public static <T> T remove(String key) {
        return (T) getThread().remove(key);
    }
}