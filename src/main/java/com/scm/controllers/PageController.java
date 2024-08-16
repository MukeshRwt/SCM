package com.scm.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;





@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String indexString() {
        return "redirect:/home";
    }
    

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home page handled");

        // sending data to view
        model.addAttribute("name", "substring techmologui");
        model.addAttribute("youtube", "learn code with durgesh");
        model.addAttribute("link", "https://start.spring.io/;");
        return "home";

    }

    //About
    @RequestMapping("/about")
    public String aboutpage(Model model){
        model.addAttribute("isLogin", true);
        // model.addAttribute(null, model);

        
     return "about";   
    }

    //Services
    @RequestMapping("/service")
    public String servicepage(Model model){
        
        // model.addAttribute(null, model);
        
        
     return "service";   
    }

// Contact
@RequestMapping("/contact")
public String contactPage() {
    return "contact";
}

// login
@GetMapping("/login")
public String loginPage() {
    return "login";
}



// service
@RequestMapping("/register")
public String registerPage(Model model) {
    UserForm userForm = new UserForm();
    // userForm.setName("MukeshRawat");
    // userForm.setAbout("hey this is mukesh");
    model.addAttribute("userForm", userForm);

    return "register";
}

// processing register
@RequestMapping( value = "/do-register" , method=RequestMethod.POST)
public String processRegister( @Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult,  HttpSession session){

    System.out.println("prosseing registering");
// fetch the data ====> user form
System.out.println(userForm);
// validate form data
if (rBindingResult.hasErrors()) {
    return "register";
    
}
// save to database

// userform --> user
// User user= User.builder().
// name(userForm.getName()).
// email(userForm.getEmail()).
// password(userForm.getPassword()).
// about(userForm.getAbout()).
// phoneNumber(userForm.getPhoneNumber()).
// profilePic("https://www.pngkit.com/png/detail/126-1262807_instagram-default-profile-picture-png.png").
// build();

User user= new User();
user.setName(userForm.getName());
user.setEmail(userForm.getEmail());
user.setPassword(userForm.getPassword());
user.setAbout(userForm.getAbout());
user.setPhoneNumber(userForm.getPhoneNumber());
user.setProfilePic("https://www.pngkit.com/png/detail/126-1262807_instagram-default-profile-picture-png.png");


 User savedUser= userService.saveUser(user);
 System.out.println("user saved");
// message = "registration successful"
// add the message
Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
session.setAttribute("message", message);

// redirect loing page

    return "redirect:/register";
 
     
      }

}




