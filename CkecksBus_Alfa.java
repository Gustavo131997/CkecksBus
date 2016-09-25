/*
 Este programa va a estar orientado a los funcionarios de un terminal por ahora.
 */
package ckecksbus_alfa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;

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
       System.out.println("Nombre de Terminal: "+ nombreterminal+" Region: "+region+" Direccion: "+direccion);
        System.out.println("Funcionarios del Terminal");
        for (Iterator i = funcionarios.iterator(); i.hasNext();) {
            System.out.println(i.next());
        }
        System.out.println("Itinerario del Terminal;");
        for (Iterator i = itinerario.iterator(); i.hasNext();) {
            System.out.println(i.next());
        }

    }

    public static void menuFuncionarios(ArrayList<String> funcionarios) {
        Scanner leer = new Scanner(System.in);
        String nombrefuncionario, empresaquepertenece, nombreusuario, contraseña, terminalquepertenece, opc = "";
        do {
            System.out.println(" 1 - Añadir funcionario" + "\n 2 - Eliminar funcinario" + "\n 3 - Modificar funcionario" + "\n 4 - Mostrar funcionario" + "\n 0 - salir");
            do {
                System.out.println("Ingrese opcion");
                opc = leer.next();
            } while (!"1".equals(opc) && !"2".equals(opc) && !"3".equals(opc) && !"4".equals(opc) && !"0".equals(opc));
            switch (opc) {
                case "1":
                    String holanosirveparanada1 = leer.nextLine(); // Esto es para que no salte el leer contraseña
                    do {
                        System.out.println("Nombre Funcionario: ");
                        nombrefuncionario = leer.nextLine();
                    } while (!ValidarNombres(nombrefuncionario));//el ciclo seguira mientras el validador diga que el patron de escritura del nombre es erroneo
                    do {
                        System.out.println("Empresa: ");
                        empresaquepertenece = leer.nextLine();
                    } while (!ValidarNombres(empresaquepertenece));//el ciclo seguira mientras el validador diga que el patron de escritura es erroneo
                    do {
                        System.out.println("Nombre Usuario: (8 digitos minimo) ");
                        nombreusuario = leer.next();
                    } while (nombreusuario.replaceAll(" ", "").length() < 8);//el ciclo seguira mientras el usuario tenga menos de 8 digitos descontando los espacios
                    String holanosirveparanada = leer.nextLine(); // Esto es para que no salte el leer contraseña
                    do {
                        System.out.println("Contraseña:(8 digitos minimo) ");
                        contraseña = leer.nextLine();
                    } while (contraseña.replaceAll(" ", "").length() < 8);// el ciclo seguira mientras la contraseÃ±a tenga menos de 8 dijitos descontando los espacios
                    añadirFuncionarioTerminal(nombrefuncionario, empresaquepertenece, nombreusuario, funcionarios);

                    break;
                case "2": String holanosirveparanada2 = leer.nextLine(); // Esto es para que no salte el leer contraseña
                    System.out.println("Ingrese Nombre de funcionario");
                    nombrefuncionario = leer.nextLine();
                    System.out.println("Ingrese empresa que pertenece");
                    empresaquepertenece = leer.nextLine();
                    System.out.println("Ingrese nombre de usuario");
                    nombreusuario = leer.next();
                    System.out.println("Contraseña:");
                    contraseña = leer.next();
                    eliminarFuncionario(nombrefuncionario, empresaquepertenece, nombreusuario, funcionarios);
                    break;
                case "3":System.out.println("Ingrese nombre de funcionario");
                    nombrefuncionario = leer.nextLine();
                    System.out.println("Ingrese Empresa que pertenece");
                    empresaquepertenece = leer.nextLine();
                    System.out.println("Ingrese Nombre de Usuario");
                    nombreusuario = leer.nextLine();
                    System.out.println("Ingrese Contraseña de Usuario");
                    contraseña = leer.nextLine();
                    modificarFuncionario(nombrefuncionario, empresaquepertenece,nombreusuario, funcionarios);
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

    public static void añadirFuncionarioTerminal(String nombrefuncionario, String empresaquepertenece, String nombreusuario, ArrayList<String> funcionario) {
        if (validarExistenciaFuncionarioTerminal(nombrefuncionario, empresaquepertenece, nombreusuario, funcionario)== null) {
            funcionario.add("Funcionario: " + nombrefuncionario + "\t Empresa: " + empresaquepertenece + "\n Nombre de usuario: " + nombreusuario + " \t contraseña: *********");// agrega los datos de los funcionario en una linea  
        } else {
            System.out.println("Existe funcionario");
        }
    }
    
    public static void eliminarFuncionario(String nombrefuncionario,String empresa,String nombreusu, ArrayList<String> funcionarios){
        if (validarExistenciaFuncionarioTerminal(nombrefuncionario, empresa, nombreusu, funcionarios) != null) {
            funcionarios.remove(validarExistenciaFuncionarioTerminal(nombrefuncionario, empresa, nombreusu, funcionarios));
        }else{ System.out.println("El funcionario a eliminar no existe");}
    }
    /*Esta que entrega el String que coideciden con los datos que se piden,  dado que los concatenan en un solo String y lo busca en el ArrayList funcionario que tiene
    que tiene misma secuencia de caracteres */
    public static String validarExistenciaFuncionarioTerminal(String nombrefuncionario,String empresa,String nombreusu, ArrayList<String> funcionarios){
    String funcionario1 = "Funcionario: " + nombrefuncionario + "\t Empresa: " + empresa + "\n Nombre de usuario: " + nombreusu + " \t contraseña: *********";
        for (String l : funcionarios) {
            if (l.contains(funcionario1)) {
                return l;
            }
        }
        return null;
    }
    public static void menuModificar(){
        System.out.println(" 1 - modificar nombre"+"\t 2 - modificar empresa");
    }
    public static void modificarFuncionario(String nombrefuncionario,String empresaquepertenece,String terminalquepertenece,ArrayList<String> funcionarios){
        
    }
    
    public static void mostrarFuncionarios(ArrayList<String> funcionarios){
        System.out.println("Funcionarios");
         for (Iterator i = funcionarios.iterator(); i.hasNext();) {
            System.out.println(i.next());
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
                    String contraseña = leer.next(); //La contraseña es una mera formalidad para este programa
                    if (validarUsuario(usuario, funcionarios)) {// comprueba que lo ingresado sea valido en caso de ser verdadero
                        op="0";
                    }else{
                        System.out.println("Usuario y/o Contraseña no valida");
                    }
                break;
                default: System.out.println("Opcion no valida");
                break;
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
                            System.out.println("Ingrese Fecha: (ej: dd/mm/yyyy)");
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
                        String holanosirveparanada3 = leer.nextLine(); // Esto es para que no salte el leer contraseña
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

    public static boolean validarUsuario(String usuario, ArrayList<String> funcionario) {
        String val = usuario;

        for (String l : funcionario) {
            if (l.contains(val)) {
                return true;
            }
        }
        return false;
    }
  
    public static void añadirItinerarioBus(String estadoBus, String destino, String empresa, String horaSalidaBus, String horaLlegadaBus, String fecha, ArrayList<String> itinerario) {

        if (validarAñadirItinerarioBus(estadoBus, destino, empresa, horaSalidaBus, horaLlegadaBus, fecha, itinerario)) {
            String hola = "Fecha: " + fecha + "\n Hora de Llegada: " + horaLlegadaBus + "\t Hora de salida: " + horaSalidaBus
                    + "\n Empresa: " + empresa + "\t Destino: " + destino + " estado de Bus: " + estadoBus;
            itinerario.add(hola);
        } else {
            System.out.println("Itinerario de Bus existente");
        }

    }
/*El validarAñadirItinerarioBus recive los datos de un itinerario y el ArrayList de String funcionario y genera un String (que en este caso le pusimos iti) que
  contiene la misma secuencia caracteres entre comillas con el que esta en el metodo añadirItinerarioBus para que si el ArrayList contiene algo igual retorne false (Para el metodo que añade diga que existe funcionario) y sino retorne true*/  
    public static boolean validarAñadirItinerarioBus(String estadoBus, String destino, String empresa, String horaSalidaBus, String horaLlegadaBus, String fecha, ArrayList<String> itinerario) {
        String iti = "Fecha: " + fecha + "\n Hora de Llegada: " + horaLlegadaBus + "\t Hora de salida: " + horaSalidaBus
                + "\n Empresa: " + empresa + "\t Destino: " + destino + " estado de Bus: " + estadoBus;
        for (String l : itinerario) {
            if (l.contains(iti)) {
                return false;
            }
        }
        return true;
    }

    public static boolean ValidarNombres(String nombre) {
        Pattern pat = Pattern.compile("[A-Za-z]+( [A-Za-z]+)*");
        Matcher mac = pat.matcher(nombre);
        return (mac.matches());
    }

    public static boolean ValidarFecha(String fecha) {
        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        Date testDate = null;
        String date1 = fecha;
        try{
            testDate = df1.parse(date1);
        } catch (Exception e){ System.out.println("Formato invalido");}
        return df1.format(testDate).equals(date1);
    }

    public static boolean ValidarHora(String hora) {
        Pattern pat = Pattern.compile("[0-2]*[0-9]{1}:[0-5]{1}[0-9]{1}");
        Matcher mac = pat.matcher(hora);
        return (mac.matches());
    }
}
