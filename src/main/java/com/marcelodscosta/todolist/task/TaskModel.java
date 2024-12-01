package com.marcelodscosta.todolist.task;

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
@Entity(name = "tb_tasks") // Nome da tabela
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private UUID userId;

    @Column(length = 50)
    private String title;
    private String description;

    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;


    @CreationTimestamp // Automatizando o gerenciamento
    private LocalDateTime createdAt;

    @UpdateTimestamp // Automatizando o gerenciamento
    private LocalDateTime updatedAt;
}
