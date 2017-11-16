/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Category;
import model.StaticPage;
import model.User;

/**
 *
 * @author kylecaaspers
 */
public interface StaticPageDao {
    
    public void addStaticPage(StaticPage staticPage);
    
    public void deleteStaticPage(int staticPageId);
    
    public void updateStaticPage(StaticPage staticPage);
    
    public StaticPage getStaticPageById(int staticPageId);
    
    public List<StaticPage> getAllStaticPages();
    
    public List<StaticPage> getStaticPagesByUser(int userId);
    
}
