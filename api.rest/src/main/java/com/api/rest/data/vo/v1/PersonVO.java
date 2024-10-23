package com.api.rest.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

public class PersonVO implements Serializable {

    private Long id;
    private String fistName;
    private String lastName;
    private String address;
    private String gender;

    public PersonVO(Long id, String fistName, String lastName, String address, String gender) {
        this.id = id;
        this.fistName = fistName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public PersonVO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonVO person = (PersonVO) o;
        return Objects.equals(id, person.id) && Objects.equals(fistName, person.fistName) && Objects.equals(lastName, person.lastName) && Objects.equals(address, person.address) && Objects.equals(gender, person.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fistName, lastName, address, gender);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
