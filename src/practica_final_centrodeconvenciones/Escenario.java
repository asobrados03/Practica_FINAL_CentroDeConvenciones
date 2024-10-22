/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_final_centrodeconvenciones;

/**
 * Esta clase contiene los metodos y atributos del equipamiento escenario
 * @author Fabio Rodriguez y Alfredo Sobrados
 */
public class Escenario extends EquipamientoDecorador{
    private double coste;
    
    /**
     * Método constructor parametrizado
     * @param e espacio para decorar con los escenario
     * @param ingreso ingreso percibido del escenario
     */
    public Escenario(Espacio e, double ingreso){
        super(e, 2);
        this.coste = ingreso;
    }
    /**
     * Método getter de la descripción del escenario 
     * @return la descripción del escenario
     */
 
    @Override
    public String getNombre(){
        return super.getNombre() + ", con Escenario";
    }
    /**
     * Método de tipo double para calcular el ingreso del escenario
     * @return el ingreso ya calculado
     */
    @Override
    public double calcularIngreso(){
        return this.coste + super.calcularIngreso();
    }
}
