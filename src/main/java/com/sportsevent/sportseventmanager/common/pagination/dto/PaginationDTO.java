package com.sportsevent.sportseventmanager.common.pagination.dto;

import com.sportsevent.sportseventmanager.common.exception.InvalidDTOException;
import com.sportsevent.sportseventmanager.common.validation.Validatable;

public class PaginationDTO implements Validatable {
    private Integer page;
    private Integer size;

    public PaginationDTO(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PaginationDTO{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }

    @Override
    public void validate() throws InvalidDTOException {
        if (page == null) {
            page = 1;
        }

        if (size == null) {
            size = 10;
        }

        if (page < 1) {
            throw new InvalidDTOException("page must be greater than 0", 400);
        }

        if (size < 1) {
            throw new InvalidDTOException("size must be greater than 0", 400);
        }

        if (size > 100) {
            throw new InvalidDTOException("size must be less than 100", 400);
        }
    }
}
