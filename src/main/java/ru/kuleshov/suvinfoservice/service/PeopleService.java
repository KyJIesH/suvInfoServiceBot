package ru.kuleshov.suvinfoservice.service;

import ru.kuleshov.suvinfoservice.model.Event;
import ru.kuleshov.suvinfoservice.model.Person;

import java.util.List;

public interface PeopleService {

    String getListPeople(Long numberKurs);

    Person getPersonByLastNameAndName(String lastName, String name);

    List<Event> getEventsByPerson(Person person);
}
