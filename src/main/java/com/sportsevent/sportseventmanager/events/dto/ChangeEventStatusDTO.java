package com.sportsevent.sportseventmanager.events.dto;

import com.sportsevent.sportseventmanager.common.exception.InvalidDTOException;
import com.sportsevent.sportseventmanager.common.validation.Validatable;

public class ChangeEventStatusDTO implements Validatable {
    private String status;

    public ChangeEventStatusDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void validate() throws InvalidDTOException {
        if (status == null) {
            throw new InvalidDTOException("status is required", 400);
        }

        if (!status.equals("Programado") && !status.equals("En Progreso") && !status.equals("Finalizado") && !status.equals("Cancelado")) {
            throw new InvalidDTOException("status should be \"Programado\" or \"En Progreso\" or \"Finalizado\" or \"Cancelado\"", 400);
        }
    }
}
