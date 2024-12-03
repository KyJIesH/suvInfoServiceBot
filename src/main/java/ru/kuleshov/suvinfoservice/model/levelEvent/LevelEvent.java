package ru.kuleshov.suvinfoservice.model.levelEvent;

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
@Table(name = "level_events")
public class LevelEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lev_ev_id")
    private Long levelId;

    @Column(name = "lev_ev_level", nullable = false)
    @Enumerated(EnumType.STRING)
    @Size(max = 30)
    private LevelEventList eventLevel;
}
