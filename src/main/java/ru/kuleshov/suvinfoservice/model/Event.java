package ru.kuleshov.suvinfoservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ev_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ev_owner_id", referencedColumnName = "us_id")
    private User owner;

    @Column(name = "ev_data_event")
    @NotNull
    private LocalDateTime dataEvent;

    @ManyToOne
    @JoinColumn(name = "ev_level_event_id", referencedColumnName = "lev_ev_id")
    private LevelEvent levelEvent;


    @ManyToOne
    @JoinColumn(name = "ev_status_event_id", referencedColumnName = "st_id")
    private Status statusEvent;

    @Column(name = "ev_description")
    @NotBlank
    @Size(min = 5, max = 10000)
    private String description;
}
