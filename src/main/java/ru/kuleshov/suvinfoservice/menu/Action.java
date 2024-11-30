package ru.kuleshov.suvinfoservice.menu;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kuleshov.suvinfoservice.bot.TelegramBot;
import ru.kuleshov.suvinfoservice.service.AbsoluteService;

public interface Action {

    boolean viewStart(Message msg, AbsoluteService absoluteService, TelegramBot bot) throws TelegramApiException;

    boolean viewMainMenu(Message msg, AbsoluteService absoluteService, TelegramBot bot) throws TelegramApiException;

    boolean viewAdminMenu(Message msg, AbsoluteService absoluteService, TelegramBot bot) throws TelegramApiException;

    boolean viewActionMenu(Message msg, AbsoluteService absoluteService, TelegramBot bot) throws TelegramApiException;

    void sendResponse(Message message, String text, TelegramBot bot) throws TelegramApiException;

    void messageNeedAuthorisation(Message msg, TelegramBot bot) throws TelegramApiException;
}