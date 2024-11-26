package ru.kuleshov.suvinfoservice.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kuleshov.suvinfoservice.model.Person;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    private static final String TAG = "PEOPLE SERVICE";

    @Override
    public List<Person> getListPeople() {
        log.info("{} - получение расхода ЛС", TAG);
        return List.of();
    }
}
