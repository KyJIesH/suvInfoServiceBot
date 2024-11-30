package ru.kuleshov.suvinfoservice.state.dao;

import jakarta.annotation.Nonnull;
import ru.kuleshov.suvinfoservice.state.State;

public interface DaoState {
    @Nonnull
    State viewState(Long chatId);

    void updateState(Long chatId, State newState);

    void waitInputIdUserForAdd(Long chatId);

    void waitInputIdUserForDelete(Long chatId);

    void waitInputIdUserForDelegateAdminRoot(Long chatId);
}
