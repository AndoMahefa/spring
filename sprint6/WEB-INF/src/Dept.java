package models;

import etu1896.annotation.Anno_Url;
import etu1896.framework.ModelView;

public class Dept {
    @Anno_Url(url = "/Dept/find")
    public ModelView findall() {
        ModelView view  =  new ModelView("dept-findall.jsp");
        return view;
    }

    public void insert() {

    }
}
