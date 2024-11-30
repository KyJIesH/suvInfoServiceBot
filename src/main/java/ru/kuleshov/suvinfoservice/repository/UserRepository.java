package ru.kuleshov.suvinfoservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kuleshov.suvinfoservice.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByTelegramId(Long telegramId);

    List<User> findByRoleId(Long roleId);
}
