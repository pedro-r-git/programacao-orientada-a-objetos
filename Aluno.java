package com.example.sistema;
public class Aluno extends Pessoa{
    private String curso;
    public Aluno(){super();}
    public Aluno(int id,String nome,int idade,String curso){super(id,nome,idade);this.curso=curso;}
    public Aluno(String nome,int idade,String curso){super(nome,idade);this.curso=curso;}
    public String getCurso(){return curso;} public void setCurso(String curso){this.curso=curso;}
    public String getTipo(){return "Aluno";}
    public String getDescricao(){return "curso="+curso;}
}
