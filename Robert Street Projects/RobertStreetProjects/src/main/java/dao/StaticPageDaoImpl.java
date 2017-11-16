/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.PreparedStatements.*;
import dao.UserDaoImpl.UserMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Category;
import model.StaticPage;
import model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author kylecaaspers
 */
public class StaticPageDaoImpl implements StaticPageDao{
      private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void addStaticPage(StaticPage staticPage) {
       jdbcTemplate.update(SQL_INSERT_STATIC_PAGE,
               staticPage.getName(),
               staticPage.getContent(),

               staticPage.getUser().getUserId());

       staticPage.setStaticPageId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }

    @Override
    public void deleteStaticPage(int staticPageId) {
        jdbcTemplate.update(SQL_DELETE_STATIC_PAGE, staticPageId);
    }

    @Override
    public void updateStaticPage(StaticPage staticPage) {
        jdbcTemplate.update(SQL_UPDATE_STATIC_PAGE,
                staticPage.getName(), 
                staticPage.getContent(),
                staticPage.getUser().getUserId(),
                staticPage.getStaticPageId());
        
        //delete user and rebuild the ojbect again for the update in this method?
    }

    @Override
    public StaticPage getStaticPageById(int staticPageId) {
    
        try{
            StaticPage staticPage = jdbcTemplate.queryForObject(SQL_GET_STATIC_PAGE_BY_ID, new StaticPageMapper(), staticPageId);
            staticPage.setUser(findUserForStaticPage(staticPage));
       return staticPage;
       
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    
    }

    @Override
    public List<StaticPage> getAllStaticPages() {
    List<StaticPage> allStaticPage = jdbcTemplate.query(SQL_GET_ALL_STATIC_PAGES, new StaticPageMapper());
    return allStaticPage;
    }

    @Override
    public List<StaticPage> getStaticPagesByUser(int userId) {
        try{ 
            List<StaticPage> staticPageList = jdbcTemplate.query(SQL_GET_STATIC_PAGE_PAGE_BY_USER, new StaticPageMapper(), userId);
            return associateStaticPageAndUser(staticPageList);
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
    public User findUserForStaticPage(StaticPage staticPage){
        return jdbcTemplate.queryForObject(SQL_GET_USERS_BY_STATIC_PAGE, new UserMapper(), staticPage.getStaticPageId());
    }
    private List<StaticPage> associateStaticPageAndUser(List<StaticPage> staticPageList){
        for(StaticPage currentStaticPage: staticPageList){
            currentStaticPage.setUser(findUserForStaticPage(currentStaticPage));
        }
        return staticPageList;
    }
    private static final class StaticPageMapper implements RowMapper<StaticPage>{

        @Override
        public StaticPage mapRow(ResultSet rs, int i) throws SQLException {
          
            StaticPage staticPage = new StaticPage();

            staticPage.setName(rs.getString("Name"));
            staticPage.setContent(rs.getString("Content"));
            staticPage.setStaticPageId(rs.getInt("StaticPageId"));
            return staticPage;
        }
        
    }
    
}

