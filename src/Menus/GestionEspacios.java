/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import practica_final_centrodeconvenciones.Centro_Convenciones;
import practica_final_centrodeconvenciones.Espacio;
import practica_final_centrodeconvenciones.Coste;
import java.time.LocalDate;
import Entrada.MyInput;

/**
 *
 * @author Usuario
 */
public class GestionEspacios extends Menus{
    
    public GestionEspacios(Centro_Convenciones centConv){
        super(centConv);
    }
    
    public void darAltaEspacio(){
        double superficie = 0.0;
        int capacidad = 0;
        double precio = 0.0;
        int contadorEx1, contadorEx2, contadorEx3;
        
        System.out.println("\t--------------------------------");
        System.out.println("\t        ALTA DE ESPACIO         ");
        System.out.println("\t--------------------------------\n");
        
        System.out.println("-> Introduzca el nombre del espacio: ");
        String nombre=MyInput.readString();
        
        do{
            contadorEx1 = 0;
            System.out.println("-> Introduzca la superficie del espacio: ");
            try{
                superficie = MyInput.readDouble();
            }catch(NumberFormatException nfe){
                System.out.println("El formato del número es erróneo.");
                contadorEx1++;
            }catch(NullPointerException npe){
                System.out.println("El valor introducido es nulo.");
                contadorEx1++;
            }
        }while(contadorEx1 > 0);
        
        do{
            contadorEx2 = 0;
            System.out.println("-> Introduzca la capacidad (número de personas) del espacio: ");
            try{
                capacidad = MyInput.readInt();
            }catch(NumberFormatException nfe){
                System.out.println("El formato del número es erróneo.");
                contadorEx2++;
            }catch(NullPointerException npe){
                System.out.println("El valor introducido es nulo.");
                contadorEx2++;
            }
        }while(contadorEx2 > 0);
        
        do{
            contadorEx3 = 0;
            System.out.println("-> Introduzca un precio para el año actual (" + LocalDate.now().getYear() + "): ");
            try{
                precio = MyInput.readDouble();
            }catch(NumberFormatException nfe){
                System.out.println("El formato del número es erróneo.");
                contadorEx3++;
            }catch(NullPointerException npe){
                System.out.println("El valor introducido es nulo.");
                contadorEx3++;
            }
        }while(contadorEx3 > 0);
        
        
        //Generamos el código automático para el espacio:
        String codigo = generarCodigoEspacio();
        
        Espacio e = new Espacio(codigo, nombre, superficie, capacidad, true);
        e.setListaCostes(new Coste(precio, LocalDate.now().getYear()));
        
        boolean verificacion = getCentConv().altaEspacio(e);
        if (verificacion)
            System.out.println("Perfecto! Se dió de alta el espacio satisfactoriamente.");
        else
            System.out.println("ERROR! No se dió de alta el espacio porque ya existía"); 
        }
    
    public String generarCodigoEspacio(){
        int numeroEspacios = getCentConv().getListaEstacios().size();
        if(numeroEspacios == 0){
            return "ES" + "001";
        } 
        if(numeroEspacios >= 1 && numeroEspacios < 9){
            int suma = numeroEspacios+1;
            return "ES" + "00" + suma;
        }
        if(numeroEspacios >= 9 && numeroEspacios < 99){
            int suma = numeroEspacios+1;
            return "ES" + "0" + suma;
        }
        if(numeroEspacios >= 99 && numeroEspacios < 999){
            int suma = numeroEspacios+1;
            return "ES" + suma;
        }
        return null;
    }
    
    public void aniadirPrecioEspacio(){
        System.out.println("\t---------------------------------------------");
        System.out.println("\t        AÑADIR PRECIO A UN ESPACIO         ");
        System.out.println("\t---------------------------------------------\n");
        
        System.out.println("\tINFORMACIÓN PREVIA: A CONTINUCACIÓN SE MUESTRAN TODOS LOS ESPACIOS EXISTENTES: \n");
        
        int contador = 1;
        for (Espacio es:super.getCentConv().getListaEstacios()){
            System.out.println("-"+contador+".   Código: "+es.getCodigo()+"  Nombre: "+es.getNombre());
            contador = contador + 1; 
        }
        
        System.out.println("-> Introduzca el código del espacio: ");
        String codigo = MyInput.readString();
        
        Espacio esp = super.getCentConv().buscarEspacio(codigo);
        
        if(esp == null){
            System.out.println("\tERROR! No existe ningún espacio disponible para el código introducido.");
        }
        else
        {
            System.out.println("-> Introduzca el año que se quiere registrar: ");
            int anio = MyInput.readInt();
        
            System.out.println("-> Introduzca el porcentaje: ");
            int porcen = MyInput.readInt();
            
            getCentConv().aniadirPrecioEspc(anio, esp, porcen);
            System.out.println("\tSe ha añadido el precio para el año "+anio+" de forma satisfactoria!");
        }
    }
    
