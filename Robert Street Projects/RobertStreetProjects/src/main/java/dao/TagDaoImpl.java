/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.PreparedStatements.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Tag;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kylecaaspers
 */
public class TagDaoImpl implements TagDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Tag addTag(Tag tag) {
        jdbcTemplate.update(SQL_INSERT_TAG, tag.getTagName());
        tag.setTagId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return tag;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteTag(int tagId) {
        jdbcTemplate.update(SQL_DELETE_TAG, tagId);
    }

    @Override
    public void updateTag(Tag tag) {
        jdbcTemplate.update(SQL_UPDATE_TAG, tag.getTagName(), tag.getTagId());
    }

    @Override
    public Tag getTagById(int tagId) {
        try {
            Tag tag = jdbcTemplate.queryForObject(PreparedStatements.SQL_SELECT_TAG, new TagMapper(), tagId);
            return tag;
        } catch (EmptyResultDataAccessException ex) {
            return null;//return if there is no tag asssociated with the Id;
        }

    }

    @Override
    public List<Tag> getAllTags() {
        List<Tag> tags = jdbcTemplate.query(SQL_SELECT_ALL_TAG, new TagMapper());
        return tags;

    }

    public static final class TagMapper implements RowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet rs, int i) throws SQLException {

            Tag tag = new Tag();
            tag.setTagId(rs.getInt("tagId"));
            tag.setTagName(rs.getString("name"));
            return tag;
        }
    }
}
