/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.InsufficientFundsException;
import dao.VendingMachineInventoryException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import model.Change;
import model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.VendingMachineService;

/**
 *
 * @author kylecaaspers
 */
@Controller
public class VendingMachineController {

    //We will need seperate methods for adding each kind of money
    //we will need a method for make purchase
    //we will need a method for change return
    VendingMachineService service;
    private String itemName = "";
    private String change;
    private String vendingMessage = "";

    @Inject
    public VendingMachineController(VendingMachineService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadPage(Map<String, Object> model) {
        //first I load all the items and put them on the model
        ArrayList<Item> items = new ArrayList();
        try {
            items = service.getAllItems();
        } catch (VendingMachineInventoryException e) {
            this.vendingMessage = e.getMessage();
        }
        model.put("items", items);

        //and I update all displays
        model.put("vendingMessage", this.vendingMessage);
        model.put("moneyTotal", service.getUserFunds());
        model.put("itemName", this.itemName);
        model.put("changeAmount", this.change);

        //and send it to the index
        return "index";
    }


    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public String addItem(HttpServletRequest request, Map<String, Object> model) {
        //i need to get a handle on the current item's name
        this.itemName = request.getParameter("itemName");
        return "redirect:/";
    }

    @RequestMapping(value = "/makePurchase", method = RequestMethod.POST)
    public String makePurchase(HttpServletRequest request, Map<String, Object> model){
        String itemToPurchase = request.getParameter("selectedItem");
        String change;
        if (itemToPurchase.trim().equals("")) {
            this.vendingMessage = "No item selected";
        } else {
            try {
                service.areFundsSufficient(itemToPurchase);
                change = service.purchaseItem(itemToPurchase);
                this.change = change;
                //this part is only reached on a succesful transaction, so I will clear money here
                service.clearFunds();
                this.vendingMessage = "Thank you";
                this.itemName = "";
            } catch (InsufficientFundsException e) {
                    String errorMessage = e.getMessage();
                    BigDecimal difference = service.moreRequired(itemToPurchase);
                    this.vendingMessage = errorMessage + "\n$" + difference + " required";
            } catch (VendingMachineInventoryException i) {
                this.vendingMessage = i.getMessage();
            }
        }
        return "redirect:/";
    }

    @RequestMapping(value = "changeReturn", method = RequestMethod.POST)
    public String changeReturn(Model model) {
        //this should take any money in userFunds and convert it into coins, then delete it in memory
        //it should also clear any messages
        this.change = service.dispenseChange();
        service.clearFunds();
        this.vendingMessage = "";
        this.itemName = "";

        return "redirect:/";
    }

    @RequestMapping(value = "/selectItem", method = RequestMethod.POST)
    public String selectItem(HttpServletRequest request, Model model) {
        //the jsp call to this method should include the item name in the call
        //look at the contacts.jsp delete contact href body
        this.itemName = request.getParameter("name");
        //add the name value to the model and have the item name text slot recieve it
        return "redirect:/";
    }

    @RequestMapping(value = "/addDollar", method = RequestMethod.POST)
    public String addDollar(Model model) {
        service.addFunds("1.00");
        return "redirect:/";
    }

    @RequestMapping(value = "/addQuarter", method = RequestMethod.POST)
    public String addQuarter(Model model) {
        service.addFunds("0.25");
        return "redirect:/";
    }

    @RequestMapping(value = "/addDime", method = RequestMethod.POST)
    public String addDime(Model model) {
        service.addFunds("0.10");
        return "redirect:/";
    }

    @RequestMapping(value = "/addNickel", method = RequestMethod.POST)
    public String addNickel(Model model) {
        service.addFunds("0.05");
        return "redirect:/";
    }

}
