/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import Entrada.MyInput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import practica_final_centrodeconvenciones.Carta;
import practica_final_centrodeconvenciones.Centro_Convenciones;
import practica_final_centrodeconvenciones.Consumicion;
import practica_final_centrodeconvenciones.Escenario;
import practica_final_centrodeconvenciones.Espacio;
import practica_final_centrodeconvenciones.Musica;
import practica_final_centrodeconvenciones.Ornamento;
import practica_final_centrodeconvenciones.Producto;
import practica_final_centrodeconvenciones.Reserva;

/**
 *
 * @author Usuario
 */
public class GestionReservas extends Menus{
    
    public GestionReservas(Centro_Convenciones centConv){
        super(centConv);
    }
    
    public void darAltaReserva(){
        ArrayList<Espacio> espaciosDisponibles = new ArrayList<Espacio>();
        String respuesta;
        int opcion;
        int precio;
        int contadorEx1, contadorEx2;
        LocalDate f = null;
        int c = 0;
        
        System.out.println("\t--------------------------------");
        System.out.println("\t        ALTA DE RESERVA         ");
        System.out.println("\t--------------------------------\n");
        
        do{
            contadorEx1 = 0;
            try{
                System.out.println("\t-> Introduzca una fecha: (yyyy-MM-dd)");
                String fechaString = MyInput.readString();
                f = LocalDate.parse(fechaString);
            }catch(DateTimeParseException dte){
                System.out.println("ERROR! El formato de la fecha es erróneo.");
                contadorEx1++;
            }
        }while(contadorEx1 > 0);
        
        
        do{
            contadorEx2 = 0;
            System.out.println("\t-> Introduzca el número de personas que van a participar en el evento: ");
            try{
                c = MyInput.readInt();
            }catch(NumberFormatException nfe){
                System.out.println("El formato del número es erróneo.");
                contadorEx2++;
            }catch(NullPointerException npe){
                System.out.println("El valor introducido es nulo.");
                contadorEx2++;
            }
        }while(contadorEx2 > 0);
        
        System.out.println("\tESPACIOS DISPONIBLES PARA LA FECHA Y CAPACIDAD SELECCIONADOS: ");
        System.out.println("\t-----------------------------------------------------------\n");
        
        for(Espacio es:super.getCentConv().getListaEstacios()){
            int contador = 0;
            for(Reserva res:super.getCentConv().getListaReservas()){
                if(res.getEspacioDecorado().getCodigo().equals(es.getCodigo())){
                    if(res.getFecha().compareTo(f) == 0){
                        contador++;
                    }
                }
            }
            if(contador == 0 && c <= es.getCapacidad()){
                espaciosDisponibles.add(es);
            }
        }
        
        if(espaciosDisponibles.size()==0){
            System.out.println("\tLO SENTIMOS, NO EXISTEN ESPACIOS DISPONIBLES PARA LA FECHA Y CAPACIDAD SELECCIONADOS.");
        }
        else{
            for (Espacio es:espaciosDisponibles){
                System.out.println("\tNombre: "+es.getNombre()+" - Codigo: "+es.getCodigo());
            }
  
            System.out.println("\n\tIntroduzca el código del Espacio que desea reservar: ");
            respuesta = MyInput.readString();
            Espacio esp = super.getCentConv().buscarEspacio(respuesta);
        
            if(esp != null){
            
                System.out.println("-> Introduzca el nombre del cliente a formalizar la reserva: ");
                String nombre=MyInput.readString();
        
                System.out.println("-> Introduzca un teléfono de contacto: ");
                int telefono = MyInput.readInt();

                System.out.println("\n\t¿Desea algún equipamiento especial? (s/n)");
                respuesta = MyInput.readString().toLowerCase();
                if(respuesta.equals("s")){
                    do{
                       System.out.println("Añada equipamiento: 1. Ornamento, 2. Música, 3. Escenario");
                       System.out.println("Escoja una opción (1-3): ");
                       opcion = MyInput.readInt();
                       switch(opcion){
                           case 1: System.out.println("\n\tHas elegido Ornamento! Introduzca el precio del Ornamento:");
                                   precio = MyInput.readInt();
                                   esp = new Ornamento(esp, precio);
                                   break;
                           case 2: System.out.println("\n\tHas elegido Música! Introduzca el precio de la Música:");
                                   precio = MyInput.readInt();
                                   esp = new Musica(esp, precio);
                                   break;
                           case 3: System.out.println("\n\tHas elegido Escenario! Introduzca el precio del Escenario: ");
                                   precio = MyInput.readInt();
                                   esp = new Escenario(esp, precio);
                       }
                       
                       System.out.println("¿Desea añadir más equipamientos? (s/n)");
                       respuesta = MyInput.readString().toLowerCase();
                           
                    }while(respuesta.equals("s"));
                }
                
                Reserva r = new Reserva(nombre, telefono, generarCodigoReserva(), esp, f);
                
                System.out.println("\t¿Desea realizar alguna consumición? (s/n)");
                respuesta = MyInput.readString().toLowerCase();
                if(respuesta.equals("s")){
                    
                        System.out.println("\tA continuación se muestran todos los productos disponibles en el Sistema: ");
                    
                        for(Carta s: super.getCentConv().obtenerLista(super.getCentConv().getCarta())){
                            for(Carta p: s.getLista()){
                                if(p.getEstado() == true){
                                    p.mostrarInfo();
                                }
                            }
                        }
                    
                    
                        System.out.println("\n\tIntroduzca el Código del Producto que desee: ");
                        String nombreP = MyInput.readString();
                    
                        Carta p = super.getCentConv().getCarta().recuperarCarta(nombreP);
                        
                        if(p == null){
                            System.out.println("ERROR! El producto introducido no existe.");  
                        }else{
                            System.out.println("\tIntroduzca la cantidad que desee: ");
                            int cantidad = MyInput.readInt();
                        
                            Consumicion consumicion = new Consumicion((Producto) p, cantidad, generarCodigoConsumicion());
                            r.aniadirConsumicion(consumicion);
                        }
                      
              
                }
                
                boolean verificacion = super.getCentConv().altaReserva(r);
                
                if (verificacion){
                   System.out.println("Perfecto! Se dió de alta la reserva satisfactoriamente.");}
                else{
                   System.out.println("ERROR! No se dió de alta la reserva porque ya existía");}
                
                espaciosDisponibles.clear();
                
            }else{
                System.out.println("ERROR! El espacio introducido es erroneo o no existe.");
                System.out.println("Misión Abortada");
            }
        }
    }
    
