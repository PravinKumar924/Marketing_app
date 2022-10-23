package com.marketing.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketing.dto.LeadData;
import com.marketing.entities.Lead;
import com.marketing.services.LeadService;

@Controller 
//@ComponentScan(basePackages = {"com.marketing.controller"})
public class LeadController {
	
	@Autowired
	private LeadService leadService;
	
	
	//Handler Methods
    @RequestMapping("/createLead") 
	public String viewCreateLeadPage() {
		return "create_lead"; 
	}
    
                                      //Entity class 
    @RequestMapping("/saveLead")
    public String saveOneLead(@ModelAttribute("lead") Lead lead, ModelMap model) {
    	leadService.saveLead(lead);
    	model.addAttribute("msg", "Lead is saved!!");
    	return "create_lead";
    }
    
    
    @RequestMapping("/listall")
    public String listAllleads(ModelMap model) {
    	List<Lead> leads = leadService.listLeads();
        model.addAttribute("leads", leads);
    	return "lead_search_result";
    }
    
    @RequestMapping("/delete")
    public String deleteOneLead(@RequestParam("id")long id,ModelMap model) {
    	
    	leadService.deleteLeadById(id);
    	
    	List<Lead> leads = leadService.listLeads();
        model.addAttribute("leads", leads);
    	return "lead_search_result";
    }
    
    @RequestMapping("/update")
    public String updateOneLead(@RequestParam("id")long id,ModelMap model) {
    	Lead lead = leadService.getOneLead(id);
    	model.addAttribute("lead", lead);
    	return"update_lead";
    }
    
    @RequestMapping("/updateLead")
    public String updateOneLeadData(LeadData data, ModelMap model) {
		Lead lead = new Lead();
    	lead.setId(data.getId());
		lead.setFirstName(data.getFirstName());
		lead.setLastName(data.getLastName());
		lead.setEmail(data.getEmail());
		lead.setMobile(data.getMobile());
		
		leadService.saveLead(lead);
		
		List<Lead> leads = leadService.listLeads();
        model.addAttribute("leads", leads);
    	return "lead_search_result";
    	
    }
    
   
                                   //Approach1-@RequestParam
//    @RequestMapping("/saveLead")
//    public String saveOneLead(@RequestParam("name")String fName,@RequestParam("lastName")String lName,@RequestParam("emailId")String email,@RequestParam("mobileNumber")long mobile) {
//    	Lead l = new Lead();
//    	l.setFirstName(fName);
//    	l.setLastName(lName);
//    	l.setEmail(email);
//    	l.setMobile(mobile);
//    	leadService.saveLead(l);
//    	
//    	return "create_lead";
//    }
//Using @RequestParam we can read the data form of .jsp file(view layer) from controller layer
//Drawback of this is its make your method arguments is lengthy form we can use this for making small forms.    
//so,we have to go for other approaches
    
                                      //Approach2-DTO(Data Transfer Object)   
                                      //this is ordinary class not a entity class
//      @RequestMapping("/saveLead")
//      public String saveOneLead( LeadData data, ModelMap model) {
//    	System.out.println(data.getFirstName());
//    	System.out.println(data.getLastName());
//    	System.out.println(data.getEmail());
//    	System.out.println(data.getMobile());
//    	System.out.println("Done"); 
//        model.addAttribute("msg", "Lead is saved!!");
//    	return "create_lead";
//This method is used for read the data in backend    
                    
                                     //Or-For save the data into the database  	         
//        @RequestMapping("/saveLead")
//        public String saveOneLead( LeadData data, ModelMap model) {
//           Lead lead = new Lead();
//           lead.setFirstName(data.getFirstName());
//           lead.setLastName(data.getLastName());
//           lead.setEmail(data.getEmail());
//           lead.setMobile(data.getMobile());
//           leadService.saveLead(lead);
//           System.out.println("success");
//           model.addAttribute("msg", "Lead is saved!!");
//           return "create_lead";
//        }
}     