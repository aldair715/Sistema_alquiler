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
public class PersonaBean {
   
    //llamamos a los atributos
    private conexion variable;
    private Connection conexion;
    private PreparedStatement insertPersona;
    //constructores
    public PersonaBean() throws SQLException
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
    public String registrarPersona(HttpServletRequest request)
    {
        String mensaje="";
        if(request==null)
        {
            return "";
        }
        if(conexion!=null)
        {
            try{
                //rescantando variables del jps
                String nombre=request.getParameter("nombre");
                String procedencia=request.getParameter("lugar");
                String cedula=request.getParameter("cedula").toString();
                String telefono=request.getParameter("telefono").toString();
                String ciudad=request.getParameter("ciudad");
                String titulo=request.getParameter("nom_domicilio");
                Double precio=Double.parseDouble(request.getParameter("precio"));
                String direccion=request.getParameter("direccion");
                String descripcion=request.getParameter("descripcion");
                int ciudad_dom=Integer.parseInt(request.getParameter("ciudad_domicilio"));
                //definimos la consulta
                StringBuilder query=new StringBuilder();
                query.append(" INSERT INTO persona (ci, telefono, ciudad, nombre, ciudad_id_ciudad)" );
                query.append(" VALUES ("+cedula+","+telefono+",'"+procedencia+"','"+nombre+"',"+ciudad+")" );
                insertPersona=conexion.prepareStatement(query.toString());
                //enviando la consultas
                insertPersona=conexion.prepareStatement(query.toString());
                int registro1=insertPersona.executeUpdate();
                int registro=0;
                //Sacamos la consulta del codigo dado a la persona
                if(registro1==1)
                {
                    int codigo_persona=listarUltimoIdPersona();
                    //insertmos en roles
                    
                    if(codigo_persona!=0)
                    {
                        int rol=registrarRolDePersona(codigo_persona);
                        if(rol==1){
                            registro=1;
                            int dom=registrarDomicilioDePersona(codigo_persona,titulo,precio,direccion,descripcion,ciudad_dom);
                            if(dom!=1)
                            {
                                mensaje+="Problemas al almacenar domicilio";
                            }
                        }
                        else
                        {
                            mensaje+="Problemas al almacenar aqui rol";
                        }
                    }
                    
                }
                
                if(registro==1)
                {
                    mensaje+="REGISTRO REALIZADO CON EXITO";
                }
                else
                {
                    mensaje+="ERROR AL INSERTAR EL REGISTRO";
                }
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
        return mensaje;
    }
    public int registrarDomicilioDePersona(int codigo_persona,String titulo,double precio,String direccion,String descripcion, int ciudad_dom)
    {
        int mensaje=0;
        if(conexion!=null)
        {
            try{

                //insertmos en roles
                StringBuilder query=new StringBuilder();
                query.append(" INSERT INTO domicilio (nombre_dom,precio,direccion,descripcion,estado,ciudad_id_ciudad,roles_has_persona_roles_idroles1,roles_has_persona_persona_id_persona1)" );
                query.append(" VALUES ('"+titulo+"',"+precio+",'"+direccion+"','"+descripcion+"',1,"+ciudad_dom+",2,'"+codigo_persona+"')" );
                insertPersona=conexion.prepareStatement(query.toString());
                int registro=insertPersona.executeUpdate();
                //insertamos en domicilio
                if(registro==1)
                {
                    mensaje=1;
                }
                else
                {
                    mensaje=0;
                }
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
        return mensaje;
    }
    public int registrarRolDePersona(int codigo_persona)
    {
        int mensaje=0;
        if(conexion!=null)
        {
            try{

                //insertmos en roles
                StringBuilder query=new StringBuilder();
                query.append(" INSERT INTO roles_has_persona (roles_idroles1,persona_id_persona)" );
                query.append(" VALUES (2,"+codigo_persona+") " );
                insertPersona=conexion.prepareStatement(query.toString());
                int registro=insertPersona.executeUpdate();
                //insertamos en domicilio
                if(registro==1)
                {
                    mensaje=1;
                }
                else
                {
                    mensaje=0;
                }
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
        return mensaje;
    }
    public int listarUltimoIdPersona()
    {
        StringBuilder salidaTabla=new StringBuilder();
        StringBuilder queryBusqueda=new StringBuilder();
        int codigo_persona=0;
        queryBusqueda.append(" SELECT id_persona " +
            " FROM persona" +
            " ORDER by id_persona DESC " +
            " LIMIT 1 ");
        try{
                PreparedStatement pst=conexion.prepareStatement(queryBusqueda.toString());
                ResultSet resultado=pst.executeQuery();
                
                int i=1;
                while(resultado.next()){
                   codigo_persona=resultado.getInt(1);
                }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return codigo_persona;
    }
    public String listarPersonaTable(){
        StringBuilder salidaTabla=new StringBuilder();
        StringBuilder query=new StringBuilder();
        query.append(" SELECT " +
" per.nombre," +
" per.ci," +
" per.telefono," +
" per.nombre," +
" ci.nom_ciudad," +
" count(rol.persona_id_persona) as Numero_PROPIEDADES,  " +
" per.id_persona FROM "+
" ((persona as per inner join ciudad as ci on ci.id_ciudad=per.ciudad_id_ciudad) inner join " +
" roles_has_persona as rol on rol.persona_id_persona=per.id_persona) inner join domicilio as dom " +
" on rol.persona_id_persona=dom.roles_has_persona_persona_id_persona1  where rol.roles_idroles1='2' group by per.id_persona ");
        try {
            PreparedStatement pst=conexion.prepareStatement(query.toString());
            ResultSet resultado=pst.executeQuery();
            int i=1;
            while(resultado.next()){
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>"+i+"</td>");
                salidaTabla.append("<td>"+resultado.getString(1)+"</td>");
                salidaTabla.append("<td>"+resultado.getString(2)+"</td>");
                salidaTabla.append("<td>"+resultado.getString(3)+"</td>");
                salidaTabla.append("<td>"+resultado.getString(4)+"</td>");
                salidaTabla.append("<td>"+resultado.getString(5)+"</td>");
                salidaTabla.append("<td>"+resultado.getInt(6)+"</td>");
                salidaTabla.append("<td class='btn btn-primary' data-id='"+resultado.getInt(7)+"'>MODIFICAR</td>");
                salidaTabla.append("<td class='btn btn-danger' data-id='"+resultado.getInt(7)+"'>ELIMINAR</td>");
                salidaTabla.append("</tr>");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion");
        }
        return salidaTabla.toString();
    }
    //metodo que enlista categorias en un select
    public String listarPersonaSelect()
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
