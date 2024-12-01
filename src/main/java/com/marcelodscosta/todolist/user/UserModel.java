package com.marcelodscosta.todolist.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_users") // Nome da tabela
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID") // Automatizando o gerenciamento
    private UUID id;

    private String name;

    @Column(unique = true) //Validação de usuário, somente 1 usuário com este username
    private String username;
    private String password;

    @CreationTimestamp // Automatizando o gerenciamento
    private LocalDateTime createdAt;

    @UpdateTimestamp // Automatizando o gerenciamento
    private LocalDateTime updatedAt;
}
