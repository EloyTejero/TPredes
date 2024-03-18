/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.tpredes;

import java.util.ArrayList;

/**
 *
 * @author Eloy
 */
public class TPRedes {
    
    public static int login(){
        
        return 0;
    }
    
    public static void menu(){
        
    }

    public static void main(String[] args) {
        Usuario admin = new Usuario("admin", "admin", 1);
        Usuario user = new Usuario("user", "user", 0);
        
        Producto pr1 = new Producto("choclo", 0, 15);
        Producto pr2 = new Producto("atun", 1, 150);
        
        Stock stock = new Stock();
        
        stock.ingresoStock(new Almacen(pr1, 0));
        stock.ingresoStock(new Almacen(pr2, 10));
        
        String productos[] = stock.listarProductos();
        for(String i:productos){
            System.out.println(i);
        }
    }
}
