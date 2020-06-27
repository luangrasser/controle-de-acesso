package br.com.gl.controledeacesso.form;

import br.com.gl.controledeacesso.dao.*;
import br.com.gl.controledeacesso.model.Acesso;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
public class AcessoForm {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer morador;
    private Integer visitante;
    private Integer prestadorServico;
    private Integer corretor;
    private Integer entregador;
    private String movimento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDateTime dataMovimento;

    public Acesso transform(MoradorRepository moradorDao, VisitanteRepository visitanteDao,
            PrestadorServicoRepository prestadorDao, CorretorRepository corretorDao, EntregadorRepository entregadorDao) throws Exception {
        Acesso acesso = Acesso.builder().id(id).movimento(movimento)
                .dataMovimento(dataMovimento != null ? dataMovimento : LocalDateTime.now()).build();
        if (morador != null) {
            acesso.setMorador(moradorDao.findById(morador).orElseThrow(() -> new Exception("Morador desconhecido.")));
        }
        if (visitante != null) {
            acesso.setVisitante(visitanteDao.findById(visitante).orElseThrow(() -> new Exception("Visitante desconhecido.")));
        }
        if (prestadorServico != null) {
            acesso.setPrestadorServico(prestadorDao.findById(prestadorServico).orElseThrow(() -> new Exception("Prestador de serviço desconhecido.")));
        }
        if (corretor != null) {
            acesso.setCorretor(corretorDao.findById(corretor).orElseThrow(() -> new Exception("Corretor desconhecido.")));
        }
        if (entregador != null) {
            acesso.setEntregador(entregadorDao.findById(entregador).orElseThrow(() -> new Exception("Entregador desconhecido.")));
        }
        return acesso;
    }
}
