/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_final_centrodeconvenciones;

/**
 * Esta clase contiene los metodos y atributos del decorador de los equipamientos
 * @author Fabio Rodriguez y Alfredo Sobrados
 */
public abstract class EquipamientoDecorador extends Espacio{
    private int codigoNumerico;
    private String descripcion;
    private Espacio espacio;
    
    /**
     * Método constructor parametrizado
     * @param e espacio para decorar con los equipamientos
     * @param codigo código del equipamiento 
     * @param descripcion descripcion del equipamiento
     */
    public EquipamientoDecorador(Espacio e, int codigoNumerico){
        super("", "", 0.0, 0, false);
        espacio = e;
        
        this.codigoNumerico = codigoNumerico;
    }
 
    
    @Override
    public String getCodigo(){
        return espacio.getCodigo();
    }
    
    @Override
    public String getNombre(){
        return espacio.getNombre();
    }
    
    @Override
    public double getSuperficie(){
        return espacio.getSuperficie();
    }
    
    @Override
    public int getCapacidad(){
        return espacio.getCapacidad();
    }
    
    @Override
    public boolean getEstado(){
        return espacio.getEstado();
    }
    /**
     * Método de tipo double para calcular el ingreso de los equipamientos
     * @return devuelve el ingreso ya calculado
     */
    @Override
    public double calcularIngreso(){
        return espacio.calcularIngreso();
    }
}