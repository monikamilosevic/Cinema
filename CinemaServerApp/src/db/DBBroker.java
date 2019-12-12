/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domain.GeneralEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.SettingsLoader;

/**
 *
 * @author Monika
 */
public class DBBroker {

    private static DBBroker instance;
    private Connection connection;

    private DBBroker() {
    }

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public void openConnection() throws SQLException {
        String url = SettingsLoader.getInstance().getProperty("url");
        String dbuser = SettingsLoader.getInstance().getProperty("user");
        String dbpassword = SettingsLoader.getInstance().getProperty("password");

        connection = DriverManager.getConnection(url, dbuser, dbpassword);
        connection.setAutoCommit(false);

    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert(GeneralEntity ge) throws SQLException {
        String sql = "INSERT INTO " + ge.getTableName() + "(" + ge.getColumnName() + ")" + " VALUES (" + ge.getValueForInsert() + ")";
        System.out.println(sql);
        Statement s = connection.createStatement();
        s.executeUpdate(sql);
        s.close();
    }

    public void update(GeneralEntity ge) throws SQLException {
        String sql = "UPDATE " + ge.getTableName() + " SET " + ge.getValueForUpdate() + " WHERE " + ge.getWhere();
        System.out.println(sql);
        Statement s = connection.createStatement();
        s.executeUpdate(sql);
        s.close();
    }

    public void delete(GeneralEntity ge) throws SQLException {
        String sql = "DELETE FROM " + ge.getTableName() + " WHERE " + ge.getWhere();
        System.out.println(sql);
        Statement s = connection.createStatement();
        s.executeUpdate(sql);
        s.close();
    }

    public ResultSet select(GeneralEntity ge) throws SQLException {
        String sql = "SELECT " + ge.getColumnForSelection() + " FROM " + ge.getTableName() + " as " + ge.getAlias()
                + ge.getJoin() + ge.getWhereForSelect() + ge.getGroup();
        System.out.println(sql);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(sql);
        return rs;
    }

    public int getID(GeneralEntity ge) throws SQLException {
        int max = 0;
        String sql = "SELECT max(" + ge.getMaxPrimary() + ") as maxKey FROM " + ge.getTableName();
        System.out.println(sql);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            max = rs.getInt("maxKey");
        }

        return max;
    }
}
