/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_final_centrodeconvenciones;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public abstract class Carta implements Serializable{
    private String nombre;
    private String id;
    private String descripcion;
    
    public Carta(String nombre, String id, String descripcion){
        this.nombre = nombre;
        this.id = id;
        this.descripcion = descripcion;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getId(){
        return id;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public abstract void mostrarInfo();
    public abstract void agregar(Carta c);
    public abstract Carta recuperarCarta(String nombre);
    public abstract void setListaCostes(Coste c);
    public abstract ArrayList<Carta> getLista();
    public abstract boolean getEstado();
    public abstract void setEstado(boolean estado);
    
}
