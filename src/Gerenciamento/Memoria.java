package Gerenciamento;

import java.util.List;

public class Memoria {

    public static final Integer TAMANHO = 1000;
    private static List<Processo> processos;
    
    //private LinkedList<Processo> memoria = new LinkedList<Processo>();

    public void adicionar(Processo processo) {
    	processos.add(processo);
    }

    public void remover(Processo processo) {
    	processos.remove(processo);
    }
    

}
