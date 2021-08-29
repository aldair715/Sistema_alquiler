/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.clases;

import java.io.Serializable;

/**
 *
 * @author hp
 */
public class Persona implements Serializable{
    //atributos
    private int id_persona;
    private int ci;
    private int telefono;
    private String ciudad;
    private String nombre;
    private int ciudad_id_ciudad;
    //constructor
    public Persona()
    {
        
    }
    //getters and setter

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCiudad_id_ciudad() {
        return ciudad_id_ciudad;
    }

    public void setCiudad_id_ciudad(int ciudad_id_ciudad) {
        this.ciudad_id_ciudad = ciudad_id_ciudad;
    }
    
}
