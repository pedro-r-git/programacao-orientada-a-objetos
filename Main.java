package atividade1;

public class Main {
    public static void main(String[] args) {
        Computador pc = new Computador(4096, 256, 4, 15.0);
        SistemaOperacional so = new SistemaOperacional(pc);

        Programa p1 = new Programa(2048, 512, 5000);
        Programa p2 = new Programa(8192, 128, 5000);
        Programa p3 = new Programa(2048, 128, 5000);

        so.executarPrograma(p1);
        so.executarPrograma(p2);
        so.executarPrograma(p3);
    }
}
