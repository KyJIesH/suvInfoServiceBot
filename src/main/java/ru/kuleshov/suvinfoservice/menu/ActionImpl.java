package ru.kuleshov.suvinfoservice.menu;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kuleshov.suvinfoservice.bot.TelegramBot;
import ru.kuleshov.suvinfoservice.menu.command.ActionCommand;
import ru.kuleshov.suvinfoservice.menu.command.AdminMenuCommand;
import ru.kuleshov.suvinfoservice.menu.command.MainMenuCommand;
import ru.kuleshov.suvinfoservice.service.AbsoluteService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Setter
@Component
public class ActionImpl implements Action {

    private static final String TAG = "ACTION IMPL";

    private MainMenuCommand mainMenuCommand = MainMenuCommand.DEFAULT;
    private ActionCommand actionCommand = ActionCommand.DEFAULT;
    private AdminMenuCommand adminMenuCommand = AdminMenuCommand.DEFAULT;

    @Override
    public boolean viewStart(Message msg, AbsoluteService absoluteService, TelegramBot bot) throws TelegramApiException {
        log.info("{} - start", TAG);
        if (!msg.getText().equalsIgnoreCase("/start")) {
            return false;
        }

        if (absoluteService.getUserService().checkUser(msg.getChatId())) {
            absoluteService.getMainKeyboard().sendMainMenu(msg.getChatId().toString(), mainMenuCommand, bot);
            log.info("{} - пользователь найден - выход из метода", TAG);
        }
        return true;
    }

    @Override
    public boolean viewMainMenu(Message msg, AbsoluteService absoluteService, TelegramBot bot) throws TelegramApiException {
        if (mainMenuCommand.equals(MainMenuCommand.DEFAULT)) {
            log.info("{} - mainMenu {}", TAG, msg.getText());

            switch (MainMenuCommand.from(msg.getText())) {
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

                    sendResponse(msg, sbInfo.toString(), bot);
                    absoluteService.getMainKeyboard().sendMainMenu(msg.getChatId().toString(), mainMenuCommand, bot);
                    break;
                case LIST_PEOPLE:
                    log.info("{} - touchListPeople", TAG);
                    sendResponse(msg, "Введите номер учебного курса", bot);
                    absoluteService.getDaoState().waitInputNumberKurs(msg.getChatId());
                    break;
                case ENTRANCE:
                    log.info("{} - touchEntrance", TAG);
                    absoluteService.getActionKeyboard().sendActionMenu(msg.getChatId().toString(), actionCommand, bot);
                    break;
                case ADMIN:
                    log.info("{} - touchAdmin", TAG);
                    if (!absoluteService.getUserService().getAdmin(msg.getChatId())) {
                        messageNeedAuthorisation(msg, bot);
                        return false;
                    }
                    absoluteService.getAdminKeyboard().sendAdminMenu(msg.getChatId().toString(), adminMenuCommand, bot);
                    break;

                // Обработка неизвестной команды
                default:
                    if (MainMenuCommand.from(msg.getText()).equals(MainMenuCommand.UNDEFINED_MAIN) &&
                            ActionCommand.from(msg.getText()).equals(ActionCommand.UNDEFINED_ACTION) &&
                            AdminMenuCommand.from(msg.getText()).equals(AdminMenuCommand.UNDEFINED_ADMIN)) {
                        log.info("{} - UNDEFINED", TAG);
                        sendResponse(msg, "\u274c" + " Неизвестная команда, попробуйте еще раз! " + "\u274c", bot);
                    }
                    break;
            }
        }
        return true;
    }

    @Override
    public boolean viewAdminMenu(Message msg, AbsoluteService absoluteService, TelegramBot bot) throws TelegramApiException {
        if (adminMenuCommand.equals(AdminMenuCommand.DEFAULT)) {
            log.info("{} - adminMenu {}", TAG, msg.getText());

            switch (AdminMenuCommand.from(msg.getText())) {
                case ADD_USER:
                    log.info("{} - touchAdminAddUser", TAG);
                    sendResponse(msg, "Введите telegramId нового пользователя", bot);
                    absoluteService.getDaoState().waitInputIdUserForAdd(msg.getChatId());
                    break;
                case DELETE_USER:
                    log.info("{} - touchAdminDeleteUser", TAG);
                    sendResponse(msg, "Введите telegramId удаляемого пользователя", bot);
                    absoluteService.getDaoState().waitInputIdUserForDelete(msg.getChatId());
                    break;
                case CREATE_ADMIN:
                    log.info("{} - touchAdminCreateAdmin", TAG);
                    sendResponse(msg, "Введите telegramId пользователя для наделения правами администратора", bot);
                    absoluteService.getDaoState().waitInputIdUserForDelegateAdminRoot(msg.getChatId());
                    break;
                // Обработка нажатия кнопки "НАЗАД"
                case BACK:
                    log.info("{} - touchAdminBack", TAG);
                    absoluteService.getMainKeyboard().sendMainMenu(msg.getChatId().toString(), mainMenuCommand, bot);
                    return false;
            }
        }
        return true;
    }

    @Override
    public boolean viewActionMenu(Message msg, AbsoluteService absoluteService, TelegramBot bot) throws TelegramApiException {
        if (actionCommand.equals(ActionCommand.DEFAULT)) {
            log.info("{} - actionMenu {}", TAG, msg.getText());

            if (!absoluteService.getUserService().checkUser(msg.getChatId())) {
                messageNeedAuthorisation(msg, bot);
                return false;
            }

            switch (ActionCommand.from(msg.getText())) {
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
            }
        }
        return true;
    }

    @Override
    public String getDateFormatter() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    // Отправка ответов пользователю
    @Override
    public void sendResponse(Message message, String text, TelegramBot bot) throws TelegramApiException {
        log.info("{} - Отправка ответа", TAG);
        SendMessage sendMsg = new SendMessage();
        sendMsg.setChatId(String.valueOf(message.getChatId()));
        sendMsg.setText(text);
        bot.execute(sendMsg);
    }

    // Неавторизованный пользователь отправка ответа
    @Override
    public void messageNeedAuthorisation(Message msg, TelegramBot bot) throws TelegramApiException {
        log.info("{} - Отправка ответа неавторизованному пользователю", TAG);

        StringBuilder sb = new StringBuilder();
        sb.append(msg.getFrom().getFirstName());
        sb.append(", у Вас нет разрешения на работу с сервисом.\n\n");
        sb.append("Для получения разрешения, необходимо отправить администратору ваш идентификатор:\n\n");
        sb.append(msg.getChatId());
        sendResponse(msg, sb.toString(), bot);
    }

    // Некорректный ввод данных
    @Override
    public void messageIncorrectInput(Message msg, TelegramBot bot) throws TelegramApiException {
        log.info("{} - Отправка ответа ввод некорректных данных", TAG);

        StringBuilder sb = new StringBuilder();
        sb.append(msg.getFrom().getFirstName());
        sb.append(", Вы ввели некорректные данные.\n\n");
        sb.append("Пожалуйста выполните ввод корректных данных.\n\n");
        sendResponse(msg, sb.toString(), bot);
    }
}
