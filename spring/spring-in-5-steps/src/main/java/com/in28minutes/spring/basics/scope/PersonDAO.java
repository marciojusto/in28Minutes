package com.in28minutes.spring.basics.scope;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class PersonDAO {

    @Autowired
    JdbcConnection jdbcConnection;
}
