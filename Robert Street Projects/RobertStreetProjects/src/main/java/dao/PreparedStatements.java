/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author kylecaaspers
 */
public class PreparedStatements {
    
    //insert category
    protected static final String SQL_INSERT_CATEGORY
            = "insert into category (`Name`) "
            + "VALUES (?)";

    //delete category
    protected static final String SQL_DELETE_CATEGORY
            = "DELETE FROM `Category` where CategoryId = ?";
    
    //update category
    protected static final String SQL_UPDATE_CATEGORY
            = "UPDATE Category set Name = ? where CategoryId = ?";
    
    //select category
    protected static final String SQL_SELECT_CATEGORY
            = "select * from category where categoryId = ?";
    
    //select all category
    protected static final String SQL_SELECT_ALL_CATEGORY
            = "select * from category";
    
    //select all blogPosts that belong to a category, this will populate the list in Category DTO
    protected static final String SQL_SELECT_ALL_POSTS_BY_CATEGORY
            = "select * from blog_post where categoryId = ?";
    
    protected static final String SQL_SELECT_CATEGORY_BY_POST_ID
            = "select c.* from category c "
            + "inner join blog_post bp on c.CategoryId = bp.categoryId "
            + "where bp.blogId = ?";
    
    //insert status
    protected static final String SQL_INSERT_STATUS
            = "insert into status (name) values (?)";
    
    //delete status
    protected static final String SQL_DELETE_STATUS
            = "delete from status where statusID = ?";
    
    //update status
    protected static final String SQL_UPDATE_STATUS
            = "update status set name = ? where statusID = ?";
    
    //select status
    protected static final String SQL_SELECT_STATUS
            = "select * from status where statusID = ?";
    
    //select all status
    protected static final String SQL_SELECT_ALL_STATUS
            = "select * from status";
    
    protected static final String SQL_SELECT_STATUS_BY_POST_ID
            ="select s.* from `status` s "
            + "inner join blog_post bp on s.StatusId = bp.StatusId "
            + "where bp.blogId = ?";
    
    //insert tag
    protected static final String SQL_INSERT_TAG
            = "insert into tag (name) values (?)";
    //delete tag
    protected static final String SQL_DELETE_TAG
            = "delete from tag where tagID = ?";
    
    //update tag
    protected static final String SQL_UPDATE_TAG
            = "update tag set name = ? where tagID = ?";
    
    //select tag
    protected static final String SQL_SELECT_TAG
            = "select * from tag where tagID = ?";
    
    //select all tags
    protected static final String SQL_SELECT_ALL_TAG
            = "select * from tag";
    

    //insert static page
    protected static final String SQL_INSERT_STATIC_PAGE
            = "insert into static_page (name, content, userID) "
            + "values (?, ?, ?)";

    //delete static page
    protected static final String SQL_DELETE_STATIC_PAGE
            = "delete from static_page where staticpageID = ?";
    
    //update static page
    protected static final String SQL_UPDATE_STATIC_PAGE
            = "update static_page set name = ?, content = ?, userID = ? "
            + "where staticpageId = ?";
    
    //get static page
    protected static final String SQL_GET_STATIC_PAGE_BY_ID
            = "select * from static_page where staticpageId = ?";
    
    //get all static pages
    protected static final String SQL_GET_ALL_STATIC_PAGES
            = "select * from static_page";
    
    //get all static pages by user
    protected static final String SQL_GET_STATIC_PAGE_PAGE_BY_USER
            = "select * from static_page where userId = ?";
    
    protected static final String SQL_GET_USERS_BY_STATIC_PAGE
            = "select * from user "
            + "inner join Static_page sp on sp.userId = user.userId "
            + "where StaticPageId = ? ";
    
    //insert user
    protected static final String SQL_INSERT_USER
            = "insert into user (username, userpassword, enabled) "
            + "values (?, ?, 1)";
    
    //delete user
    protected static final String SQL_DELETE_USER
            = "delete from user where username = ?";
    
    
    //update user
    protected static final String SQL_UPDATE_USER
            = "update user set username = ?, userpassword = ?, enabled = ? "
            + "where userID = ?";
    
