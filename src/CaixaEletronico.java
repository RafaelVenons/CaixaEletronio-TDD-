
public class CaixaEletronico {
	
	private ServicoRemoto sr;
	private Hardware hw;
	
	CaixaEletronico(ServicoRemoto sr, Hardware hw){
		this.sr = sr;
		this.hw = hw;
	}
	
	public String login(String numero, String senha) {
		try {
			if(!hw.pegarNumeroDaContaCartao().equals(numero))
				throw new UsuarioNaoAutenticadoExeption();
			sr.recuperarConta(numero, senha);
			return "Usuário Autenticado";
		} catch(UsuarioNaoAutenticadoExeption e) {
			return e.getMessage();
		}
	}
	
	public String sacar(double valor) {
		
		if(!valorValido(valor))
			return "Não é permitido sacar valor nulo ou negativo!";
		if(sr.getSaldo() < valor)
			return "Saldo insuficiente";
		try {
			hw.entregarDinheiro();
			sr.persistirConta(-valor);
			return "Retire seu dinheiro";
		} catch (EntregaDinheiroExeption e) {
			return e.getMessage();
		}
	}
	
	public String depositar(double valor) {
		
		if(!valorValido(valor))
			return "Não é permitido depositar valor nulo ou negativo!";
		try {
			hw.lerEnvelope();
			sr.persistirConta(valor);
			return "Depósito recebido com sucesso";
		}catch (LerEnvelopeException e) {
			return e.getMessage();
		}
	}
	
	public String saldo() {
		return String.format("R$%.2f", sr.getSaldo()).replace(".", ",");
	}
	
	private boolean valorValido(double valor) {
		return valor > 0;
	}
}
