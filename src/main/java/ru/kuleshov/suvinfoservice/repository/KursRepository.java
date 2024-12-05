package ru.kuleshov.suvinfoservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kuleshov.suvinfoservice.model.Kurs;

import java.util.Optional;

@Repository
public interface KursRepository extends CrudRepository<Kurs, Long> {

    Optional<Kurs> findByNumberKurs(Long numberKurs);
}
