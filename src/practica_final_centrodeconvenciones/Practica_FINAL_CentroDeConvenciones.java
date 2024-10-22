/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package practica_final_centrodeconvenciones;

import Entrada.MyInput;
import Menus.MenuPrincipal;

/**
 *
 * @author Usuario
 */
public class Practica_FINAL_CentroDeConvenciones{

    public static void main(String[] args) {
        
        Centro_Convenciones CC = MyInput.deserialize("fichero.dat");
        if(CC==null){
            CC = Centro_Convenciones.obtenerInstanciaCC();
        }
        MenuPrincipal mp = new MenuPrincipal(CC);
        mp.ejecutar();
        MyInput.serialize(CC, "fichero.dat");
            
        
    }
}
