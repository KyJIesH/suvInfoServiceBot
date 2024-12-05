package ru.kuleshov.suvinfoservice.model.statusEvent;

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
@Table(name = "statuses_events")
public class StatusEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "st_ev_id")
    private Long id;

    @Column(name = "st_ev_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Size(max = 30)
    private StatusEventList status;
}
