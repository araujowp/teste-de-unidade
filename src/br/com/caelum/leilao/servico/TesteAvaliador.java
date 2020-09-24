package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class TesteAvaliador {

	@Test
    public void deveEntenderLeilaoOrdemCrescente() {
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");
        Usuario alexandra = new Usuario("Alexandra");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 250.0));
        leilao.propoe(new Lance(jose, 300.0));
        leilao.propoe(new Lance(maria, 400.0));
        leilao.propoe(new Lance(alexandra, 450.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double maiorEsperado = 450;
        double menorEsperado = 250;
        double medioEsperado = 350;
        
        assertEquals(maiorEsperado,leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(menorEsperado,leiloeiro.getMenorLance(),0.00001); 
        assertEquals(medioEsperado,leiloeiro.getLanceMedio(),0.01); 
    }
    
    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
        Usuario joao = new Usuario("João"); 
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,1000.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        // veja que não precisamos mais da palavra Assert! 
        assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(1000.0, leiloeiro.getMenorLance(), 0.0001);
        assertEquals(1000.0, leiloeiro.getLanceMedio(),0.01);
    }
    
    @Test
    public void encontrarMaioresLances() {
    	
    	Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");
        
        Leilao leilao = new Leilao("Playstation 3 Novo");
        
        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(jose,350.0));
        leilao.propoe(new Lance(maria,400.0));
        
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
        
        List<Lance> maiores = leiloeiro.getTresMaiores(); 
        assertEquals(3, maiores.size());
        assertEquals(400, maiores.get(0).getValor(), 0.00001);
        assertEquals(350, maiores.get(1).getValor(), 0.00001);
        assertEquals(200, maiores.get(2).getValor(), 0.00001);
        
    }
}
