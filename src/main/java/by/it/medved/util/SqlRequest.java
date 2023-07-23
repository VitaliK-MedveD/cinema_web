package by.it.medved.util;

public final class SqlRequest {

    public static final String USER_CREATE = "INSERT INTO \"user\" (role, login, password, " +
            "first_name, email, date_birthday, date_created, salt) VALUES (?,?,?,?,?,?,?,?)";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM \"user\" WHERE login=?";
    public static final String GET_USER_BY_ID = "SELECT * FROM \"user\" WHERE id=?";
    public static final String GET_ALL_USERS = "SELECT * FROM \"user\"";
    public static final String UPDATE_ACCESS = "UPDATE \"user\" SET role =? WHERE id =?";
    public static final String UPDATE_USER_FIELDS = "UPDATE \"user\" SET first_name =?, email =?, " +
            "date_birthday =? WHERE id =?";
    public static final String DELETE_USER_BY_ID = "DELETE FROM \"user\" WHERE id=?";
    public static final String CREATE_TICKET = "INSERT INTO ticket (movie_id, movie_title, show_date, " +
            "show_time, number_of_place, price) VALUES (?,?,?,?,?,?)";
    public static final String GET_ALL_TICKETS = "SELECT * FROM ticket WHERE %s =?";
    public static final String BUY_OR_RETURN_TICKET = "UPDATE ticket SET user_id =? WHERE id =?";
    public static final String UPDATE_TICKET = "UPDATE ticket SET movie_id =?, person_id =?," +
            "movie_title =?, show_date =?, show_time =?, number_of_place =?, price =? WHERE id =?";
    public static final String DELETE_TICKET_BY_ID = "DELETE FROM ticket WHERE id=?";
    public static final String ADD_MOVIE = "INSERT INTO movie (movie_title, show_date, show_time, " +
            "price, age_limit) VALUES (?,?,?,?,?)";
    public static final String GET_MOVIE_BY_ID = "SELECT * FROM movie WHERE id =?";
    public static final String GET_MOVIE_BY_TITLE = "SELECT * FROM movie WHERE movie_title =?";
    public static final String GET_ALL_MOVIES = "SELECT * FROM movie";
    public static final String UPDATE_MOVIE = "UPDATE movie SET show_date =?, show_time =?, price =?, " +
            "age_limit =? WHERE id =?";
    public static final String DELETE_MOVIE_BY_ID = "DELETE FROM movie WHERE id=?";
}