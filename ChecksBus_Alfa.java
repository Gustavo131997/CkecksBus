/*
 Este programa va a estar orientado a los funcionarios de un terminal por ahora.
 */
package ckecksbus_alfa;

import java.util.ArrayList;
import java.util.Iterator;
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
        String nombreterminal= leer.nextLine();
        System.out.println("Ingrese Region pertenece");
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
            } while (!"1".equals(opcion) && !"2".equals(opcion) && !"0".equals(opcion));
            switch (opcion) {
                case "1":MenuFuncionarios(funcionarios);
                    break;
                case "2":MenuItinerario(itinerario);
                case "3": MostrarTerminal(nombreterminal, region, direccion, funcionarios, itinerario); 
                    break;
                case "0": System.out.println("Adios");
                    break;
                default: System.out.println("Opcion invalida"); break;
            }
            
        } while (!opcion.equals("0"));
    }
    /* el metodo MenuFuncionarios recibe unicamente ArrayList funcionarios para que los guarde en el metodo menu */
    public static void MenuFuncionarios(ArrayList<String> funcionarios){
        Scanner leer = new Scanner(System.in);
        String nombrefuncionario, empresaquepertenece, nombreusuario, contraseña, terminalquepertenece, opc="";
        System.out.println(" 1 - Añadir funcionario"+"\n 2 - Eliminar funcinario"+"\n 3 - Modificar funcionario"+"\n 4 - Mostrar funcionario"+"\n 0 - salir");
        
        switch(opc){
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
                        contraseña = leer.nextLine(); //la contraseña va hacer mera formalidad del programa pero no se va usar como tal
                    } while (contraseña.replaceAll(" ", "").length() < 8);// el ciclo seguira mientras la contraseña tenga menos de 8 dijitos descontando los espacios
                    añadirFuncionarioTerminal(nombrefuncionario, empresaquepertenece, nombreusuario, funcionarios);
            break;
            case "2": EliminarFuncionario(nombrefuncionario,empresa,nombredeusuario,funcionarios); //Este es un metodo que elimina
            break;
            case "3": MenuModificar(); //Este menu para modificar el parametro que desee
            break;
            case "4": MostrarFuncionarios(funcionarios); 
            break;
            case "0": System.out.println("Ok");
            break;
            default: System.out.println("Opcion invalida");
            break;
          }
    }
    public static void añadirFuncionarioTerminal(String nombrefuncionario, String empresaquepertenece, String nombreusuario,  ArrayList<String> funcionario  ) {
        if (ValidarAñadirFuncionarioTerminal(nombrefuncionario, empresaquepertenece, nombreusuario, funcionario )) {
             funcionario.add("Funcionario: "+nombrefuncionario+"\t Empresa: "+empresaquepertenece+"\n Nombre de usuario: "+nombreusuario+" \t contraseña: *********");// agrega los datos de los funcionario en una linea  
        }else{ System.out.println("Existe funcionario");}
    }
    /* El metodo ValidarAñadirFuncionarioTerminal los datos de los funcionarios los cuales contatenan en un String y tambien resive un ArrayList funcionario
     guarda los String de los funcionarios y este metodo busca que no contenga dos veces el mismo String funcionario*/
    public static boolean ValidarAñadirFuncionarioTerminal(String nombrefun, String empresa, String nombreusu, ArrayList<String> funcionario ){
        String funcionario1 = "Funcionario: "+nombrefun+"\t Empresa: "+empresa+"\n Nombre de usuario: "+nombreusu+" \t contraseña: *********";
        return funcionario.stream().noneMatch((l) -> (l.contains(funcionario1)));   
    }
   public static void MenuItinerario(ArrayList<String> itinerario){
            Scanner leer = new Scanner(System.in);
         String estadoBus, destino, empresa, horaSalidaBus, horaLlegadaBus, fecha;
         System.out.println(" 1 - Añadir Itinerario"+"\n 2 - Eliminar Itinerario"+"\n 3 - Modificar Itinerario"+"\n 0 - salir");
         String opc = leer.nextLine();
       switch(opc){
           case "1":
                    do {
                        System.out.println("Ingrese Fecha: (ej: DD/MM)");
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
                        empresa =leer.nextLine();   
                    } while (!ValidarNombres(empresa));
                    do {
                        System.out.println("Ingrese destino de bus");
                        destino = leer.nextLine();
                    } while (!ValidarNombres(destino));
                    do {
                        System.out.println("Ingrese estado de bus:(ej: atrasado, llego, etc.)");
                        estadoBus = leer.nextLine();    
                    } while (!ValidarNombres(estadoBus));
            break;
           case "2":
        }
   }
    public static void añadirItinerarioBus(String estadoBus,String destino, String empresa, String horaSalidaBus, String horaLlegadaBus, String fecha, ArrayList<String> itinerario) {
        
        if (ValidarAñadirItinerarioBus(fecha, horaLlegadaBus, horaSalidaBus, empresa, destino, estadoBus )) {
             itinerario.add("Fecha: "+fecha+"\t Hora de llegada de bus: "+horaLlegadaBus+"\t Hora de salida de bus: "+horaSalidaBus+"\t Empresa: "+empresa+"\t Destino: "+destino+"\t Estado de Bus: "+estadoBus);// agrega los datos de los funcionario en una linea  
        }else{ System.out.println("Existe itinerario");}
        
    }
     private static boolean ValidarAñadirItinerarioBus(String fecha, String horaLlegadaBus, String horaSalidaBus, String empresa, String destino, String estadoBus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    private static void MostrarFuncionarios(ArrayList<String> funcionarios) {
        for (Iterator i = funcionarios.iterator(); i.hasNext();) {
            System.out.println(i.next());
        }
    }

    private static void MostrarTerminal(String nombreterminal, String region, String direccion, ArrayList<String> funcionarios, ArrayList<String> itinerario) {
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

    private static void MenuModificar() { //Dentro de este menu de va a pedir todos los datos que pertenezcan a funcionario que quieran modificar y despues las opciones de lo que quiere modificar
        
    }

   
    
}
