/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Tag;

/**
 *
 * @author kylecaaspers
 */
public interface TagDao {
    
    public Tag addTag(Tag tag);
    
    public void deleteTag(int tagId);
    
    public void updateTag(Tag tag);
    
    public Tag getTagById(int tagId);
    
    public List<Tag> getAllTags();
    
}
