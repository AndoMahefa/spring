package models;

import java.util.ArrayList;

import etu1896.annotation.Anno_Url;
import etu1896.framework.ModelView;

public class Emp {
    String nom;
    String prenom;
    Integer age;
    Double salaire;

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Anno_Url(url = "/Emp/findall")
    public ModelView findall() {
        ModelView view  = new ModelView();
        view.setView("/emp-findall.jsp");
        ArrayList<String> employees = new ArrayList<String>();
        employees.add("Ando");
        employees.add("Deba");
        employees.add("Andy");
        view.addItem("list-emp", employees);
        return view;
    }

    @Anno_Url(url = "/Emp/insert")
    public ModelView insert(){
        ModelView view = new ModelView("/emp-insert.jsp");
        return view;
    }

    public void test(){
        
    }

    @Anno_Url(url = "/Emp/save")
    public void save() {

    }
}