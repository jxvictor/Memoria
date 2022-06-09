package Gerenciamento;

import java.util.Timer;
import java.util.TimerTask;

import javax.management.InstanceNotFoundException;

public class Main {

    public static void main(String[] args) throws InstanceNotFoundException {
    	//int numProcessosRemovidos = Processo.getNumeroAleatorio(1, 2);
        int cont = 0;
        final long time = 1000; // a cada 1 segundo = 1000
		Timer timer = new Timer();
		
		while(true) {
			TimerTask tarefa = new TimerTask() {
				private int count = 0;
				double tamTotalProcessosGerados = 0;
				public void run() {
					Processo processo;
					processo = GeradorProcessos.gerar();
					processo = GeradorProcessos.gerar();
		        	tamTotalProcessosGerados += processo.getTamanhoAloc();
		        	System.out.println("PROCESSO CRIADO!");
		        	System.out.println("Tamanho Alocação: " + processo.getTamanhoAloc());
		        	System.out.println("ID: " + processo.getId());
		        	System.out.println("Posição: " + processo.getPosicao());
		        	System.out.println("----------------------------------------------------");
		        	count++;
		        	tamTotalProcessosGerados ++;
		        	if (count == 100) {
		        		timer.cancel();
		        		System.out.println("-------------RELATÓRIO--------------");
		                System.out.println("Tamanho médio dos processos gerados: " + tamTotalProcessosGerados/100);
		        	}
				}
			};
			cont ++;
			if (cont == 2) {
        		break;
        	}
			timer.scheduleAtFixedRate(tarefa, time, time);
		}
    }
}