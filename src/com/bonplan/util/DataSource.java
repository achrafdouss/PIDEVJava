/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bhk
 */
public class DataSource {

    Connection connexion;
    final String url = "jdbc:mysql://localhost:3306/pidev";
    final String user = "root";
    final String password = "";
    private static DataSource instance;

    private DataSource() {
        try {
            connexion = DriverManager.getConnection(url, user, password);

        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }

        return instance;
    }

    public Connection getConnection() {
        return connexion;
    }
}
