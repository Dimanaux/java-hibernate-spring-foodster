package com.example.food.db.repositories;

import com.example.food.db.entities.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
public class JdbcIngredientRepo implements IngredientRepo {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<Ingredient> ingredientRowMapper = (rs, i) -> Ingredient.builder()
            .id(rs.getInt("id"))
            .name(rs.getString("name"))
            .build();

    @Autowired
    public JdbcIngredientRepo(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
//        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Ingredient> findAll() {
        return jdbcTemplate.query("SELECT * FROM ingredient", ingredientRowMapper);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "INSERT INTO ingredient (name) VALUES (?);",
                ingredient.getName()
        );
        return jdbcTemplate.queryForObject(
                "SELECT * FROM ingredient WHERE name = ? LIMIT 1",
                ingredientRowMapper,
                ingredient.getName()
        );
    }

    @Override
    public List<Ingredient> findAllById(List<Integer> ids) {
        return namedParameterJdbcTemplate.query(
                "SELECT * FROM ingredient WHERE id IN (:ids)",
                Collections.singletonMap("ids", ids),
                ingredientRowMapper
        );
    }

    @Override
    public List<Ingredient> findAllByName(List<String> names) {
        return namedParameterJdbcTemplate.query(
                "SELECT * FROM ingredient WHERE name IN (:names)",
                Collections.singletonMap("names", names),
                ingredientRowMapper
        );
    }
}
