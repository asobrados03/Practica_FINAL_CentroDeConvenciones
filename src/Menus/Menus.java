/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import practica_final_centrodeconvenciones.Centro_Convenciones;

/**
 *
 * @author Usuario
 */
public abstract class Menus {
    private Centro_Convenciones centConv;
    
    public Menus(Centro_Convenciones centConv){
        this.centConv = centConv;
    }
    
    public Centro_Convenciones getCentConv(){
        return this.centConv; 
    }
    
    public void ejecutar(){
        String respuesta="";
        
        do{
            respuesta = ejecutarOpciones();
        }while(respuesta.equals("s"));
    }
    
    public abstract String ejecutarOpciones();
}
