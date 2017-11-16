/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.CategoryDaoImpl.CategoryMapper;
import dao.StatusDaoImpl.StatusMapper;
import dao.TagDaoImpl.TagMapper;
import dao.UserDaoImpl.UserMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.BlogPost;
import model.Category;
import model.Status;
import model.Tag;
import model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alejandro
 */
public class BlogPostDaoImpl implements BlogPostDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
    public void insertBlogPostTags(BlogPost post) {
        final int postId = post.getBlogPostId();
        final List<Tag> tags = post.getTags();
        try {
            for (Tag currentTag : tags) {
                jdbcTemplate.update(PreparedStatements.SQL_INSERT_BLOG_POST_TAG,
                        postId,
                        currentTag.getTagId());
            }
        } catch (Exception e) {
        }
    }
//
//    @Override

    public List<Tag> findTagsForBlog(BlogPost post) {
        return jdbcTemplate.query(PreparedStatements.SQL_SELECT_TAGS_BY_POST_ID,
                new TagMapper(),
                post.getBlogPostId());
    }

//    @Override
    public Category findCategoryForBlogPost(BlogPost post) {
        return jdbcTemplate.queryForObject(PreparedStatements.SQL_SELECT_CATEGORY_BY_POST_ID,
                new CategoryMapper(),
                post.getBlogPostId());
    }

    public Status findStatusForBlogPost(BlogPost post) {
        return jdbcTemplate.queryForObject(PreparedStatements.SQL_SELECT_STATUS_BY_POST_ID,
                new StatusMapper(),
                post.getBlogPostId());
    }

    public User findUserForBlogPost(BlogPost post) {
        return jdbcTemplate.queryForObject(PreparedStatements.SQL_SELECT_USER_BY_POST_ID,
                new UserMapper(),
                post.getBlogPostId());
    }

