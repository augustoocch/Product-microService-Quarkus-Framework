package org.augustoocc.data;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.augustoocc.domain.Product;

import javax.enterprise.context.ApplicationScoped;

@Getter
@Setter
public class ProductMessage {

    private Long id;
    private Product product;

    public ProductMessage (Long id, Product p) {
        this.product = p;
        this.id = id;
    }


}
