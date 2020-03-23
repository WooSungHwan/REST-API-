package co.worker.board.domain.todo.repository;

import co.worker.board.domain.todo.model.TodoData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoData, Long> {
    void deleteById(Long seq);
}
