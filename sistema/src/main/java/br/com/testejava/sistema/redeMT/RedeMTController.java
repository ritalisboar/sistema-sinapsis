package br.com.testejava.sistema.redeMT;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/redemt")
public class RedeMTController {

  @Autowired
  private RedeMTService redeMTService;

  @Autowired 
  private RedeMTRepository redeMTRepository;

  public RedeMTController(RedeMTService redeMTService) {
    this.redeMTService = redeMTService;
  }

  @GetMapping("/list")
  public List<RedeMTModel> list(HttpServletRequest request) {
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
  public ResponseEntity<RedeMTModel> createRedeMTModel(@RequestBody RedeMTDto redeMTDto) {
    RedeMTModel redeMT = redeMTService.createRedeMTModel(
        redeMTDto.getId_rede_mt(), 
        redeMTDto.getCodigo(), 
        redeMTDto.getNome(), 
        redeMTDto.getTensao_nominal(), 
        redeMTDto.getSubestacao()
    );

    return ResponseEntity.ok(redeMT);
  }

  @PutMapping("/{id_rede_mt}")
  public ResponseEntity<RedeMTModel> updateRedeMTModel(@PathVariable Integer id_rede_mt, @RequestBody RedeMTDto redeMTDto) {
    RedeMTModel redeMT = redeMTService.updateRedeMTModel(
        id_rede_mt,
        redeMTDto.getCodigo(),
        redeMTDto.getNome(),
        redeMTDto.getTensao_nominal(),
        redeMTDto.getSubestacao()
    );

    return ResponseEntity.ok(redeMT);
  }

  @DeleteMapping("/{id_rede_mt}")
  public ResponseEntity<String> deleteRedeMTModel(@PathVariable Integer id_rede_mt) {
    redeMTService.deleteRedeMTModel(id_rede_mt);
    return ResponseEntity.ok("RedeMT deletada com sucesso!");
  }
}
