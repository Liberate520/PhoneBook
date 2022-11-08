package com.example.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.Entity.PhoneNumbers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumbersRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createPhoneNumber(Long value, Integer idPerson){
        return jdbcTemplate.update("INSERT INTO \"Phone\" (\"value\", \"id_person\") VALUES (?,?)", value, idPerson);
    }

    public int updatePhoneNumber(PhoneNumbers phoneNumbers){
        return jdbcTemplate.update("UPDATE \"Phone\" SET \"value\" = ? WHERE \"id\" = ?", phoneNumbers.getValue(), phoneNumbers.getId());
    }

    public int deletePhoneNumbers(Integer id){
        return jdbcTemplate.update("DELETE FROM \"Phone\" WHERE \"id\" = ?", id);
    }

    public JSONArray getPhoneBook(){
        JSONObject json;
        JSONArray jsonArr = new JSONArray();
        try {
            Connection conn=jdbcTemplate.getDataSource().getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT \"Phone\".\"id\" AS \"ID\", \"Phone\".\"value\" AS \"NUMBER\", \"Persons\".\"name\"  AS \"NAMEPERSON\" FROM \"Phone\" LEFT JOIN \"Persons\" ON \"Persons\".\"id\" = \"Phone\".\"id_person\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                json = new JSONObject();
                json.put("name", rs.getString("NAMEPERSON"));
                json.put("value", rs.getString("NUMBER"));
                jsonArr.put(json);
            }
        } catch (SQLException e) {
            e.getLocalizedMessage();
            return null;
        } catch (JSONException e) {
            e.getLocalizedMessage();
            return null;
        }
        return jsonArr;
    }
}
