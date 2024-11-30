package ru.kuleshov.suvinfoservice.state.dao;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.kuleshov.suvinfoservice.state.State;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class DaoStateImpl implements DaoState {

    private static final String TAG = "DAO STATE IMPL";

    private Map<Long, State> daoStateMap = new ConcurrentHashMap<>();

    @Override
    @Nonnull
    public State viewState(Long chatId) {
        if (!daoStateMap.containsKey(chatId)) {
            daoStateMap.put(chatId, State.NEW_USER);
        }
        return daoStateMap.get(chatId);
    }

    @Override
    public void updateState(Long chatId, State newState) {
        daoStateMap.put(chatId, newState);
    }

    @Override
    public void waitInputIdUserForAdd(Long chatId) {
        daoStateMap.put(chatId, State.WAIT_INPUT_ID_USER_FOR_ADD);
    }

    @Override
    public void waitInputIdUserForDelete(Long chatId) {
        daoStateMap.put(chatId, State.WAIT_INPUT_ID_USER_FOR_DELETE);
    }

    @Override
    public void waitInputIdUserForDelegateAdminRoot(Long chatId) {
        daoStateMap.put(chatId, State.WAIT_INPUT_ID_USER_FOR_DELEGATE_ADMIN_ROOT);
    }
}
