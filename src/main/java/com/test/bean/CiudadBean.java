/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bean;

import com.test.conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author hp
 */
public class CiudadBean {
    //llamamos a los atributos
    private conexion variable;
    private Connection conexion;
    private PreparedStatement insertCiudad;
    //constructores
    public CiudadBean() throws SQLException
    {
        variable=new conexion();
        variable.iniciarConexion();
        conexion=variable.getConexion();
        System.out.println("INICIANDO LA CONEXION");
    }
    @PreDestroy
    public void cerrarConextion()
    {
        variable.cerrarConextion();
    }
    //metodos
    //INSERTAR UNA NUEVA CIUDAD
    public String registrarCiudad(HttpServletRequest request)
    {
        String mensaje="";
        if(request==null)
        {
            return "";
        }
        if(conexion!=null)
        {
            try{
                //definimos la consulta
                StringBuilder query=new StringBuilder();
                query.append(" insert into ciudad(nom_ciudad) values (?)");
                //enviando la consulta
                if(insertCiudad==null)
                {
                    insertCiudad=conexion.prepareStatement(query.toString());
                }
                //rescatando variables de jsp
                String nombre=request.getParameter("nom_ciudad");
                //pasando datos a los parametros de consulta
                insertCiudad.setString(2, nombre);
                int registro=insertCiudad.executeUpdate();
                if(registro==1)
                {
                    mensaje="REGISTRO REALIZADO CON EXITO";
                }
                else
                {
                    mensaje="ERROR AL INSERTAR EL REGISTRO";
                }
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
        return mensaje;
    }
    public String listarCiudadTable(){
        StringBuilder salidaTabla=new StringBuilder();
        StringBuilder query=new StringBuilder();
        query.append(" select * ");
        query.append(" from ciudad ");
        try {
            PreparedStatement pst=conexion.prepareStatement(query.toString());
            ResultSet resultado=pst.executeQuery();
            while(resultado.next()){
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(2));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                
                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion");
        }
        return salidaTabla.toString();
    }
    //metodo que enlista categorias en un select
    public String listarCiudadSelect()
    {
        StringBuilder salidaTabla=new StringBuilder();
        StringBuilder query=new StringBuilder();
        query.append(" select * ");
        query.append(" from ciudad ");
        try{
            PreparedStatement pst=conexion.prepareStatement(query.toString());
            ResultSet resultado=pst.executeQuery();
            while(resultado.next())
            {
                salidaTabla.append("<option value='"+resultado.getInt(1)+"'>");
                salidaTabla.append(resultado.getString(2));
                salidaTabla.append("</option>");
                
            }
            System.out.println("EXITO");
        }catch(Exception e){
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }
}
