/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import Entrada.MyInput;
import java.time.LocalDate;
import practica_final_centrodeconvenciones.Centro_Convenciones;
import practica_final_centrodeconvenciones.Espacio;

/**
 *
 * @author Usuario
 */

public class GestionEconomica extends Menus{
    
    
    public GestionEconomica(Centro_Convenciones centConv){
        super(centConv);
    }
    
    public void mostrarIngresosEspacio(){
        LocalDate f1, f2;
        
        System.out.println("\t-------------------------------------------------------");
        System.out.println("\t        MOSTRAR INGRESOS TOTALES DE UN ESPACIO         ");
        System.out.println("\t-------------------------------------------------------\n");
        
        System.out.println("\tINFORMACIÓN PREVIA: A continuación se muestran los Espacios disponibles en el sistema: \n");
        int contador = 1;
        for(Espacio es: super.getCentConv().getListaEstacios()){
            System.out.println("-"+contador+".  Nombre: "+es.getNombre()+" - Código: "+es.getCodigo());
            contador++;
        }
        
        System.out.println("\t-> Introduzca el código del Espacio del cuál mostrar sus ingresos: ");
        String codEspacio = MyInput.readString();
        
        Espacio e = super.getCentConv().buscarEspacio(codEspacio);
        
        if(e == null){
            System.out.println("\tERROR! Código invalido, el espacio no existe en el sistema.");
        }else{
            
            do{
                System.out.println("\t-> Introduzca la fecha de inicio del perido: (yyyy-MM-dd)");
                String fechaPrimera = MyInput.readString();
                f1 = LocalDate.parse(fechaPrimera);
                
                if(f1.isAfter(LocalDate.now()) == true){
                    System.out.println("\tERROR! La fecha introducida es posterior a la fecha actual. Inténtelo de nuevo.");
                }
                
            }while(f1.isAfter(LocalDate.now()) == true);
            
            do{
                System.out.println("\t-> Introduzca la fecha de fin del periodo: (yyyy-MM-dd)");
                String fechaSegunda = MyInput.readString();
                f2 = LocalDate.parse(fechaSegunda);
                
                if(f2.isAfter(LocalDate.now()) == true){
                    System.out.println("\tERROR! La fecha introducida es posterior a la fecha actual. Inténtelo de nuevo.");
                }
                
            }while(f2.isAfter(LocalDate.now()) == true); 
            
            if(f1.compareTo(f2) < 0){
                System.out.println("\t-> Los Ingresos Totales obtenidos por el Espacio "+e.getNombre()+" con Código "+e.getCodigo()+" son:\n");
                System.out.println("\t\t"+super.getCentConv().calcularIngresosEspacio(f1, f2, e)+" euros.");
            }
            else if(f1.compareTo(f2) > 0){
                System.out.println("\t-> Los Ingresos Totales obtenidos por el Espacio "+e.getNombre()+" con Código "+e.getCodigo()+" son:\n");
                System.out.println("\t\t"+super.getCentConv().calcularIngresosEspacio(f2, f1, e)+" euros.");
            }
        }
    }
    
    public void mostrarIngresosCC(){
        LocalDate f1, f2;
        
        System.out.println("\t-------------------------------------------------------------------");
        System.out.println("\t        MOSTRAR INGRESOS TOTALES DEL CENTRO DE CONVENCIONES        ");
        System.out.println("\t-----------------------------------------------------------------\n");
        
        do{
            System.out.println("\t-> Introduzca la fecha de inicio del perido: (yyyy-MM-dd)");
            String fechaPrimera = MyInput.readString();
            f1 = LocalDate.parse(fechaPrimera);
                
            if(f1.isAfter(LocalDate.now()) == true){
                System.out.println("\tERROR! La fecha introducida es posterior a la fecha actual. Inténtelo de nuevo.");
            }
                
        }while(f1.isAfter(LocalDate.now()) == true);
            
        do{
            System.out.println("\t-> Introduzca la fecha de fin del periodo: (yyyy-MM-dd)");
            String fechaSegunda = MyInput.readString();
            f2 = LocalDate.parse(fechaSegunda);
                
            if(f2.isAfter(LocalDate.now()) == true){
                System.out.println("\tERROR! La fecha introducida es posterior a la fecha actual. Inténtelo de nuevo.");
            }
                
        }while(f2.isAfter(LocalDate.now()) == true); 
         
        if(f1.compareTo(f2) < 0){
            System.out.println("\t-> Los Ingresos Totales obtenidos por el Centro de Convenciones entre "+f1+" y "+f2+"son: ");
            System.out.println("\t\t"+super.getCentConv().calcularIngresosCC(f1, f2)+" euros.");
                
        }
        else if(f1.compareTo(f2) > 0){
            System.out.println("\t-> Los Ingresos Totales obtenidos por el Centro de Convenciones entre "+f2+" y "+f1+"son: ");
            System.out.println("\t\t"+super.getCentConv().calcularIngresosCC(f2, f1)+" euros.");

        }
           
    }
    
    public String ejecutarOpciones(){
        System.out.println("");
        System.out.println("Menú Gestion Espacios.");
        System.out.println("Seleccione una opción:");
        System.out.println("0. Volver al menu principal");
        System.out.println("1. Mostrar los Ingresos totales obtenidos por un Espacio. (especificar periodo)");
        System.out.println("2. Mostrar los Ingresos totales obtenidos del Centro de Convenciones. (especificar periodo)");
 
        String s=MyInput.readString();
        switch(s){
                case "0": {return "n";}
                case "1": {mostrarIngresosEspacio();return "s";}
                case "2": {mostrarIngresosCC();return "s";}
                default: {System.out.println("Opción no válida. Vuelva a intentarlo."); return "s";}
            }
        }
}
