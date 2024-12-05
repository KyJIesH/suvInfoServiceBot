package ru.kuleshov.suvinfoservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kuleshov.suvinfoservice.model.statusPeople.StatusPeople;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "pep_status_people_id", referencedColumnName = "st_pep_id")
    private StatusPeople statusPeople;

    @OneToOne
    @JoinColumn(name = "pep_kurs_id", referencedColumnName = "kur_number_kurs")
    private Kurs kurs;

    @ManyToMany
    @JoinTable(name = "events_people",
            joinColumns = @JoinColumn(name = "ev_pep_people_id"),
            inverseJoinColumns = @JoinColumn(name = "ev_pep_event_id"))
    private List<Event> events;
}
