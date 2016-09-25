/*
 Este programa va a estar orientado a los funcionarios de un terminal por ahora.
 */
package ckecksbus_alfa;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CkecksBus_Alfa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menu();

    }

    public static void menu() {
        Scanner leer = new Scanner(System.in);
        ArrayList<String> funcionarios = new ArrayList<>();
        ArrayList<String> itinerario = new ArrayList<>();
        String opcion = "";
        System.out.println("Ingrese nombre del Terminal");
        String nombreterminal = leer.nextLine();
        System.out.println("Ingrese Region ");
        String region = leer.nextLine();
        System.out.println("Ingrese Direcion de Ubicacion:");
        String direccion = leer.nextLine();
        do {
            System.out.println("1 - Funcionarios");
            System.out.println("2 - Itinerario");
            System.out.println("3 - Mostrar Terminal");
            System.out.println("0 - Salir");
            do {
                System.out.println("Ingrese opcion");
                opcion = leer.next();
            } while (!"1".equals(opcion) && !"2".equals(opcion) && !"3".equals(opcion) && !"0".equals(opcion));
            switch (opcion) {
                case "1":
                    menuFuncionarios(funcionarios);
                    break;
                case "2":
                    menuItinerario(itinerario,funcionarios);
                    break;
                case "3":
                    mostrarTerminal(nombreterminal, region, direccion, funcionarios, itinerario);
                    break;
                case "0":
                    System.out.println("Adios");
                    break;
            }

        } while (!opcion.equals("0"));
    }
    /* el metodo MenuFuncionarios recibe unicamente ArrayList funcionarios para que los guarde en el metodo menu */

    public static void mostrarTerminal(String nombreterminal, String region, String direccion, ArrayList<String> funcionarios, ArrayList<String> itinerario) {
        System.out.println("Terminal: " + nombreterminal + " Region: " + region + " Direccion: " + direccion + " ");
        System.out.println("Itinerario: ");
        for (int i = 0; i < itinerario.size(); i++) {
            System.out.println(itinerario.get(i));
        }

    }

    public static void menuFuncionarios(ArrayList<String> funcionarios) {
        Scanner leer = new Scanner(System.in);
        String nombrefuncionario, empresaquepertenece, nombreusuario, contraseña, terminalquepertenece, opc = "";
        System.out.println(" 1 - Añadir funcionario" + "\n 2 - Eliminar funcinario" + "\n 3 - Modificar funcionario" + "\n 4 - Mostrar funcionario" + "\n 0 - salir");
        do {
            do {
                System.out.println("Ingrese opcion");
                opc = leer.next();
            } while (!"1".equals(opc) && !"2".equals(opc) && !"3".equals(opc) && !"4".equals(opc) && !"0".equals(opc));
            switch (opc) {
                case "1":
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
                        contraseña = leer.nextLine();
                    } while (contraseña.replaceAll(" ", "").length() < 8);// el ciclo seguira mientras la contraseÃ±a tenga menos de 8 dijitos descontando los espacios
                    do {
                        System.out.println("Ingrese terminal que pertenece");
                        terminalquepertenece = leer.nextLine();
                    } while (!ValidarNombres(terminalquepertenece));
                    añadirFuncionarioTerminal(nombrefuncionario, empresaquepertenece, nombreusuario, terminalquepertenece, funcionarios);

                    break;
                case "2":
                    eliminarFuncionario(funcionarios);
                    break;
                case "3":
                    modificarFuncionario(funcionarios);
                    break;
                case "4":
                    mostrarFuncionarios(funcionarios);
                    break;
                case "0":
                    System.out.println("Ok");
                    break;
            }
        } while (!"0".equals(opc));
    }

    public static void añadirFuncionarioTerminal(String nombrefuncionario, String empresaquepertenece, String nombreusuario, String terminalquepertenece, ArrayList<String> funcionario) {
        if (validarAñadirFuncionarioTerminal(nombrefuncionario, empresaquepertenece, nombreusuario, terminalquepertenece, funcionario)) {
            funcionario.add("Funcionario: " + nombrefuncionario + "\t Empresa: " + empresaquepertenece + "\n Nombre de usuario: " + nombreusuario + " \t contraseÃ±a: *********"
                    + "\n Terminal al que pertenece: " + terminalquepertenece);// agrega los datos de los funcionario en una linea  
        } else {
            System.out.println("Existe funcionario");
        }
    }
    /* El metodo ValidarAñadirFuncionarioTerminal los datos de los funcionarios los cuales contatenan en un String y tambien resive un ArrayList funcionario
     guarda los String de los funcionarios y este metodo busca que no contenga dos veces el mismo String funcionario*/

    public static boolean validarAñadirFuncionarioTerminal(String nombrefun, String empresa, String nombreusu, String terminal, ArrayList<String> funcionario) {
        String funcionario1 = "Funcionario: " + nombrefun + "\t Empresa: " + empresa + "\n Nombre de usuario: " + nombreusu + " \t contraseÃ±a: *********"
                + "\n Terminal al que pertenece: " + terminal;
        for (String l : funcionario) {
            if (l.contains(funcionario1)) {
                return false;
            }
        }
        return true;
    }
    
    public static void eliminarFuncionario(String nombrefuncionario,String empresaquepertenece,String terminalquepertenece,ArrayList<String> funcionarios){
        
    }
    
    public static void modificarFuncionario(String nombrefuncionario,String empresaquepertenece,String terminalquepertenece,ArrayList<String> funcionarios){
        
    }
    
    public static void mostrarFuncionarios(ArrayList<String> funcionarios){
        System.out.println("Funcionarios: ");
        for(int i=0;i<funcionarios.size();i++){
            System.out.println(funcionarios.get(i));
        }
    }

    public static void menuItinerario(ArrayList<String> itinerario, ArrayList<String> funcionarios) {
        Scanner leer = new Scanner(System.in);
        String opc="", op, estadoBus, destino, empresa, horaSalidaBus, horaLlegadaBus, fecha;
        do {
            System.out.println("Usted debe comprobar su identidad: ");
            System.out.println("0 - Salir");
            System.out.println("1 - Ingresar Usuario y Contraseña");
            do {
                System.out.println("Ingrese Opcion: ");
                op = leer.next();
            } while (!"0".equals(op) && !"1".equals(op));//mientras se cumpla que no es ni uno ni otro, en caso contrario cambia hacia el switch
            switch (op) {
                case "0":
                    opc="f";//con esto designa opc como algo para usar en el validador if de mas abajo,para ver si avanza al itinerario o no
                    break;
                case "1":
                    System.out.println("Ingrese Usuario: ");
                    String usuario = leer.next();
                    System.out.println("Ingrese Contraseña: ");
                    String contraseña = leer.next();
                    if (validarUsuarioContraseña(usuario, contraseña, funcionarios)) {// comprueba que lo ingresado sea valido en caso de ser verdadero
                        op="0";
                    }else{
                        System.out.println("Usuario y/o Contraseña no valida");
                    }
            }
        } while (!"0".equals(op));// hasta que no se ingrese un 0 no se saldra del validador y por ende del menu validador usuario
        if (!"f".equals(opc)) {//si el validador de usuario de arriba no ha asignado el valor "f" a opc , hay luz verde para avanzar al menu itinerario, caso contrario vuelve al menu principal
            do {
                System.out.println(" 1 - Añadir Itinerario" + "\n 2 - Eliminar Itinerario" + "\n 3 - Modificar Itinerario" + "\n 0 - salir");
                do {
                    System.out.println("Ingrese opcion");
                    opc = leer.next();
                } while (!"1".equals(opc) && !"2".equals(opc) && !"3".equals(opc) && !"0".equals(opc));
                switch (opc) {
                    case "1":

                        do {
                            System.out.println("Ingrese Fecha: (ej: 12/1)");
                            fecha = leer.next();
                        } while (!ValidarFecha(fecha));//el ciclo seguira mientras el validador indique que no se cumple con el formato de fecha
                        do {
                            System.out.println("Ingrese Hora LLegada del Bus: (ej: hh:mm) ");
                            horaLlegadaBus = leer.next();
                        } while (!ValidarHora(horaLlegadaBus));//el ciclo seguira mientras el formato de hora no se cumpla
                        do {
                            System.out.println("Ingrese Hora Salida del Bus: (ej: hh:mm)");
                            horaSalidaBus = leer.next();
                        } while (!ValidarHora(horaSalidaBus));
                        do {
                            System.out.println("Ingrese empresa que pertenece el bus");
                            empresa = leer.nextLine();
                        } while (!ValidarNombres(empresa));
                        do {
                            System.out.println("Ingrese destino de bus");
                            destino = leer.nextLine();
                        } while (!ValidarNombres(destino));
                        do {
                            System.out.println("Ingrese estado de bus:(ej: atrasado, llego, etc.)");
                            estadoBus = leer.nextLine();
                        } while (!ValidarNombres(estadoBus));
                        añadirItinerarioBus(estadoBus, destino, empresa, horaSalidaBus, horaLlegadaBus, fecha, itinerario);
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "0":
                        break;
                }
            } while (!"0".equals(opc));
        }
    }

    public static boolean validarUsuarioContraseña(String usuario, String contraseña, ArrayList<String> funcionario) {
        String val = usuario, val2 = contraseña;

        for (String l : funcionario) {
            if (l.contains(val) && l.contains(val2)) {
                return true;
            }
        }
        return false;
    }

    public static void añadirItinerarioBus(String estadoBus, String destino, String empresa, String horaSalidaBus, String horaLlegadaBus, String fecha, ArrayList<String> itinerario) {

        if (validarAñadirItinerarioBus(estadoBus, destino, empresa, horaSalidaBus, horaLlegadaBus, fecha, itinerario)) {
            String hola = "Fecha: " + fecha + "Hora de Llegada: " + horaLlegadaBus + " Hora de salida: " + horaSalidaBus
                    + " Empresa: " + empresa + " Destino: " + destino + " estado de Bus: " + estadoBus;
            itinerario.add(hola);
        } else {
            System.out.println("Itinerario de Bus existente");
        }

    }

    public static boolean validarAñadirItinerarioBus(String estadoBus, String destino, String empresa, String horaSalidaBus, String horaLlegadaBus, String fecha, ArrayList<String> itinerario) {
        String hola = "Fecha: " + fecha + "Hora de Llegada: " + horaLlegadaBus + " Hora de salida: " + horaSalidaBus
                + " Empresa: " + empresa + " Destino: " + destino + " estado de Bus: " + estadoBus;
        for (String l : itinerario) {
            if (l.contains(hola)) {
                return false;
            }
        }
        return true;
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
