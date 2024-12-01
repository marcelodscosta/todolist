package com.marcelodscosta.todolist.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Pegar autenticação
        var authorization = request.getHeader("Authorization");
        var authEncoded = authorization.substring("Basic".length()).trim();

        byte[] decodedAuth = Base64.getDecoder().decode(authEncoded);

        String auth = new String(decodedAuth);
        String[] authSplit = auth.split(":");


        System.out.printf("Usuário: %s%n", authSplit[0]);
        System.out.printf("Senha: %s%n", authSplit[1]);

        // Validar Usuário

        // Validar Senha

        // Seguir fluxo
    }
}
