package by.it.medved.repositories;

import by.it.medved.entities.Ticket;
import by.it.medved.util.ConnectionManager;
import by.it.medved.util.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImpl implements TicketRepository{

    @Override
    public boolean createTicket(Ticket ticket) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO ticket (movie_id, movie_title, show_date, " +
                            "show_time, number_of_place, price) VALUES (?,?,?,?,?,?)");
            statement.setLong(1, ticket.getMovieId());
            statement.setString(2, ticket.getMovieTitle());
            statement.setDate(3, Date.valueOf(ticket.getShowDate()));
            statement.setTime(4, Time.valueOf(ticket.getShowTime()));
            statement.setInt(5, ticket.getNumberOfPlace());
            statement.setInt(6, ticket.getPrice());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ticket> getAllTickets(Long id, String columnName) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ticket WHERE " +
                    columnName + " =?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = buildTicketFromDatabase(resultSet);
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean buyOrReturnTicket(Long ticketId, Long userId) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE ticket SET user_id =? " +
                    "WHERE id =?");
            statement.setLong(1, userId);
            statement.setLong(2, ticketId);
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean updateTicket(Ticket ticket) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE ticket SET movie_id =?, person_id =?," +
                    "movie_title =?, show_date =?, show_time =?, number_of_place =?, price =? WHERE id =?");
            statement.setLong(1, ticket.getMovieId());
            statement.setLong(2, ticket.getUserId());
            statement.setString(3, ticket.getMovieTitle());
            statement.setDate(4, Date.valueOf(ticket.getShowDate()));
            statement.setTime(5, Time.valueOf(ticket.getShowTime()));
            statement.setInt(6, ticket.getNumberOfPlace());
            statement.setInt(7, ticket.getPrice());
            statement.setLong(8, ticket.getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deleteTicketById(Long id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ticket WHERE id=?");
            statement.setLong(1, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    private Ticket buildTicketFromDatabase(ResultSet resultSet) throws SQLException {
        Ticket ticket = Ticket.builder()
                .id(resultSet.getLong(DataBase.ID))
                .movieId(resultSet.getLong(DataBase.MOVIE_ID))
                .userId(resultSet.getLong(DataBase.USER_ID))
                .movieTitle(resultSet.getString(DataBase.MOVIE_TITLE))
                .showDate(resultSet.getDate(DataBase.SHOW_DATE).toLocalDate())
                .showTime(resultSet.getTime(DataBase.SHOW_TIME).toLocalTime())
                .numberOfPlace(resultSet.getInt(DataBase.NUMBER_PLACE))
                .price(resultSet.getInt(DataBase.PRICE))
                .build();
        return ticket;
    }
}
