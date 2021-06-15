/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class conexion {
    public static String URL_BBDD="jdbc:mysql://localhost:3306/sistema_alquiler?useSSL=false";
    public static String DRIVER_BBDD="com.mysql.jdbc.Driver";
    public static String USER_BBDD="root";
    public static String PASSWORD_BBDD="Nitro0Air";
    //objeto para realizar la conexion
    private Connection conexion;
    //metodo para realizar la conexion
    public void iniciarConexion()throws SQLException{
        try{
            Class.forName(DRIVER_BBDD);
            conexion=DriverManager.getConnection(URL_BBDD,USER_BBDD,PASSWORD_BBDD);
            
        }catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        
    }
    public void cerrarConextion()
    {
        if(conexion!=null)
        {
            try{
                conexion.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    //getter and setter

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
}
