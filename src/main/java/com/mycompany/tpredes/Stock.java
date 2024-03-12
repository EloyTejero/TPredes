/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpredes;

import java.util.ArrayList;

/**
 *
 * @author Eloy
 */
public class Stock {
    private ArrayList<Almacen> almacenes = new ArrayList<>();
    
    public Stock(){}
    
    public Stock(ArrayList<Almacen> almacenes){
        this.almacenes = almacenes;
    }
    
    public String[] listarProductos(){
        
        return new String[0];
    }
    
    public void ingresoStock(){
        
    }
    
    public void modificarStock(){
        
    }
    
    public void eliminarStock(){
        
    }
}
