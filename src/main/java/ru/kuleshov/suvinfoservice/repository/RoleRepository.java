package ru.kuleshov.suvinfoservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kuleshov.suvinfoservice.model.role.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
