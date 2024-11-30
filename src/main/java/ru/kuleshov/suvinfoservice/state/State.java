package ru.kuleshov.suvinfoservice.state;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum State {

    //Основная ветка
    NEW_USER("НОВЫЙ ПОЛЬЗОВАТЕЛЬ"),
    PROCESSING("В ПРОЦЕССЕ ВЫБОРА"),

    //Функциональность админа
    WAIT_INPUT_ID_USER_FOR_ADD("ОЖИДАНИЕ ВВОДА ID ПОЛЬЗОВАТЕЛЯ ДЛЯ ДОБАВЛЕНИЯ"),
    WAIT_INPUT_ID_USER_FOR_DELETE("ОЖИДАНИЕ ВВОДА ID ПОЛЬЗОВАТЕЛЯ ДЛЯ УДАЛЕНИЯ"),
    WAIT_INPUT_ID_USER_FOR_DELEGATE_ADMIN_ROOT("ОЖИДАНИЕ ВВОДА ID ПОЛЬЗОВАТЕЛЯ ДЛЯ НАДЕЛЕНИЯ ПРАВАМИ АДМИНА");

    private final String nameState;
}
