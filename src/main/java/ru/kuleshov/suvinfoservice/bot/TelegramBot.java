package ru.kuleshov.suvinfoservice.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kuleshov.suvinfoservice.config.BotConfig;
import ru.kuleshov.suvinfoservice.menu.handler.MenuHandler;
import ru.kuleshov.suvinfoservice.service.AbsoluteService;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private static final String TAG = "TELEGRAM BOT";

    private final BotConfig botConfig;
    private final MenuHandler menuHandler;

    @Autowired
    public TelegramBot(BotConfig botConfig, AbsoluteService absoluteService) {
        super(botConfig.getToken());
        this.botConfig = botConfig;
        this.menuHandler = absoluteService.getMenuHandler();

        menuHandler.setBot(this);
        menuHandler.setAbsoluteService(absoluteService);
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("{} - onUpdateReceived", TAG);
        new Thread(new Runnable() {
            public void run() {
                if (update.hasMessage()) {
                    try {
                        menuHandler.selectingButtonMenu(update.getMessage());
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }
}
