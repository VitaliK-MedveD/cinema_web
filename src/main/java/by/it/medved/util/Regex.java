package by.it.medved.util;

import java.time.format.DateTimeFormatter;

public final class Regex {

    public static final String LOGIN = "^[A-Za-z]\\w{3,29}$";
    public static final String PASSWORD = "^[a-zA-Z0-9_]{6,20}$";
    public static final String FIRST_NAME = "^[а-яА-ЯёЁa-zA-Z]{4,20}$";
    public static final String EMAIL = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    public static final String NUMBER = "^[1-9]+[0-9]?$";
    public static final String DATE = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])";
    public static final String TIME = "^([01]\\d|2[0-3]):([0-5]\\d)$";

    private Regex() {
    }
}
