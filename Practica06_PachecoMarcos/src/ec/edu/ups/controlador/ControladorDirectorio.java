/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author xpacheco
 */
public class ControladorDirectorio {

    //Atributos
    private String ruta;
    private File archivo;
    private File[] archivos;
    
    
    //Controlador Directorio
    public ControladorDirectorio(String ruta) {
        this.ruta = ruta;
    }
    
     
    //metodo para listar los archivos existentes
    public List<String> listarArchivos(String ruta) {
        List<String> listarArchivos = new ArrayList<>();
        listarArchivos.clear();
        archivo = new File(ruta);
        archivos = archivo.listFiles();
        for (File directorio : archivos) {
            if (!directorio.isHidden()) {
                listarArchivos.add(directorio.getName());
            }
        }
        return listarArchivos;
    }
    
    
    //metodo para listar directorios existentes
    public List<String> listarDirectorios(String ruta) {
        List<String> listarDirectorio = new ArrayList<>();
        listarDirectorio.clear();
        archivo = new File(ruta);
        archivos = archivo.listFiles();
        for (File directorio : archivos) {
            if (!directorio.isHidden()) {
                listarDirectorio.add(directorio.getName());
            }
        }
        return listarDirectorio;
    }
    
    
    //metodo para listar los archivos ocultos
    public List<String> listarArchivosOcultos(String ruta) {
        List<String> listarDirectorioOculto = new ArrayList<>();
        listarDirectorioOculto.clear();
        archivo = new File(ruta);
        archivos = archivo.listFiles();
        for (File directorioOculto : archivos) {
            if (directorioOculto.isHidden() && directorioOculto.isFile()) {
                listarDirectorioOculto.add(directorioOculto.getName());
            }
        }
        return listarDirectorioOculto;
    }
    
    //metodo para listar los directorios ocultos
    public List<String> listarDirectoriosOcultos(String ruta) {
        List<String> listarDirectorioOculto = new ArrayList<>();
        listarDirectorioOculto.clear();
        archivo = new File(ruta);
        archivos = archivo.listFiles();
        for (File directorioOculto : archivos) {
            if (directorioOculto.isHidden() && directorioOculto.isDirectory()) {
                listarDirectorioOculto.add(directorioOculto.getName());
            }
        }
        return listarDirectorioOculto;
    }

    
    // metodo para crear directorio
    public void crearDirectorio(String ruta, String nombre) {
        archivo = new File(ruta + File.separator + nombre);
        archivo.mkdir();
    }
    

     //metodo para eliminar directorio
    public void eliminarDirectorio(String nombre, String ruta){
        archivos = archivo.listFiles();
        //String rutaArchivo = ruta + File.separator + nombre;
        for (File archivoEliminar : archivos) {
            if (archivoEliminar.getName().equals(nombre)) {
                archivoEliminar.delete();
            }
        }
    }
    
    
    //metodo renombrar los directorios
    public void renombrar(String actual, String nuevo){
        archivos = archivo.listFiles();
        File archivoNuevo = new File(ruta + File.separator + nuevo);
        for (File actualArchivo : archivos) {
            if(actualArchivo.getName().equals(actual)){
                actualArchivo.renameTo(archivoNuevo);
            } 
        }
    }
    
    
    //metodo para mostrar la informacion 
    public String mostrarInformacion(String nombre, String ruta) {
        archivos = archivo.listFiles();
        String informacionDirectorio = "Informacion del archivo\n";
        for (File inf : archivos) {
            if (inf.getName().equals(nombre)) {
                String path = "Path absoluto: " + inf.getAbsolutePath();
                informacionDirectorio = informacionDirectorio.concat(path) + "\n";
                long tamaño = + inf.length();
                String tamañoArchivo = "Tamaño del archivo: " + String.valueOf(tamaño) + " bytes\n";
                informacionDirectorio = informacionDirectorio.concat(tamañoArchivo);
                String sPermisoRead = "Permisos de lectura: " + "Tiene acceso de lectura\n";
                String nPermisoRead = "Permisos de lectura: " + "No tiene acceso de lectura\n";
                if (inf.canRead()) {
                    informacionDirectorio = informacionDirectorio.concat(sPermisoRead);
                } else {
                    informacionDirectorio = informacionDirectorio.concat(nPermisoRead);
                }
                
                String sPermisoWrite = "Permisos de escritura: " + "Tiene acceso de escritura\n";
                String nPermisoWrite = "Permisos de escritura: " + "No tiene acceso de escritura\n";
                if (inf.canWrite()) {
                    informacionDirectorio = informacionDirectorio.concat(sPermisoWrite);
                } else {
                    informacionDirectorio = informacionDirectorio.concat(nPermisoWrite);
                }
                long fechaModificacion = inf.lastModified();
                String formato = "dd - mm - yyyy  HH:mm aa";
                SimpleDateFormat sdf = new SimpleDateFormat(formato);
                Date ultimaModificacion = new Date(fechaModificacion);
                
                String fecha = "Fecha de ultima modificacion: " + sdf.format(ultimaModificacion);
                informacionDirectorio = informacionDirectorio.concat(fecha);
            }

        }
        return informacionDirectorio;
    }
    
    

}
