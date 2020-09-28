package br.com.caelum.leilao.dominio;

import org.junit.Test;

public class LanceTest {

	@Test(expected = IllegalArgumentException.class )
	public void lanceDeveSerMaiorQue0() {
		Usuario maria  = new Usuario("maria");
		Lance lance = new Lance(maria, 0);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void lanceDeveSerPositivo() {
		Usuario maria  = new Usuario("maria");
		Lance lance = new Lance(maria, -16);
	}
	
}
