package domain;

import base.domain.BaseEntity;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends BaseEntity<Long> {

    public static final String MOVING_TIME="moving_time";
    public static final String ORIGIN_CITY="origin_city";
    public static final String DESTINATION_CITY="destination_city";
    public static final String FLIGHT_PERIOD="flight_period";

    @Column(name = ORIGIN_CITY,nullable = false)
    private String originCity;

    @Column(name = DESTINATION_CITY,nullable = false)
    private String destinationCity;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Integer capacity;

    @Column(name = MOVING_TIME,nullable = false)
    private LocalDateTime takeOfTime;

    @Column(name =FLIGHT_PERIOD ,nullable = false)
    private LocalTime flightPeriod;

    Pair<Long,LocalDateTime> discount;

    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL)
    private List<TicketCompanyAccount> ticketCompanyAccounts=new LinkedList<>();

    public Ticket(String originCity, String destinationCity, Long amount, Integer capacity, LocalDateTime takeOfTime, LocalTime flightPeriod) {
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.amount = amount;
        this.capacity = capacity;
        this.takeOfTime = takeOfTime;
        this.flightPeriod = flightPeriod;
    }
}
