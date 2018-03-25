package by.itacademy.entity;

import lombok.Getter;

@Getter

public enum Role {

    ADMIN("ADMIN"),
    CLIENT("CLIENT");

    private String description;

    Role(String description) {
        this.description = description;
    }
}
