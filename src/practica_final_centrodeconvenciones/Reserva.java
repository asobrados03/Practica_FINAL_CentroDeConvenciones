 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_final_centrodeconvenciones;

import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;

/**
 * Esta clase contiene los metodos y atributos de la reserva
 * @author Fabio Rodriguez y Alfredo Sobrados
 */
public class Reserva implements Serializable{
    private Espacio espacio;
    private LocalDate fecha;
    private String cliente;
    private long telefono;
    private String id;
    private ArrayList<Consumicion> listaConsumiciones;
    /**
     * Método constructor parametrizado
     * @param cliente
     * @param telefono
     * @param id 
     */
    public Reserva(String cliente, long telefono, String id, Espacio espacio, LocalDate fecha){
        this.cliente = cliente;
        this.telefono = telefono;
        this.id = id;
        this.espacio = espacio;
        this.fecha = fecha;
        listaConsumiciones = new ArrayList<Consumicion>();
    }
    
    /**
     * Método setter de la fecha de la reserva
     * @param fecha fecha que queremos almacenar en la reserva
     */
    public void setFecha(LocalDate fecha){
        this.fecha = fecha;
    }
    /**
     * Método getter del espacio decorado de la reserva
     * @return espacio decorado almacenado en la reserva
     */
    public Espacio getEspacioDecorado(){
        return this.espacio;
    }
    /**
     * Método getter de la fecha de la reserva
     * @return la fecha de la reserva
     */
    public LocalDate getFecha(){
        return this.fecha;
    }
    /**
     * Método getter del cliente de la reserva
     * @return el cliente de la reserva
     */
    public String getCliente(){
        return this.cliente;
    }
    /**
     * Método getter del telefóno de la reserva
     * @return el telefóno de la reserv
     */
    public long getTelefono(){
        return this.telefono;
    }
    /**
     * Método getter del identificador de la reserva
     * @return el identificador de la reserva
     */
    public String getId(){
        return this.id;
    }
    /**
     * Método que añade una consumición al sistema
     * @param consumicion consumición que se quiere añadir al sistema 
     */
    public void aniadirConsumicion(Consumicion consumicion){
        listaConsumiciones.add(consumicion);
        System.out.println("\tPerfecto! Has agregado la consumición "+consumicion.getId()+" a la lista de consumiciones de la reserva "+getId()+".");
    }
    /**
     * Método privado de tipo Consumicion que busca una consummición en la lista de consumiciones
     * @param id idenficador de la consimición que queremos buscar
     * @return consumición encontrada en la lista de consumiciones o nada
     */
    private Consumicion buscarConsumicion(String id){
        for(Consumicion con:listaConsumiciones)
            if(con.getId().equals(id)) return con;
        return null;
    }
    /**
     * Método que elimina una consumicion del sistema
     * @param id idenrtificador de la consumición que se quiere eliminar
     */
    public void eliminarConsumicion(String id){
        Consumicion consumicion = buscarConsumicion(id);
        
        if(listaConsumiciones.contains(consumicion)){
            listaConsumiciones.remove(consumicion);
            System.out.println("Has eliminado la consumición "+consumicion.getId()+" de la lista de consumiciones de la reserva "+getId()+".");
        }
        else{
            System.out.println("ERROR! No exisiste en el sistema la consumicion que quieres eliminar.");
        }
    }
    
    public void mostrarConsumiciones(){
        for(Consumicion c: listaConsumiciones){
            System.out.println("Id Consumición: "+c.getId()+" - Producto: "+c.getProducto().getNombre() + " - Precio Total: "+c.calcularCosteConsumicion());
        }
    }
    
    public ArrayList<Consumicion> getListaConsumiciones(){
        return listaConsumiciones;
    }
}
