package ru.kuleshov.suvinfoservice.menu.handler;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.kuleshov.suvinfoservice.bot.TelegramBot;
import ru.kuleshov.suvinfoservice.service.AbsoluteService;

@Setter
@Slf4j
@Service
public class MenuHandler {

    private static final String TAG = "MENU HANDLER";

    private TelegramBot bot;
    private AbsoluteService absoluteService;

    // Взаимодействие с пользователем
    public void selectingButtonMenu(Message msg) throws Exception {
        log.info("{} - selectingButtonMenu", TAG);

        if (absoluteService.getAction().viewStart(msg, absoluteService, bot)) {
            log.info("{} - selected button menu START", TAG);
            return;
        }

        if (!absoluteService.getUserService().checkUser(msg.getChatId())) {
            log.info("{} - Проверка наличия доступа пользователя", TAG);
            absoluteService.getAction().messageNeedAuthorisation(msg, bot);
            return;
        }

        if (!absoluteService.getAction().viewMainMenu(msg, absoluteService, bot)) {
            log.info("{} - selected button menu MAIN", TAG);
            return;
        }

        if (!absoluteService.getAction().viewAdminMenu(msg, absoluteService, bot)) {
            log.info("{} - selected button menu ADMIN", TAG);
            return;
        }

        if (!absoluteService.getAction().viewActionMenu(msg, absoluteService, bot)) {
            log.info("{} - selected button menu ACTION", TAG);
            return;
        }
    }
}