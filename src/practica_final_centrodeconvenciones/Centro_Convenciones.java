/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_final_centrodeconvenciones;

import Entrada.MyInput;
import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Esta clase contiene los metodos y atributos del centro de convenciones
 * @author Fabio Rodriguez y Alfredo Sobrados
 */
public class Centro_Convenciones implements Serializable{
    private static Centro_Convenciones instancia;
    private ArrayList<Espacio> listaEspacios;
    private Seccion carta;
    private ArrayList<Reserva> listaReservas;
    /**
     * Método constructor parametrizado
     * @param ingresos ingresos totales del centro de convenciones
     * @param carta la carta de productos de la cocina del centro de convenciones
     */
    private Centro_Convenciones() {
        listaEspacios = new ArrayList<Espacio>();
        listaReservas = new ArrayList<Reserva>();
        carta = new Seccion("Carta", "C_PRINCIPAL", "");
    }
    /**
     * Método estático que sirve para obtener una unica instancia de la clase Centro_Convenciones
     * @param ingresos ingresos totales del centro de convenciones 
     * @param carta la carta de prodcutos de la cocina del centro de convenciones
     * @return devuelve la única instancia de esta clase
     */
    public static Centro_Convenciones obtenerInstanciaCC(){
        if(instancia==null){
            instancia = new Centro_Convenciones();
        }
        return instancia;
    }
    /*
    public Object clone() throws CloneNotSupportedException{
        throw new CloneNotSupportedException;
    }
    */
    
    /*----------------------------------------------------------------------------------------------
                                      GESTIÓN DE ESPACIOS.
    -----------------------------------------------------------------------------------------------*/
    /**
     *  Método booleano que comprueba si existe un espacio
     * @param e espacio al cual comprobamos su existencia
     * @return true si existe y false si no existe
     */
    public boolean existe(Espacio e){
        for(Espacio es:listaEspacios)
            if(es.getNombre().equals(e.getNombre())) return true;
        return false;
    }
    
    /**
     * Método de tipo Espacio que busca un espacio en su coleción
     * @param cod codigo del espacio a buscar en la colección
     * @return el espacio encontrado o nada
     */
    public Espacio buscarEspacio(String cod){
        for(Espacio es:listaEspacios)
            if(es.getCodigo().equals(cod)) return es;
        return null;
    }
    /**
     * Método que da de alta un espacio en el sistema
     * @param e espacio que queremos dar de alta en el sistema 
     * @return false si el espacio ya esta creado o true si no estaba creado de antemano 
     */
    public boolean altaEspacio(Espacio e){ 
        for(Espacio es:listaEspacios)
            if(es.equals(e)) 
                return false;
        listaEspacios.add(e);
        return true;   
    }
    /**
     * Método que hace disponible o no disponible un espacio ya existente
     * @param cod código del espacio que queremos alterar su disponibilidad
     */
    public void alterarDispEspc(Espacio es){
        if(es.getEstado()==false){
            es.setDisponibilidad(true);
        }
        else{
            es.setDisponibilidad(false);
        } 
    }
    /**
     * Metodo que añade el precio de un espacio para un año y porcentaje dados
     * @param anio año del precio que queremos añadir (debe ser el año actual)
     * @param codigo codigo del espacio al que le queremos asignar el precio
     * @param porcen porcentaje que actualiza el valor del precio del anterior año
     */
    public void aniadirPrecioEspc(int anio, Espacio es, double porcen){
        double porcentaje = porcen/100;
        
        Coste c = es.buscarCoste(anio);
        
        if(c == null){
            Coste aux = es.buscarCoste(anio-1);
            double calculo = aux.getPrecio()+aux.getPrecio()*porcentaje;
            
            es.setListaCostes(new Coste(calculo, anio));
        }
    }
    /**
     * Método que lista la información de todos los espacios disponibles
     */
    public void listarInfoEspcDisponible(){
        System.out.println("\tINFORMACIÓN DE ESPACIOS DISPONIBLES");
        System.out.println("\t-----------------------------------\n");
        
        for (Espacio es:listaEspacios){
            Coste coste = es.buscarCoste(LocalDate.now().getYear());
            if(es.getEstado() == true){
                System.out.println("\t-----------------------------------------------------------");
                System.out.println("\tNombre: "+es.getNombre()+"  -  Codigo: "+es.getCodigo());
                System.out.println("\tSuperficie: "+es.getSuperficie()+"  -  Capacidad:"+es.getCapacidad());
                System.out.println("\tCoste Actual (Año "+LocalDate.now().getYear()+"): "+coste.getPrecio()); 
                System.out.println("\t-----------------------------------------------------------\n");
            }
        }
    }
    /**
     * Método que lista la información de todos los espacios no disponibles
     */
    public void listarInfoEspcNoDisponible(){
        
        System.out.println("\tINFORMACIÓN DE ESPACIOS NO DISPONIBLES");
        System.out.println("\t-------------------------------------\n");
        
        for (Espacio es:listaEspacios){
            Coste coste = es.buscarCoste(LocalDate.now().getYear());
            if(es.getEstado() == false){
                System.out.println("\t------------------------------------------------------------");
                System.out.println("\tNombre: "+es.getNombre()+"  -  Codigo: "+es.getCodigo());
                System.out.println("\tSuperficie: "+es.getSuperficie()+"  -  Capacidad:"+es.getCapacidad());
                System.out.println("\tCoste Actual (Año "+LocalDate.now().getYear()+"): "+coste.getPrecio()); 
                System.out.println("\t------------------------------------------------------------\n");
            }
        }
    }
    
