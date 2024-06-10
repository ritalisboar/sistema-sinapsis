package br.com.testejava.sistema.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

   @Autowired
    private LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Optional<LoginModel> findByUsername(String username) {
        return loginRepository.findByUsername(username);
    }

    public boolean authenticate(String username, String password) {
        Optional<LoginModel> loginOptional = loginRepository.findByUsername(username);
        if (loginOptional.isPresent()) {
            LoginModel login = loginOptional.get();
            return login.getPassword().equals(password); 
        }
        return false;
    }
}
