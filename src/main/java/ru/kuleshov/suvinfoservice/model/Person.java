package ru.kuleshov.suvinfoservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pep_id")
    private Long id;

    @Column(name = "pep_last_name")
    @NotBlank
    @Size(min = 2, max = 40)
    private String lastName;

    @Column(name = "pep_name")
    @NotBlank
    @Size(min = 2, max = 40)
    private String name;

    @Column(name = "pep_patronymic")
    @NotBlank
    @Size(min = 2, max = 40)
    private String patronymic;

    @Column(name = "pep_kurs_id")
    @NotNull
    private Long kursId;
}
