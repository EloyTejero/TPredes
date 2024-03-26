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
        do {            
            login();
            menu();
        } while (usuarioActual!=null);
        
        /*menu1();
        
        System.out.println("asd".equals(null));
        if(admin.getRol()== Rol.ADMIN){
            System.out.println("mamaita");
        }*/
    }
    
    public static void login(){
        UserManager userManager = UserManager.userManagerGetInstance();
        Scanner in = new Scanner(System.in);
        
        do{
            System.out.println("1. Ingreso");
            System.out.println("2. Salir");
            int op = in.nextInt();
            in.nextLine();
            if(op==2){
                usuarioActual=null;
                break;
            }
            
            System.out.print("Ingrese su usuario: ");
            String user = in.nextLine();
            System.out.print("Ingrese contraseña: ");
            String password = in.nextLine();
            
            usuarioActual = userManager.login(user, password);
            if(usuarioActual==null){
                System.out.println("Error en la contraseña o usuario");
                System.out.println();
                continue;
            }
            System.out.println("Ingreso Exitoso");
            System.out.println("");
        }while(usuarioActual==null);
        
    }
    
    public static void menu(){
        if(usuarioActual==null){
            return;
        }
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
            menu.showMenuUser();
            op = in.nextInt();
            in.nextLine();
            switch (op) {
                case 1 -> listarProductos();
                case 2 -> venta();
                case 3 -> System.out.println("Cerrando");
                default -> System.out.println("Opcion invalida");
            }
        } while (op!=3);   
    }
    
    public static void opcionesAdmin(){
        MenuManager menu = new MenuManager();
        Scanner in = new Scanner(System.in);
        int op;
        do {
            menu.showMenuAdmin();
            op = in.nextInt();
            in.nextLine();
            switch (op) {
                case 1 -> listarProductos();
                case 2 -> venta();
                case 3 -> ingresoProducto();
                case 4 -> modificarStock();
                case 5 -> eliminarStock();
                case 6 -> anadirUser();
                case 7 -> eliminarUser();
                case 8 -> verUsuarios();
                case 9 -> System.out.println("Cerrando");
                default -> System.out.println("Opcion invalida");
            }
        } while (op!=9);
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
        in.nextLine();

        
        int idAlmacen = stock.busquedaAlmacenIdPorProducto(idProducto);
        int cantidadProductoEnAlmacen = stock.getAlmacenenPorId(idAlmacen).getCantidad();
        if(cantidadProductoEnAlmacen>=cantidadCompra){
            stock.eliminarStock(idAlmacen, cantidadCompra);
        }else{
            System.out.println("Cantidad de producto insuficiente");
        }
        
    }

    private static void modificarStock() {
        int idProducto, nuevaCantidad;
        Scanner in = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto: ");
        idProducto = in.nextInt();
        in.nextLine();
        System.out.print("Ingrese la nueva cantidad de producto: ");
        nuevaCantidad = in.nextInt();
        in.nextLine();
        
        int idAlmacen = stock.busquedaAlmacenIdPorProducto(idProducto);
        stock.modificarStock(idAlmacen, nuevaCantidad);
    }

    private static void eliminarStock() {
        int idProducto, cantidad;
        Scanner in = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto: ");
        idProducto = in.nextInt();
        in.nextLine();
        System.out.print("Ingrese la cantidad de producto a eliminar: ");
        cantidad = in.nextInt();
        in.nextLine();
        
        int idAlmacen = stock.busquedaAlmacenIdPorProducto(idProducto);
        int cantidadProductoEnAlmacen = stock.getAlmacenenPorId(idAlmacen).getCantidad();
        if(cantidadProductoEnAlmacen>=cantidad){
            stock.eliminarStock(idAlmacen, cantidad);
        }else{
            System.out.println("Cantidad de producto insuficiente");
        }
    }

    private static void anadirUser() {
        Scanner in = new Scanner(System.in);
        System.out.print("Ingrese nombre usuario: ");
        String nombre = in.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String password = in.nextLine();
        System.out.println("Ingrese rol: ");
        System.out.println("1. admin");
        System.out.println("2. user");
        int op = in.nextInt();
        in.nextLine();
        Rol rol;
        if(op==1){
            rol = Rol.ADMIN;
        } else{
            rol = Rol.USER;
        }
        UserManager.userManagerGetInstance().addUser(new Usuario(nombre, password, rol));
    }

    private static void eliminarUser() {
        UserManager userManager = UserManager.userManagerGetInstance();
        userManager.showUsers();
        Scanner in = new Scanner(System.in);
        System.out.print("Ingrese el nombre del user a eliminar: ");
        String user = in.nextLine();
        Usuario usuario = userManager.getUser(user);
        userManager.deleteUser(usuario);
    }

    private static void verUsuarios() {
        UserManager userManager = UserManager.userManagerGetInstance();
        userManager.showUsers();
    }

}
