/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Batman
 */

public class Archivo {
    private String ruta;
    private FileWriter insertarFichero;
    private PrintWriter pw;
    private FileReader rd;
    private File fichero;

    public Archivo(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta(){
        return this.ruta;
    }

    public void setRuta(String newRuta){
        this.ruta=newRuta;
    }
    
    
    /**
     * Permite ingresar un dato en una linea de un fichero txt
     * @param datos es el "dato" que se desea agregar al fichero
     */

    public void insertar(String datos){
                
        fichero = new File(this.ruta);
        try{
            insertarFichero = new FileWriter(fichero.getAbsoluteFile().toString(), true);
            pw = new PrintWriter(insertarFichero);

            pw.println(datos);//imprime  los "datos" en el fichero con un salto de linea
            System.out.println("correcto");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(null!=insertarFichero){
                    insertarFichero.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
    }
    
    
    /**
     * Metodo que guarda todos los datos del archivo .txt en un ArrayList
     * @return Retorna un ArrayList con los datos del Archivo txt
     * @throws IOException 
     */
    
    public ArrayList <String> getLineasFichero() throws IOException{
        ArrayList <String> lineas= new ArrayList();
        int i=0;
        String aux1;

        try {
            rd = new FileReader(this.ruta);
            BufferedReader leer = new BufferedReader(rd);            
            try{
                while(!(aux1=leer.readLine()).contains("null")){
                    lineas.add(aux1);
                }                
            }catch(Exception e){}                       
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        rd.close();
        return lineas;
    }
    
    
    /**
     * Permite eliminar los datos deseados de un fichero.
     * Primero guarda todos los registros del fichero en un ArrayList, luego elimina el fichero y remueve el dato que se desea
     * eliminar. Finalmente vuelve a generar el fichero e ingresa todos los datos guardados en el ArrayList
     * @param datos es el registro que queremos eliminar del fichero
     * @throws IOException 
     */
    
    public void eliminarDatos(String datos) throws IOException{        
        int i=0;
        ArrayList <String> lineas=getLineasFichero();//guardamos los datos en un ArrayList lineas
        if (lineas.contains(datos)) {//si el ArrayList contiene los datos se remueve de este
            lineas.remove(datos);            
            eliminarArchivo(); //eliminar el archivo          
            for (int j = 0; j < lineas.size(); j++) {
                insertar(lineas.get(j));//se vuelven a ingresar los datos en el archivo vacio
            }
        }                          
        
    }
    
    
    
    /**
     * Elimina un archivo 
     */
    public void eliminarArchivo(){         
        fichero = new File(this.ruta);
        if (fichero.delete()) {
            System.out.println("Eliminado");
        }else{
            System.out.println("NO SE HA eliminado");
        }        
    }
}

