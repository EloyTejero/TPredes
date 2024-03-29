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
        String listaProductos [] = new String[almacenes.size()];
        
        for(int i=0;i<almacenes.size();i++){
            listaProductos[i] = almacenes.get(i).toString();
        }
        return listaProductos;
    }
    
    public void ingresoStock(Almacen almacen){
        almacenes.add(almacen);
    }
    
    public void ingresoStock(int index, int ingreso){
        almacenes.get(index).sumarProducto(ingreso);
    }
    
    public void modificarStock(int index, int valor){
        almacenes.get(index).setCantidad(valor);
    }
    
    public void eliminarStock(int id, int resta){
        almacenes.get(id).restarProducto(resta);
    }
    
    public int busquedaAlmacenIdPorProducto(int id){
        for(int i=0;i<almacenes.size();i++){
            int productoId = almacenes.get(i).getProducto().getId();
            if(productoId == id){
                return i;
            }
        }
        return -1;
    }

    public Almacen getAlmacenenPorId(int idAlmacen) {
        return almacenes.get(idAlmacen);
    }
    
    
}
