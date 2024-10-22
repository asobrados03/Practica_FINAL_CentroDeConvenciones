/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_final_centrodeconvenciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.*;

/**
 * Esta clase contiene los metodos y atributos del espacio
 * @author Fabio Rodriguez y Alfredo Sobrados
 */
public class Espacio implements Serializable{
    private String codigo;
    private String nombre;
    private double superficie;
    private int capacidad;
    private ArrayList<Coste> listaCostes;
    private boolean estado;
    /**
     * Método constructor parametrizado
     * @param cod código del espacio
     * @param nom nombre del espacio
     * @param suprf superficie del espacio
     * @param cap capacidad del espacio
     * @param estado estado del espacio
     */
    public Espacio(String cod, String nom, double suprf, int cap, boolean estado){
        this.codigo = cod;
        this.nombre = nom;
        this.superficie = suprf;
        this.capacidad = cap;
        listaCostes = new ArrayList<Coste>();
        this.estado = estado;
    }
 
    /**
     * Método getter del código del espacio
     * @return el código del espacio
     */
    public String getCodigo(){
        return this.codigo;
    }
    /**
     * Método getter del nombre del espacio
     * @return el nombre del espacio
     */
    public String getNombre(){
        return this.nombre;
    }
    /**
     * Método getter de la superficie del espacio
     * @return la superficie del espacio
     */
    public double getSuperficie(){
        return this.superficie;
    }
    /**
     * Método getter de la capaciadad del espacio
     * @return la capcidad del espacio
     */
    public int getCapacidad(){
        return this.capacidad;
    }
    /**
     * Método getter del estado del espacio
     * @return el estado
     */
    public boolean getEstado(){
        return this.estado;
    }
    /**
     * Método getter de la lista de los costes del espacio
     * @return lista de los costes
     */
    public ArrayList<Coste> getListaCostes(){
        return this.listaCostes;
    }
    /**
     * Método setter de la disponibilidad del espacio
     * @param estado true o false depende del valor asignado
     */
    public void setDisponibilidad(boolean estado){
        this.estado = estado;
    }
    /**
     * Método setter de la lista de costes
     * @param c el coste que va a ser almacenado en la lista de costes
     */
    public void setListaCostes(Coste c){
        listaCostes.add(c);
    }
    
    public Coste buscarCoste(int anio){
        for (Coste c: listaCostes){
            if(c.getAnio() == anio){
                return c;
            } 
        }
        return null;
    }
    /**
     * Método de tipo double para calcular el ingreso del espacio
     * @return cero
     */
    public double calcularIngreso(){
        Coste c = buscarCoste(LocalDate.now().getYear());
        return c.getPrecio();
    }
}
