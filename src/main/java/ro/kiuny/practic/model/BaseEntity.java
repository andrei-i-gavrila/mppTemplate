package ro.kiuny.practic.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity<T> {

    @Id
    @GeneratedValue
    private T id;
}
