package ru.lanit.at.utils.selenide.extensions;

import com.codeborne.selenide.commands.Commands;
import com.codeborne.selenide.impl.WebElementSource;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;

/** Класс для переопределения и добавление методов Selenide элемента */
@ParametersAreNonnullByDefault
public class CustomCommands
        extends Commands {
    @Nullable
    @Override
    public <T> T execute(Object proxy, WebElementSource webElementSource, String methodName, @Nullable Object[] args) throws IOException {
        return super.execute(proxy, webElementSource, methodName, args);
    }
}
