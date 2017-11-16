/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.BlogPost;
import model.StaticPage;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Alejandro
 */
public class UserDaoTest {
    
    UserDao userDao;
    BlogPostDao blogPostDao;
    StaticPageDao staticPageDao;
    
    public UserDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        userDao = ctx.getBean("userDao", UserDao.class);
        blogPostDao = ctx.getBean("blogPostDao", BlogPostDao.class);
        staticPageDao = ctx.getBean("staticPageDao", StaticPageDao.class);

        List<StaticPage> staticPages = staticPageDao.getAllStaticPages();
        for (StaticPage currentStaticPage : staticPages) {
            staticPageDao.deleteStaticPage(currentStaticPage.getStaticPageId());
        }

        List<BlogPost> posts = blogPostDao.getAllBlogPosts();
        for (BlogPost currentPost : posts) {
            blogPostDao.deleteBlogPost(currentPost.getBlogPostId());
        }

        List<User> users = userDao.getAllUsers();
        for (User currentUser : users) {
            userDao.deleteUser(currentUser.getUserName());
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetUser() {
        
        User user = new User();
        user.setUserName("admin");
        user.setUserPassword("admin");
        user.setEnable(true);
        
        userDao.addUser(user);
        
        User fromDao = userDao.getUserById(user.getUserId());
        
        assertEquals(fromDao, user);
    }

    @Test
    public void testDeleteUser() {
        
        User user = new User();
        user.setUserName("admin");
        user.setUserPassword("admin");
        user.setEnable(true);
        
        userDao.addUser(user);
        
        userDao.deleteUser(user.getUserName());
        assertNull(userDao.getUserById(user.getUserId()));
    }

    @Test
    public void testUpdateUser() {
        
        User user = new User();
        user.setUserName("admin");
        user.setUserPassword("admin");
        user.setEnable(true);
        
        userDao.addUser(user);
        
        user.setUserName("Administrator");
        userDao.updateUser(user);
        
        assertEquals("Administrator", userDao.getUserById(user.getUserId()).getUserName());
    }

    @Test
    public void testGetAllUsers() {
        
        User user = new User();
        user.setUserName("admin");
        user.setUserPassword("admin");
        user.setEnable(true);
        
        userDao.addUser(user);
        
        User user2 = new User();
        user2.setUserName("user");
        user2.setUserPassword("user");
        user2.setEnable(true);
        
        userDao.addUser(user2);
        
        assertEquals(2, userDao.getAllUsers().size());
    }

}
