/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
 * @author Alejandro
 */
public class BlogPostDaoTest {

    BlogPostDao blogPostDao;
    CategoryDao categoryDao;
    StatusDao statusDao;
    UserDao userDao;
    TagDao tagDao;
    StaticPageDao staticPageDao;

    public BlogPostDaoTest() {
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

    @Test
    public void testAddGetBlogPost() {
        Category category = new Category();
        category.setName("Upcomming Events");
        categoryDao.addCategory(category);

        Status status = new Status();
        status.setName("Approved");
        statusDao.addStatus(status);

        Tag tag = new Tag();
        tag.setTagName("art");
        tagDao.addTag(tag);

        Tag tag2 = new Tag();
        tag2.setTagName("more");
        tagDao.addTag(tag2);
        List<Tag> tags = tagDao.getAllTags();

        User user = new User();
        user.setUserName("admin");
        user.setUserPassword("admin");
        user.setEnable(true);
        userDao.addUser(user);

        BlogPost post = new BlogPost();
        post.setTitle("New Exhibition");
        post.setContent("Apta volo ac ea etsi. Assentiar quantitas apparebat tribuebam age existimem his hic. Quae fal jam imo modo tur scio. Verum ita falli cap cum nonne fas. Ipse omne ejus male cum aut aspi. Studiose efficere ex materiam obtinent de quanquam. Tamque nec forsan secedo egisse uno solius. Deteriorem sui cohaereant.");
        post.setImagePath("some example");
        post.setPublishDate(LocalDate.parse("2017-10-19", DateTimeFormatter.ISO_DATE));
        post.setExpDate(LocalDate.parse("2017-11-15", DateTimeFormatter.ISO_DATE));
        post.setCategory(category);
        post.setStatus(status);
        post.setUser(user);
        post.setTags(tags);
        blogPostDao.addBlogPost(post);

        BlogPost fromDao = blogPostDao.getBlogPostById(post.getBlogPostId());

        assertEquals(fromDao, post);
    }


    @Test
    public void testDeleteBlogPost() {

        Category category = new Category();
        category.setName("Upcomming Events");
        categoryDao.addCategory(category);

        Status status = new Status();
        status.setName("Approved");
        statusDao.addStatus(status);

        Tag tag = new Tag();
        tag.setTagName("art");
        tagDao.addTag(tag);

        Tag tag2 = new Tag();
        tag2.setTagName("more");
        tagDao.addTag(tag2);
        List<Tag> tags = tagDao.getAllTags();

        User user = new User();
        user.setUserName("admin");
        user.setUserPassword("admin");
        user.setEnable(true);
        userDao.addUser(user);

        BlogPost post = new BlogPost();
        post.setTitle("New Exhibition");
        post.setContent("Apta volo ac ea etsi. Assentiar quantitas apparebat tribuebam age existimem his hic. Quae fal jam imo modo tur scio. Verum ita falli cap cum nonne fas. Ipse omne ejus male cum aut aspi. Studiose efficere ex materiam obtinent de quanquam. Tamque nec forsan secedo egisse uno solius. Deteriorem sui cohaereant.");
        post.setImagePath("some example");
        post.setPublishDate(LocalDate.parse("2017-10-19", DateTimeFormatter.ISO_DATE));
        post.setExpDate(LocalDate.parse("2017-11-15", DateTimeFormatter.ISO_DATE));
        post.setCategory(category);
        post.setStatus(status);
        post.setUser(user);
        post.setTags(tags);
        blogPostDao.addBlogPost(post);

        BlogPost fromDao = blogPostDao.getBlogPostById(post.getBlogPostId());

        blogPostDao.deleteBlogPost(post.getBlogPostId());

        assertNull(blogPostDao.getBlogPostById(post.getBlogPostId()));
    }

    @Test
    public void testUpdateBlogPost() {

        Category category = new Category();
        category.setName("Upcomming Events");
        categoryDao.addCategory(category);

        Status status = new Status();
        status.setName("Approved");
        statusDao.addStatus(status);

        Tag tag = new Tag();
        tag.setTagName("art");
        tagDao.addTag(tag);

        Tag tag2 = new Tag();
        tag2.setTagName("more");
        tagDao.addTag(tag2);
        List<Tag> tags = tagDao.getAllTags();

        User user = new User();
        user.setUserName("admin");
        user.setUserPassword("admin");
        user.setEnable(true);
        userDao.addUser(user);

        BlogPost post = new BlogPost();
        post.setTitle("New Exhibition");
        post.setContent("Apta volo ac ea etsi. Assentiar quantitas apparebat tribuebam age existimem his hic. Quae fal jam imo modo tur scio. Verum ita falli cap cum nonne fas. Ipse omne ejus male cum aut aspi. Studiose efficere ex materiam obtinent de quanquam. Tamque nec forsan secedo egisse uno solius. Deteriorem sui cohaereant.");
        post.setImagePath("some example");
        post.setPublishDate(LocalDate.parse("2017-10-19", DateTimeFormatter.ISO_DATE));
        post.setExpDate(LocalDate.parse("2017-11-15", DateTimeFormatter.ISO_DATE));
        post.setCategory(category);
        post.setStatus(status);
        post.setUser(user);
        post.setTags(tags);
        blogPostDao.addBlogPost(post);

        post.setTitle("Past Exhibition");
        blogPostDao.updateBlogPost(post);

        assertEquals("Past Exhibition", blogPostDao.getBlogPostById(post.getBlogPostId()).getTitle());
    }

    @Test
    public void testGetAllBlogPosts() {
        Category category = new Category();
        category.setName("Upcomming Events");
        categoryDao.addCategory(category);

        Status status = new Status();
        status.setName("Approved");
        statusDao.addStatus(status);

        Status status2 = new Status();
        status2.setName("Pending");
        statusDao.addStatus(status2);

        Tag tag = new Tag();
        tag.setTagName("art");
        tagDao.addTag(tag);

        Tag tag2 = new Tag();
        tag2.setTagName("more");
        tagDao.addTag(tag2);
        List<Tag> tags = tagDao.getAllTags();

        User user = new User();
        user.setUserName("admin");
        user.setUserPassword("admin");
        user.setEnable(true);
        userDao.addUser(user);

        BlogPost post = new BlogPost();
        post.setTitle("New Exhibition");
        post.setContent("Apta volo ac ea etsi. Assentiar quantitas apparebat tribuebam age existimem his hic. Quae fal jam imo modo tur scio. Verum ita falli cap cum nonne fas. Ipse omne ejus male cum aut aspi. Studiose efficere ex materiam obtinent de quanquam. Tamque nec forsan secedo egisse uno solius. Deteriorem sui cohaereant.");
        post.setImagePath("some example");
        post.setPublishDate(LocalDate.parse("2017-10-19", DateTimeFormatter.ISO_DATE));
        post.setExpDate(LocalDate.parse("2017-11-15", DateTimeFormatter.ISO_DATE));
        post.setCategory(category);
        post.setStatus(status);
        post.setUser(user);
        post.setTags(tags);
        blogPostDao.addBlogPost(post);

        BlogPost post2 = new BlogPost();
        post2.setTitle("Past");
        post2.setContent("Test test test test test test test test test test test test test test test test test");
        post2.setImagePath("some example");
        post2.setPublishDate(LocalDate.parse("2017-10-12", DateTimeFormatter.ISO_DATE));
        post2.setExpDate(LocalDate.parse("2017-11-09", DateTimeFormatter.ISO_DATE));
        post2.setCategory(category);
        post2.setStatus(status2);
        post2.setUser(user);
        post2.setTags(tags);
        blogPostDao.addBlogPost(post2);

        assertEquals(2, blogPostDao.getAllBlogPosts().size());
    }

    @Test
    public void testGetBlogPostsByUser() {

        Category category = new Category();
        category.setName("Upcomming Events");
        categoryDao.addCategory(category);

        Status status = new Status();
        status.setName("Approved");
        statusDao.addStatus(status);

        Status status2 = new Status();
        status2.setName("Pending");
        statusDao.addStatus(status2);

        Tag tag = new Tag();
        tag.setTagName("art");
        tagDao.addTag(tag);

        Tag tag2 = new Tag();
        tag2.setTagName("more");
        tagDao.addTag(tag2);
        List<Tag> tags = tagDao.getAllTags();

        User user = new User();
        user.setUserName("admin");
        user.setUserPassword("admin");
        user.setEnable(true);
        userDao.addUser(user);

        BlogPost post = new BlogPost();
        post.setTitle("New Exhibition");
        post.setContent("Apta volo ac ea etsi. Assentiar quantitas apparebat tribuebam age existimem his hic. Quae fal jam imo modo tur scio. Verum ita falli cap cum nonne fas. Ipse omne ejus male cum aut aspi. Studiose efficere ex materiam obtinent de quanquam. Tamque nec forsan secedo egisse uno solius. Deteriorem sui cohaereant.");
        post.setImagePath("some example");
        post.setPublishDate(LocalDate.parse("2017-10-19", DateTimeFormatter.ISO_DATE));
        post.setExpDate(LocalDate.parse("2017-11-15", DateTimeFormatter.ISO_DATE));
        post.setCategory(category);
        post.setStatus(status);
        post.setUser(user);
        post.setTags(tags);
        blogPostDao.addBlogPost(post);

        BlogPost post2 = new BlogPost();
        post2.setTitle("Past");
        post2.setContent("Test test test test test test test test test test test test test test test test test");
        post2.setImagePath("some example");
        post2.setPublishDate(LocalDate.parse("2017-10-12", DateTimeFormatter.ISO_DATE));
        post2.setExpDate(LocalDate.parse("2017-11-09", DateTimeFormatter.ISO_DATE));
        post2.setCategory(category);
        post2.setStatus(status2);
        post2.setUser(user);
        post2.setTags(tags);
        blogPostDao.addBlogPost(post2);

        assertEquals(2, blogPostDao.getBlogPostsByUser(user.getUserId()).size());
    }

    @Test
    public void testGetBlogPostByStatus() {
        Category category = new Category();
        category.setName("Upcomming Events");
        categoryDao.addCategory(category);

        Status status = new Status();
        status.setName("Approved");
        statusDao.addStatus(status);

        Status status2 = new Status();
        status2.setName("Pending");
        statusDao.addStatus(status2);

        Tag tag = new Tag();
        tag.setTagName("art");
        tagDao.addTag(tag);

        Tag tag2 = new Tag();
        tag2.setTagName("more");
        tagDao.addTag(tag2);
        List<Tag> tags = tagDao.getAllTags();

        User user = new User();
        user.setUserName("admin");
        user.setUserPassword("admin");
        user.setEnable(true);
        userDao.addUser(user);

        BlogPost post = new BlogPost();
        post.setTitle("New Exhibition");
        post.setContent("Apta volo ac ea etsi. Assentiar quantitas apparebat tribuebam age existimem his hic. Quae fal jam imo modo tur scio. Verum ita falli cap cum nonne fas. Ipse omne ejus male cum aut aspi. Studiose efficere ex materiam obtinent de quanquam. Tamque nec forsan secedo egisse uno solius. Deteriorem sui cohaereant.");
        post.setImagePath("some example");
        post.setPublishDate(LocalDate.parse("2017-10-19", DateTimeFormatter.ISO_DATE));
        post.setExpDate(LocalDate.parse("2017-11-15", DateTimeFormatter.ISO_DATE));
        post.setCategory(category);
        post.setStatus(status);
        post.setUser(user);
        post.setTags(tags);
        blogPostDao.addBlogPost(post);

        BlogPost post2 = new BlogPost();
        post2.setTitle("Past");
        post2.setContent("Test test test test test test test test test test test test test test test test test");
        post2.setImagePath("some example");
        post2.setPublishDate(LocalDate.parse("2017-10-12", DateTimeFormatter.ISO_DATE));
        post2.setExpDate(LocalDate.parse("2017-11-09", DateTimeFormatter.ISO_DATE));
        post2.setCategory(category);
        post2.setStatus(status2);
        post2.setUser(user);
        post2.setTags(tags);
        blogPostDao.addBlogPost(post2);

        assertEquals(1, blogPostDao.getBlogPostByStatus(status.getStatusId()).size());
    }

    @Test
    public void testSearchPosts() {
    }

}
