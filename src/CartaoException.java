
public class CartaoException extends RuntimeException {
	CartaoException(){
		super("Não foi possivel ler o Cartão");
	}
}
