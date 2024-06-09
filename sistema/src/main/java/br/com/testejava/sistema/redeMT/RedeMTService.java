package br.com.testejava.sistema.redeMT;

import br.com.testejava.sistema.subestacao.SubstationRepository;
import br.com.testejava.sistema.subestacao.SubstationModel;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedeMTService {

    private RedeMTRepository redeMTRepository;
    private SubstationRepository substationRepository;

    @Autowired
    public RedeMTService(RedeMTRepository redeMTRepository, SubstationRepository substationRepository) {
        this.redeMTRepository = redeMTRepository;
        this.substationRepository = substationRepository;
    }

    @Transactional
    public RedeMTModel createRedeMTModel(Integer id_rede_mt, String codigo, String nome, BigDecimal tensao_nominal, SubstationModel subestacao) {
      RedeMTModel redeMT = new RedeMTModel();
      redeMT.setCodigo(codigo);
      redeMT.setNome(nome);
      redeMT.setTensao_nominal(tensao_nominal);
      redeMT.setSubestacao(subestacao);

      return redeMTRepository.save(redeMT);
    }

    @Transactional
    public RedeMTModel updateRedeMTModel(Integer id_rede_mt, String codigo, String nome, BigDecimal tensao_nominal,
    SubstationModel subestacao) {
      RedeMTModel existingRedeMT = redeMTRepository.findById(id_rede_mt)
               .orElseThrow(() -> new RuntimeException("Rede MT não encontrada"));

      existingRedeMT.setNome(nome);
      existingRedeMT.setCodigo(codigo);
      existingRedeMT.setTensao_nominal(tensao_nominal);
      existingRedeMT.setSubestacao(subestacao);
      
      return redeMTRepository.save(existingRedeMT);
    }

    public void deleteRedeMTModel(Integer id_rede_mt) {
        RedeMTModel existingRedeMT = redeMTRepository.findById(id_rede_mt)
               .orElseThrow(() -> new RuntimeException("Rede MT não encontrada"));
        redeMTRepository.deleteById(id_rede_mt);
    }
}
