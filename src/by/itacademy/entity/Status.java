package by.itacademy.entity;

import lombok.Getter;

@Getter

public enum Status {

    DONE("DONE"),
    PROCESSED("PROCESSED");

    private String description;

    Status(String description) {
        this.description = description;
    }
}
