package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

public class LCDTester {
    //cadena que detiene el comando de pedir los datos y muestra en pantalla los numeros ingresados en formato lcd
    static final String CADENA_FINAL = "0,0";
    public static void main(String[] args) {
        // Establece los segmentos de cada numero, es decir, guarda cada cadena en una lista
        List<String> ordenesDeImpresion = new ArrayList<>();
        // variable no nativa que almacena el espaciado de los caracteres
        String comando;
        //variable entera de espacio entre los digitos
        int espacioEntreDigitos = 0;
        try {
            try{
                //se le pide al usuario que ingrese un valor entre 0 y 5 para determinar el espaciado entre cada numero
               comando= JOptionPane.showInputDialog("---------------CONVERSOR DE NUMEROS A LCD------------------\n"+
                       "Proporcionado por CARLOS AGUDELO PARRA\n"+
                       "Editado por JASSON ESTEBAN GUALGUAN GUZMÁN\n"+
                        "Antes de iniciar, digite un valor que servira para el espaciado entre numeros\n"+
                        "el valor debe ir entre 0 y 5: \n");
                // se valida si el valor ingresado (comando) es un numero, para ello invocamos la funcion esNumerico de la clase Utilidades
                if (Utilidades.esNumerico(comando)) {
                    //hacemos que la variable espacioEntreDigitos tome el valor entero de la variable comando, es decir, si ingresa un
                    // valor para comando de 3 y valido si es numerico,espacioEntreDigitos vale en int el valor de comando
                    espacioEntreDigitos = Integer.parseInt(comando);

                    // se valida que el espaciado este entre 0 y 5
                    if(espacioEntreDigitos <0 || espacioEntreDigitos >5) {
                        JOptionPane.showMessageDialog(null, "El espacio entre digitos debe estar entre 0 y 5");
                    }
                } 
                else {
                    JOptionPane.showMessageDialog(null, "La cadena " + comando+ " no es un numero entero");
                }

                //a partir de aca, se inicia el proceso de pedir al usuario dos datos, el tamaño que quiere que se muestren los datos
                // y el numero que se quiere representar en lcd
                do {
                    //se debe ingresar los datos en formato **,** para que el programa corra correctamente, de lo contrario, se proceden a realizar las validaciones
                    comando = JOptionPane.showInputDialog("Ingrese un numero, para finalizar, ingrese la cadena 0,0");
                    //preguntamos si la cadena ingresada es igual a 0,0 --> CADENA_FINAL
                    if(!comando.equalsIgnoreCase(CADENA_FINAL)) {
                        //a partir de aca la variable ordenesDeImpresion añade elementos a la lista
                        ordenesDeImpresion.add(comando);
                    }
                    //la secuencia termina cuando el usuario ingresa 0,0
                }while (!comando.equalsIgnoreCase(CADENA_FINAL));

            }catch (Exception error){
                JOptionPane.showMessageDialog(null, "Hay un error " + error.getMessage());
            }
            //se instancia la clase ImpresorLCD con el objeto impresorLCD
            ImpresorLCD impresorLCD = new ImpresorLCD();
            //Un iterador (Iterator)  es un objeto que nos permite  recorrer una lista y presentar por pantalla todos sus elementos
            Iterator<String> iterator = ordenesDeImpresion.iterator();
            //con el .hasNext() se realiza la operacion de recorrer
            while (iterator.hasNext()) {
                try {
                    //se llama la funcion imprimir de la clase ImpresorLCD, se envian las variables iterator y espacioEntreDigitos
                    impresorLCD.imprimir(iterator.next(), espacioEntreDigitos);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Hay el siguiente error: " + ex.getMessage());
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Hay el siguiente error: "+ ex.getMessage());
        }
    }
}
