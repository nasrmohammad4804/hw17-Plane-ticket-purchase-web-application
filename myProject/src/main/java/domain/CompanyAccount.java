package domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@DiscriminatorValue(value = "company_account")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "define_unique",columnNames = {CompanyAccount.COMPANY_NUMBER})
})
public class CompanyAccount extends Account{

    public static final String COMPANY_NUMBER="company_number";

    @Column(name = COMPANY_NUMBER)
    private String companyNumber;


    @OneToMany(mappedBy = "companyAccount",cascade = CascadeType.ALL)
    private Set<TicketCompanyAccount> ticketCompanyAccounts=new LinkedHashSet<>();

    @Builder
    public CompanyAccount(String userName, String password, LocalDateTime localDateTime, String name, String companyNumber) {
        super(userName, password, localDateTime, name);
        this.companyNumber = companyNumber;
    }
    public CompanyAccount(Long id){
        super.setId(id);
    }
}