    public void listarAllEspacios(){
        int contador = 1;
        for (Espacio es:listaEspacios){
            System.out.println("-"+contador+".   Código: "+es.getCodigo()+"  Nombre: "+es.getNombre());
            contador = contador + 1;
        }
    }
    
    
    /*----------------------------------------------------------------------------------------------
                                      GESTIÓN DE RESERVAS.
    -----------------------------------------------------------------------------------------------*/
    /**
     * Método que da de alta las reservas del centro de convenciones en el sistema
     * @param f fecha de la reserva
     * @param r objeto tipo reserva para guardar la reserva 
     * @param c capacidad del salon que se quiere reservar
     */
     public boolean altaReserva(Reserva r){
        for(Reserva res:listaReservas)
            if(res.equals(r)) 
                return false;
        listaReservas.add(r);
        return true;    
    }
    /**
     * Método que da de baja la reserva del sistema
     * @param id identificador de la reserva que queremos dar de baja
     */
    public void bajaReserva(String id){
        Reserva res = buscarReserva(id);
        
        if(listaReservas.contains(res) && (res.getFecha().isBefore(LocalDate.now()) == false)){
            listaReservas.remove(res);
            System.out.println("Se ha eliminado satisfactoriamente del sistema!");
        }
        else{
            System.out.println("ERROR: La reserva ya venció o no existe en el sistema. No se puede dar de baja.");
        }
        
    }
    /**
     * Método que busca una reserva en la lista de Reservas y la devuelve
     * @param id identificador de la reserva que queremos buscar
     * @return la Reserva encontrada o null
     */
    private Reserva buscarReserva(String id){
        for(Reserva res:listaReservas)
            if(res.getId().equals(id)) return res;
        return null;
    }
    
    public void aniadirConsumicionReserva(String idReserva, Consumicion con){
        Reserva r = buscarReserva(idReserva);
       
        if(r != null){
            r.aniadirConsumicion(con);
        }else{
            System.out.println("ERROR! La reserva introducida no existe.");
        }
    }
    
    public void mostrarConsumicionesReserva(String idReserva){
        Reserva r = buscarReserva(idReserva);
        
        if(r != null){
            r.mostrarConsumiciones();
        }
    }
    
    public void elminarConsumicionReserva(String idReserva, String idConsumicion){
        Reserva r = buscarReserva(idReserva);
     
        if(r != null){
            r.eliminarConsumicion(idConsumicion);
        }else{
            System.out.println("ERROR! La reserva introducida no existe.");
        }
    }
    
    /**
     * Método que lista la información de las Reservas del sistema
     */
    public void listarInfoReservas(){
        System.out.println("\t------------------------------------------------------");
        System.out.println("\tINFORMACION DE LAS RESERVAS DISPONIBLES EN EL SISTEMA");
        System.out.println("\t------------------------------------------------------\n");
        for(Reserva res:listaReservas){
            
            System.out.println("\t------------------------------------------------------------------------------------------");
            System.out.println("\t| Código de la Reserva: "+res.getId());
            System.out.println("\t| Fecha: "+res.getFecha());
            System.out.println("\t| Nombre del cliente: "+res.getCliente());
            System.out.println("\t| Numero de telefono del cliente: "+res.getTelefono());
            System.out.println("\t| Espacio Reservado: "+res.getEspacioDecorado().getNombre()+"   - Codigo Espacio: "+res.getEspacioDecorado().getCodigo());
            System.out.println("\t| Consumiciones: ");
            for(Consumicion c:res.getListaConsumiciones()){
                System.out.println("\t\tNombre Producto: "+c.getProducto().getNombre()+ " - Cantidad: "+c.getCantidad()+". Precio total: "+c.calcularCosteConsumicion()+" euros.");
            }
            System.out.println("\t-------------------------------------------------------------------------------------------");
        }
    }
    /**
     * Método que muestra la información de una reserva en específico
     * @param id identificador de la reserva que se quiere consultar su información
     */
    public void mostrarInfoReserva(String id){
        Reserva res = buscarReserva(id);
        if(res != null){
            System.out.println("\t-------------------------------------------------------------------------------");
            System.out.println("\t| Informacion de la reserva con identificador: "+id);
            System.out.println("\t| Fecha: "+res.getFecha());
            System.out.println("\t| Nombre del cliente: "+res.getCliente());
            System.out.println("\t| Numero de telefono del cliente: "+res.getTelefono());
            System.out.println("\t| Espacio Reservado: "+res.getEspacioDecorado().getNombre());
            System.out.println("\t| Consumiciones: ");
            for(Consumicion c:res.getListaConsumiciones()){
                System.out.println("\tNombre Producto: "+c.getProducto().getNombre()+ " - Cantidad: "+c.getCantidad()+". Precio total: "+c.calcularCosteConsumicion()+" euros.");
            }
            System.out.println("\t---------------------------------------------------------------------------------");
        }
        else
        {
            System.out.println("\tERROR! El código introducido es erroneo, no existe en el sistema.");
        }
        
    }
    
