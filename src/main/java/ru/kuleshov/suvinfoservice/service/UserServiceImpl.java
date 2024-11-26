package ru.kuleshov.suvinfoservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kuleshov.suvinfoservice.model.User;
import ru.kuleshov.suvinfoservice.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String TAG = "USER SERVICE IMPL";

    private UserRepository userRepository;

    @Override
    public Boolean checkUser(Long telegramId) {
        log.info("{} - Проверка доступа пользователя {}", TAG, telegramId);
        Optional<User> user = userRepository.findByTelegramId(telegramId);
        return user.isPresent();
    }
}
