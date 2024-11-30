package ru.kuleshov.suvinfoservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kuleshov.suvinfoservice.model.User;
import ru.kuleshov.suvinfoservice.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String TAG = "USER SERVICE IMPL";

    private UserRepository userRepository;

    @Override
    public boolean checkUser(Long telegramId) {
        log.info("{} - Проверка доступа пользователя {}", TAG, telegramId);
        Optional<User> user = userRepository.findByTelegramId(telegramId);
        return user.isPresent();
    }

    @Override
    public boolean getAdmin(Long telegramId) {
        log.info("{} - Получение списка всех админитстраторов", TAG);
        List<User> admins = userRepository.findByRoleId(1L);
        boolean result = false;

        for (User admin : admins) {
            if (admin.getTelegramId().equals(telegramId)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public User createUser(Long telegramId) {
        log.info("{} - Добавление администратором пользователя с id {}", TAG, telegramId);

        User user = new User();
        user.setTelegramId(telegramId);
        user.setRoleId(2L);
        user.setUseDate(LocalDateTime.now());

        return userRepository.save(user);
    }
}
