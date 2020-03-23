package co.worker.board.domain.todo.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "seq")
@Entity
@Table(name = "TodoData")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoData {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private boolean completed;
    private String item;
}
