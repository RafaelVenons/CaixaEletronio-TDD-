
public interface Hardware {
	
	String pegarNumeroDaContaCartao();
	
	void entregarDinheiro() throws EntregaDinheiroExeption;
	
	void lerEnvelope();
}
