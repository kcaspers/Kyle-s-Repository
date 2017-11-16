/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.ArrayList;
import java.util.List;
import model.BlogPost;
import model.Category;
import model.StaticPage;
import model.Status;
import model.Tag;
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
 * @author ritheenhep
 */
public class StaticPageDaoTest {

    BlogPostDao blogPostDao;
    CategoryDao categoryDao;
    StatusDao statusDao;
    UserDao userDao;
    TagDao tagDao;
    StaticPageDao staticPageDao;

    public StaticPageDaoTest() {
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
        blogPostDao = ctx.getBean("blogPostDao", BlogPostDao.class);
        categoryDao = ctx.getBean("categoryDao", CategoryDao.class);
        statusDao = ctx.getBean("statusDao", StatusDao.class);
        userDao = ctx.getBean("userDao", UserDao.class);
        tagDao = ctx.getBean("tagDao", TagDao.class);
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

        List<Status> statuses = statusDao.getAllStatuses();
        for (Status currentStatus : statuses) {
            statusDao.deleteStatus(currentStatus.getStatusId());
        }

        List<Tag> tags = tagDao.getAllTags();
        for (Tag currentTag : tags) {
            tagDao.deleteTag(currentTag.getTagId());
        }

        List<Category> categories = categoryDao.getAllCategories();
        for (Category currentCategory : categories) {
            categoryDao.deleteCategory(currentCategory.getCategoryId());
        }

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testAddGetStaticPage() {

        User user = new User();
        user.setUserName("JoJo");
        user.setUserPassword("1234");
        user.setEnable(true);
        userDao.addUser(user);

        StaticPage staticPage = new StaticPage();
        staticPage.setName("Home");
        staticPage.setContent("Art");
        staticPage.setUser(user);
        staticPageDao.addStaticPage(staticPage);
        StaticPage fromDao = staticPageDao.getStaticPageById(staticPage.getStaticPageId());

        assertEquals(fromDao, staticPage);
    }

    @Test
    public void tesDeleteStaticPage() {
        User user = new User();
        user.setUserName("JoJo");
        user.setUserPassword("1234");
        user.setEnable(true);
        userDao.addUser(user);

        StaticPage staticPage = new StaticPage();
        staticPage.setName("Home");
        staticPage.setContent("Art");
        staticPage.setUser(user);
        staticPageDao.addStaticPage(staticPage);
        StaticPage fromDao = staticPageDao.getStaticPageById(staticPage.getStaticPageId());

        assertEquals(fromDao, staticPage);
        staticPageDao.deleteStaticPage(staticPage.getStaticPageId());
        StaticPage deletedPage = staticPageDao.getStaticPageById(staticPage.getStaticPageId());

        assertNull(staticPageDao.getStaticPageById(staticPage.getStaticPageId()));

    }

    @Test
    public void testUpdateStaticPage() {
        User user = new User();
        user.setUserName("JoJo");
        user.setUserPassword("1234");
        user.setEnable(true);
        userDao.addUser(user);

        StaticPage staticPage = new StaticPage();
        staticPage.setName("Home");
        staticPage.setContent("Art");
        staticPage.setUser(user);
        staticPageDao.addStaticPage(staticPage);
       
        staticPage.setName("House");
        staticPageDao.updateStaticPage(staticPage);

        assertEquals("House", staticPageDao.getStaticPageById(staticPage.getStaticPageId()).getName());

    }
    
   @Test
   public void testGetStaticPageById(){
        
        User user = new User();
        user.setUserName("JoJo");
        user.setUserPassword("1234");
        user.setEnable(true);
        userDao.addUser(user);

        StaticPage staticPage = new StaticPage();
        staticPage.setName("Home");
        staticPage.setContent("Art");
        staticPage.setUser(user);
        staticPageDao.addStaticPage(staticPage);
        
        StaticPage staticPageId = staticPageDao.getStaticPageById(staticPage.getStaticPageId());
        
        assertEquals(staticPage.getStaticPageId(), staticPageId.getStaticPageId());
        
   }
   
   @Test
   public void testGetAllStaticPage(){
        User user = new User();
        user.setUserName("JoJo");
        user.setUserPassword("1234");
        user.setEnable(true);
        userDao.addUser(user);

        StaticPage staticPage = new StaticPage();
        staticPage.setName("Home");
        staticPage.setContent("Art");
        staticPage.setUser(user);
        staticPageDao.addStaticPage(staticPage);
        
        List<StaticPage> staticPageList= new ArrayList<>();
        StaticPage staticPageFromDao = staticPageDao.getStaticPageById(staticPage.getStaticPageId());
        staticPageList.add(staticPageFromDao);
        assertEquals(1, staticPageList.size());
       
   }
   
   @Test
   public void testGetStaticPageByUser(){
        
        User user = new User();
        user.setUserName("JoJo");
        user.setUserPassword("1234");
        user.setEnable(true);
        userDao.addUser(user);

        StaticPage staticPage = new StaticPage();
        staticPage.setName("Home");
        staticPage.setContent("Art");
        staticPage.setUser(user);
        staticPageDao.addStaticPage(staticPage);
        
       List<StaticPage> staticPageFromUser = staticPageDao.getStaticPagesByUser(user.getUserId());
       assertEquals(1, staticPageFromUser.size());
   }

}

