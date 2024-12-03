package ru.kuleshov.suvinfoservice.model.role;

import jakarta.persistence.*;
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
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    @NotNull
    private Long id;

    @Column(name = "rol_role", nullable = false)
    @Enumerated(EnumType.STRING)
    @Size(max = 30)
    private RoleList role;
}
