package com.example.interview;

import java.util.Objects;

class User {

    public User(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    private int id;

    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id &&
                Objects.equals(userName, user.userName);
    }

    /*@Override
    public int hashCode() {
        return Objects.hash(id, userName);
    }*/

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
