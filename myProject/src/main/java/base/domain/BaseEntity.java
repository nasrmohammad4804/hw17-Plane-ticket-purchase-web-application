package base.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
public abstract class BaseEntity<ID extends Serializable>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    private Boolean isDeleted;
}
