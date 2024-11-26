package ru.kuleshov.suvinfoservice.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.kuleshov.suvinfoservice.handler.MenuHandler;
import ru.kuleshov.suvinfoservice.menu.FirstMenu;
import ru.kuleshov.suvinfoservice.menu.SecondMenu;

/**
 * Сервис всевластия
 */
@Getter
@Service
public class AbsoluteService {
    private final MenuHandler menuHandler;
    private final FirstMenu fistMenu;
    private final SecondMenu secondMenu;
    private final EventService eventService;
    private final PeopleService peopleService;
    private final UserService userService;

    public AbsoluteService(MenuHandler menuHandler, FirstMenu fistMenu, SecondMenu secondMenu,
                           EventService eventService, PeopleService peopleService, UserService userService) {
        this.menuHandler = menuHandler;
        this.fistMenu = fistMenu;
        this.secondMenu = secondMenu;

        this.eventService = eventService;
        this.peopleService = peopleService;
        this.userService = userService;
    }
}
