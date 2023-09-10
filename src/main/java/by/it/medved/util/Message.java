package by.it.medved.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Message {

    public final String EMPTY = "";
    public final String LOGIN_BUSY_MESSAGE = "Login '%s' is busy! Invent a new login";
    public final String INCORRECT_PASSWORD = "Password is incorrect";
    public final String PASSWORDS_MISMATCH = "Passwords mismatch!";
    public final String USER_BY_ID_NOT_EXIST = "User with id '%s' does not exist";
    public final String USER_BY_LOGIN_NOT_EXIST = "User with login '%s' does not exist";
    public final String MOVIE_BY_ID_NOT_EXIST = "Movie with id '%s' does not exist";
    public final String TICKET_BY_ID_NOT_EXIST = "Ticket with id '%s' does not exist";
    public final String LOGIN_REQUIREMENTS = "Login must contain between 4 and 30 characters from upper and " +
            "lower case Latin letters";
    public final String PASSWORD_REQUIREMENTS = "Password must contain between 6 and 20 characters from " +
            "numerals and Latin letters of upper and lower case";
    public final String FIRST_NAME_REQUIREMENTS = "The name must contain between 4 and 20 characters from " +
            "Russian and Latin upper and lower case letters";
    public final String EMAIL_REQUIREMENTS = "Doesn't match the email format";
    public final String DATE_REQUIREMENTS = "Date value should be in the past";
    public final String TOKEN = "X-API-KEY=7MRRCKR-GZYMBJN-HT1WDBS-G7C0V5C";
}