package ru.lanit.at.reflections;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

public final class ReflectionUtil {

    /**
     * Получение списка классов по аннотации
     */
    public static Set<Class<?>> getPagesAnnotatedWith(String packageName, Class<? extends Annotation> annotation) {
        return new Reflections(packageName).getTypesAnnotatedWith(annotation);
    }

    /**
     * Получение поля класса с помощью механизма рефлексии
     */
    public static Object extractFieldValue(Field field, Object owner) {
        field.setAccessible(true);
        try {
            return field.get(owner);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            field.setAccessible(false);
        }
    }
}