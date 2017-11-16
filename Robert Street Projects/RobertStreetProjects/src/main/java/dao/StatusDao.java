/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Status;

/**
 *
 * @author kylecaaspers
 */
public interface StatusDao {
    
    public void addStatus(Status status);
    
    public void deleteStatus(int statusId);
    
    public void updateStatus(Status status);
    
    public Status getStatusById(int statusId);
    
    public List<Status> getAllStatuses();
    
}
