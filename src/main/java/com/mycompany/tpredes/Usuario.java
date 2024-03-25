/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpredes;

/**
 *
 * @author Eloy
 */
public class Usuario {
    static int idPublic;
    private int id;
    private String nombre;
    private String password;
    private Rol rol;

    public Usuario(String nombre, String password, Rol rol) {
        id = idPublic;
        idPublic++;
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", password=" + password + '}';
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public Rol getRol() {
        return rol;
    }
    
}