    public void hacerDisponible(){
        System.out.println("\t---------------------------------------------");
        System.out.println("\t        HACER DISPONIBLE UN ESPACIO         ");
        System.out.println("\t---------------------------------------------\n");
        
        System.out.println("\tINFORMACIÓN PREVIA: A CONTINUCACIÓN SE MUESTRAN LOS ESPACIOS NO DISPONIBLES TOTALES: \n");
        
        int contador = 1;
        for (Espacio es:super.getCentConv().getListaEstacios()){
            if(es.getEstado()==false){
                System.out.println("-"+contador+".   Código: "+es.getCodigo()+"  Nombre: "+es.getNombre());
                contador = contador + 1;
            }
        }
        
        System.out.println("\t-> Introduzca el código del espacio a hacer disponible: ");
        String codigo = MyInput.readString();
        
        Espacio es = super.getCentConv().buscarEspacio(codigo);
        if (es == null){
            System.out.println("\tERROR! El espacio introducido no existe.");
        }else{
            if (es.getEstado() == true){
                System.out.println("\tEl espacio introducido ya era disponible.");
            }else{
                super.getCentConv().alterarDispEspc(es);
                System.out.println("\tEXITO! El espacio introducido ahora es disponible.");
            }
        }
    }
    
    public void hacerNoDisponible(){
        System.out.println("\t---------------------------------------------");
        System.out.println("\t        HACER NO DISPONIBLE UN ESPACIO         ");
        System.out.println("\t---------------------------------------------\n");
        
        System.out.println("\tINFORMACIÓN PREVIA: A CONTINUCACIÓN SE MUESTRAN LOS ESPACIOS DISPONIBLES TOTALES: \n");
        
        int contador = 1;
        for (Espacio es:super.getCentConv().getListaEstacios()){
            if(es.getEstado()==true){
                System.out.println("-"+contador+".   Código: "+es.getCodigo()+"  Nombre: "+es.getNombre());
                contador = contador + 1;
            }
        }
        
        System.out.println("\t-> Introduzca el código del espacio a hacer disponible: ");
        String codigo = MyInput.readString();
        
        Espacio es = super.getCentConv().buscarEspacio(codigo);
        if (es == null){
            System.out.println("\tERROR! El espacio introducido no existe.");
        }else{
            if (es.getEstado() == false){
                System.out.println("\tEl espacio introducido ya era no disponible.");
            }else{
                super.getCentConv().alterarDispEspc(es);
                System.out.println("\tEXITO! El espacio introducido ahora es NO disponible.");
            }
        }
    }
        
     
    public String ejecutarOpciones(){
        System.out.println("");
        System.out.println("Menú Gestion Espacios.");
        System.out.println("Seleccione una opción:");
        System.out.println("0. Volver al menu principal");
        System.out.println("1. Dar de alta un nuevo espacio");
        System.out.println("2. Añadir el precio de un espacio");
        System.out.println("3. Hacer disponible un espacio existente");
        System.out.println("4. Hacer no disponible un espacio existente");
        System.out.println("5. Listar la información de todos los espacios disponibles");
        System.out.println("6. Listar la información de todos los espacios no disponibles");
        String s=MyInput.readString();
        switch(s){
                case "0": {return "n";}
                case "1": {darAltaEspacio();return "s";}
                case "2": {aniadirPrecioEspacio();return "s";}
                case "3": {hacerDisponible();return "s";}
                case "4": {hacerNoDisponible();return"s";}
                case "5": {super.getCentConv().listarInfoEspcDisponible();return"s";}
                case "6": {super.getCentConv().listarInfoEspcNoDisponible();return"s";}
                default: {System.out.println("Opción no válida. Vuelva a intentarlo."); return "s";}
            }
        }
    }
   
