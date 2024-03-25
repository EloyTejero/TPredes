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
    static String usuarioActual = null;
    
    public static void main(String[] args) {
        Usuario admin = new Usuario("admin", "admin", Rol.ADMIN);
        Usuario user = new Usuario("user", "user", Rol.USER);
        usuarios.add(admin);
        usuarios.add(user);
        
        Producto pr1 = new Producto("choclo", 0, 15);
        Producto pr2 = new Producto("atun", 1, 150);
        
        stock.ingresoStock(new Almacen(pr1, 0));
        stock.ingresoStock(new Almacen(pr2, 10));
        
        //UserManager u = new UserManager(usuarios);
        //System.out.println(u.login("a", "admin"));
        /*
        login();
        menu();*/
        
        System.out.println("asd".equals(null));
        if(admin.getRol()== Rol.ADMIN){
            System.out.println("mamaita");
        }
    }
    
    public static void login(){
        LoginService loginService = new LoginService(usuarios);
        
        do{
            Scanner in = new Scanner(System.in);
            System.out.print("Ingrese su usuario: ");
            String user = in.nextLine();
            System.out.print("Ingrese contraseña: ");
            String password = in.nextLine();

            if(loginService.login(user, password)){
                System.out.println("Ingreso Exitoso");
                usuarioActual = user;
            }else{
                System.out.println("Error en la contraseña o usuario");
                System.out.println();
            }
        }while(usuarioActual==null);
    }
    
    public static void menu(){
        int op;
        if(usuarioActual.equals("admin")){
            //menu admin
            System.out.println("menu admin");
        }else{
            do {            
                Scanner in = new Scanner(System.in);
                System.out.println("Opcciones");
                System.out.println("1. Listar productos");
                System.out.println("2. Ingreso producto");
                op = in.nextInt();
                switch (op) {
                    case 1 -> listarProductos();
                    case 2 -> ingresoProducto();
                    default -> throw new AssertionError();
                }
            } while (op>0 && op<3);
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
        
}
