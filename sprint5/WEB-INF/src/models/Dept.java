package models;

import etu1896.annotation.Anno_Url;

public class Dept {
    @Anno_Url(url = "/Dept/find")
    public String findall() {
        return "/dept-findall.jsp";
    }

    public void insert() {

    }
}
