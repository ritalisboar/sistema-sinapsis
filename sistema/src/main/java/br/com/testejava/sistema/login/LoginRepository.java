package br.com.testejava.sistema.login;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginModel, UUID> {
  Optional<LoginModel> findByUsername(String username);
  Optional<LoginModel> findByPassword(String password);
}
