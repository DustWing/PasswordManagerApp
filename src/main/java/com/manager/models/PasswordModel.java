package com.manager.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public final class PasswordModel {

    @JsonProperty("id")
    private String id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("domain")
    private String domain;

    @JsonProperty("description")
    private String description;

    @JsonProperty("category")
    private String category;

    public PasswordModel() {
    }

    public PasswordModel(String id, String username, String password,
                         String domain, String description, String category) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.domain = domain;
        this.description = description;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDomain() {
        return domain;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "PasswordModel{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", domain='" + domain + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PasswordModel) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.username, that.username) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.domain, that.domain) &&
                Objects.equals(this.description, that.description) &&
                Objects.equals(this.category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, domain, description, category);
    }
}