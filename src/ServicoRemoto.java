
public interface ServicoRemoto {
	
	void recuperarConta(String numero, String senha) throws UsuarioNaoAutenticadoExeption;
	
	void persistirConta(double valor);
	
	double getSaldo();
}
