package by.it.medved.util;

public final class Message {

    public static final String LOGIN_BUSY_MESSAGE = " -> занят! Придумайте новый логин.";
    public static final String LOGIN_NOT_REGISTERED = "Введенный логин не зерегистрирован.";
    public static final String INCORRECT_PASSWORD = "Введен неверный пароль.";
    public static final String INVALID_AGE = "Вы не можете купить билет, ограничение %d+";
    public static final String INVALID_DATE_TO_BUY = "Мероприятие уже прошло!";
    public static final String INVALID_DATE_TO_RETURN = "Tickets can be returned no later than 10 minutes before the start!";
    public static final String INVALID_PLACES = "No free places.";
    public static final String REGEX_PASSWORD = "Пароль должен содержать от 6 до 20 симвлов из цифр и " +
            "латинских букв верхнего и нижнего регистра";
    public static final String REGEX_FIRST_NAME = "Имя должно содержать от 4 до 20 симвлов из руских и " +
            "латинских букв верхнего и нижнего регистра";
    public static final String REGEX_EMAIL = "Doesn't match email format";
    public static final String REGEX_DATE = "Date input format: YYYY-MM-DD";
    public static final String UPDATE_SUCCESSFUL = "Update successful";
    public static final String RETURN_SUCCESSFULLY = "Ticket return successfully";
    public static final String USER_TICKETS_EMPTY = "The user has no buyed tickets";
    public static final String TICKETS_EMPTY = "You has no buyed tickets";

    private Message() {
    }
}
