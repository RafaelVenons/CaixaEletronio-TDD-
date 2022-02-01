
public class ContaCorrente {
	private String numero;
	private String senha;
	private double saldo;
	
	ContaCorrente(String numero, String senha){
		this.numero = numero;
		this.senha = senha;
		this.saldo = 0;
	}
	
	protected String getNumero(){
		return this.numero;
	}
	
	protected boolean realizaLogin(String senha) {
		return this.senha.equals(senha);
	}
	
	protected double getSaldo(){
		return this.saldo;
	}

	protected void persistirConta(double valor){
		this.saldo += valor;
	}
}
