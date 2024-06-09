package br.com.testejava.sistema.redeMT;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.testejava.sistema.subestacao.SubstationModel;

public interface RedeMTRepository extends JpaRepository<RedeMTModel, Integer> {

  List<RedeMTModel> findBySubestacao(SubstationModel subestacao);
  Optional<RedeMTModel> findByCodigoAndSubestacao(String codigo, SubstationModel subestacao);
   
}
