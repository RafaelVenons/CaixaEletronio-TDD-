
public class UsuarioNaoAutenticadoExeption extends Exception {
	UsuarioNaoAutenticadoExeption(){
		super("Não foi possível autenticar o usuário");
	}
}
