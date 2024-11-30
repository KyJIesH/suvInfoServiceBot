package ru.kuleshov.suvinfoservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "kurses")
public class Kurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kur_id")
    private Long id;

    @Column(name = "kur_number_kurs")
    private Long numberKurs;
}
