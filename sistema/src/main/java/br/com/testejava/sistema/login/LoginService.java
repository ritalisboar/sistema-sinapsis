package br.com.testejava.sistema.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    // private final LoginRepository loginRepository;

    // @Autowired
    // public LoginService(LoginRepository loginRepository) {
    //     this.loginRepository = loginRepository;
    // }

    // public LoginModel save(LoginModel loginModel) {
    //     return loginRepository.save(loginModel);
    // }

    // public Optional<LoginModel> findById(Integer id) {
    //     return loginRepository.findById(id);
    // }

    // public void deleteById(Integer id) {
    //     loginRepository.deleteById(UUID id);
    // }

    // public Optional<Login> findByUsername(String username) {
    //     return loginRepository.findByUsername(username);
    // }

    // public boolean authenticate(String username, String password) {
    //     Optional<Login> loginOptional = loginRepository.findByUsername(username);
    //     if (loginOptional.isPresent()) {
    //         Login login = loginOptional.get();
    //         return login.getPassword().equals(password); // Simples comparação de senha, você pode usar hash/salt para segurança
    //     }
    //     return false;
    // }
}
