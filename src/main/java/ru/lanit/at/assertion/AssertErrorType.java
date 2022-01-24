package ru.lanit.at.assertion;

public enum AssertErrorType {

    SOFT_ASSERT("SoftAssert"),
    CRITICAL_ASSERT("CriticalAssert");

    private String name;

    AssertErrorType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}