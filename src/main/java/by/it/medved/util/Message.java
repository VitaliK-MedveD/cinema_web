package by.it.medved.util;

public final class Message {

    public static final String LOGIN_BUSY_MESSAGE = " -> занят! Придумайте новый логин.";
    public static final String LOGIN_NOT_REGISTERED = "Введенный логин не зерегистрирован.";
    public static final String INCORRECT_PASSWORD = "Введен неверный пароль.";
    public static final String REGEX_PASSWORD = "Пароль должен содержать от 6 до 20 симвлов из цифр и " +
            "латинских букв верхнего и нижнего регистра";
    public static final String REGEX_FIRST_NAME = "Имя должно содержать от 4 до 20 симвлов из руских и " +
            "латинских букв верхнего и нижнего регистра";
    public static final String REGEX_EMAIL = "Не соответствует формату электронной почты";
    public static final String REGEX_DATE = "Формат ввода даты: YYYY-MM-DD";

    private Message() {
    }
}
