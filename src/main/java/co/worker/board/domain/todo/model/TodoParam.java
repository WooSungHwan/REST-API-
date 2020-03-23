package co.worker.board.domain.todo.model;

import lombok.Data;

@Data
public class TodoParam {
    private Long seq;
    private boolean completed;
    private String item;
}
