/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.tpredes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Eloy
 */
public class TPRedes {
    
    static Stock stock = new Stock();
    
    public static int login(Usuario user){
        
        return 0;
    }
    
    public static void menu(){
        Scanner in = new Scanner(System.in);
        System.out.println("Opcciones");
        System.out.println("1. Listar productos");
        System.out.println("2. Ingreso producto");
        int op = in.nextInt();
        switch (op) {
            case 1 -> listarProductos();
            case 2 -> ingresoProducto();
            default -> throw new AssertionError();
        }
    }
    
    public static void listarProductos(){
        String productos[] = stock.listarProductos();
        for(String i:productos){
            System.out.println(i);
        }
    }
    
    public static void ingresoProducto(){
        Scanner in = new Scanner(System.in);
        System.out.println("1. Ingresar por id");
        System.out.println("2. Ingresar nuevo producto");
        int op = in.nextInt();
        if(op==1){
            System.out.print("Ingrese id: ");
            int id = in.nextInt();
            System.out.print("Ingrese cantidad: ");
            int cantidad = in.nextInt();
            int idAlmacen = stock.busquedaAlmacenIdPorProducto(id);
            stock.ingresoStock(idAlmacen, cantidad);
        } else{
            System.out.println("Ingrese producto");
            System.out.print("Nombre: ");
            String nombre = in.nextLine();
            System.out.print("ID: ");
            int id = in.nextInt();
            System.out.println("Precio: ");
            float precio = in.nextFloat();
            System.out.println("Cantidad del producto: ");
            int cantidad = in.nextInt();
            stock.ingresoStock(new Almacen(new Producto(nombre, id, precio), cantidad));
        }
    }

    public static void main(String[] args) {
        Usuario admin = new Usuario("admin", "admin", 1);
        Usuario user = new Usuario("user", "user", 0);
        
        Producto pr1 = new Producto("choclo", 0, 15);
        Producto pr2 = new Producto("atun", 1, 150);
        
        stock.ingresoStock(new Almacen(pr1, 0));
        stock.ingresoStock(new Almacen(pr2, 10));
        
        while(true){
            menu();
        }
    }
        
}
