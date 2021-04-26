package com.example.crudapp.Modelo;


import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author adria
 */
public class SqlServerConnection {
    private String url, server, puerto, user, password, database;
    private Connection connection = null;
    private Statement st = null;
    /***
     * Metodo que crea la conexion con un servidor, puerto, nombre de la base de datos, usuario y password
     */
    public SqlServerConnection(String pServer, String pDatabase, String pUser, String pPassword) throws SQLException, ClassNotFoundException {
        setServer(pServer);
        //setPuerto(pPuerto);
        setUser(pUser);
        setPassword(pPassword);
        setDatabase(pDatabase);

        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            setUrl("jdbc:jtds:sqlserver://" + server + ";databaseName=" + database + ";user=" + user + ";password=" + password + ";");
            connection = DriverManager.getConnection(url);
            if(connection != null){
                st = connection.createStatement();
            }
        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
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
