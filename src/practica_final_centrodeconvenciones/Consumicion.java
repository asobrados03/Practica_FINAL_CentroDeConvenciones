/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_final_centrodeconvenciones;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Esta clase contiene los metodos y atributos de la consumición
     @author Fabio Rodriguez y Alfredo Sobrados
 */
public class Consumicion implements Serializable {
    private Producto producto;
    private int cantidad;
    private String id;
    /**
     * Método constructor parametrizado
     * @param producto
     * @param cantidad
     * @param id 
     */
    public Consumicion(Producto producto, int cantidad, String id){
        this.producto = producto;
        this.cantidad = cantidad;
        this.id = id;
    }
    /**
     * Método getter del producto de la consumición
     * @return el producto de la consumición 
     */
    public Producto getProducto(){
        return this.producto;
    }
    /**
     * Método getter de la cantidad de la consumición
     * @return la cantidad de la consumición
     */
    public int getCantidad(){
        return this.cantidad;
    }
    /**
     * Método getter del identificador de la consumición
     * @return el identificador de la consumición
     */
    public String getId(){
        return this.id;
    }
    
    public double calcularCosteConsumicion(){
        Coste c = producto.buscarCoste(LocalDate.now().getYear());
        double precio = c.getPrecio();
        double calculo = cantidad*precio;
        
        return calculo;
    }
}
