package com.marcelodscosta.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.marcelodscosta.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if(servletPath.equals("/tasks/")) {
            // Pegar autenticação
            var authorization = request.getHeader("Authorization"); // Pegando usuário e senha do Header de forma bruta
            var authEncoded = authorization.substring("Basic".length()).trim(); // Tratando informação e removendo espaços

            byte[] decodedAuth = Base64.getDecoder().decode(authEncoded); // Decodificando

            String auth = new String(decodedAuth); // Convertendo em String
            String[] authSplit = auth.split(":"); // Separando informação

            String username = authSplit[0];
            String password = authSplit[1];

            // Validar Usuário


            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                // Validar Senha

                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                System.out.printf("User %s verified.\n", passwordVerify.verified);
                if(passwordVerify.verified){
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
                // Seguir fluxo
            }

        }else {
            filterChain.doFilter(request, response);
        }




    }

}
