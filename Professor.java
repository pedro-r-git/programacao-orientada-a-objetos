package com.example.sistema;
public class Professor extends Pessoa{
    private String materia;
    public Professor(){super();}
    public Professor(int id,String nome,int idade,String materia){super(id,nome,idade);this.materia=materia;}
    public Professor(String nome,int idade,String materia){super(nome,idade);this.materia=materia;}
    public String getMateria(){return materia;} public void setMateria(String m){this.materia=m;}
    public String getTipo(){return "Professor";}
    public String getDescricao(){return "matéria="+materia;}
}
