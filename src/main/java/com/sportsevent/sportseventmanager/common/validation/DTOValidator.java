package com.sportsevent.sportseventmanager.common.validation;

import com.sportsevent.sportseventmanager.common.exception.InvalidDTOException;

public class DTOValidator {
    public static void validate(Validatable dto) throws InvalidDTOException {
        dto.validate();
    }
}
