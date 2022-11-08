package com.example.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.Entity.Person;
import org.springframework.jdbc.core.RowMapper;

public class PersonsMapper implements RowMapper<Person> {

    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setName(rs.getString("name"));
        return person;
    }
}