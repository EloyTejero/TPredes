package com.mycompany.tpredes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static UserManager instancia;
    private final Map<String, Usuario> credenciales;
    
    public UserManager userManagerGetInstance(){
        if(instancia==null){
            instancia = new UserManager();
        }
        return instancia;
    }
    
    private UserManager() {
        credenciales = new HashMap<>();
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
        if(credenciales.get(user.getNombre()))
        credenciales.put(user.getNombre(), user);
    }
    
    public void deleteUser(Usuario user){
        credenciales.remove(user.getNombre(),user);
    }
    
    public void cargaUsers(ArrayList<Usuario>usuarios){
        for(int i=0;i<usuarios.size();i++){
            addUser(usuarios.get(i));
        }
    }
}
