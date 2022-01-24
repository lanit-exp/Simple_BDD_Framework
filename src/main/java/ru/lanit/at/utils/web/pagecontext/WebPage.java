package ru.lanit.at.utils.web.pagecontext;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.aeonbits.owner.ConfigFactory;
import ru.lanit.at.utils.reflections.ReflectionUtil;
import ru.lanit.at.utils.web.annotations.Name;
import ru.lanit.at.utils.web.properties.Configurations;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public abstract class WebPage {

    protected final Configurations configurations = ConfigFactory.create(Configurations.class, System.getProperties(),
            System.getenv());


    /**
     * Список всех элементов страницы
     */
    private Map<String, Object> namedElements;

    /**
     * @param name Annotations.Name
     *             Возвращает объект SelenideElement по его имени (аннотированного "Annotations.Name")
     */
    public SelenideElement getElement(String name) {
        Object instance = namedElements.get(name);
        if (instance != null && !(instance instanceof SelenideElement)) {
            throw new ClassCastException(String.format("Элемент [%s] должен иметь тип 'SelenideElement'", name));
        }
        return (SelenideElement) Optional.ofNullable(namedElements.get(name))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Элемент [%s] отсутствует в классе [%s]", name, this.getClass().getName())));
    }

    /**
     * @param name Annotations.Name
     * @return Возвращает объект ElementsCollection по его имени (аннотированного "Annotations.Name")
     */
    public ElementsCollection getElementsCollection(String name) {
        Object instance = namedElements.get(name);
        if (instance != null && !(instance instanceof ElementsCollection)) {
            throw new ClassCastException(String.format("Элемент [%s] должен иметь тип 'ElementsCollection'", name));
        }
        return (ElementsCollection) Optional.ofNullable(namedElements.get(name))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Элемент [%s] отсутствует в классе [%s]", name, this.getClass().getName())));
    }

    /**
     * @return Возвращает значение аннотации @Name(...) текущей страницы
     */
    public String name() {
        return this
                .getClass()
                .getAnnotation(Name.class)
                .value();
    }

    public WebPage initialize() {
        namedElements = readNamedElements();
        return this;
    }

    /**
     * Поиск и инициализации элементов страницы
     */
    private Map<String, Object> readNamedElements() {
        checkNamedAnnotations();
        return Arrays.stream(getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(Name.class) != null)
                .peek(this::checkFieldType)
                .collect(toMap(f -> f.getDeclaredAnnotation(Name.class).value(), this::extractFieldValueViaReflection));
    }

    private void checkFieldType(Field f) {
        if (!SelenideElement.class.isAssignableFrom(f.getType())
                && !WebPage.class.isAssignableFrom(f.getType())
        ) {
            this.checkCollectionFieldType(f);
        }
    }

    private void checkCollectionFieldType(Field f) {
        if (ElementsCollection.class.isAssignableFrom(f.getType())) {
            return;
        } else if (List.class.isAssignableFrom(f.getType())) {
            ParameterizedType listType = (ParameterizedType) f.getGenericType();
            Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];
            if (SelenideElement.class.isAssignableFrom(listClass) || WebPage.class.isAssignableFrom(listClass)) {
                return;
            }
        }
        throw new IllegalStateException(
                format("Поле с аннотацией '@Name' должно иметь тип SelenideElement, List<SelenideElement> или ElementsCollection.\n" +
                        "Найдено поле с типом %s", f.getType()));
    }

    /**
     * Поиск по аннотации "Annotations.Name"
     */
    private void checkNamedAnnotations() {
        List<String> list = Arrays.stream(getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(Name.class) != null)
                .map(f -> f.getDeclaredAnnotation(Name.class).value())
                .collect(toList());
        if (list.size() != new HashSet<>(list).size()) {
            throw new IllegalStateException("Найдено несколько аннотаций '@Name' с одинаковым значением в классе " + this.getClass().getName());
        }
    }

    private Object extractFieldValueViaReflection(Field field) {
        return ReflectionUtil.extractFieldValue(field, this);
    }


}
