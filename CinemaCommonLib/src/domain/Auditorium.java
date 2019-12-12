/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import transfer.util.Status;

/**
 *
 * @author Monika
 */
public class Auditorium implements GeneralEntity {

    private int id;
    private String name;
    private int capacity;
    private String status = Status.UPDATE;

    public Auditorium() {
    }

    public Auditorium(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return name + ", capacity=" + capacity;
    }

    @Override
    public String getTableName() {
        return " auditorium ";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws Exception {
        List<GeneralEntity> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Auditorium(rs.getInt("auditoriumID"), rs.getString("name"), rs.getInt("capacity")));
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
        return " a ";
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

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Auditorium other = (Auditorium) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
