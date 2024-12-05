package ru.kuleshov.suvinfoservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kuleshov.suvinfoservice.model.Kurs;
import ru.kuleshov.suvinfoservice.model.Person;
import ru.kuleshov.suvinfoservice.model.statusPeople.StatusPeopleList;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findAllByKurs(Kurs kurs);

    List<Person> findAllByKursAndStatusPeople_Status(Kurs kurs, StatusPeopleList status);

    Optional<Person> findByLastNameAndName(String lastName, String name);
}
