import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestContaCorrente {

	ContaCorrente cc;
	
	@BeforeEach
	void inicializa() {
		cc = new ContaCorrente("123", "123");
	}
	
	@Test
	void testContaNova() {
		assertEquals("123",cc.getNumero());
		assertTrue(cc.realizaLogin("123"));
		assertEquals(0,cc.getSaldo());		
	}

	@Test
	void testSenhaErrada() {
		assertFalse(cc.realizaLogin("321"));
	}
	
	@Test
	void testVerificaSaldo() {
		assertEquals(0,cc.getSaldo());
		cc.persistirConta(100);
		assertEquals(100,cc.getSaldo());
		cc.persistirConta(-50);
		assertEquals(50,cc.getSaldo());
		cc.persistirConta(-50);
		assertEquals(0,cc.getSaldo());
		cc.persistirConta(-1.99);
		assertEquals(-1.99,cc.getSaldo());
	}
}
