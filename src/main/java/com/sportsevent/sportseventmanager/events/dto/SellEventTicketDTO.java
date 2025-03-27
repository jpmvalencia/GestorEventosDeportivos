package com.sportsevent.sportseventmanager.events.dto;

import com.sportsevent.sportseventmanager.common.exception.InvalidDTOException;
import com.sportsevent.sportseventmanager.common.validation.Validatable;

public class SellEventTicketDTO implements Validatable {
    private Integer quantity;

    public SellEventTicketDTO(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public void validate() throws InvalidDTOException {
        if (quantity == null) {
            throw new InvalidDTOException("quantity is required", 400);
        }

        if (quantity <= 0) {
            throw new InvalidDTOException("quantity must be greater than 0", 400);
        }
    }
}
