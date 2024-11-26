package ru.kuleshov.suvinfoservice.handler;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kuleshov.suvinfoservice.service.AbsoluteService;
import ru.kuleshov.suvinfoservice.bot.TelegramBot;
import ru.kuleshov.suvinfoservice.command.Action;
import ru.kuleshov.suvinfoservice.command.MainMenu;

@Setter
@Slf4j
@Service
public class MenuHandler {

    private static final String TAG = "MENU HANDLER";

    private TelegramBot bot;
    private AbsoluteService absoluteService;

    private MainMenu mainMenu = MainMenu.DEFAULT;
    private Action action = Action.DEFAULT;

    // Взаимодействие с пользователем
    public void selectingButtonMenu(Message msg) throws Exception {
        log.info("{} - selectingButtonMenu", TAG);

        String messageText = msg.getText();

        if (messageText.equalsIgnoreCase("/start")) {
            if (absoluteService.getUserService().checkUser(msg.getChatId())) {
                absoluteService.getFistMenu().sendMainMenu(msg.getChatId().toString(), mainMenu, bot);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(msg.getFrom().getFirstName());
                sb.append(", у Вас нет разрешения на работу с сервисом.\n\n");
                sb.append("Для получения разрешения, необходимо отправить администратору ваш идентификатор:\n\n");
                sb.append(msg.getChatId());
                sendResponse(msg, sb.toString());
            }

            return;
        }

        if (mainMenu.equals(MainMenu.DEFAULT)) {
            log.info("{} - mainMenu {}", TAG, messageText);
            switch (MainMenu.from(messageText)) {
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
                    absoluteService.getFistMenu().sendMainMenu(msg.getChatId().toString(), mainMenu, bot);
                    break;
                case LIST_PEOPLE:
                    log.info("{} - touchListPeople", TAG);
                    break;
                case ENTRANCE:
                    log.info("{} - touchEntrance", TAG);
                    absoluteService.getSecondMenu().sendActionMenu(msg.getChatId().toString(), action, bot);
                    break;

                // Обработка неизвестной команды
                default:
                    if (MainMenu.from(messageText).equals(MainMenu.UNDEFINED_MAIN) &&
                            Action.from(messageText).equals(Action.UNDEFINED_ACTION)) {
                        log.info("{} - UNDEFINED", TAG);
                        sendResponse(msg, "\u274c" + " Неизвестная команда, попробуйте еще раз! " + "\u274c");
                    }
                    break;
            }
        }

        if (action.equals(Action.DEFAULT)) {
            log.info("{} - action {}", TAG, messageText);
            switch (Action.from(messageText)) {
                case FIND:
                    log.info("{} - touchFind", TAG);
                    break;
                case ADD_EVENT:
                    log.info("{} - touchActionAddEvent", TAG);
                    absoluteService.getSecondMenu().sendActionMenu(msg.getChatId().toString(), action, bot);
                    break;
                case DELETE_EVENT:
                    log.info("{} - touchActionDeleteEvent", TAG);
                    absoluteService.getSecondMenu().sendActionMenu(msg.getChatId().toString(), action, bot);
                    break;
                // Обработка нажатия кнопки "НАЗАД"
                case BACK:
                    log.info("{} - touchActionBack", TAG);
                    absoluteService.getFistMenu().sendMainMenu(msg.getChatId().toString(), mainMenu, bot);
                    return;
            }
        }
    }

    // Отправка ответов пользователю
    public void sendResponse(Message message, String text) throws TelegramApiException {
        log.info("{} - sendResponse", TAG);
        SendMessage sendMsg = new SendMessage();
        sendMsg.setChatId(String.valueOf(message.getChatId()));
        sendMsg.setText(text);
        bot.execute(sendMsg);
    }
}