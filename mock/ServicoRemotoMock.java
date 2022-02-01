
public class ServicoRemotoMock implements ServicoRemoto {

	private ContaCorrente cc;
	private boolean fail;
	
	ServicoRemotoMock(){
		fail = false;
	}
	
	@Override
	public void recuperarConta(String numero, String senha) throws UsuarioNaoAutenticadoExeption {
		if(fail) throw new UsuarioNaoAutenticadoExeption();
		cc = new ContaCorrente(numero, senha);
	}

	public void recuperarContaFail(boolean f) {
		this.fail = f;
	}
	
	@Override
	public void persistirConta(double valor) {
		verificaConeccao();
		cc.persistirConta(valor);
	}
	
	@Override
	public double getSaldo() {
		verificaConeccao();
		return cc.getSaldo();
	}
	
	private void verificaConeccao() {
		if(cc == null) throw new LoginNaoEfetuadoException();
	}
}
