package by.it.medved.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Patterns {

    public final String LOGIN = "^[A-Za-z]\\w{3,29}$";
    public final String PASSWORD = "^[a-zA-Z0-9_]{6,20}$";
    public final String FIRST_NAME = "^[а-яА-ЯёЁa-zA-Z]{4,20}$";
    public final String EMAIL = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    public final String REQUEST_LOG_PATTERN = "{} -> {}, request: {}, URI: {}";
    public final String RESPONSE_LOG_PATTERN = "{} -> {}, URI: {}, response: {}";
}