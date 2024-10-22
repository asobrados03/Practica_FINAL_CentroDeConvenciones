/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_final_centrodeconvenciones;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Esta clase contiene los metodos y atributos del producto
 * @author Fabio Rodriguez y Alfredo Sobrados
 */
public class Producto extends Carta implements Serializable{
    private ArrayList<Coste> listaCostes;
    private boolean estado; 
    /**
     * Método constructor parametrizado
     * @param cod
     * @param descrpc
     * @param nom 
     */
    public Producto(String nom, String id, String descripcion, boolean estado){
        super(nom, id, descripcion);
        this.estado = estado;
        listaCostes = new ArrayList<Coste>();
    }
    
    @Override
    public void mostrarInfo(){
        if(getId().equals("")){
            System.out.println("No existe el producto buscado.");
        }
        else
        {
            Coste coste = buscarCoste(LocalDate.now().getYear());
            System.out.println("\t-----------------------------------------");
            System.out.println("\t| Nombre: "+getNombre()+" - Código: "+getId());
            System.out.println("\t| Descripción: "+getDescripcion());
            System.out.println("\t| Coste Actual: "+coste.getPrecio()+" euros");
            System.out.println("\t| Estado: "+getEstadoImprimir(getEstado()));
            System.out.println("\t-----------------------------------------\n");
        }
    }
    
    
    public void agregar(Carta e){
        System.out.println("no se le pueden agregar productos");
    }
    
    
    /**
     * 
     * @param estado 
     */
    public void setEstado(boolean estado){
        this.estado = estado;
    }
    /**
     * 
     * @param listaCostes 
     */
    public void setListaCostes(Coste c){
        this.listaCostes.add(c);
    }
  
    /**
     * Método getter del estado del producto
     * @return el estado del producto
     */
    public boolean getEstado(){
        return this.estado;
    }
    
    public String getEstadoImprimir(boolean estado){
        if(estado == true){
            return "Disponible";
        }else{
            return "No Disponible";
        }
    }
    /**
     * Método getter de los costes del producto
     * @return los costes del producto
     */
    public ArrayList<Coste> getCostes(){
        return this.listaCostes;
    }
    /**
     * 
     * @param nombre
     * @return 
     */
    @Override
    public Carta recuperarCarta(String id){
        return this;
    }
    
    public ArrayList<Carta> getLista(){
        return null;
    }
    
    public Coste buscarCoste(int anio){
        for (Coste c: listaCostes){
            if(c.getAnio() == anio){
                return c;
            } 
        }
        return null;
    }
    
    
}
