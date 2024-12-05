package ru.kuleshov.suvinfoservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kuleshov.suvinfoservice.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

}