    //select user
    protected static final String SQL_SELECT_USER
            = "select * from user where userID = ?";
    
    protected static final String SQL_SELECT_USER_BY_USERNAME
            = "select * from `user` "
            + "where username = ?";
    
    //select all users
    protected static final String SQL_SELECT_ALL_USERS
            = "select * from user";
    
    //select all users by authority
    protected static final String SQL_SELECT_USERS_BY_AUTHORITY
            = "select * from user "
            + "inner join Authorities a on a.username = user.username "
            + "where Authority = ?";
    
    protected static final String SQL_SELECT_USER_BY_POST_ID
            = "select u.* from `user` u "
            + "inner join blog_post bp on u.UserId = bp.UserId "
            + "where bp.blogId = ?";
    
    
    //insert into authority (username authority) values = (?,?)
    protected static final String SQL_INSERT_AUTHORITY
            = "insert into authorities (username, authority) values (?, ?)";
    
    protected static final String SQL_DELETE_AUTHORITY
            = "delete from authorities where username = ?";
    
    
    
    //insert post
    protected static final String SQL_INSERT_POST
            = "insert into blog_post (title, content, imagepath, publishdate, expdate, "
            + "userId, categoryID, statusId) "
            + "values (?, ?, ?, ?, ?, ?, ?, ?)";
    
    protected static final String SQL_INSERT_NEW_POST
            = "insert into blog_post (title, content, imagepath, "
            + "userId, categoryID, statusId) "
            + "values (?, ?, ?, ?, ?, ?)";
    
    //delete post
    protected static final String SQL_DELETE_POST
            = "delete from blog_post where blogId = ?";
    
    protected static final String SQL_DELETE_POST_FROM_BLOG_POST_TAG
            = "delete from blog_post_tag where blogId = ?";
    
    //update post
    protected static final String SQL_UPDATE_POST
            = "update blog_post set title = ?, content = ?, imagepath = ?, publishdate = ?, "
            + "expdate = ?, userId = ?, categoryId = ?, statusId = ? where blogId = ?";
    //select post
    protected static final String SQL_SELECT_POST
            = "select * from blog_post where blogId = ?";
    
    //select all posts
    protected static final String SQL_SELECT_ALL_POSTS
            = "select * from blog_post";
    
    //select all posts by user
    protected static final String SQL_SELECT_ALL_POSTS_BY_USER
            = "select * from blog_post where userId = ?";
    
    //select all posts by status
    protected static final String SQL_SELECT_ALL_POSTS_BY_STATUS
            = "select * from blog_post where statusId = ?";
    
   
    
    //insert blogpost_tag
    protected static final String SQL_INSERT_BLOG_POST_TAG
            = "insert into blog_post_tag (blogId, tagId) values (?, ?)";
    
    //delete blogpost_tag
    protected static final String SQL_DELETE_BLOG_POST_TAG
            = "delete from blog_post_tag where blogId = ?";

    protected static final String SQL_SELECT_TAGS_BY_POST_ID
            = "select t.* from tag t "
            + "inner join blog_post_tag bt on t.tagId = bt.tagId "
            + "where bt.blogId = ?";

    //search statement
//    protected static final String SQL_SEARCH_POST_BY_CRITERIA
//            = "select distinct blog_post.blogid, blog_post.title, blog_post.content from blog_post "
//            + "inner join blog_post_tag bpt on bpt.blogid = blog_post.blogid "
//            + "inner join tag on bpt.tagid = tag.tagid "
//            + "inner join user on blog_post.userID = user.userID "
//            + "where name like ? or "
//            + "title like ? or "
//            + "username like ? or "
//            + "content like ?";
    
    //search statement
    protected static final String SQL_SEARCH
            = "select distinct blog_post.blogid, blog_post.title, blog_post.content from blog_post "
            + "inner join blog_post_tag bpt on bpt.blogid = blog_post.blogid "
            + "inner join tag on bpt.tagid = tag.tagid "
            + "inner join user on blog_post.userID = user.userID "
            + "where name like ? or "
            + "title like ? or "
            + "username like ? or "
            + "content like ?";
    
}
