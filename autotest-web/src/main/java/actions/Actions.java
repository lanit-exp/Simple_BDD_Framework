package actions;

import com.codeborne.selenide.SelenideElement;
import ru.lanit.at.utils.Sleep;

public class Actions {

    public static int getRandom(int range) {
        return (int)(Math.random() * range);
    }
    public static int getRandom(int start, int range) {
        return start + (int)(Math.random() * range);
    }

    /**
     * Заполнение поля рандомным числовым значением в определенном диапазоне
     *  @param element - элемент
     */
    public static void fillFieldRandInt(SelenideElement element, int start, int finish) {
        element.setValue(String.valueOf(getRandom(start, finish)));
        Sleep.pauseSec(0.2);
    }

    public static void fillInputByCharacter(SelenideElement element, String text) {
        for (char character : text.toCharArray()) {
            element.sendKeys(String.valueOf(character));
            Sleep.pauseSec(0.2);
        }
    }
}
