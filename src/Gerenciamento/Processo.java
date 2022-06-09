package Gerenciamento;

public class Processo {

    private int id;

    private Integer posicao;

    private Integer tamanhoAloc;
    
    private static final int MIN_INST = 10;
	private static final int MAX_INST = 50;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}

	public Integer getTamanhoAloc() {
		return tamanhoAloc;
	}

	public void setTamanhoAloc(Integer tamanho) {
		this.tamanhoAloc = tamanho;
	}

	public Processo(int id) {
        this.id = id;
        this.tamanhoAloc = getNumeroAleatorio(MIN_INST, MAX_INST);;
    }
    
    public Processo() {
    	
    }
    
    public static int getNumeroAleatorio(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);

    }

    
}
