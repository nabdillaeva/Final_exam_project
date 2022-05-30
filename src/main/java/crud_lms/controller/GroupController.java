package crud_lms.controller;


import crud_lms.models.Group;
import crud_lms.services.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @ModelAttribute("groupList")
    public List<Group> findAllGroups(){
        return groupService.findAllGroups();
    }

    @GetMapping
    public String findAll(){
        return "group/allGroups";
    }

    @GetMapping("/save")
    public String saveGroupPage(Model model){
        model.addAttribute("emptyGroup", new Group());
        return "group/saveGroupPage";
    }

    @PostMapping("/save")
    public String saveCompany(Group group){
        groupService.saveGroup(group);
        return "redirect:/api/groups";
    }

    @GetMapping("/{id}/update")
    public String updateCompany(Model model, @PathVariable("id") long id){
        model.addAttribute("groupUpdate",groupService.show(id));
        return "group/update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("groupUpdate") Group group,
                         @PathVariable("id") long id) {
        groupService.update(group,id);
        return "redirect:/api/groups";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        groupService.deleteById(id);
        return "redirect:/api/groups";
    }
}
