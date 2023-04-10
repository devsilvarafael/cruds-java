package com.crud.javacrud.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity // Indica que a classe Product é uma entidade gerenciada pelo JPA
@Table(name="products") // Nome da tabela no banco de dados
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id // Indica que o atributo é a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define qual atributo vai ser sua chave primária no banco e vai auto-incrementar.
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

}
