package ru.kuleshov.suvinfoservice.menu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kuleshov.suvinfoservice.bot.TelegramBot;
import ru.kuleshov.suvinfoservice.command.MainMenu;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class FirstMenu {

    private static final String TAG = "FIRST MENU";

    public void sendMainMenu(String chatId, MainMenu menu, TelegramBot bot) throws TelegramApiException {
        log.info("{} - sendMainMenu", TAG);
        menu = MainMenu.DEFAULT;

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите пункт меню");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row;

        // Первая строка меню из 1 кнопки
        row = new KeyboardRow();
        row.add("РАСХОД ЛС");
        keyboard.add(row);

        // Вторая строка меню из 1 кнопки
        row = new KeyboardRow();
        row.add("НАЧАЛО РАБОТЫ");
        keyboard.add(row);

        // Третья строка меню из 1 кнопки
        row = new KeyboardRow();
        row.add("ФУНКЦИОНАЛ");
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        bot.execute(message);
    }
}