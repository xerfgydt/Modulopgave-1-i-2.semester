package dk.kea.Controllers;

import dk.kea.Model.Entites.User;
import dk.kea.Model.Repository.CrudRepository;
import dk.kea.Model.Repository.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    UserInterface uif = new CrudRepository();


    @GetMapping("/")
    public String StartSide(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User u, Model model){

        u = uif.loginDB(u.getEmail(), u.getPassword());
        if (u != null){

        model.addAttribute("user" , u);
        return "homepage";
         }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(){
        uif.byebye();
        return "redirect:/";
    }

    @GetMapping("/new")
    public String registrer(Model model){
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute User u){
        uif.create(u);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
         //razz
        if(uif.read(id) != null){
            model.addAttribute("user", uif.read(id));

            return "update";
        }
        return "redirect:/";
    }

    @PostMapping("/update2")
    public String update2(@RequestParam("id") int id, User user, String newPassword)
    {
        //User myUser = uif.read(id);
        user = uif.greenLight(user.getPassword(), id);
        if(user != null){
            uif.updatedb(user, newPassword);
            return "redirect:/";
        }

        return "/update";
    }



    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, Model model)
    {

        if (uif.read(id) != null) {
            model.addAttribute("user", uif.read(id));
            return "delete";

        }
        return "redirect:/";
    }

    @PostMapping("/delete2")
    public String delete2(@RequestParam("id") int id, User user){
        user = uif.greenLight(user.getPassword(), id );
        if(user != null)
        {
            uif.delete(id);
            return "redirect:/";
        }
     return "/delete";
    }
}