    /**
     *
     * @return
     */
    public String generarCodigoReserva(){
        int numeroReservas = getCentConv().getListaReservas().size();
        if(numeroReservas == 0){
            return "R" + "001";
        } 
        if(numeroReservas >= 1 && numeroReservas < 9){
            int suma = numeroReservas+1;
            return "R" + "00" + suma;
        }
        if(numeroReservas >= 9 && numeroReservas < 99){
            int suma = numeroReservas+1;
            return "R" + "0" + suma;
        }
        return null;
    }
    
    
    public String generarCodigoConsumicion(){
        int numeroConsumiciones = contarNumeroConsumiciones();
        if(numeroConsumiciones == 0){
            return "C" + "0001";
        } 
        if(numeroConsumiciones >= 1 && numeroConsumiciones < 9){
            int suma = numeroConsumiciones+1;
            return "C" + "000" + suma;
        }
        if(numeroConsumiciones >= 9 && numeroConsumiciones < 99){
            int suma = numeroConsumiciones+1;
            return "C" + "00" + suma;
        }
        if(numeroConsumiciones >= 99 && numeroConsumiciones < 999){
            int suma = numeroConsumiciones+1;
            return "C" + "0" + suma;
        }
        return null;
    }
    
    public int contarNumeroConsumiciones(){
        int contador = 0;
        for(Reserva r: super.getCentConv().getListaReservas()){
            for(Consumicion c: r.getListaConsumiciones()){
                contador++;
            }
        }
        
        return contador;
    }
    
    public void darBajaReserva(){
        int contador = 1;
        
        System.out.println("\t--------------------------------");
        System.out.println("\t        BAJA DE RESERVA         ");
        System.out.println("\t--------------------------------\n");
        
        System.out.println("\tINFORMACIÓN PREVIA! A continuación se muestran todas las reservas del sistema: \n");
        
        for (Reserva res:super.getCentConv().getListaReservas()){
            System.out.println("-"+contador+".  Código: "+res.getId()+"   Fecha Vencimiento: "+res.getFecha());
            contador++;
        }
        System.out.println("\n\tIntroduzca el código de la Reserva que desea eliminar: ");
        String respuesta = MyInput.readString();
        
        super.getCentConv().bajaReserva(respuesta);
    }
    
