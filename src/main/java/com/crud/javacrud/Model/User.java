package com.crud.javacrud.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name="name")
    public String name;

    @Column(name="occupation")
    public String occupation;

    @Column(name="company")
    public String company;

    @Column(name="ativo")
    public Boolean active;
}
