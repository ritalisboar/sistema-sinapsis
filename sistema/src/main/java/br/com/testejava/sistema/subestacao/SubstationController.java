package br.com.testejava.sistema.subestacao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/substation")
public class SubstationController {

  @Autowired
  private SubstationService substationService;

  @Autowired
  private SubstationRepository subestacaoRepository;

  public SubstationController(SubstationService substationService) {
    this.substationService = substationService;
  }

  @GetMapping("/list")
  public List<SubstationModel> list(HttpServletRequest request) {
    var listaDeSubestacoes = this.subestacaoRepository.findAll();
    return listaDeSubestacoes;
  }

  @GetMapping("/{id_subestacao}")
  public ResponseEntity<SubstationModel> getSubestacao(@PathVariable Integer id_subestacao) {
    Optional<SubstationModel> subestacao = this.subestacaoRepository.findById(id_subestacao);
    if (subestacao.isPresent()) {
      return ResponseEntity.ok(subestacao.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  @PostMapping("/")
  public ResponseEntity<SubstationModel> createSubestacao(@RequestBody SubstationModel subestacao) {
    SubstationModel savedSuvestacao = substationService.save(subestacao);
    return ResponseEntity.ok(savedSuvestacao);
  }

  @PutMapping("/{id_subestacao}")
  public ResponseEntity<SubstationModel> updateSubestacao(@PathVariable Integer id_subestacao, @RequestBody SubstationModel subestacao) {
    SubstationModel updetedSubestacao = substationService.update(id_subestacao, subestacao);
    return ResponseEntity.ok(updetedSubestacao);
  }

  @DeleteMapping("/{id_subestacao}")
  public ResponseEntity<String> deleteSubestacao(@PathVariable Integer id_subestacao) {
    substationService.delete(id_subestacao);
    return ResponseEntity.ok("Subestação excluída com sucesso!");
  }
}
