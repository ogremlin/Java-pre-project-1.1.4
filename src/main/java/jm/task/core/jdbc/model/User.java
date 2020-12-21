package jm.task.core.jdbc.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    private Long id;

    @Column
    @NonNull
    private String name;

    @Column
    @NonNull
    private String lastName;

    @Column
    @NonNull
    private Byte age;

    @Override
    public String toString() {
        return String.format("| Имя: %s | Фамилия: %s | Возраст: %d |", name, lastName, age);
    }
}
