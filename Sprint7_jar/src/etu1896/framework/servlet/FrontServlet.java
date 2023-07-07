package etu1896.framework.servlet;

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;
import java.util.*;
import etu1896.framework.*;

public class FrontServlet extends HttpServlet {
    HashMap<String,Mapping> mapping_url = null;

    @Override
    public void init() throws ServletException {
        super.init();
         try {
            ServletContext ctx = getServletContext() ;
            String path =  ctx.getRealPath("/WEB-INF/classes/models");    
            this.mapping_url = Utilitaire.getHashMap(path);            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void processRequest (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        PrintWriter out = res.getWriter();
        out.println("Url: " + req.getRequestURL());
        out.println("Contexte: " + req.getContextPath());


        Utilitaire.afficher_MappingUrls(this.mapping_url,out);
    
        String url = String.valueOf(req.getRequestURL());
        String apres_contexte = Utilitaire.getUrl(url);
        out.println("url ou valeur annotation: " + apres_contexte);
 
        try {
            if(apres_contexte != null) {
                for (String key: mapping_url.keySet()) {
                    if(apres_contexte.equals(key)) {
                        Class<?> clazz = Class.forName(mapping_url.get(key).getClassName());
                        Object ob = clazz.getDeclaredConstructor().newInstance();
                        if(ob.getClass().getDeclaredMethod(mapping_url.get(key).getMethod()).getReturnType().getSimpleName().equals("void")) {
                            String[] fields = new Utilitaire().getAttribute(ob);
                            for (int i = 0; i < fields.length; i++) {
                                String parametre = req.getParameter(fields[i]);
                                if(!parametre.equals(null)) {
                                    String getSetters = new Utilitaire().getSetters(fields[i]);
                                    String setter = new Utilitaire().setFields(ob, getSetters, parametre, fields[i]);
                                    out.println(getSetters);
                                    String getGetters = new Utilitaire().getGetters(fields[i]);
                                    Object getters = new Utilitaire().getFields(ob, getGetters);
                                    out.println(getGetters);
                                    out.println(getters);
                                }
                            }
                        } else if(ob.getClass().getDeclaredMethod(mapping_url.get(key).getMethod()).getReturnType().getSimpleName().equals("ModelView")) {
                            ModelView view = ModelView.loadView(apres_contexte, this.mapping_url);
                            if(view != null) {
                                ArrayList<String> keys =  new ArrayList<String>(view.getData().keySet());
                                out.println(keys.size());
                                for(int i=0; i<keys.size(); i++) {
                                    out.println(keys.size());
                                    out.println("key="+keys.get(i));
                                    out.println(view.getData().get(keys.get(i)));
                                    req.setAttribute(keys.get(i), view.getData().get(keys.get(i)));
                                }
                            RequestDispatcher dispatcher = req.getRequestDispatcher(view.getView());  
                            dispatcher.forward(req, res);
                            }
                        }
                    }        
                }
            }                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        processRequest(req, res);
    }

    protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        processRequest(req, res);
    }
}