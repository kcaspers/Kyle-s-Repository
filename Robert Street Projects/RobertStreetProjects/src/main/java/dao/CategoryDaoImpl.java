/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Category;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kylecaaspers
 */
public class CategoryDaoImpl implements CategoryDao{

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    

//   Need to create BlogPost Mapper... etc
//    public List<BlogPost> findBlogPostsForCategory(Category category) {
//        return jdbcTemplate.query(PreparedStatements.SQL_SELECT_ORGANIZATIONS_BY_SUPER_ID,
//                new BlogPostMapper(),
//                category.getCategoryId());
//    }


//    @Override
//    public List<Category> associateBlogPostsWithCategory(List<Category> categoryList) {
//        for (Category currentCategory : categoryList) {
//            currentCategory.setBlogPost(findBlogPostsForCategory(currentCategory));
//        }
//        return categoryList;
//    }

    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Category addCategory(Category category) {
        jdbcTemplate.update(PreparedStatements.SQL_INSERT_CATEGORY, 
                category.getName());
        category.setCategoryId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                Integer.class));
        
        return category;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteCategory(int categoryId) {
//        jdbcTemplate.update(PreparedStatements) Need Prepared Statement to Delete BlogPost with certain category ID
        jdbcTemplate.update(PreparedStatements.SQL_DELETE_CATEGORY, 
                categoryId);
    }

    @Override
    public void updateCategory(Category category) {
        jdbcTemplate.update(PreparedStatements.SQL_UPDATE_CATEGORY, 
                category.getName(),
                category.getCategoryId());
    }
    
    @Override
    public Category getCategoryById(int categoryId) {
        try {
            Category category = jdbcTemplate.queryForObject(PreparedStatements.SQL_SELECT_CATEGORY,
                    new CategoryMapper(),
                    categoryId);
//            category.setBlogPost(findBlogPostsForCategory(category)); We need to implement BlogPostDao
            return category;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList = jdbcTemplate.query(PreparedStatements.SQL_SELECT_ALL_CATEGORY, 
                new CategoryMapper());
//        return associateBlogPostsWithCategory(categoryList);
        return categoryList;
    }
    
    public static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {

            Category category = new Category();
            category.setCategoryId(rs.getInt("categoryId"));
            category.setName(rs.getString("name"));
            return category;
        }
    }
}
