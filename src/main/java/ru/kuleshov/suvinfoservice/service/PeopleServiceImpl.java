package ru.kuleshov.suvinfoservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kuleshov.suvinfoservice.model.Event;
import ru.kuleshov.suvinfoservice.model.Kurs;
import ru.kuleshov.suvinfoservice.model.Person;
import ru.kuleshov.suvinfoservice.model.statusPeople.StatusPeopleList;
import ru.kuleshov.suvinfoservice.repository.KursRepository;
import ru.kuleshov.suvinfoservice.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    private static final String TAG = "PEOPLE SERVICE";

    private PersonRepository personRepository;
    private KursRepository kursRepository;

    @Override
    public String getListPeople(Long numberKurs) {
        log.info("{} - получение расхода ЛС", TAG);

        StringBuilder sb = new StringBuilder();
        Kurs kurs = null;

        Optional<Kurs> kursOptional = kursRepository.findByNumberKurs(numberKurs);
        if (kursOptional.isPresent()) {
            kurs = kursOptional.get();
        }

        List<Person> personList = personRepository.findAllByKurs(kurs);

        List<Person> statNormal = personRepository.findAllByKursAndStatusPeople_Status(kurs, StatusPeopleList.NORMAL);
        List<Person> statTrips = personRepository.findAllByKursAndStatusPeople_Status(kurs, StatusPeopleList.TRIPS);
        List<Person> statDisease = personRepository.findAllByKursAndStatusPeople_Status(kurs, StatusPeopleList.DISEASE);

        sb.append("По списку: ").append(personList.size()).append("\n");
        sb.append("В строю: ").append(statNormal.size()).append("\n");
        if (!statTrips.isEmpty()) {
            sb.append("Командировка: ").append(statTrips.size()).append("\n");
            for (Person person : statTrips) {
                sb.append(person.getLastName()).append(" ").append(person.getName()).append("\n");
            }
        }
        if (!statDisease.isEmpty()) {
            sb.append("Больны: ").append(statDisease.size()).append("\n");
            for (Person person : statDisease) {
                sb.append(person.getLastName()).append(" ").append(person.getName()).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public Person getPersonByLastNameAndName(String lastName, String name) {
        log.info("{} - получение объекта Person по ФИ", TAG);

        Optional<Person> personOptional = personRepository.findByLastNameAndName(lastName, name);
        return personOptional.orElse(null);
    }

    @Override
    public List<Event> getEventsByPerson(Person person) {
        log.info("{} - получение всех событий Person {} {}", TAG, person.getLastName(), person.getName());

        List<Event> events = person.getEvents();

        return events;
    }
}
