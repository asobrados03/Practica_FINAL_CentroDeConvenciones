/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import Entrada.MyInput;
import java.time.LocalDate;
import java.util.ArrayList;
import practica_final_centrodeconvenciones.Carta;
import practica_final_centrodeconvenciones.Centro_Convenciones;
import practica_final_centrodeconvenciones.Coste;
import practica_final_centrodeconvenciones.Producto;
import practica_final_centrodeconvenciones.Seccion;

/**
 *
 * @author Usuario
 */

public class GestionCartaProductos extends Menus{
    
    
    public GestionCartaProductos(Centro_Convenciones centConv){
        super(centConv);
    }
    
    public void darAltaSeccion(){
        System.out.println("\t--------------------------------");
        System.out.println("\t        ALTA DE SECCIÓN         ");
        System.out.println("\t--------------------------------\n");
        
        System.out.println("\tIntroduzca el nombre de la Sección: ");
        String nombre = MyInput.readString();
        
        System.out.println("\tIntroduzca una breve descripción de las características de la Seccion a incluir: ");
        String descripcion = MyInput.readString();
        
        String codigo = generarCodigoSeccion();
        Carta c = new Seccion(nombre, codigo, descripcion);
        
        super.getCentConv().agregarSeccion(c);
        
    }
    
    public int contarNumeroSecciones(){
        int contador = 0;
        for(Carta c: super.getCentConv().obtenerLista(super.getCentConv().getCarta())){
            contador++;
        }
        return contador;
    }

    public void darAltaProducto(){
        System.out.println("\t--------------------------------");
        System.out.println("\t        ALTA DE PRODUCTO         ");
        System.out.println("\t--------------------------------\n");
        
        System.out.println("\tINFORMACIÓN PREVIA: A continuación se muestran las Secciones Disponibles: \n");
        
        for(Carta c: super.getCentConv().obtenerLista(super.getCentConv().getCarta())){
            c.mostrarInfo();
        }
        
        System.out.println("\t-> Introduzca el Código de la Sección donde introducir el producto: ");
        String codigo = MyInput.readString();
        
        Carta c = super.getCentConv().getCarta().recuperarCarta(codigo);
        
        System.out.println("\t-> Perfecto! Introduzca el nombre del Producto: ");
        String nombreP = MyInput.readString();
        
        System.out.println("\t-> Introduzca una breve descripción del producto: ");
        String descripcion = MyInput.readString();
        
        System.out.println("-> Introduzca un precio para el año actual (" + LocalDate.now().getYear() + "): ");
        double precio = MyInput.readDouble();
        
        //Generamos el código automático para el producto: 
        String id = generarCodigoProducto();
        
        Carta p = new Producto(nombreP, id, descripcion, true);
        p.setListaCostes(new Coste(precio, LocalDate.now().getYear()));
        
        super.getCentConv().agregarProducto(c, p);
    }
    
    public int contarNumeroProductos(){
        int contador = 0;
        for(Carta c: super.getCentConv().obtenerLista(super.getCentConv().getCarta())){
           for(Carta ce:c.getLista()){
               contador++;
           }
        }
        return contador;
    }
    
    public String generarCodigoSeccion(){
        int numeroSecciones = contarNumeroSecciones();
        if(numeroSecciones == 0){
            return "S" + "0001";
        } 
        if(numeroSecciones >= 1 && numeroSecciones < 9){
            int suma = numeroSecciones+1;
            return "S" + "000" + suma;
        }
        if(numeroSecciones >= 9 && numeroSecciones < 99){
            int suma = numeroSecciones+1;
            return "S" + "00" + suma;
        }
        if(numeroSecciones >= 99 && numeroSecciones < 999){
            int suma = numeroSecciones+1;
            return "S" + "0" + suma;
        }
        return null;
    }
    
    public String generarCodigoProducto(){
        int numeroProductos = contarNumeroProductos();
        if(numeroProductos == 0){
            return "P" + "0001";
        } 
        if(numeroProductos >= 1 && numeroProductos < 9){
            int suma = numeroProductos+1;
            return "P" + "000" + suma;
        }
        if(numeroProductos >= 9 && numeroProductos < 99){
            int suma = numeroProductos+1;
            return "P" + "00" + suma;
        }
        if(numeroProductos >= 99 && numeroProductos < 999){
            int suma = numeroProductos+1;
            return "P" + "0" + suma;
        }
        return null;
    }
    
