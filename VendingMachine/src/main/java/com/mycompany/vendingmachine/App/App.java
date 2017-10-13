/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.App;

import com.mycompany.vendingmachine.controller.VendingMachineController;
import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.VendingMachineDaoImpl;
import com.mycompany.vendingmachine.service.VendingMachineService;
import com.mycompany.vendingmachine.service.VendingMachineServiceImpl;
import com.mycompany.vendingmachine.view.UserIO;
import com.mycompany.vendingmachine.view.UserIOConsoleImpl;
import com.mycompany.vendingmachine.view.VendingMachineView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kylecaaspers
 */
public class App {
    
    public static void main(String[] args) {
//        UserIO io = new UserIOConsoleImpl();
//        VendingMachineDao dao = new VendingMachineDaoImpl();
//        VendingMachineView view = new VendingMachineView(io);
//        VendingMachineService service = new VendingMachineServiceImpl(dao);
//        VendingMachineController controller = new VendingMachineController(view, service);
//        controller.run();
        
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = context.getBean("controller", VendingMachineController.class);
        controller.run();
    }
    
}
