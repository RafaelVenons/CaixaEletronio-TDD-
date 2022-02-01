import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestHardware {

	CaixaEletronico ce;
	ServicoRemotoMock sr_mock;
	HardwareMock hw_mock;
	
	@BeforeEach
	void inicializa() {
		sr_mock = new ServicoRemotoMock();
		hw_mock = new HardwareMock();
		hw_mock.setNumeroCartao("1");
		ce = new CaixaEletronico(sr_mock, hw_mock);
	}
	
	@Test
	void testLoginFail() {
		assertEquals("Usuário Autenticado", ce.login("1","1"));
		hw_mock.pegarNumeroDaContaCartaoFail(true);
		try {
			ce.login("1","1");
		}catch(CartaoException e) {}
	}

	@Test
	void testEntregarDinheiroFail() {
		ce.login("1","1");
		ce.depositar(100);
		ce.sacar(50);
		hw_mock.entregarDinheiroFail(true);
		assertEquals("Não foi possivel Entregar o Dinheiro!", ce.sacar(50));
		assertEquals("R$50,00",ce.saldo());
	}
	
	@Test
	void testLerEnvelopeFail() {
		ce.login("1","1");
		ce.depositar(100);
		hw_mock.lerEnvelopeFail(true);
		assertEquals("Não foi possivel ler o Envelope", ce.depositar(100));
		assertEquals("R$100,00",ce.saldo());
	}
}
