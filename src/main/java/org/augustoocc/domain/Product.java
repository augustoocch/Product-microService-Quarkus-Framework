package org.augustoocc.domain;


import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends PanacheEntity {

    private String code;
    private String name;
    private String description;


}
