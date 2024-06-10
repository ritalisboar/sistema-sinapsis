package br.com.testejava.sistema.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

  @Autowired
  private LoginService loginService;

  @Autowired
  private LoginRepository loginRepository;

  @GetMapping("/access")
  public ResponseEntity<LoginModel> login(@RequestBody LoginModel loginModel) {
    Optional<LoginModel> username = this.loginRepository.findByUsername(loginModel.getUsername());
    Optional<LoginModel> password = this.loginRepository.findByPassword(loginModel.getPassword());
    
    if (username.isPresent()) {
      return (ResponseEntity<LoginModel>) ResponseEntity.ok(username.get());
    } else if (password.isPresent()) {
      return (ResponseEntity<LoginModel>) ResponseEntity.ok(password.get());
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
  }
}
