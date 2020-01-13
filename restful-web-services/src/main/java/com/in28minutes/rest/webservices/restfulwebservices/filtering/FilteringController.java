package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        SomeBean someBean = new SomeBean("Field1", "Field2", "Field3");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value1", "value3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> retrieveListSomeBean() {
        return new ArrayList<SomeBean>(Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value1", "value2", "value3")));
    }
}
