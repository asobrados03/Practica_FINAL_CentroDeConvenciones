/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import java.util.ArrayList;
import practica_final_centrodeconvenciones.Centro_Convenciones;
import Entrada.MyInput;

/**
 *
 * @author Usuario
 */
public class MenuPrincipal extends Menus{
    private ArrayList<Menus> menus = new ArrayList<Menus>();
            
    public MenuPrincipal(Centro_Convenciones centConv){
        super(null);
        menus.add(new GestionEspacios(centConv));
        menus.add(new GestionReservas(centConv));
        menus.add(new GestionCartaProductos(centConv));
        menus.add(new GestionEconomica(centConv));
    }
    
    public String ejecutarOpciones(){
        System.out.println("");
            System.out.println("Menú Prncipal.");
            System.out.println("seleccione una opción:");
            System.out.println("0. Salir del programa");
            System.out.println("1. Gestion Espacios");
            System.out.println("2. Gestión Reservas");
            System.out.println("3. Gestión Carta de Productos");
            System.out.println("4. Gestión Economica");
            
        String s=MyInput.readString();
        int i=0;
        try{  
               i= Integer.parseInt(s);
        }catch(NumberFormatException ex){
                  System.out.println("La entrada no tiene formato de número. Inténtelo de nuevo");
                  return "s";
               } 
        if((i>0)&&(i<=menus.size())){
                menus.get(i-1).ejecutar();
                return "s";}
        else if ((i<0)||(i>menus.size())){
                System.out.println("opción no válida. Inténtelo de nuevo");
                return "s";}
        else                  
                return "n";
                
    }
    
    
}
