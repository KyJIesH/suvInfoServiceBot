package ru.kuleshov.suvinfoservice.model;

import jakarta.persistence.*;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private Long id;

    @Column(name = "us_telegram_id")
    private Long telegramId;

    @Column(name = "us_role_id")
    private Long roleId;

    @Column(name = "us_use_date")
    private LocalDateTime useDate;
}
