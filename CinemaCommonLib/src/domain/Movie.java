/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Monika
 */
public class Movie implements GeneralEntity {

    private int id;
    private String title;
    private String genre;

    public Movie() {
    }

    public Movie(int id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public String getTableName() {
        return " movie ";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws Exception {
        List<GeneralEntity> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Movie(rs.getInt("movieID"), rs.getString("title"), rs.getString("genre")));
        }
        return list;
    }

    @Override
    public String getColumnName() {
        return " ";
    }

    @Override
    public String getValueForInsert() {
        return "";
    }

    @Override
    public String getValueForUpdate() {
        return "";
    }

    @Override
    public String getWhere() {
        return "";
    }

    @Override
    public String getColumnForSelection() {
        return " * ";
    }

    @Override
    public String getAlias() {
        return " m ";
    }

    @Override
    public String getJoin() {
        return "";
    }

    @Override
    public String getWhereForSelect() {
        return "";
    }

    @Override
    public String getGroup() {
        return "";
    }

    @Override
    public String getMaxPrimary() {
        return "";
    }

}