//    @Override
    public List<BlogPost> associateCategoryStatusAndTagsWithBlogPost(List<BlogPost> postList) {
        for (BlogPost currentPost : postList) {
            currentPost.setTags(findTagsForBlog(currentPost));
            currentPost.setCategory(findCategoryForBlogPost(currentPost));
            currentPost.setStatus(findStatusForBlogPost(currentPost));
            currentPost.setUser(findUserForBlogPost(currentPost));
        }
        return postList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addBlogPost(BlogPost blogPost) {
        
        jdbcTemplate.update(PreparedStatements.SQL_INSERT_POST,
                blogPost.getTitle(),
                blogPost.getContent(),
                blogPost.getImagePath(),
                blogPost.getPublishDate().toString(),
                blogPost.getExpDate().toString(),
                blogPost.getUser().getUserId(),
                blogPost.getCategory().getCategoryId(),
                blogPost.getStatus().getStatusId());
        blogPost.setBlogPostId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
        insertBlogPostTags(blogPost);
        
        
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addNewBlogPost(BlogPost blogPost) {
        
        jdbcTemplate.update(PreparedStatements.SQL_INSERT_NEW_POST,
                blogPost.getTitle(),
                blogPost.getContent(),
                blogPost.getImagePath(),
                blogPost.getUser().getUserId(),
                blogPost.getCategory().getCategoryId(),
                blogPost.getStatus().getStatusId());
        blogPost.setBlogPostId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
        insertBlogPostTags(blogPost);
        
        
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteBlogPost(int blogPostID) {
        jdbcTemplate.update(PreparedStatements.SQL_DELETE_POST_FROM_BLOG_POST_TAG, blogPostID);

        jdbcTemplate.update(PreparedStatements.SQL_DELETE_POST, blogPostID);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateBlogPost(BlogPost blogPost) {
        LocalDate fakeDate = LocalDate.MIN;

        if (blogPost.getPublishDate() == null && blogPost.getExpDate() == null) {
            jdbcTemplate.update(PreparedStatements.SQL_UPDATE_POST,
                    blogPost.getTitle(),
                    blogPost.getContent(),
                    blogPost.getImagePath(),
                    null,
                    null,
                    blogPost.getUser().getUserId(),
                    blogPost.getCategory().getCategoryId(),
                    blogPost.getStatus().getStatusId(),
                    blogPost.getBlogPostId());
            jdbcTemplate.update(PreparedStatements.SQL_DELETE_POST_FROM_BLOG_POST_TAG, blogPost.getBlogPostId());
            insertBlogPostTags(blogPost);
        } else {
            jdbcTemplate.update(PreparedStatements.SQL_UPDATE_POST,
                    blogPost.getTitle(),
                    blogPost.getContent(),
                    blogPost.getImagePath(),
                    blogPost.getPublishDate().toString(),
                    blogPost.getExpDate().toString(),
                    blogPost.getUser().getUserId(),
                    blogPost.getCategory().getCategoryId(),
                    blogPost.getStatus().getStatusId(),
                    blogPost.getBlogPostId());
            jdbcTemplate.update(PreparedStatements.SQL_DELETE_POST_FROM_BLOG_POST_TAG, blogPost.getBlogPostId());
            insertBlogPostTags(blogPost);
        }

    }

    @Override
    public BlogPost getBlogPostById(int blogPostID) {
        try {
            BlogPost post = jdbcTemplate.queryForObject(PreparedStatements.SQL_SELECT_POST,
                    new BlogPostMapper(),
                    blogPostID);
            post.setTags(findTagsForBlog(post));
            post.setCategory(findCategoryForBlogPost(post));
            post.setStatus(findStatusForBlogPost(post));
            post.setUser(findUserForBlogPost(post));
            return post;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<BlogPost> getAllBlogPosts() {
        List<BlogPost> postList = jdbcTemplate.query(PreparedStatements.SQL_SELECT_ALL_POSTS,
                new BlogPostMapper());

        return associateCategoryStatusAndTagsWithBlogPost(postList);
    }

    @Override
    public List<BlogPost> getBlogPostsByUser(int userID) {
        List<BlogPost> postList = jdbcTemplate.query(PreparedStatements.SQL_SELECT_ALL_POSTS_BY_USER,
                new BlogPostMapper(),
                userID);
        return associateCategoryStatusAndTagsWithBlogPost(postList);
    }

    @Override
    public List<BlogPost> getBlogPostByStatus(int statusId) {
        List<BlogPost> postList = jdbcTemplate.query(PreparedStatements.SQL_SELECT_ALL_POSTS_BY_STATUS,
                new BlogPostMapper(),
                statusId);
        return associateCategoryStatusAndTagsWithBlogPost(postList);
    }

    @Override
    public List<BlogPost> searchPosts(String criteria) {
        List<BlogPost> postList = jdbcTemplate.query(PreparedStatements.SQL_SEARCH,
                new BlogPostMapper(),
                criteria,
                criteria,
                criteria,
                "%" + criteria + "%");
        return associateCategoryStatusAndTagsWithBlogPost(postList);
    }

//    public static final class BlogPostMapper implements RowMapper<BlogPost> {
//        //we need some way of mapping nullable fields
//        @Override
//        public BlogPost mapRow(ResultSet rs, int i) throws SQLException {
//            
//            BlogPost post = new BlogPost();
//            post.setBlogPostId(rs.getInt("BlogId"));
//            post.setTitle(rs.getString("Title"));
//            post.setContent(rs.getString("Content"));
//            post.setExpDate(rs.getTimestamp("expDate").toLocalDateTime().toLocalDate());
//            post.setPublishDate(rs.getTimestamp("PublishDate").toLocalDateTime().toLocalDate());
//            post.setImagePath(rs.getString("ImagePath"));
//            
//            return post;
//        }
//    }
    public static final class BlogPostMapper implements RowMapper<BlogPost> {

        //we need some way of mapping nullable fields
        @Override
        public BlogPost mapRow(ResultSet rs, int i) throws SQLException {

            BlogPost post = new BlogPost();
            post.setBlogPostId(rs.getInt("BlogId"));
            post.setTitle(rs.getString("Title"));
            post.setContent(rs.getString("Content"));
            try {
                post.setExpDate(rs.getTimestamp("expDate").toLocalDateTime().toLocalDate());
            } catch (Exception e) {

            }
            try {
                post.setPublishDate(rs.getTimestamp("PublishDate").toLocalDateTime().toLocalDate());
            } catch (Exception e) {

            }
            try {
                post.setImagePath(rs.getString("ImagePath"));
            } catch (Exception e) {

            }
            return post;
        }
    }
}
