package br.com.caelum.ano;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class AnoBissestoTest {

	@Test
	public void deveRetornoVerdadeiro() {
		
		AnoBissexto ano = new AnoBissexto();
		assertEquals(true, ano.isAnoBissexto(2012));
		assertEquals(true, ano.isAnoBissexto(2016));
		
	}
	
	@Test
	public void deveRetornoFalso() {
		
		AnoBissexto ano = new AnoBissexto();
		assertEquals(false, ano.isAnoBissexto(2015));
		assertEquals(false, ano.isAnoBissexto(2011));
		
	}
}
