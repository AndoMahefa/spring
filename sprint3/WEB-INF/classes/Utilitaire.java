package etu1896.framework;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.lang.annotation.Annotation;
import etu1896.annotation.*;
import etu1896.framework.*;

public class Utilitaire{
        
    public Utilitaire() {
    }

    public String Pathing(HttpServletRequest req, HttpServletResponse res){
        return req.getServletPath();
    }

    public String getPath(){
        String path = "";
        File file = new File(new Utilitaire().getClass().getSimpleName()+".java");
        String[] chemins = file.getAbsolutePath().replace("\\", "/").split("/");
        for(int i = 0; i<chemins.length - 2; i++) {
            path += chemins[i] + "/";
        }
        path += chemins[chemins.length - 2];
        return path;
    }

    public static ArrayList<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file,packageName + "." + file.getName()));
            } 
            else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName+ '.'+ file.getName().substring(0,file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public static HashMap<String,Mapping> getHashMap(String path) throws ClassNotFoundException{
        HashMap<String,Mapping> mappingUrls = new HashMap<String,Mapping>();
        // String path = new Utilitaire().getPath();
        // D:/Logiciel/Tomcat/webapps/framework-spring/WEB-INF/classes
        ArrayList<Class<?>> classes = Utilitaire.findClasses(new File(path), "models");
        ArrayList<Method> methods = null;
        Url anno = null;
        for(int i=0; i<classes.size(); i++) {
            methods = new ArrayList<Method>(List.of(classes.get(i).getDeclaredMethods()));  
            for(int j=0; j<methods.size(); j++) {
                if(methods.get(j).isAnnotationPresent(Url.class)) {
                    anno = methods.get(j).getAnnotation(Url.class);
                    mappingUrls.put(anno.url(),new Mapping(classes.get(i).getName(),methods.get(j).getName()));
                }
            }
        }
        return mappingUrls;
    }

    public static void afficher_MappingUrls(HashMap<String,Mapping> mappingUrls,PrintWriter out){
        ArrayList<String> keys =  new ArrayList<String>(mappingUrls.keySet());
        out.println("MappingUrls:");
        for(int i=0;i<keys.size();i++) {
            out.println("key:"+keys.get(i)+" , "+"(classname = "+mappingUrls.get(keys.get(i)).getClassName()+" "+"; method = "+mappingUrls.get(keys.get(i)).getMethod()+")");
        }
    }
}