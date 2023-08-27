package by.it.medved.util;

public final class Message {

    public static final String LOGIN_BUSY_MESSAGE = " -> занят! Придумайте новый логин.";
    public static final String LOGIN_NOT_REGISTERED = "Введенный логин не зерегистрирован.";
    public static final String INCORRECT_PASSWORD = "Введен неверный пароль.";
    public static final String INVALID_AGE = "Вы не можете купить билет, ограничение %d+";
    public static final String INVALID_DATE_TO_BUY = "Мероприятие уже прошло!";
    public static final String INVALID_DATE_TO_RETURN = "Tickets can be returned no later than 10 minutes before the start!";
    public static final String INVALID_PLACES = "No free places.";
    public static final String INCORRECT_CURRENT_PASSWORD = "Incorrect current password!";
    public static final String PASSWORDS_MISMATCH = "Passwords mismatch!";
    public static final String REGEX_LOGIN = "Login must contain between 4 and 30 characters from upper and " +
            "lower case Latin letters";
    public static final String REGEX_PASSWORD = "Password must contain between 6 and 20 characters from " +
            "numerals and Latin letters of upper and lower case";
    public static final String REGEX_FIRST_NAME = "The name must contain between 4 and 20 characters from " +
            "Russian and Latin upper and lower case letters";
    public static final String REGEX_EMAIL = "Doesn't match the email format";
    public static final String REGEX_DATE = "Date input format must match: YYYY-MM-DD";
    public static final String UPDATE_SUCCESSFUL = "Update successful";
    public static final String BUY_SUCCESSFULLY = "Ticket buy successfully";
    public static final String RETURN_SUCCESSFULLY = "Ticket return successfully";
    public static final String USER_TICKETS_EMPTY = "The user has no buyed tickets";
    public static final String TICKETS_EMPTY = "You has no buyed tickets";

    private Message() {
    }
}
