package ru.kuleshov.suvinfoservice.menu.keyboard;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kuleshov.suvinfoservice.bot.TelegramBot;
import ru.kuleshov.suvinfoservice.menu.command.ActionCommand;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ActionKeyboard {

    private static final String TAG = "SECOND MENU";

    public void sendActionMenu(String chatId, ActionCommand actionCommand, TelegramBot bot) throws TelegramApiException {
        log.info("{} - sendActionMenu", TAG);
        actionCommand = ActionCommand.DEFAULT;

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите действие");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row;

        // Первая строка меню из 1 кнопки
        row = new KeyboardRow();
        row.add("ВВОД ФИО");
        keyboard.add(row);

        // Вторая строка меню из 2 кнопок
        row = new KeyboardRow();
        row.add("ДОБАВИТЬ");
        row.add("УДАЛИТЬ");
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
