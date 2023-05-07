package org.augustoocc.validations;

import org.augustoocc.domain.Product;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Validations {

    public boolean postValidation(Product p) {
        if (p.getCode()==null || p.getName()==null || p.getDescription()==null
            || p.getCode().isBlank() || p.getName().isBlank() || p.getDescription().isBlank()) {
            return true;
        } else {
            return false;
        }
    }

}
