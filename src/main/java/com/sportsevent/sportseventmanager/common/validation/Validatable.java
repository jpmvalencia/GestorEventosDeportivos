package com.sportsevent.sportseventmanager.common.validation;

import com.sportsevent.sportseventmanager.common.exception.InvalidDTOException;

public interface Validatable {
    void validate() throws InvalidDTOException;
}
