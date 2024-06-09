package br.com.testejava.sistema.subestacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.testejava.sistema.redeMT.RedeMTRepository;


@Service
public class SubstationService {
    @Autowired
    private SubstationRepository substationRepository;

    @Autowired
    private RedeMTRepository redeMTRepository;

    public SubstationService(SubstationRepository substationRepository) {
        this.substationRepository = substationRepository;
    }

    public SubstationModel save(SubstationModel subestacao) {
        return substationRepository.save(subestacao);
    }

    public SubstationModel update(Integer id_subestacao, SubstationModel subestacao) {
        SubstationModel existingSubstation = substationRepository.findById(id_subestacao)
               .orElseThrow(() -> new RuntimeException("Subestação não encontrada"));
               
        existingSubstation.setNome(subestacao.getNome());
        existingSubstation.setCodigo(subestacao.getCodigo());
        existingSubstation.setLatitude(subestacao.getLatitude());
        existingSubstation.setLongitude(subestacao.getLongitude());

        return substationRepository.save(existingSubstation);
    }

    public void delete(Integer id_subestacao) {
        SubstationModel existingSubstation = substationRepository.findById(id_subestacao)
               .orElseThrow(() -> new RuntimeException("Subestação não encontrada"));
        substationRepository.deleteById(id_subestacao);
        redeMTRepository.deleteAll(redeMTRepository.findBySubestacao(existingSubstation));
    }
}
