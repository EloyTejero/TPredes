/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpredes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ET36
 */
public class LoginService {
    
    private Map<String, String> credenciales;
    
    public LoginService(ArrayList<Usuario>usuarios) {
        credenciales = new HashMap<>();
        for(int i=0;i<usuarios.size();i++){
            credenciales.put(usuarios.get(i).getNombre(), usuarios.get(i).getPassword());
        }
    }

    public boolean login(String nombre, String password) {
        String storedPassword = credenciales.get(nombre);
        return credenciales.get(nombre)!=null && storedPassword.equals(password);
    }
}
