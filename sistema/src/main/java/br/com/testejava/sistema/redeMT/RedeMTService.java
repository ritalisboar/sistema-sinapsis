package br.com.testejava.sistema.redeMT;

import br.com.testejava.sistema.subestacao.SubstationRepository;
import br.com.testejava.sistema.subestacao.SubstationModel;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RedeMTService {

    @Autowired
    private RedeMTRepository redeMTRepository;

    @Autowired
    private SubstationRepository substationRepository;

    public RedeMTService(RedeMTRepository redeMTRepository) {
        this.redeMTRepository = redeMTRepository;
        this.substationRepository = substationRepository;
    }

    @Transactional
    public RedeMTModel createRedeMTModel(String codigo, String nome, BigDecimal tensao_nominal, Integer id_subestacao) {
      Optional<SubstationModel> substation = substationRepository.findById(id_subestacao);
      if (substation.isEmpty()) {
        throw new RuntimeException("Subestação não encontrada");
      }

      RedeMTModel redeMT = new RedeMTModel();
      redeMT.setCodigo(codigo);
      redeMT.setNome(nome);
      redeMT.setTensao_nominal(tensao_nominal);
      redeMT.setSubestacao(substation.get());

      return redeMTRepository.save(redeMT);
    }

    public RedeMTModel update(Integer id_rede_mt, RedeMTModel redeMT) {
      RedeMTModel existingRedeMT = redeMTRepository.findById(id_rede_mt)
               .orElseThrow(() -> new RuntimeException("Rede MT não encontrada"));

      existingRedeMT.setNome(redeMT.getNome());
      existingRedeMT.setCodigo(redeMT.getCodigo());
      existingRedeMT.setTensao_nominal(redeMT.getTensao_nominal());
      existingRedeMT.setSubestacao(redeMT.getSubestacao());
      
      return redeMTRepository.save(existingRedeMT);
    }

    public void delete(Integer id_rede_mt) {
        RedeMTModel existingRedeMT = redeMTRepository.findById(id_rede_mt)
               .orElseThrow(() -> new RuntimeException("Rede MT não encontrada"));
        redeMTRepository.deleteById(id_rede_mt);
    }
}
