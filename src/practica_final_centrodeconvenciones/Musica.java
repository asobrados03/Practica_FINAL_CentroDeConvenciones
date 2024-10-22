/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_final_centrodeconvenciones;

/**
 * Esta clase contiene los metodos y atributos del equipamiento música
 * @author Fabio Rodriguez y Alfredo Sobrados
 */
public class Musica extends EquipamientoDecorador{
    private double ingreso;
    
    /**
     * Método constructor parametrizado
     * @param e espacio para decorar con la música
     * @param ingreso ingreso percibido de la música
     */
    public Musica(Espacio e, double ingreso){
        super(e, 3);
        this.ingreso = ingreso;
    }
    /**
     * Método getter de la descripción de la música
     * @return la descripción de la música
     */
    
    @Override
    public String getNombre(){
        return super.getNombre() + ", con Música";
        
    }
    /**
     * Método de tipo double para calcular el ingreso de la música
     * @return el ingreso ya calculado
     */
    @Override
    public double calcularIngreso(){
        return ingreso + super.calcularIngreso();
    }
    
}
