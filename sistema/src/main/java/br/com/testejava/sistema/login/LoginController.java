package br.com.testejava.sistema.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

  @Autowired
  private LoginRepository loginRepository;

  // @PostMapping("/")
  // public ResponseEntity login(@RequestBody LoginModel loginModel) {
  // //   var username = this.loginRepository.findByUsername(loginModel.getUsername());
  // //   var password = this.loginRepository.findByPassword(loginModel.getPassword());
    
  // //   // if (username!= null || password!= null) {
  // //   //   //logica para buscar dados da tabela TB_LOGIN
  // //   //   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário ou senha incorretos!");
  // //   // } 

  // //   var pageList = "page list";
  // //   return ResponseEntity.status(HttpStatus.OK).body(pageList);
  // }
}
