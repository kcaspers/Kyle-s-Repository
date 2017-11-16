/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Category;

/**
 *
 * @author kylecaaspers
 */
public interface CategoryDao {
    
    public Category addCategory(Category category);
    
    public void deleteCategory(int categoryId);
    
    public void updateCategory(Category category);
    
    public Category getCategoryById(int categoryId);
    
    public List<Category> getAllCategories();
    
    
}
