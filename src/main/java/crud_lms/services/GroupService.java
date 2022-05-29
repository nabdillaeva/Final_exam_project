package crud_lms.services;

import crud_lms.models.Group;
import crud_lms.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {


    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    public void saveGroup(Group group){
        groupRepository.saveGroup(group);
    }

    public List<Group> findAllGroups(){
        return groupRepository.findAllGroups();
    }

    public Group show(long id){
        return groupRepository.show(id);
    }

    public void update (Group group, long groupId){
        groupRepository.updateGroup(group, groupId);
    }


    public Group findById(long id){
        return groupRepository.findById(id);
    }

    public void deleteById(long id){
        groupRepository.deleteById(id);
    }
}
