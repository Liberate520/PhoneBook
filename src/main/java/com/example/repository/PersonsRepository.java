package com.example.repository;

import java.util.List;

import com.example.Entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PersonsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createPerson(String name){
        return jdbcTemplate.update("INSERT INTO \"Persons\" (\"name\") VALUES(?)", name);
    }

    public int updatePerson(Person person){
        return jdbcTemplate.update("UPDATE \"Persons\" SET \"name\" = ? WHERE \"id\" = ?", person.getName(), person.getId());
    }

    public int deletePerson(Integer id){
        return jdbcTemplate.update("DELETE FROM \"Persons\" WHERE \"id\" = ?",id);
    }

    public Person getPerson(Integer id){
        return jdbcTemplate.queryForObject("SELECT * FROM \"Persons\" WHERE \"id\"=?", new PersonsMapper(), id);
    }

    public List<Person> getPersons(){
        return jdbcTemplate.query("SELECT * FROM \"Persons\"", new PersonsMapper());
    }
}