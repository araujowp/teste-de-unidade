package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;

	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	public void propoe(Lance lance) {

		int total = qtdeLancesUsuario(lance);
		
		
		if (lances.isEmpty() || podeDarLance(lance, total))
			lances.add(lance);
	}

	private boolean podeDarLance(Lance lance, int total) {
		return !ultimoLance().getUsuario().equals(lance.getUsuario())
				&& total < 5;
	}

	private int qtdeLancesUsuario(Lance lance) {
		int total = 0;
		for (Lance l : lances) {
			if(l.getUsuario().equals(lance.getUsuario())) total++;
		}
		return total;
	}

	private Lance ultimoLance() {
		return lances.get(lances.size() - 1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

    public void dobraLance(Usuario usuario) {
        Lance ultimoLance = ultimoLanceDo(usuario);
        if(ultimoLance!=null) {
            propoe(new Lance(usuario, ultimoLance.getValor()*2));
        }
    }

    private Lance ultimoLanceDo(Usuario usuario) {
        Lance ultimo = null;
        for(Lance lance : lances) {
            if(lance.getUsuario().equals(usuario)) ultimo = lance;
        }

        return ultimo;
    }
}
