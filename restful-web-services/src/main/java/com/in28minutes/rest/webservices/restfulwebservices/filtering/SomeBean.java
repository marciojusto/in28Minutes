package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonFilter("SomeBeanFilter")
public class SomeBean {

    private String value1;
    private String value2;
    private String value3;

    public SomeBean(String value1, String value2, String value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }

    public String getValue3() {
        return value3;
    }
}
