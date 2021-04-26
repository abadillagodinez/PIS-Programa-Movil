/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.crudapp.Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author adrian
 */
public class MySqlConnection {
    private String url, server, puerto, user, password, database;
    private Connection connection = null;
    private Statement st = null;

    /***
     * Metodo que crea la conexion con un servidor, puerto, nombre de la base de datos, usuario y password
     */
    public MySqlConnection(String pServer, String pPuerto, String pDatabase, String pUser, String pPassword) throws SQLException {
        setServer(pServer);
        setPuerto(pPuerto);
        setUser(pUser);
        setPassword(pPassword);
        setDatabase(pDatabase);
        setUrl("jdbc:mysql://" + server + ":" + puerto + "/" + database);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            if(connection != null){
                st = connection.createStatement();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Statement getSt() {
        return st;
    }


}