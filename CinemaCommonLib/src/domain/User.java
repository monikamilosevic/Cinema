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
public class User implements GeneralEntity {

    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;

    public User() {
    }

    public User(int id, String username, String password, String firstname, String lastname, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws Exception {
        List<GeneralEntity> listUsers = new ArrayList<>();
        while (rs.next()) {
            listUsers.add(new User(rs.getInt("userID"), rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email")));
        }
        return listUsers;
    }

    @Override
    public String getColumnName() {
        return "";
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
        return "*";
    }

    @Override
    public String getAlias() {
        return "u";
    }

    @Override
    public String getJoin() {
        return "";
    }

    @Override
    public String getWhereForSelect() {
        return " where username='" + username + "' and password='" + password + "' LIMIT 1";
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
    public String toString() {
        return firstname + " " + lastname;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
