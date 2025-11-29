package com.example.sistema;
import java.sql.*; import java.util.*;
public class Sistema {
    private static final String DB_URL="jdbc:sqlite:sistema.db";
    public static void main(String[]a){Sistema s=new Sistema();s.initDatabase();s.runMenu();}
    private void initDatabase(){
        String sql="""CREATE TABLE IF NOT EXISTS pessoas(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            tipo TEXT, nome TEXT, idade INTEGER, info TEXT);""";
        try(Connection c=DriverManager.getConnection(DB_URL);Statement st=c.createStatement()){st.execute(sql);}catch(Exception e){e.printStackTrace();}
    }
    private void runMenu(){
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("1-Cadastrar
2-Consultar
3-Sair");
            String op=sc.nextLine();
            switch(op){
                case "1"->cadastrar(sc);
                case "2"->consultar(sc);
                case "3"->{return;}
            }
        }
    }
    private void cadastrar(Scanner sc){
        System.out.print("Tipo (1-Aluno,2-Professor): ");String t=sc.nextLine();
        System.out.print("Nome: ");String nome=sc.nextLine();
        System.out.print("Idade: ");int idade=Integer.parseInt(sc.nextLine());
        if(t.equals("1")){
            System.out.print("Curso: ");String curso=sc.nextLine();
            inserirPessoa(new Aluno(nome,idade,curso));
        } else {
            System.out.print("Matéria: ");String mat=sc.nextLine();
            inserirPessoa(new Professor(nome,idade,mat));
        }
    }
    private void inserirPessoa(Pessoa p){
        String sql="INSERT INTO pessoas(tipo,nome,idade,info) VALUES(?,?,?,?)";
        try(Connection c=DriverManager.getConnection(DB_URL);PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,p.getTipo());
            ps.setString(2,p.getNome());
            ps.setInt(3,p.getIdade());
            ps.setString(4,p.getDescricao().split("=",2)[1]);
            ps.executeUpdate();
        }catch(Exception e){e.printStackTrace();}
    }
    private void consultar(Scanner sc){
        System.out.println("1-Todos
2-Por tipo
3-Por id");
        String op=sc.nextLine();
        switch(op){
            case "1"->listar("SELECT * FROM pessoas");
            case "2"->{
                System.out.print("Tipo: ");String t=sc.nextLine();
                listar("SELECT * FROM pessoas WHERE tipo='"+t+"'");
            }
            case "3"->{
                System.out.print("Id: ");int id=Integer.parseInt(sc.nextLine());
                listar("SELECT * FROM pessoas WHERE id="+id);
            }
        }
    }
    private void listar(String sql){
        try(Connection c=DriverManager.getConnection(DB_URL);Statement st=c.createStatement();ResultSet rs=st.executeQuery(sql)){
            while(rs.next()){
                System.out.println(map(rs));
            }
        }catch(Exception e){e.printStackTrace();}
    }
    private Pessoa map(ResultSet rs)throws Exception{
        String tipo=rs.getString("tipo");
        if(tipo.equals("Aluno")) return new Aluno(rs.getInt("id"),rs.getString("nome"),rs.getInt("idade"),rs.getString("info"));
        return new Professor(rs.getInt("id"),rs.getString("nome"),rs.getInt("idade"),rs.getString("info"));
    }
}
