// package br.com.testejava.sistema.login;

// import java.io.IOException;
// import java.util.Base64;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class LoginAuth extends OncePerRequestFilter {

//   @Autowired
//   private LoginRepository loginRepository;

//   @Override
//   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//     throws ServletException, IOException {
//       var servletPath = request.getServletPath();
      
//       if (servletPath.equals("/login/")) {
//         var authorization = request.getHeader("Authorization");
//         var authEncoded = authorization.substring("Basic".length()).trim();
//         byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
//         var authString = new String(authDecoded, "UTF-8");
//         var credencials = authString.split(":");
//         String username = credencials[0];
//         String password = credencials[1];

//         // validar e senha
//         var userLogin = this.loginRepository.findByUsername(username);
//         var userPassword = this.loginRepository.findByPassword(password);

//         if (userLogin == null || userPassword == null || !userLogin.getPassword().equals(userPassword) || !userLogin.getUsername().equals(username)) {
//           response.sendError(401, "Usuário ou senha incorretos!");
//         } 
//       } else {
//         filterChain.doFilter(request, response);
//       }
//   }
// }
