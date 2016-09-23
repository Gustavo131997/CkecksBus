/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheksbus_alfa;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Fabian
 */
public class CheksBus_Alfa {

    public static void main(String[] args) {
        ArrayList chile = new ArrayList();
        menu();
    }

    public static void menu() {
        Scanner leer = new Scanner(System.in);
        String opcion = "";
        do {
            System.out.println("1 - Editar Terminal");
            System.out.println("2 - Mostrar Itinerario Terminal");
            System.out.println("0 - Salir");
            do {
                opcion = leer.next();
            } while (opcion != "1" && opcion != "2" && opcion != "0");
            switch (opcion) {
                case "1":
                    break;
                case "2":
                    break;
                case "0":
                    break;
            }
        } while (!opcion.equals("0"));
    }

    public static void añadirTerminal() {
        ArrayList terminal1 = new ArrayList();
        ArrayList terminal2 = new ArrayList();

    }

    public static ArrayList añadirFuncionarioTerminal() {
        Scanner leer = new Scanner(System.in);
        ArrayList funcionario = new ArrayList();//Inicia un ArrayList para guardar  los datos del funcionario
        String nombrefuncionario, empresaquepertenece, nombreusuario, contraseña;
        do {
            System.out.println("Nombre Funcionario: ");
            nombrefuncionario = leer.next();
        } while (!ValidarNombres(nombrefuncionario));//el ciclo seguira mientras el validador diga que el patron de escritura del nombre es erroneo
        do {
            System.out.println("Empresa: ");
            empresaquepertenece = leer.next();
        } while (!ValidarNombres(empresaquepertenece));//el ciclo seguira mientras el validador diga que el patron de escritura es erroneo
        do {
            System.out.println("Nombre Usuario: (8 digitos minimo) ");
            nombreusuario = leer.next();
        } while (nombreusuario.replaceAll(" ", "").length() < 8);//el ciclo seguira mientras el usuario tenga menos de 8 digitos descontando los espacios
        do {
            System.out.println("Contraseña:(8 digitos minimo) ");
            contraseña = leer.next();
        } while (contraseña.replaceAll(" ", "").length() < 8);// el ciclo seguira mientras la contraseña tenga menos de 8 dijitos descontando los espacios
        funcionario.add(0, nombrefuncionario);// agrega nombrefuncionario en posicion 0 del arraylist
        funcionario.add(1, empresaquepertenece);//agrega empresa que pertenece a pocicion 1 del arraylist
        funcionario.add(2, nombreusuario);//agrega nombreusuario a pocicion 2 del arraylist
        funcionario.add(3, contraseña);//agrega contraseña a pocicion 3 del arraylist

        return funcionario;// regresa el arraylist funcionario
    }

    public static ArrayList añadirItinerarioBus() {
        String estadoBus, destino, empresa, horaSalidaBus, horaLlegadaBus, fecha;
        Scanner leer = new Scanner(System.in);
        ArrayList itinerario = new ArrayList();//crea un ArrayList para el itinerario de un bus;

        do {
            System.out.println("Ingrese Fecha: ");
            fecha = leer.next();
        } while (!ValidarFecha(fecha));//el ciclo seguira mientras el validador indique que no se cumple con el formato de fecha
        do {
            System.out.println("Ingrese Hora LLegada del Bus: ");
            horaLlegadaBus = leer.next();
        } while (!ValidarHora(horaLlegadaBus));//el ciclo seguira mientras el formato de hora no se cumpla
        do {
            System.out.println("Ingrese Hora Salida del Bus: ");
            horaSalidaBus = leer.next();
        } while (!ValidarHora(horaSalidaBus));

        return itinerario;
    }

    public static boolean ValidarNombres(String nombre) {
        Pattern pat = Pattern.compile("[A-Za-z]+");
        Matcher mac = pat.matcher(nombre);
        return (mac.matches());
    }

    public static boolean ValidarFecha(String fecha) {
        Pattern pat = Pattern.compile("[\\d]{1,2}[-|/]{1}[\\d]{1,2}");
        Matcher mac = pat.matcher(fecha);
        return (mac.matches());
    }

    public static boolean ValidarHora(String hora) {
        Pattern pat = Pattern.compile("[\\d]{2,2}:[\\d]{2,2}");
        Matcher mac = pat.matcher(hora);
        return (mac.matches());
    }
}