    public void aniadirPrecioProductos(){
        int contador = 1;
        System.out.println("\t--------------------------------------------------------------------");
        System.out.println("\t        AÑADIR UN PRECIO A TODOS LOS PROCUTOS DEL SISTEMA       ");
        System.out.println("\t-------------------------------------------------------------------\n");
        
        System.out.println("\tINFORMACIÓN PREVIA: A CONTINUCACIÓN SE MUESTRAN TODOS LOS PRODUCTOS EXISTENTES: \n");
        
        for(Carta c: super.getCentConv().obtenerLista(super.getCentConv().getCarta())){
           for(Carta ce:c.getLista()){
               if(ce.getEstado() == true){
                   System.out.println("-"+contador+".   Código: "+ce.getId()+"  Nombre: "+ce.getNombre());
               }
           }
        }
        
        System.out.println("-> Introduzca el código del producto: ");
        String codigo = MyInput.readString();
        
        Carta p = super.getCentConv().getCarta().recuperarCarta(codigo);
        
        if(p==null){
            System.out.println("ERROR! El producto introducido no existe o es erroneo. Operación Abortada");
        }else{
            System.out.println("-> Introduzca el año que se quiere registrar: ");
            int anio = MyInput.readInt();
        
            System.out.println("-> Introduzca el porcentaje: ");
            int porcen = MyInput.readInt();
            
            getCentConv().aniadirPrecioProd(anio, (Producto) p, porcen);
            System.out.println("\tSe ha añadido el precio para el año "+anio+" de forma satisfactoria!");
        }
    }
    
    public void mostrarInfoProducto(){
        System.out.println("\t------------------------------------------");
        System.out.println("\t           INFO DE PRODUCTO              ");
        System.out.println("\t------------------------------------------");
        
        System.out.println("\tIntroduzca el Código del Producto: ");
        String cod = MyInput.readString();
        
        super.getCentConv().mostrarInfoCarta(cod);
    }
    
    public void mostrarInfoSeccionConProductos(){
        System.out.println("\t---------------------------------------------------");
        System.out.println("\t           INFO DE SECCIÓN Y SUS PRODUCTOS              ");
        System.out.println("\t---------------------------------------------------");
        
        System.out.println("\tINFORMACIÓN PREVIA! Secciones disponibles en el sistema: ");
        for(Carta c: super.getCentConv().obtenerLista(super.getCentConv().getCarta())){
            System.out.println(c.getId());
        }
        
        System.out.println("\n\tIntroduzca el Código de la Sección: ");
        String cod = MyInput.readString();
        super.getCentConv().mostrarInfoCarta(cod);
        
        System.out.println("\n\tPRODUCTOS DE LA SECCIÓN: ");
        ArrayList<Carta> prods = super.getCentConv().getCarta().recuperarCarta(cod).getLista();
        
        for(Carta c: prods){
            c.mostrarInfo();
        }
        
        
        
    }
    
    public void hacerProductoDisponible(){
        System.out.println("\t---------------------------------------------");
        System.out.println("\t        HACER DISPONIBLE UN PRODUCTO         ");
        System.out.println("\t---------------------------------------------\n");
        
        System.out.println("\tINFORMACIÓN PREVIA: A CONTINUCACIÓN SE MUESTRAN LOS PRODUCTOS NO DISPONIBLES TOTALES: \n");
        
        for(Carta c: super.getCentConv().obtenerLista(super.getCentConv().getCarta())){
           for(Carta ce:c.getLista()){
               if(ce.getEstado()==false){
                   System.out.println("-Codigo: "+ce.getId()+"  Nombre: "+ce.getNombre());
               }
           }
        }
        
        System.out.println("\t-> Introduzca el código del producto a hacer disponible: ");
        String codigo = MyInput.readString();
        
        Carta p = super.getCentConv().getCarta().recuperarCarta(codigo);
        
        if(p == null){
            System.out.println("ERROR! El producto introducido no existe.");
        }else{
            if (p.getEstado() == true){
                System.out.println("\tEl producto introducido ya era disponible.");
            }else{
                super.getCentConv().alterarDispProducto(p);
                System.out.println("\tEXITO! El producto introducido ahora es disponible.");
            }
        }
    }
    
