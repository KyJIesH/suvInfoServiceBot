package ru.kuleshov.suvinfoservice.menu.handler;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.kuleshov.suvinfoservice.bot.TelegramBot;
import ru.kuleshov.suvinfoservice.service.AbsoluteService;
import ru.kuleshov.suvinfoservice.state.State;

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

        if (!absoluteService.getUserService().checkUser(msg.getChatId())) {
            log.info("{} - Проверка наличия доступа пользователя", TAG);
            absoluteService.getAction().messageNeedAuthorisation(msg, bot);
            return;
        }

        State currentUserState = absoluteService.getDaoState().viewState(msg.getChatId());

        switch (currentUserState) {
            case NEW_USER:
                if (absoluteService.getAction().viewStart(msg, absoluteService, bot)) {
                    absoluteService.getDaoState().updateState(msg.getChatId(), State.PROCESSING);
                }
                break;
            case PROCESSING:
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
                break;

            // Обработка меню администратора
            case WAIT_INPUT_ID_USER_FOR_ADD:
                absoluteService.getUserService().createUser(Long.parseLong(msg.getText()));

                absoluteService.getAction().sendResponse(msg, "Пользователь с telegramId: " +
                        msg.getText() + " - ДОБАВЛЕН!", bot);
                absoluteService.getDaoState().updateState(msg.getChatId(), State.PROCESSING);
                break;
            case WAIT_INPUT_ID_USER_FOR_DELETE:
                absoluteService.getUserService().deleteUser(Long.parseLong(msg.getText()));

                absoluteService.getAction().sendResponse(msg, "Пользователь с telegramId: " +
                        msg.getText() + " - УДАЛЕН!", bot);
                absoluteService.getDaoState().updateState(msg.getChatId(), State.PROCESSING);
                break;
            case WAIT_INPUT_ID_USER_FOR_DELEGATE_ADMIN_ROOT:
                absoluteService.getUserService().updateUser(Long.parseLong(msg.getText()));

                absoluteService.getAction().sendResponse(msg, "Пользователь с telegramId: " +
                        msg.getText() + " - Наделен правами АДМИНА!", bot);
                absoluteService.getDaoState().updateState(msg.getChatId(), State.PROCESSING);
                break;
        }
    }
}