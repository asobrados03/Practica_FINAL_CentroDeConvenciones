/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_final_centrodeconvenciones;

import java.io.Serializable;

/**
 * Esta clase contiene los metodos y atributos del coste
 * @author Fabio Rodriguez y Alfredo Sobrados
 */
public class Coste implements Serializable{
    private double precio;
    private int anio;
    /**
     * Método constructor parametrizado
     * @param precio el precio asignado a cada coste
     * @param anio el año asignado a cada coste
     */
    public Coste(double precio, int anio){
        this.precio = precio;
        this.anio = anio;
    }
    /**
     * Método getter del precio del coste
     * @return el precio del coste
     */
    public double getPrecio(){
        return this.precio;
    }
    /**
     * Método getter del año del coste
     * @return el año del coste
     */
    public int getAnio(){
        return this.anio;
    }
}