    /**
     *
     */
    public void hacerProductoNoDisponible(){
        System.out.println("\t---------------------------------------------");
        System.out.println("\t        HACER NO DISPONIBLE UN PRODUCTO         ");
        System.out.println("\t---------------------------------------------\n");
        
        System.out.println("\tINFORMACIÓN PREVIA: A CONTINUCACIÓN SE MUESTRAN LOS PRODUCTOS DISPONIBLES TOTALES: \n");
        
        for(Carta c: super.getCentConv().obtenerLista(super.getCentConv().getCarta())){
           for(Carta ce:c.getLista()){
               if(ce.getEstado()==true){
                   System.out.println("-Codigo: "+ce.getId()+"  Nombre: "+ce.getNombre());
               }
           }
        }
        
        System.out.println("\t-> Introduzca el código del producto a hacer NO disponible: ");
        String codigo = MyInput.readString();
        
        Carta p = super.getCentConv().getCarta().recuperarCarta(codigo);
        
        if(p == null){
            System.out.println("ERROR! El producto introducido no existe.");
        }else{
            if (p.getEstado() == false){
                System.out.println("\tEl producto introducido ya era NO disponible.");
            }else{
                super.getCentConv().alterarDispProducto(p);
                System.out.println("\tEXITO! El producto introducido ahora es NO disponible.");
            }
        }
    }
        
    
    
    public void mostrarInfoProductosDisponibles(){
        System.out.println("\t----------------------------------------------------");
        System.out.println("\t         INFORMACIÓN DE PRODUCTOS DISPONIBLES"         );
        System.out.println("\t----------------------------------------------------\n");
        
        for(Carta c: super.getCentConv().obtenerLista(super.getCentConv().getCarta())){
           for(Carta ce:c.getLista()){
               if(ce.getEstado() == true){
                   ce.mostrarInfo();
               }
           }
        }
    }
    
    public void mostrarInfoProductosNoDisponibles(){
        System.out.println("\t----------------------------------------------------");
        System.out.println("\t      INFORMACIÓN DE PRODUCTOS NO DISPONIBLES"         );
        System.out.println("\t----------------------------------------------------\n");
        
        for(Carta c: super.getCentConv().obtenerLista(super.getCentConv().getCarta())){
           for(Carta ce:c.getLista()){
               if(ce.getEstado() == false){
                   ce.mostrarInfo();
               }
           }
        }
    }
    
    public String ejecutarOpciones(){
        System.out.println("");
        System.out.println("Menú Gestion de la Carta de Productos (Cocina).");
        System.out.println("Seleccione una opción:");
        System.out.println("0. Volver al menu principal");
        System.out.println("1. Dar de alta una Sección de la Carta");
        System.out.println("2. Dar de alta un nuevo Producto");
        System.out.println("3. Añadir un nuevo precio a todos los productos de la carta (para un año dado).");
        System.out.println("4. Hacer disponible un producto ya existente");
        System.out.println("5. Hacer no disponible un producto ya existente");
        System.out.println("6. Mostrar la información de la carta con los productos disponibles.");
        System.out.println("7. Mostrar la información de la carta con los productos no disponibles.");
        System.out.println("8. Mostrar la información de una sección con los productos disponibles.");
        System.out.println("9. Mostrar la información de un producto");

        String s=MyInput.readString();
        switch(s){
                case "0": {return "n";}
                case "1": {darAltaSeccion();return "s";}
                case "2": {darAltaProducto();return "s";}
                case "3": {aniadirPrecioProductos();return "s";}
                case "4": {hacerProductoDisponible();return"s";}
                case "5": {hacerProductoNoDisponible();return"s";}
                case "6": {mostrarInfoProductosDisponibles();return"s";}
                case "7": {mostrarInfoProductosNoDisponibles();return"s";}
                case "8": {mostrarInfoSeccionConProductos();return"s";}
                case "9": {mostrarInfoProducto();return"s";}
                default: {System.out.println("Opción no válida. Vuelva a intentarlo."); return "s";}
            }
        }
}
