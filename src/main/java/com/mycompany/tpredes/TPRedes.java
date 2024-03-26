package com.mycompany.tpredes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Eloy
 */
public class TPRedes {
    
    static Stock stock = new Stock();
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static Usuario usuarioActual = null;
    
    public static void main(String[] args) {
        //lo siguiente para un metodo de booteo
        UserManager userManager = UserManager.userManagerGetInstance();
        
        Usuario admin = new Usuario("admin", "admin", Rol.ADMIN);
        Usuario user = new Usuario("user", "user", Rol.USER);
        usuarios.add(admin);
        usuarios.add(user);
        
        userManager.cargaUsers(usuarios);
        
        Producto pr1 = new Producto("choclo", 0, 15);
        Producto pr2 = new Producto("atun", 1, 150);
        
        stock.ingresoStock(new Almacen(pr1, 0));
        stock.ingresoStock(new Almacen(pr2, 10));
        
        //UserManager u = new UserManager(usuarios);
        //System.out.println(u.login("a", "admin"));
        
        login();
        menu();
        /*menu1();
        
        System.out.println("asd".equals(null));
        if(admin.getRol()== Rol.ADMIN){
            System.out.println("mamaita");
        }*/
    }
    
    public static void login(){
        UserManager userManager = UserManager.userManagerGetInstance();
        
        do{
            Scanner in = new Scanner(System.in);
            System.out.print("Ingrese su usuario: ");
            String user = in.nextLine();
            System.out.print("Ingrese contraseña: ");
            String password = in.nextLine();
            
            usuarioActual = userManager.login(user, password);
            if(usuarioActual==null){
                System.out.println("Error en la contraseña o usuario");
                System.out.println();
            }
        }while(usuarioActual==null);
        
        System.out.println("Ingreso Exitoso");
    }
    
    public static void menu(){
        
        UserManager userManager = UserManager.userManagerGetInstance();
        if(userManager.isAdmin(usuarioActual)){
            opcionesAdmin();
        } else{
            opcionesUser();
        }
    }
    
    public static void opcionesUser(){
        MenuManager menu = new MenuManager();
        Scanner in = new Scanner(System.in);
        int op;
        do {
            menu.menuUser();
            op = in.nextInt();
            in.nextLine();
            switch (op) {
                case 1 -> listarProductos();
                case 2 -> venta();
                default -> throw new AssertionError();
            }
        } while (op>0 && op<3);
        
    }
    
    public static void opcionesAdmin(){
        MenuManager menu = new MenuManager();
        Scanner in = new Scanner(System.in);
        int op;
        do {
            menu.menuAdmin();
            op = in.nextInt();
            in.nextLine();
            switch (op) {
                case 1 -> listarProductos();
                case 2 -> ingresoProducto();
                default -> throw new AssertionError();
            }
        } while (true);
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
        in.nextLine();
        if(op==1){
            System.out.print("Ingrese id: ");
            int id = in.nextInt();
            in.nextLine();
            System.out.print("Ingrese cantidad: ");
            int cantidad = in.nextInt();
            in.nextLine();
            int idAlmacen = stock.busquedaAlmacenIdPorProducto(id);
            stock.ingresoStock(idAlmacen, cantidad);
        } else if(op==2){
            System.out.println("Ingrese producto");
            System.out.print("Nombre: ");
            String nombre = in.nextLine();
            System.out.print("ID: ");
            int id = in.nextInt();
            in.nextLine();
            System.out.println("Precio: ");
            float precio = in.nextFloat();
            System.out.println("Cantidad del producto: ");
            int cantidad = in.nextInt();
            stock.ingresoStock(new Almacen(new Producto(nombre, id, precio), cantidad));
        }
    }
    
    public static void venta(){
        int idProducto, cantidadCompra;
        Scanner in = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto: ");
        idProducto = in.nextInt();
        in.nextLine();
        System.out.print("Ingrese la cantidad de producto: ");
        cantidadCompra = in.nextInt();
        
        int idAlmacen = stock.busquedaAlmacenIdPorProducto(idProducto);
        int cantidadProductoEnAlmacen = stock.getAlmacenenPorId(idAlmacen).getCantidad();
        if(cantidadProductoEnAlmacen>=cantidadCompra){
            stock.eliminarStock(idAlmacen, cantidadCompra);
        }else{
            System.out.println("Cantidad de producto insuficiente");
        }
        
    }
        
}
