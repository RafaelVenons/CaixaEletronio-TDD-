
public class HardwareMock implements Hardware {

	private String numero;
	private boolean pncf;
	private boolean edf;
	private boolean lef;
	
	HardwareMock(){
		this.pncf = false;
		this.edf = false;
		this.lef = false;
		this.numero = null;
	}
		
	public void setNumeroCartao(String numero) {
		this.numero = numero;
	}
	
	@Override
	public String pegarNumeroDaContaCartao() {
		if(this.numero == null || this.pncf)
			throw new CartaoException();
		return this.numero;
	}

	public void pegarNumeroDaContaCartaoFail(boolean fail) {
		this.pncf = fail;
	}
	
	@Override
	public void entregarDinheiro() throws EntregaDinheiroExeption {
		if(this.edf)
			throw new EntregaDinheiroExeption();
	}

	public void entregarDinheiroFail(boolean fail) {
		this.edf = fail;
	}
	
	@Override
	public void lerEnvelope() {
		if(this.lef)
			throw new LerEnvelopeException();
	}
	
	public void lerEnvelopeFail(boolean fail) {
		this.lef = fail;
	}
}
