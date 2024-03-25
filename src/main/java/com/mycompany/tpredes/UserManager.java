package com.mycompany.tpredes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static UserManager instancia;
    private final Map<String, Usuario> credenciales;
    
    public UserManager userManagerGetInstance(ArrayList<Usuario> usuarios){
        if(instancia==null){
            instancia = new UserManager(usuarios);
        }
        return instancia;
    }
    
    private UserManager(ArrayList<Usuario>usuarios) {
        credenciales = new HashMap<>();
        if(usuarios!=null){
            for(int i=0;i<usuarios.size();i++){
                credenciales.put(usuarios.get(i).getNombre(), usuarios.get(i));
            }
        }
    }
    
    public Usuario login(String nombre, String password) {
        String storedPassword=null;
        if(credenciales.get(nombre)!=null){
          storedPassword = credenciales.get(nombre).getPassword();  
        }
        
        if(credenciales.get(nombre)!=null && password.equals(storedPassword)){
            return credenciales.get(nombre);
        }
        
        return null;
    }
    
    public void addUser(Usuario user){
        credenciales.put(user.getNombre(), user);
    }
    
    public void deleteUser(Usuario user){
        credenciales.remove(user.getNombre(),user);
    }
    
}
