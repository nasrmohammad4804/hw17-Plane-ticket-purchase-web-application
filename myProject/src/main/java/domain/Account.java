package domain;

import base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance //
@DiscriminatorColumn(name = Account.ENTITY_NAME)
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "define_unique",columnNames = {Account.USER_NAME})
})
public class Account extends BaseEntity<Long>{

    public static final String ENTITY_NAME="entity_name";
    public static final String USER_NAME="user_name";
    public static final String PASSWORD="password";
    public static final String ACCOUNT_OPENED_TIME="time_opened";

    @Column(name = USER_NAME)
    private String userName;

    @Column(name = PASSWORD,nullable = false)
    private String password;

    @Column(name = ACCOUNT_OPENED_TIME)
    private LocalDateTime localDateTime;

    @Column(nullable = false)
    private String name;

}
