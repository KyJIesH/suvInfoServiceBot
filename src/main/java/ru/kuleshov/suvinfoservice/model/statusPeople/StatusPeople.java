package ru.kuleshov.suvinfoservice.model.statusPeople;

import jakarta.persistence.*;
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
@Table(name = "statuses_people")
public class StatusPeople {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "st_pep_id")
    private Long id;

    @Column(name = "st_pep_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Size(max = 30)
    private StatusPeopleList status;
}
