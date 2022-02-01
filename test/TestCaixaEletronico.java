import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCaixaEletronico {

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
	void testLoginConta() {
		assertEquals("Usuário Autenticado", ce.login("1","1"));
	}

	@Test
	void testLoginContaFalha() {
		sr_mock.recuperarContaFail(true);
		assertEquals("Não foi possível autenticar o usuário", ce.login("1","1"));
	}

	@Test
	void testDepositar() {
		ce.login("1","1");
		assertEquals("Depósito recebido com sucesso", ce.depositar(100));
	}
	
	@Test
	void testDepositarValorInvalido() {
		ce.login("1","1");
		assertEquals("Não é permitido depositar valor nulo ou negativo!", ce.depositar(0));
		assertEquals("Não é permitido depositar valor nulo ou negativo!", ce.depositar(-100));
	}

	@Test
	void testDepositarSemLogar() {
		try{
			ce.depositar(100);
			fail("Foi possivel depositar sem logar!");
		} catch(LoginNaoEfetuadoException e) {}
	}
	
	@Test
	void testSacar() {
		ce.login("1","1");
		ce.depositar(100);
		assertEquals("Retire seu dinheiro", ce.sacar(50));
	}
	
	@Test
	void testSacarVariasVezes() {
		ce.login("1","1");
		ce.depositar(100);
		assertEquals("Retire seu dinheiro", ce.sacar(50));
		assertEquals("Retire seu dinheiro", ce.sacar(50));
		assertEquals("Saldo insuficiente", ce.sacar(50));
	}


	@Test
	void testSacarValorInvalido() {
		ce.login("1","1");
		assertEquals("Não é permitido sacar valor nulo ou negativo!", ce.sacar(0));
		assertEquals("Não é permitido sacar valor nulo ou negativo!", ce.sacar(-50));
		ce.depositar(100);
		assertEquals("Não é permitido sacar valor nulo ou negativo!", ce.sacar(0));
		assertEquals("Não é permitido sacar valor nulo ou negativo!", ce.sacar(-50));
	}
	
	@Test
	void testSacarSemLogar() {
		try{
			ce.sacar(100);
			fail("Foi possivel sacar sem logar!");
		} catch(LoginNaoEfetuadoException e) {}
	}
	
	@Test
	void testSaldo() {
		ce.login("1","1");
		assertEquals("R$0,00",ce.saldo());
		ce.depositar(100);
		assertEquals("R$100,00",ce.saldo());
		ce.depositar(23.45);
		assertEquals("R$123,45",ce.saldo());
		ce.sacar(23.05);
		assertEquals("R$100,40",ce.saldo());
	}

	@Test
	void testSaldoSemLogar() {
		try {
			ce.saldo();
			fail("Foi possivel ver saldo sem logar!");
		} catch(LoginNaoEfetuadoException e) {}
		
	}
}
