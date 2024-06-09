package br.com.testejava.sistema.redeMT;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.testejava.sistema.subestacao.SubstationModel;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/redemt")
public class RedeMTController {

  @Autowired
  private RedeMTService redeMTService;

  // @Autowired
  // private SubstationRepository subestacaoRepository;

  @Autowired 
  private RedeMTRepository redeMTRepository;

  public RedeMTController(RedeMTService redeMTService) {
    this.redeMTService = redeMTService;
  }

  @GetMapping("/list")
  public ResponseEntity <List<RedeMTModel>> findByCodigoAndSubestacao(@RequestParam String codigo, @RequestParam Integer subestacao) {
    List<RedeMTModel> listaDeRedeMT = redeMTRepository.findByCodigoAndSubestacao(codigo, subestacao);
    var listaDeRedeMT = this.redeMTRepository.findAll();
    return listaDeRedeMT;
  }

  @GetMapping("/{id_rede_mt}")
  public ResponseEntity<RedeMTModel> getRedeMT(@PathVariable Integer id_rede_mt) {
    Optional<RedeMTModel> redeMT = this.redeMTRepository.findById(id_rede_mt);
    if (redeMT.isPresent()) {
      return ResponseEntity.ok(redeMT.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  @PostMapping("/")
  public ResponseEntity<RedeMTModel> createRedeMTModel(@RequestBody RedeMTModel redeMT) {
    var savedRedeMT = this.redeMTService.save(redeMT);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedRedeMT);
  }


}
