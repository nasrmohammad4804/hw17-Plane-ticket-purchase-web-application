package domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@DiscriminatorValue(value = "user_account")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "define_unique", columnNames = {UserAccount.NATIONAL_CODE})
})
public class UserAccount extends Account {

    public static final String NATIONAL_CODE = "national_code";
    public static final String USER_TICKETS = "user_ticket";
    public static final String USER_ID = "user_id";
    public static final String TICKET_WITH_COMPANY = "ticket_of_companies";

    private String family;
    private String nationalCode;

    @Column(columnDefinition = "tinyint(1)")
    private boolean isAdmin;


    @Transient
    private String gender;

    private long Inventory;

    @Builder
    public  UserAccount(String firstName,String lastName, String nationalCode, String gender){
        this.setName(firstName);
        this.family=lastName;
        this.nationalCode=nationalCode;
        this.gender=gender;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = USER_TICKETS, joinColumns = {@JoinColumn(name = USER_ID)},
            inverseJoinColumns = {@JoinColumn(name = TICKET_WITH_COMPANY)})
    private List<TicketCompanyAccount> ticketCompanyAccounts = new LinkedList<>();

    public UserAccount(String userName, String password, LocalDateTime localDateTime, String name) {
        super(userName, password, localDateTime, name);
    }

}
