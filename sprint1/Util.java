package etu1896.framework;
import javax.servlet.*; 
import javax.servlet.http.*;
public class Util {
    
    public Util() {
    }
    public String Pathing(HttpServletRequest req, HttpServletResponse res){
        return req.getServletPath() ;
    }   
}
