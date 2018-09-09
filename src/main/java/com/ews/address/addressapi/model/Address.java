package com.ews.address.addressapi.model;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "TB_ADDRESS")
public class Address extends ResourceSupport {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressID;

    @NotEmpty(message = "O campo rua e obrigatorio!")
    @Column(name = "STREET")
    private String street;

    @NotNull(message = "O campo numero e obrigatorio!")
    @Column(name = "NUMBER")
    private Integer number;

    @NotNull(message = "O campo cep e obrigatorio!")
    @Column(name = "ZIP_CODE")
    private String zipCode;


    @NotEmpty(message = "O campo cidade e obrigatorio!")
    @Column(name = "CITY")
    private String city;

    @NotEmpty(message = "O campo estado e obrigatorio!")
    @Column(name = "STATE")
    private String state;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "COMPLEMENT")
    private String complement;

    public Long getAddressID() {
        return addressID;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressID=" + addressID +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", zipCode=" + zipCode +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", complement='" + complement + '\'' +
                '}';
    }

}