    /*----------------------------------------------------------------------------------------------
                                    GESTIÓN DE LA CARTA DE PRODUCTOS.
    -----------------------------------------------------------------------------------------------*/
    
    /**
     * Método para agregrar al sistema una seccion de la carta de productos 
     * @param s la seccion que quierer agregar al sistema
     * @param nombre nombre o identificador de la carta que queremos recuperar para añadir la seccion a esa carta
     */
    public void agregarSeccion(Carta c){
        carta.agregar(c);
    }
    /**
     * Método para agregrar al sistema un producto en una seccion de la carta de productos
     * @param s seccion recuperada que utilizamos para agregar el producto al sistema
     * @param p producto que queremos agregar al sistema dentro de su seccion correspondiente
     */
    public void agregarProducto(Carta s, Carta p){
        s.agregar(p);
    }
    
    public Seccion getCarta(){
        return carta;
    }
    
    public ArrayList<Carta> obtenerLista(Seccion s){
        return s.getLista();
    }
    
    public void mostrarInfoCarta(String cod){
        carta.recuperarCarta(cod).mostrarInfo();
    }
    
    public void alterarDispProducto(Carta p){
        if(p.getEstado()==false){
            p.setEstado(true);
        }
        else{
            p.setEstado(false);
        } 
    }
    
    public void aniadirPrecioProd(int anio, Producto p, double porcen){
        double porcentaje = porcen/100;
        
        Coste c = p.buscarCoste(anio);
        
        if(c == null){
            Coste aux = p.buscarCoste(anio-1);
            double calculo = aux.getPrecio()+aux.getPrecio()*porcentaje;
            
            p.setListaCostes(new Coste(calculo, anio));
            
        }
    }
    
    public ArrayList<Espacio> getListaEstacios(){
        return listaEspacios;
    }
    
    public ArrayList<Reserva> getListaReservas(){
        return listaReservas;
    }
    
    /*----------------------------------------------------------------------------------------------
                                        GESTIÓN ECONÓMICA.
    -----------------------------------------------------------------------------------------------*/
    
    public double calcularIngresosEspacio(LocalDate fechaInicial, LocalDate fechaFinal, Espacio es){
        ArrayList<Reserva> reservasAux = new ArrayList<Reserva>();
        double ingresoEspacio = 0;
        double ingresoConsumiciones = 0;
        
        for(Reserva res:getListaReservas()){
            if(res.getFecha().compareTo(fechaInicial) >= 0 && res.getFecha().compareTo(fechaFinal) <= 0){
                if(res.getEspacioDecorado().getCodigo().equals(es.getCodigo())){
                    reservasAux.add(res);
                }
            }
        }
        
        for(Reserva r:reservasAux){
            ingresoEspacio = ingresoEspacio + r.getEspacioDecorado().calcularIngreso();
            for(Consumicion c: r.getListaConsumiciones()){
                ingresoConsumiciones = ingresoConsumiciones + c.calcularCosteConsumicion();
            }
        }
        
        double ingresoTotal = ingresoEspacio + ingresoConsumiciones;
        return ingresoTotal;
    }
    
    public double calcularIngresosCC(LocalDate fechaInicial, LocalDate fechaFinal){
        double ingresoCC = 0;
        
        for(Espacio esp:listaEspacios){
            ingresoCC = ingresoCC + calcularIngresosEspacio(fechaInicial, fechaFinal, esp);
        }
        
        return ingresoCC;
    }
    
}
