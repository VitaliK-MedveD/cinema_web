package by.it.medved.util;

public final class JpqlQuery {

    public static final String READ_ALL_USERS = "select u from by.it.medved.entities.User u";
    public static final String READ_ALL_MOVIES = "select m from by.it.medved.entities.Movie m";

    private JpqlQuery() {
    }
}
