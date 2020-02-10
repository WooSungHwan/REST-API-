package co.worker.board.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@ToString
@Table(name = "UserEntity")
public class UserEntity {
    @Id @GeneratedValue
    private Long seq;
    @UniqueElements
    private String id;
    private String name;
    private String password;
}
