package domain;

import base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = TicketCompanyAccount.TABLE_NAME,uniqueConstraints = {
        @UniqueConstraint(columnNames = {TicketCompanyAccount.COMPANY_ACCOUNT_ID, TicketCompanyAccount.TICKET_ID})
})
public class TicketCompanyAccount extends BaseEntity<Long> {

    public static final String TABLE_NAME="ticket_company_account";
    public static final String TICKET_ID = "ticket_id";
    public static final String COMPANY_ACCOUNT_ID = "company_account_id";

    @ManyToOne
    @JoinColumn(name = TICKET_ID)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = COMPANY_ACCOUNT_ID)
    private CompanyAccount companyAccount;

    public TicketCompanyAccount(CompanyAccount companyAccount) {
        this.companyAccount = companyAccount;
    }

    public void print() {
        System.out.println("id is : " + getId() + "   " + "companyName :" + companyAccount.getName() + "   " +
                "capacity :" + ticket.getCapacity() + "   " + "period of flight :" + ticket.getFlightPeriod() +
                "amount :" + ticket.getAmount() + "   " + "moving time :" + ticket.getTakeOfTime());
    }
    public void printForWhenUserBuyTicket(){
        System.out.println("companyName :"+companyAccount.getName()+"   "+
                "amount :" + ticket.getAmount() + "   " + "moving time :" + ticket.getTakeOfTime());
    }
}
