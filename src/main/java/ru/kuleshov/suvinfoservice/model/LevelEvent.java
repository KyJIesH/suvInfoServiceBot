package ru.kuleshov.suvinfoservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
    @Column(name = "lev_ev_id")
    private Long levelId;

    @Column(name = "lev_ev_level")
    @NotBlank
    @Size(max = 30)
    private String eventLevel;
}
