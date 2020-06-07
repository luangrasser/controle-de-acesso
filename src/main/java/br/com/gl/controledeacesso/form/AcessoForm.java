package br.com.gl.controledeacesso.form;

import br.com.gl.controledeacesso.dao.MoradorRepository;
import br.com.gl.controledeacesso.dao.PrestadorServicoRepository;
import br.com.gl.controledeacesso.dao.VisitanteRepository;
import br.com.gl.controledeacesso.model.Acesso;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
public class AcessoForm {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer morador;
    private Integer visitante;
    private Integer prestadorServico;
    private String movimento;

    public Acesso transform(MoradorRepository moradorDao, VisitanteRepository visitanteDao, PrestadorServicoRepository prestadorDao) throws Exception {
        Acesso acesso = Acesso.builder().id(id).movimento(movimento).build();
        if (morador != null) {
            acesso.setMorador(moradorDao.findById(morador).orElseThrow(() -> new Exception("Morador desconhecido.")));
        }
        if (visitante != null) {
            acesso.setVisitante(visitanteDao.findById(visitante).orElseThrow(() -> new Exception("Visitante desconhecido.")));
        }
        if (prestadorServico != null) {
            acesso.setPrestadorServico(prestadorDao.findById(prestadorServico).orElseThrow(() -> new Exception("Prestador de serviço desconhecido.")));
        }
        return acesso;
    }
}
