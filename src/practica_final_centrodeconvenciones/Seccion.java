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
public class Seccion extends Carta implements Serializable{
    private ArrayList<Carta> listaProductos;
    
    public Seccion(String nombre, String id, String descripcion){
        super(nombre, id, descripcion);
        listaProductos = new ArrayList<Carta>();
    }
    
    
    public void mostrarInfo(){
        System.out.println("\tNombre de la Sección: "+getNombre()+" - Código: "+getId());
        System.out.println("\tDescripción de la Sección: "+getDescripcion()+"\n");
  
    }
    
    public void agregar(Carta c){
        listaProductos.add(c);
        System.out.println("Has agregado "+c.getNombre()+" a la seccion "+getNombre());
    }
    
    
    
    public Carta recuperarCarta(String id){
        if (this.getId().equals(id)) return this;
        else{
            for (Carta c:listaProductos)
                if (c.recuperarCarta(id)!=null)
                    if (c.recuperarCarta(id).getId().equals(id))
                        return c.recuperarCarta(id);
            return null;
        }
    }
    
    public ArrayList<Carta> getLista(){
        return listaProductos;
    }
    
    public void setListaCostes(Coste c){
        System.out.println("No tiene lista costes.");
    }
    
    public boolean getEstado(){
        return true;
    }
    
    public void setEstado(boolean estado){
        
    }
    
}
