/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpredes;

/**
 *
 * @author Eloy
 */
public class Almacen {
    Producto producto;
    int cantidad;

    public Almacen(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Almacen{" + "producto=" + producto.toString() + ", cantidad=" + cantidad + '}';
    }
    
    
}
