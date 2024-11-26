package ru.kuleshov.suvinfoservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.kuleshov.suvinfoservice.bot.TelegramBot;

@Slf4j
@Component
public class BotInitializer {
    private static final String TAG = "BOT INITIALIZER";
    private final TelegramBot telegramBot;

    @Autowired
    public BotInitializer(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(telegramBot);
            log.info("{} - TelegramAPI started. Look for messages", TAG);
        } catch (TelegramApiException e) {
            log.error("{} - TelegramBot error", TAG, e);
        }
    }
}
