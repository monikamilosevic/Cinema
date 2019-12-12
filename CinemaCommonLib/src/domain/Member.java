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
public class Member implements GeneralEntity {

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private int totalScore;

    public Member() {
    }

    public Member(int id, String firstname, String lastname, String email, int totalScore) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.totalScore = totalScore;
    }

    @Override
    public String getTableName() {
        return "member";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws Exception {
        List<GeneralEntity> listMembers = new ArrayList<>();
        while (rs.next()) {
            listMembers.add(new Member(rs.getInt("memberID"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getInt("totalScore")));
        }
        return listMembers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Member other = (Member) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }

    @Override
    public String getColumnName() {
        return "firstname,lastname,email,totalScore";
    }

    @Override
    public String getValueForInsert() {
        return "'" + firstname + "','" + lastname + "','" + email + "'," + totalScore;
    }

    @Override
    public String getValueForUpdate() {
        return " firstname='" + firstname + "',lastname='" + lastname + "',email='" + email + "',totalScore=" + totalScore;
    }

    @Override
    public String getWhere() {
        return " memberID=" + id;
    }

    @Override
    public String getColumnForSelection() {
        return " * ";
    }

    @Override
    public String getAlias() {
        return "m";
    }

    @Override
    public String getJoin() {
        return "";
    }

    @Override
    public String getWhereForSelect() {
        if (firstname != null && lastname != null) {
            if (totalScore != -1) {
                return " where firstname LIKE '%" + firstname + "%' OR lastname LIKE '%" + lastname + "%' OR totalScore = " + totalScore;
            } else {
                return " where firstname LIKE '%" + firstname + "%' OR lastname LIKE '%" + lastname + "%' ";
            }
        }
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
