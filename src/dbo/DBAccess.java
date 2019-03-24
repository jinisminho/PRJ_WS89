/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Hoang Pham
 */
public class DBAccess {

    Connection con = null;
    Statement stmt = null;

    public DBAccess() {
    }

    public void connectDB(String driver, String url) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
    }

    public void connectDB(String driver, String url, String uid, String pwd) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, uid, pwd);
            stmt = con.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
    }

    public ResultSet executeQuery(String selectSql) {
        if (con == null) {
            return null;
        }
        try {
            return (stmt.executeQuery(selectSql));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
        return null;
    }

    public int executeUpdate(String updateSql) {
        if (con == null) {
            return 0;
        }
        try {
            return (stmt.executeUpdate(updateSql));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
        return 0;
    }
}
