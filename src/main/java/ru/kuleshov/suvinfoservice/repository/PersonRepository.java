package ru.kuleshov.suvinfoservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kuleshov.suvinfoservice.model.Kurs;
import ru.kuleshov.suvinfoservice.model.Person;
import ru.kuleshov.suvinfoservice.model.statusPeople.StatusPeople;
import ru.kuleshov.suvinfoservice.model.statusPeople.StatusPeopleList;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findAllByKurs(Kurs kurs);

    List<Person> findAllByKursAndStatusPeople_Status(Kurs kurs, StatusPeopleList status);
}
