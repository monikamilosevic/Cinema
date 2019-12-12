/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Monika
 */
public class ProjectionAuditorium implements GeneralEntity {

    private int projectionID;
    private int auditoriumID;

    public ProjectionAuditorium() {
    }

    public ProjectionAuditorium(int projectionID, int auditoriumID) {
        this.projectionID = projectionID;
        this.auditoriumID = auditoriumID;
    }

    public int getProjectionID() {
        return projectionID;
    }

    public void setProjectionID(int projectionID) {
        this.projectionID = projectionID;
    }

    public int getAuditoriumID() {
        return auditoriumID;
    }

    public void setAuditoriumID(int auditoriumID) {
        this.auditoriumID = auditoriumID;
    }

    @Override
    public String getTableName() {
        return " projectionauditorium ";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws Exception {
        List<GeneralEntity> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Auditorium(rs.getInt("a.auditoriumID"), rs.getString("a.name"), rs.getInt("a.capacity")));
        }
        return list;
    }

    @Override
    public String getColumnName() {
        return " projectionID, auditoriumID ";
    }

    @Override
    public String getValueForInsert() {
        return " " + projectionID + ", " + auditoriumID + " ";
    }

    @Override
    public String getValueForUpdate() {
        return "";
    }

    @Override
    public String getWhere() {
        return " projectionID = " + projectionID + " AND auditoriumID = " + auditoriumID;
    }

    @Override
    public String getColumnForSelection() {
        return " a.* ";
    }

    @Override
    public String getAlias() {
        return " pa ";
    }

    @Override
    public String getJoin() {
        return " JOIN auditorium a on pa.auditoriumID = a.auditoriumID ";
    }

    @Override
    public String getWhereForSelect() {
        return " WHERE pa.projectionID = " + projectionID;
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
        final ProjectionAuditorium other = (ProjectionAuditorium) obj;
        if (this.projectionID != other.projectionID) {
            return false;
        }
        if (this.auditoriumID != other.auditoriumID) {
            return false;
        }
        return true;
    }
    
    

}
