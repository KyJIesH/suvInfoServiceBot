package ru.kuleshov.suvinfoservice.menu.handler;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kuleshov.suvinfoservice.bot.TelegramBot;
import ru.kuleshov.suvinfoservice.menu.command.ActionCommand;
import ru.kuleshov.suvinfoservice.menu.command.AdminMenuCommand;
import ru.kuleshov.suvinfoservice.menu.command.MainMenuCommand;
import ru.kuleshov.suvinfoservice.service.AbsoluteService;

@Setter
@Slf4j
@Service
public class MenuHandler {

    private static final String TAG = "MENU HANDLER";

    private TelegramBot bot;
    private AbsoluteService absoluteService;

    /*private MainMenuCommand mainMenuCommand = MainMenuCommand.DEFAULT;
    private ActionCommand actionCommand = ActionCommand.DEFAULT;
    private AdminMenuCommand adminMenuCommand = AdminMenuCommand.DEFAULT;*/

    // Взаимодействие с пользователем
    public void selectingButtonMenu(Message msg) throws Exception {
        log.info("{} - selectingButtonMenu", TAG);

        /*String messageText = msg.getText();

        if (messageText.equalsIgnoreCase("/start")) {
            log.info("{} - start", TAG);

            if (absoluteService.getUserService().checkUser(msg.getChatId())) {
                absoluteService.getMainKeyboard().sendMainMenu(msg.getChatId().toString(), mainMenuCommand, bot);
            } else {
                messageNeedAuthorisation(msg);
            }
            return;
        }*/

        if (absoluteService.getAction().viewStart(msg, absoluteService, bot)) {
            log.info("{} - selected button menu START", TAG);
            return;
        }

        if (!absoluteService.getUserService().checkUser(msg.getChatId())) {
            log.info("{} - Проверка наличия доступа пользователя", TAG);
            absoluteService.getAction().messageNeedAuthorisation(msg, bot);
            return;
        }

        /*if (mainMenuCommand.equals(MainMenuCommand.DEFAULT)) {
            log.info("{} - mainMenu {}", TAG, messageText);

            if (!absoluteService.getUserService().checkUser(msg.getChatId())) {
                messageNeedAuthorisation(msg);
                return;
            }

            switch (MainMenuCommand.from(messageText)) {
                case INFO:
                    log.info("{} - touchInfo", TAG);
                    StringBuilder sbInfo = new StringBuilder();
                    sbInfo.append("Приветствую, ").append(msg.getFrom().getFirstName()).append("!\n");
                    sbInfo.append("Данный бот предназначен для автоматизации задач воспитателя.\n\n");
                    sbInfo.append("СПИСОК КОМАНД: ").append("\n");
                    sbInfo.append("- РАСХОД ЛС - отображение расхода ЛС;").append("\n");
                    sbInfo.append("- ВВОД ФИО - отображение всех событий по ФИО;").append("\n");
                    sbInfo.append("- ДОБАВИТЬ - добавление события;").append("\n");
                    sbInfo.append("- УДАЛИТЬ - удаление события;").append("\n");

                    sendResponse(msg, sbInfo.toString());
                    absoluteService.getMainKeyboard().sendMainMenu(msg.getChatId().toString(), mainMenuCommand, bot);
                    break;
                case LIST_PEOPLE:
                    log.info("{} - touchListPeople", TAG);
                    break;
                case ENTRANCE:
                    log.info("{} - touchEntrance", TAG);
                    absoluteService.getActionKeyboard().sendActionMenu(msg.getChatId().toString(), actionCommand, bot);
                    break;
                case ADMIN:
                    log.info("{} - touchAdmin", TAG);
                    if (!absoluteService.getUserService().getAdmin(msg.getChatId())) {
                        messageNeedAuthorisation(msg);
                        return;
                    }
                    absoluteService.getAdminKeyboard().sendAdminMenu(msg.getChatId().toString(), adminMenuCommand, bot);
                    break;

                // Обработка неизвестной команды
                default:
                    if (MainMenuCommand.from(messageText).equals(MainMenuCommand.UNDEFINED_MAIN) &&
                            ActionCommand.from(messageText).equals(ActionCommand.UNDEFINED_ACTION) &&
                            AdminMenuCommand.from(messageText).equals(AdminMenuCommand.UNDEFINED_ADMIN)) {
                        log.info("{} - UNDEFINED", TAG);
                        sendResponse(msg, "\u274c" + " Неизвестная команда, попробуйте еще раз! " + "\u274c");
                    }
                    break;
            }
        }*/

        if (!absoluteService.getAction().viewMainMenu(msg, absoluteService, bot)) {
            log.info("{} - selected button menu MAIN", TAG);
            return;
        }


        /*if (adminMenuCommand.equals(AdminMenuCommand.DEFAULT)) {
            log.info("{} - adminMenu {}", TAG, messageText);

            switch (AdminMenuCommand.from(messageText)) {
                case ADD_USER:
                    log.info("{} - touchAdminAddUser", TAG);
                    sendResponse(msg, "Введите telegramId нового пользователя");


                    //TODO
//                    User user = absoluteService.getUserService().createUser(Long.parseLong(messageText));

                    break;
                case DELETE_USER:
                    log.info("{} - touchAdminDeleteUser", TAG);
                    break;
                case CREATE_ADMIN:
                    log.info("{} - touchAdminCreateAdmin", TAG);
                    break;
            }
        }*/

        if (!absoluteService.getAction().viewAdminMenu(msg, absoluteService, bot)) {
            log.info("{} - selected button menu ADMIN", TAG);
            return;
        }

        /*if (actionCommand.equals(ActionCommand.DEFAULT)) {
            log.info("{} - actionMenu {}", TAG, messageText);

            if (!absoluteService.getUserService().checkUser(msg.getChatId())) {
                messageNeedAuthorisation(msg);
                return;
            }

            switch (ActionCommand.from(messageText)) {
                case FIND:
                    log.info("{} - touchFind", TAG);
                    break;
                case ADD_EVENT:
                    log.info("{} - touchActionAddEvent", TAG);
                    absoluteService.getActionKeyboard().sendActionMenu(msg.getChatId().toString(), actionCommand, bot);
                    break;
                case DELETE_EVENT:
                    log.info("{} - touchActionDeleteEvent", TAG);
                    absoluteService.getActionKeyboard().sendActionMenu(msg.getChatId().toString(), actionCommand, bot);
                    break;
                // Обработка нажатия кнопки "НАЗАД"
                case BACK:
                    log.info("{} - touchActionBack", TAG);
                    absoluteService.getMainKeyboard().sendMainMenu(msg.getChatId().toString(), mainMenuCommand, bot);
                    return;
            }
        }*/

        if (!absoluteService.getAction().viewActionMenu(msg, absoluteService, bot)) {
            log.info("{} - selected button menu ACTION", TAG);
            return;
        }
    }

/*    // Отправка ответов пользователю
    private void sendResponse(Message message, String text) throws TelegramApiException {
        log.info("{} - Отправка ответа", TAG);
        SendMessage sendMsg = new SendMessage();
        sendMsg.setChatId(String.valueOf(message.getChatId()));
        sendMsg.setText(text);
        bot.execute(sendMsg);
    }

    // Неавторизованный пользователь отправка ответа
    private void messageNeedAuthorisation(Message msg) throws TelegramApiException {
        log.info("{} - Отправка ответа неавторизованному пользователю", TAG);

        StringBuilder sb = new StringBuilder();
        sb.append(msg.getFrom().getFirstName());
        sb.append(", у Вас нет разрешения на работу с сервисом.\n\n");
        sb.append("Для получения разрешения, необходимо отправить администратору ваш идентификатор:\n\n");
        sb.append(msg.getChatId());
        sendResponse(msg, sb.toString());
    }*/
}