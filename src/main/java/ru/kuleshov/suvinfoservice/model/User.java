package ru.kuleshov.suvinfoservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kuleshov.suvinfoservice.model.role.Role;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private Long id;

    @Column(name = "us_telegram_id")
    @NotNull
    private Long telegramId;

    @Column(name = "us_name")
    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name = "us_role_id", referencedColumnName = "rol_id")
    private Role role;

    @Column(name = "us_use_date")
    @NotNull
    private LocalDateTime useDate;
}
