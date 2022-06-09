package Gerenciamento;

public class GeradorProcessos {
	
	private static int idAuto = 0;
	
    public static Processo gerar() {
        return new Processo(idAuto++);
    }

	public static int getIdAuto() {
		return idAuto;
	}

}
