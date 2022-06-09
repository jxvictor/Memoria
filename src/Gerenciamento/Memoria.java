package Gerenciamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Memoria {

    public static final Integer TAMANHO = 1000;
    private static List<Processo> processos = new ArrayList<>();
    
    //private LinkedList<Processo> memoria = new LinkedList<Processo>();

    public static List<Processo> getProcessos() {
		return processos;
	}

	public static void setProcessos(List<Processo> processos) {
		Memoria.processos = processos;
	}

	public void adicionar(Processo processo) {
    	processos.add(processo);
    }

    public void remover(Processo processo) {
    	
    	processos.remove(processo);
    	
    }
    public static void removerAleatorio()
    {
    	if (processos.size() > 0) {
        	Random random = new Random();
        	System.out.println(processos.size());
        	int id = random.nextInt(processos.size());
        	processos.remove(id);
		}
    }

}
