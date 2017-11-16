package com.sg.robertstreetprojects;

import dao.BlogPostDao;
import dao.CategoryDao;
import dao.StaticPageDao;
import dao.StatusDao;
import dao.TagDao;
import dao.UserDao;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import model.BlogPost;
import model.Category;
import model.Status;
import model.Tag;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    TagDao tagDao;
    UserDao userDao;
    CategoryDao categoryDao;
    BlogPostDao blogPostDao;
    StaticPageDao staticPageDao;
    StatusDao statusDao;

    @Inject
    public MainController(UserDao userDao, CategoryDao categoryDao, BlogPostDao blogPostDao,
            StaticPageDao staticPageDao, TagDao tagDao, StatusDao statusDao) {
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.blogPostDao = blogPostDao;
        this.staticPageDao = staticPageDao;
        this.tagDao = tagDao;
        this.statusDao = statusDao;

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHi(Map<String, Object> model) {
        //this should also supply our main page with the list of preview contacts

        //posts with a status of 1 are approved
        List<BlogPost> postList = blogPostDao.getBlogPostByStatus(1);

        //don't include expired posts
        Status rejected = statusDao.getStatusById(3);
        LocalDate today = LocalDate.now();
        List<BlogPost> expiredPosts = new ArrayList<>();
        for (BlogPost p : postList) {
            if (p.getExpDate() != null && p.getExpDate().compareTo(today) == -1) {
                expiredPosts.add(p);
            }
        }
        postList.removeAll(expiredPosts);

        for (BlogPost bp : postList) {
            //we also need to shorten the content of the posts displayed on the front page
            String content = bp.getContent();
            String[] contentHalves = content.split("~break~");
            System.out.println(contentHalves);
            bp.setContent(contentHalves[0]);
        }
        model.put("postList", postList);

//        List<StaticPage> staticPages = staticPageDao.getAllStaticPages();
//        model.put("staticPages", staticPages);
        return "home";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Map<String, Object> model) {
        return "about";
    }

    //We will access this method by clicking on the blog preview on the main mage
    @RequestMapping(value = "/displayPostDetails/{postID}", method = RequestMethod.GET)
    public String displayPostDetails(HttpServletRequest request, Map<String, Object> model, @PathVariable("postID") int postID) {
        //for this to work take a look at the (displayContactDetails?contactId=${currentContact.contactId}) in contacts.jsp

        BlogPost fullPost = blogPostDao.getBlogPostById(postID);
        String content = fullPost.getContent().replace("~break~", "");
        fullPost.setContent(content);
        model.put("fullPost", fullPost);
        return "postDetails";
    }

    @RequestMapping(value = "/displayAddPostPage", method = RequestMethod.GET)
    public String displayAddPostPage(Map<String, Object> model) {
        //need to get all the categories
        List<Category> categories = categoryDao.getAllCategories();
        System.out.println(categories);
        List<Tag> tagList = tagDao.getAllTags();
        model.put("tagList", tagList);
        model.put("categories", categories);
        return "addPost";
    }
    
    @RequestMapping(value = "/displayAddCategory", method = RequestMethod.GET)
    public String displayAddCategory(Map<String, Object> model){
        List<Category> categories = categoryDao.getAllCategories();
        model.put("categories", categories);
        return "addCategory";
    }

    @RequestMapping(value = "/displayAddPage", method = RequestMethod.GET)
    public String displayAddPage(Map<String, Object> model) {
        return "addPage";
    }

    @RequestMapping(value = "/createUserPage", method = RequestMethod.GET)
    public String createUserPage(Map<String, Object> model) {
        return "createUser";
    }

    @RequestMapping(value = "/displayPending", method = RequestMethod.GET)
    public String displayPending(Map<String, Object> model) {
        //look at displayContactsPage in contact code-along
        //we will need to get a handle on both the pending posts and pending pages and put them on the model

        List<BlogPost> pendingPosts = blogPostDao.getBlogPostByStatus(2);
        model.put("pendingPosts", pendingPosts);
        return "pendingPosts";
    }

    @RequestMapping(value = "/displayDrafts", method = RequestMethod.GET)
    public String displayDrafts(HttpServletRequest request, Map<String, Object> model, Principal principal) {

        String userName = principal.getName();
        //get userID associated with this username
        User user = userDao.getUserByUsername(userName);
        List<BlogPost> userDrafts = blogPostDao.getBlogPostsByUser(user.getUserId());
        userDrafts = userDrafts.stream()
                .filter(bp -> !bp.getStatus().getName().equals("Approved"))
                .filter(bp -> !bp.getStatus().getName().equals("Rejected"))
                .collect(Collectors.toList());

        model.put("user", user);
        model.put("userDrafts", userDrafts);
        return "drafts";

    }

    @RequestMapping(value = "/displayManageUsers", method = RequestMethod.GET)
    public String displayManageUsers(Map<String, Object> model) {
        List<User> allUsers = userDao.getAllUsers();

        model.put("allUsers", allUsers);
        System.out.println(allUsers);

        return "manageUsers";
    }

    @RequestMapping(value = "/editPost/{id}", method = RequestMethod.GET)
    public String displayEditPost(HttpServletRequest request, @PathVariable("id") int id,
            Map<String, Object> model, Principal principal) {

        BlogPost postToEdit = blogPostDao.getBlogPostById(id);
        if (request.isUserInRole("ROLE_ADMIN")) {
            model.put("postContent", postToEdit.getContent());
            model.put("postTitle", postToEdit.getTitle());
            List<Tag> tags = postToEdit.getTags();
            List<Tag> tagList = tagDao.getAllTags();
            String stringTags = "";
            for (Tag t : tags) {
                stringTags += t.getTagName() + " ";
            }
            model.put("tagList", tagList);
            model.put("postTags", stringTags);
            model.put("photoPath", postToEdit.getImagePath());
            model.put("categories", categoryDao.getAllCategories());
            model.put("postCategory", postToEdit.getCategory().getCategoryId());
            model.put("postID", postToEdit.getBlogPostId());
            return "editPost";

        } else if (postToEdit.getUser().getUserName().equals(principal.getName())
                && postToEdit.getStatus().getName().equals("Draft")) {
            model.put("postContent", postToEdit.getContent());
            model.put("postTitle", postToEdit.getTitle());
            List<Tag> tags = postToEdit.getTags();
            List<Tag> tagList = tagDao.getAllTags();
            String stringTags = "";
            for (Tag t : tags) {
                stringTags += t.getTagName() + " ";
            }
            model.put("tagList", tagList);
            model.put("postTags", stringTags);
            model.put("photoPath", postToEdit.getImagePath());
            model.put("categories", categoryDao.getAllCategories());
            model.put("postCategory", postToEdit.getCategory().getCategoryId());
            model.put("postID", postToEdit.getBlogPostId());
            return "editPost";
        } else {
            return "redirect:/";
        }

    }
}
