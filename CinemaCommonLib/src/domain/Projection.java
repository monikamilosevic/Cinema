/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Monika
 */
public class Projection implements GeneralEntity {

    private int id;
    private Date date;
    private double price;
    private int score;
    private int freeSeats;
    private Movie movie;
    private List<Auditorium> auditoriums;

    public Projection() {
    }

    public Projection(int id, Date date, double price, int score, int freeSeats, Movie movie, List<Auditorium> auditoriums) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.score = score;
        this.freeSeats = freeSeats;
        this.movie = movie;
        this.auditoriums = auditoriums;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(List<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    @Override
    public String getTableName() {
        return " projection ";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws Exception {
        List<GeneralEntity> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Projection(rs.getInt("projectionID"),
                    new Date(rs.getTimestamp("date").getTime()), rs.getDouble("price"),
                    rs.getInt("score"), rs.getInt("freeSeats"), new Movie(rs.getInt("movieID"),
                            rs.getString("title"), rs.getString("genre")),
                    null));
        }
        return list;
    }

    @Override
    public String getColumnName() {
        return " date, price, score, freeSeats, movieID ";
    }

    @Override
    public String getValueForInsert() {
        return " '" + new java.sql.Timestamp(date.getTime()) + "', " + price + ", " + score + ", " + freeSeats + ", " + movie.getId() + " ";
    }

    @Override
    public String getValueForUpdate() {
        return " date = '" + new java.sql.Timestamp(date.getTime()) + "', price = "
                + price + ", score = " + score + ", freeSeats = " + freeSeats + ", movieID = " + movie.getId() + " ";
    }

    @Override
    public String getWhere() {
        return " projectionID = " + id;
    }

    @Override
    public String getColumnForSelection() {
        return " p.*, m.* ";
    }

    @Override
    public String getAlias() {
        return " p ";
    }

    @Override
    public String getJoin() {
        return " JOIN movie m on p.movieID = m.movieID ";
    }

    @Override
    public String getWhereForSelect() {
        if (movie.getTitle() != null) {
            return " where m.title LIKE '%" + movie.getTitle() + "%'";
        }
        return "";
    }

    @Override
    public String getGroup() {
        return "";
    }

    @Override
    public String getMaxPrimary() {
        return " projectionID ";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Projection other = (Projection) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
}
