package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;
	private Usuario alexandra;

	@BeforeClass
	public static void inicioDaClasse() {
		System.out.println("inicio da classe ");
	}
	@AfterClass
	public static void finalDaClasse() {
		System.out.println("final da classe ");
	}
	
	
	@Before
	public void criaAvaliador() {
		
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.maria = new Usuario("Maria");
		this.alexandra = new Usuario("Alexandra");
	}
	
	@After
	public void finaliza() {
	  System.out.println("fim");
	}

	@Test
    public void deveEntenderLeilaoOrdemCrescente() {

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 250.0));
        leilao.propoe(new Lance(jose, 300.0));
        leilao.propoe(new Lance(maria, 400.0));
        leilao.propoe(new Lance(alexandra, 450.0));

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
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,1000.0));

        leiloeiro.avalia(leilao);

        // veja que não precisamos mais da palavra Assert! 
        assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(1000.0, leiloeiro.getMenorLance(), 0.0001);
        assertEquals(1000.0, leiloeiro.getLanceMedio(),0.01);
    }
    
    @Test
    public void encontrarMaioresLances() {
    	        
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
        		.lance(joao,100)
        		.lance(maria,200)
        		.lance(joao,300)
        		.lance(maria,400)
        		.constroi();
        		
        leiloeiro.avalia(leilao);
        
        List<Lance> maiores = leiloeiro.getTresMaiores(); 
        assertEquals(3, maiores.size());
        assertEquals(400, maiores.get(0).getValor(), 0.00001);
        assertEquals(300, maiores.get(1).getValor(), 0.00001);
        assertEquals(200, maiores.get(2).getValor(), 0.00001);
        
    }
    
    @Test
    public void deveDobrarUltimoLance() {
    	
    	Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(billGates, 3000));
        leilao.dobraLance(steveJobs);

        assertEquals(4000, leilao.getLances().get(2).getValor(), 0.00001);
    }

    @Test
    public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");

        leilao.dobraLance(steveJobs);

        assertEquals(0, leilao.getLances().size());
    }
    
}
