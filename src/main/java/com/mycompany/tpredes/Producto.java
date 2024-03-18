/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpredes;

/**
 *
 * @author Eloy
 */
public class Producto {
    private String nombre;
    private int id;
    private float precio;

    public Producto(String nombre, int id, float precio) {
        this.nombre = nombre;
        this.id = id;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", id=" + id + ", precio=" + precio + '}';
    }

    public int getId() {
        return id;
    }
    
}
