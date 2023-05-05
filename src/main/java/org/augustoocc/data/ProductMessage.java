package org.augustoocc.data;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.augustoocc.domain.Product;

import javax.enterprise.context.ApplicationScoped;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApplicationScoped
public class ProductMessage {

    private Long id;
    private Product product;


}
