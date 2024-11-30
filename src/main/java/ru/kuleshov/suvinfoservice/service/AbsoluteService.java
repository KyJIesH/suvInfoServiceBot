package ru.kuleshov.suvinfoservice.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.kuleshov.suvinfoservice.menu.Action;
import ru.kuleshov.suvinfoservice.menu.handler.MenuHandler;
import ru.kuleshov.suvinfoservice.menu.keyboard.ActionKeyboard;
import ru.kuleshov.suvinfoservice.menu.keyboard.AdminKeyboard;
import ru.kuleshov.suvinfoservice.menu.keyboard.MainKeyboard;
import ru.kuleshov.suvinfoservice.state.dao.DaoState;

/**
 * Сервис всевластия
 */
@Getter
@Service
public class AbsoluteService {
    private final MenuHandler menuHandler;
    private final Action action;
    private final MainKeyboard mainKeyboard;
    private final ActionKeyboard actionKeyboard;
    private final AdminKeyboard adminKeyboard;
    private final DaoState daoState;
    private final EventService eventService;
    private final PeopleService peopleService;
    private final UserService userService;

    public AbsoluteService(MenuHandler menuHandler, Action action, MainKeyboard mainKeyboard, ActionKeyboard actionKeyboard,
                           AdminKeyboard adminKeyboard, DaoState daoState, EventService eventService, PeopleService peopleService,
                           UserService userService) {
        this.menuHandler = menuHandler;
        this.action = action;
        this.mainKeyboard = mainKeyboard;
        this.actionKeyboard = actionKeyboard;
        this.adminKeyboard = adminKeyboard;

        this.daoState = daoState;

        this.eventService = eventService;
        this.peopleService = peopleService;
        this.userService = userService;
    }
}
