/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Organization;

/**
 *
 * @author kylecaaspers
 */
public interface OrganizationDao {
    //addOrganization
    //deleteOrganization
    //updateOrganization
    //getOrganizationById
    //getAllOrganizations
    //getAllOrganizationsBySuperhero
    
    public void addOrganization(Organization organization);
    
    public void deleteOrganization(int organizationID);
    
    public void updateOrganization(Organization organization);
    
    public Organization getOrganizationById(int organizationID);
    
    public List<Organization> getAllOrganizations();
    
    public List<Organization> getAllOrganizationsBySuperhero(int superheroID);
}
