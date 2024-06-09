package br.com.testejava.sistema.subestacao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubstationRepository extends JpaRepository<SubstationModel, Integer> {

  List<SubstationModel> findAll();
  Optional<SubstationModel> findByCodigo(String codigo);

}
