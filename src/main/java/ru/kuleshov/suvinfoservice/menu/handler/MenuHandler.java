package ru.kuleshov.suvinfoservice.menu.handler;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.kuleshov.suvinfoservice.bot.TelegramBot;
import ru.kuleshov.suvinfoservice.service.AbsoluteService;
import ru.kuleshov.suvinfoservice.state.State;
import ru.kuleshov.suvinfoservice.utils.Utils;

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
            case WAIT_INPUT_ID_USER_FOR_ADD: {

                Long userId = Utils.convertsStringToLong(msg.getText());
                if (userId == null) {
                    absoluteService.getAction().messageIncorrectInput(msg, bot);
                    absoluteService.getDaoState().updateState(msg.getChatId(), State.PROCESSING);
                    break;
                }

                absoluteService.getUserService().createUser(userId);

                absoluteService.getAction().sendResponse(msg, "Пользователь с telegramId: " +
                        userId + " - ДОБАВЛЕН!", bot);
                absoluteService.getDaoState().updateState(msg.getChatId(), State.PROCESSING);
                break;
            }
            case WAIT_INPUT_ID_USER_FOR_DELETE: {

                Long userId = Utils.convertsStringToLong(msg.getText());
                if (userId == null) {
                    absoluteService.getAction().messageIncorrectInput(msg, bot);
                    absoluteService.getDaoState().updateState(msg.getChatId(), State.PROCESSING);
                    break;
                }

                absoluteService.getUserService().deleteUser(Long.parseLong(msg.getText()));

                absoluteService.getAction().sendResponse(msg, "Пользователь с telegramId: " +
                        msg.getText() + " - УДАЛЕН!", bot);
                absoluteService.getDaoState().updateState(msg.getChatId(), State.PROCESSING);
                break;
            }
            case WAIT_INPUT_ID_USER_FOR_DELEGATE_ADMIN_ROOT: {

                Long userId = Utils.convertsStringToLong(msg.getText());
                if (userId == null) {
                    absoluteService.getAction().messageIncorrectInput(msg, bot);
                    absoluteService.getDaoState().updateState(msg.getChatId(), State.PROCESSING);
                    break;
                }

                absoluteService.getUserService().updateUser(Long.parseLong(msg.getText()));

                absoluteService.getAction().sendResponse(msg, "Пользователь с telegramId: " +
                        msg.getText() + " - Наделен правами АДМИНА!", bot);
                absoluteService.getDaoState().updateState(msg.getChatId(), State.PROCESSING);
                break;
            }

            // Обработка получения расхода ЛС
            case WAIT_INPUT_NUMBER_KURS_FOR_GET_PERSON_LIST: {

                Long numberKurs = Utils.convertsStringToLong(msg.getText());
                if (numberKurs == null || numberKurs > 4 || numberKurs < 1) {
                    absoluteService.getAction().messageIncorrectInput(msg, bot);
                    absoluteService.getDaoState().updateState(msg.getChatId(), State.PROCESSING);
                    break;
                }

                absoluteService.getAction().sendResponse(msg, "Расход ЛС " + numberKurs + " курса на " +
                        absoluteService.getAction().getDateFormatter(), bot);
                absoluteService.getAction().sendResponse(msg, absoluteService.getPeopleService().getListPeople(numberKurs), bot);
                absoluteService.getDaoState().updateState(msg.getChatId(), State.PROCESSING);
                break;
            }
        }
    }
}