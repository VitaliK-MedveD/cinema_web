package by.it.medved.repositories;

import by.it.medved.entities.Movie;
import by.it.medved.util.ConnectionManager;
import by.it.medved.util.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepositoryImpl implements MovieRepository{

    @Override
    public Movie createMovie(Movie movie) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO movie (movie_title, show_date, show_time, " +
                            "price, age_limit) VALUES (?,?,?,?,?)");
            statement.setString(1, movie.getMovieTitle());
            statement.setDate(2, Date.valueOf(movie.getShowDate()));
            statement.setTime(3, Time.valueOf(movie.getShowTime()));
            statement.setInt(4, movie.getPrice());
            statement.setInt(5, movie.getAgeLimit());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getMovieByTitle(movie.getMovieTitle());
    }

    @Override
    public Movie getMovieById(Long id) {
        try (Connection connection = ConnectionManager.open()) {
            Movie movie = new Movie();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM movie WHERE id =?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                movie = buildMovieFromDatabase(resultSet);
            }
            return movie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie getMovieByTitle(String title) {
        try (Connection connection = ConnectionManager.open()) {
            Movie movie = new Movie();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM movie WHERE movie_title =?");
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                movie = buildMovieFromDatabase(resultSet);
            }
            return movie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM movie");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = buildMovieFromDatabase(resultSet);
                movies.add(movie);
            }
            return movies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie updateMovie(Movie movie) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE movie SET show_date =?," +
                    "show_time =?, price =?, age_limit =? WHERE id =?");
            statement.setDate(1, Date.valueOf(movie.getShowDate()));
            statement.setTime(2, Time.valueOf(movie.getShowTime()));
            statement.setInt(3, movie.getPrice());
            statement.setInt(4, movie.getAgeLimit());
            statement.setLong(5, movie.getId());
            statement.execute();
            return getMovieById(movie.getId());
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Movie deleteMovieById(Long id) {
        Movie movie = getMovieById(id);
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM movie WHERE id=?");
            statement.setLong(1, id);
            statement.execute();
            return movie;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    private Movie buildMovieFromDatabase(ResultSet resultSet) throws SQLException {
        Movie movie = Movie.builder()
                .id(resultSet.getLong(DataBase.ID))
                .movieTitle(resultSet.getString(DataBase.MOVIE_TITLE))
                .showDate(resultSet.getDate(DataBase.SHOW_DATE).toLocalDate())
                .showTime(resultSet.getTime(DataBase.SHOW_TIME).toLocalTime())
                .price(resultSet.getInt(DataBase.PRICE))
                .ageLimit(resultSet.getInt(DataBase.AGE_LIMIT))
                .build();
        return movie;
    }
}
