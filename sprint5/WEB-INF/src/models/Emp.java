package models;

import etu1896.annotation.Anno_Url;

public class Emp {
    
    @Anno_Url(url = "/Emp/findall")
    public String findall(){
        return "/emp-findall.jsp";
    }

    @Anno_Url(url = "/Emp/insert")
    public String insert(){
        return "/emp-insert.jsp";
    }

    public void test(){
        
    }
}