    public void mostrarInfoReservaConcreta(){
        int contador = 1;
        
        System.out.println("\t-------------------------------------------------------");
        System.out.println("\t       INFORMACIÓN DE UNA RESERVA CONCRETA          ");
        System.out.println("\t------------------------------------------------------\n");
        System.out.println("\tINFORMACIÓN PREVIA! A continuación se muestran todas las reservas del sistema: \n");
        
        for (Reserva res:super.getCentConv().getListaReservas()){
            System.out.println("-"+contador+".  Código: "+res.getId()+"   Fecha Vencimiento: "+res.getFecha());
            contador++;
        }
        
        System.out.println("\t-> Introduzca el código de la Reserva a mostrar: ");
        String respuesta = MyInput.readString();
        
        super.getCentConv().mostrarInfoReserva(respuesta);
    }
    
    public void aniadirConsumicionReserva(){
        System.out.println("\t-------------------------------------------------------");
        System.out.println("\t        AÑADIR UNA CONSUMICIÓN A UNA RESERVA       ");
        System.out.println("\t------------------------------------------------------\n");
        
        System.out.println("\tINFORMACIÓN PREVIA -> Reservas disponibles en el sistema: ");
        for(Reserva res:super.getCentConv().getListaReservas()){
            System.out.println(res.getId());
        }
        
        System.out.println("\t-> Introduzca el código de la Reserva: ");
        String respuesta = MyInput.readString();
        
        System.out.println("\tA continuación se muestran todos los productos disponibles en el Sistema: ");
                    
        for(Carta s: super.getCentConv().obtenerLista(super.getCentConv().getCarta())){
            for(Carta p: s.getLista()){
                if(p.getEstado() == true){
                    p.mostrarInfo();
                }
            }
        }
                    
        System.out.println("\n\tIntroduzca el Código del Producto que desee: ");
        String nombreP = MyInput.readString();
                    
        Carta p = super.getCentConv().getCarta().recuperarCarta(nombreP);
                        
        if(p == null){
            System.out.println("ERROR! El producto introducido no existe.");  
        }else{
            System.out.println("\tIntroduzca la cantidad que desee: ");
            int cantidad = MyInput.readInt();
                        
            Consumicion con = new Consumicion((Producto) p, cantidad, generarCodigoConsumicion());
            super.getCentConv().aniadirConsumicionReserva(respuesta, con);
        }
        
    }
    
    public void eliminarConsumicionReserva(){
        System.out.println("\t-------------------------------------------------------");
        System.out.println("\t        ELIMINAR UNA CONSUMICIÓN DE UNA RESERVA       ");
        System.out.println("\t------------------------------------------------------\n");
        
        System.out.println("\tINFORMACIÓN PREVIA -> Reservas disponibles en el sistema: ");
        for(Reserva res:super.getCentConv().getListaReservas()){
            System.out.println(res.getId());
        }
        
        System.out.println("\t-> Introduzca el código de la Reserva: ");
        String respuesta = MyInput.readString();
        
        System.out.println("\tA continuación se muestran todas las consumiciones de la reserva seleccionada: ");
        super.getCentConv().mostrarConsumicionesReserva(respuesta);
        
        System.out.println("\t-> Introduzca el código de la Consumición a eliminar: ");
        String idConsumicion = MyInput.readString();
        
        super.getCentConv().elminarConsumicionReserva(respuesta, idConsumicion);
        
    }
    
    public String ejecutarOpciones(){
        System.out.println("");
        System.out.println("Menú Gestion Reservas.");
        System.out.println("Seleccione una opción:");
        System.out.println("0. Volver al menu principal");
        System.out.println("1. Dar de alta una nueva Reserva");
        System.out.println("2. Dar de baja una Reserva");
        System.out.println("3. Añadir una nueva consumición a una Reserva");
        System.out.println("4. Eliminar una consumición de una Reserva");
        System.out.println("5. Listar la información de todos las Reservas");
        System.out.println("6. Mostrar información de una Reserva concreta");
        String s=MyInput.readString();
        switch(s){
                case "0": {return "n";}
                case "1": {darAltaReserva();return "s";}
                case "2": {darBajaReserva();return "s";}
                case "3": {aniadirConsumicionReserva();return "s";}
                case "4": {eliminarConsumicionReserva();return "s";} 
                case "5": {super.getCentConv().listarInfoReservas();return"s";}
                case "6": {mostrarInfoReservaConcreta();return "s";}
                default: {System.out.println("Opción no válida. Vuelva a intentarlo."); return "s";}
            }
        }
}
