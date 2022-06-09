package Gerenciamento;

import java.util.Timer;
import java.util.TimerTask;

import javax.management.InstanceNotFoundException;

public class Main {

    public static void main(String[] args) throws InstanceNotFoundException {
		
    	int numProcessosRemovidos = Processo.getNumeroAleatorio(1, 2);
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
					System.out.println("PROCESSO CRIADO!");
		        	System.out.println("ID: " + processo.getId());
		        	System.out.println("Tamanho Aloca��o: " + processo.getTamanhoAloc());
		        	System.out.println("Posi��o: " + processo.getPosicao());
		        	System.out.println("----------------------------------------------------");
		        	tamTotalProcessosGerados += processo.getTamanhoAloc();
		        	
					Processo processo1;
					processo1 = GeradorProcessos.gerar();
					System.out.println("PROCESSO CRIADO!");
		        	System.out.println("ID: " + processo1.getId());
		        	System.out.println("Tamanho Aloca��o: " + processo1.getTamanhoAloc());
		        	System.out.println("Posi��o: " + processo1.getPosicao());
		        	System.out.println("----------------------------------------------------");
		        	
		        	tamTotalProcessosGerados += processo1.getTamanhoAloc();
					Memoria memoria = new Memoria();
					memoria.adicionar(processo);
					memoria.adicionar(processo1);
		        	
		        	Memoria.removerAleatorio();
		        	System.out.println(Memoria.getProcessos());
		        	
		        	count++;
		        	tamTotalProcessosGerados ++;
		        	if (count == 5) {
		        		timer.cancel();
		        		System.out.println("-------------RELAT�RIO--------------");
		                System.out.println("Tamanho m�dio dos processos gerados: " + tamTotalProcessosGerados/100);
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