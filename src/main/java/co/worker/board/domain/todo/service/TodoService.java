package co.worker.board.domain.todo.service;

import co.worker.board.domain.todo.model.TodoData;
import co.worker.board.domain.todo.model.TodoParam;
import co.worker.board.domain.todo.repository.TodoRepository;
import co.worker.board.util.TypeChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Object add(TodoParam todoParam) {
        TodoData data = TypeChange.sourceToDestination(todoParam, new TodoData());
        return todoRepository.save(data);
    }

    public Object get(Long seq) {
        return todoRepository.getOne(seq);
    }

    public Object list() {
        return todoRepository.findAll();
    }

    public Object deleteAll() {
        todoRepository.deleteAll();
        return null;
    }

    public Object delete(Long seq) {
        todoRepository.deleteById(seq);
        return null;
    }

    public Object updateCompleted(TodoParam param) {
        TodoData data = TypeChange.sourceToDestination(param, new TodoData());
        return todoRepository.save(data);

    }
}
