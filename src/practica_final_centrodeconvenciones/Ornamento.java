/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_final_centrodeconvenciones;

/**
 * Esta clase contiene los metodos y atributos del equipamiento ornamento
 * @author Fabio Rodriguez y Alfredo Sobrados
 */
public class Ornamento extends EquipamientoDecorador{
    private double ingreso;
    
    /**
     * Método constructor parametrizado
     * @param e espacio para decorar con la ornamentacíon
     * @param ingreso ingreso percibido de la ornamentacíon
     */
    public Ornamento(Espacio e, double ingreso){
        super(e, 1);
        this.ingreso = ingreso;
    }
    /**
     * Método getter de la descripción de la ornamentación
     * @return la descripción del ornamento 
     */
    @Override
    public String getNombre(){
        return super.getNombre() + ", con Ornamento";
    }
    /**
     * Método de tipo double para calcular el ingreso de la ornamentación
     * @return el ingreso ya calculado
     */
    @Override
    public double calcularIngreso(){
        return ingreso + super.calcularIngreso();
    }
    
    
}
