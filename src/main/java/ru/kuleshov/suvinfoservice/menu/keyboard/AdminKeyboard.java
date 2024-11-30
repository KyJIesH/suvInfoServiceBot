package ru.kuleshov.suvinfoservice.menu.keyboard;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kuleshov.suvinfoservice.bot.TelegramBot;
import ru.kuleshov.suvinfoservice.menu.command.AdminMenuCommand;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AdminKeyboard {

    private static final String TAG = "ADMIN MENU";

    public void sendAdminMenu(String chatId, AdminMenuCommand menu, TelegramBot bot) throws TelegramApiException {
        log.info("{} - sendAdminMenu", TAG);
        menu = AdminMenuCommand.DEFAULT;

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите пункт меню");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row;

        // Первая строка меню из 2 кнопок
        row = new KeyboardRow();
        row.add("ДОБАВИТЬ ПОЛЬЗОВАТЕЛЯ");
        row.add("УДАЛИТЬ ПОЛЬЗОВАТЕЛЯ");
        keyboard.add(row);

        // Вторая строка меню из 1 кнопки
        row = new KeyboardRow();
        row.add("ВЫДАТЬ ПРАВА АДМИНИСТРАТОРА");
        keyboard.add(row);

        // Третья строка меню из 1 кнопки
        row = new KeyboardRow();
        row.add("НАЗАД");
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        bot.execute(message);
    }
}
