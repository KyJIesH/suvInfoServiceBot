package ru.kuleshov.suvinfoservice.service;

import ru.kuleshov.suvinfoservice.model.User;

public interface UserService {

    boolean checkUser(Long telegramId);

    boolean getAdmin(Long telegramId);

    User createUser(Long telegramId);
}
