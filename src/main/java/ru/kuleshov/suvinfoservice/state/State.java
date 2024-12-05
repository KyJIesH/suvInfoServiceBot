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
    WAIT_INPUT_ID_USER_FOR_DELEGATE_ADMIN_ROOT("ОЖИДАНИЕ ВВОДА ID ПОЛЬЗОВАТЕЛЯ ДЛЯ НАДЕЛЕНИЯ ПРАВАМИ АДМИНА"),

    //Получение расхода ЛС
    WAIT_INPUT_NUMBER_KURS_FOR_GET_PERSON_LIST("ОЖИДАНИЕ ВВОДА НОМЕРА КУРСА ДЛЯ ПОЛУЧЕНИЯ РАСХОДА ЛС"),

    //Получение списка событий по ФИО
    WAIT_INPUT_LAST_NAME_AND_NAME_FOR_GET_LIST_EVENTS("ОЖИДАНИЕ ВВОДА ФИО ДЛЯ ПОЛУЧЕНИЯ СПИСКА СОБЫТИЙ");

    private final String nameState;
}
