import enums.ActionType;
import exception.AlreadyExistException;
import exception.NotFoundException;
import model.Resource;
import model.Role;
import model.RolePermission;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RbacSystem {

    private HashMap<String, User> users = new HashMap<>();

    private HashMap<String, Resource> resources = new HashMap<>();

    private HashMap<String, Role> roles = new HashMap<>();

    public User createUser(String userIdentity, String userName){
        if(users.containsKey(userIdentity)) throw new AlreadyExistException("User", userIdentity);
        User user = new User(userIdentity, userName);
        users.put(userIdentity, user);
        return user;
    }

    public Resource createResource(String resourceIdentity, String resourceName){
        if(resources.containsKey(resourceIdentity)) throw new AlreadyExistException("Resource", resourceIdentity);
        Resource resource = new Resource(resourceIdentity, resourceName);
        resources.put(resourceIdentity, resource);
        return resource;
    }

    public Role createRole(String roleIdentity, String roleName, HashMap<String, ArrayList<ActionType>> resourceActionsMap){
        if(roles.containsKey(roleIdentity)) throw new AlreadyExistException("Role", roleIdentity);
        ArrayList<RolePermission> rolePermissions = new ArrayList<RolePermission>();
        for(Map.Entry<String, ArrayList<ActionType>> resourceActions : resourceActionsMap.entrySet()){
            if(!resources.containsKey(resourceActions.getKey())) throw new NotFoundException("Resource", resourceActions.getKey());
            Resource resource = resources.get(resourceActions.getKey());
            for(ActionType actionType : resourceActions.getValue()){
                RolePermission rolePermission = new RolePermission(actionType, resource);
                rolePermissions.add(rolePermission);
            }
        }
        Role role = new Role(roleIdentity, roleName, rolePermissions);
        roles.put(roleIdentity, role);
        return role;
    }

    public User addRoleToUser(String userIdentity, String roleIdentity){
        if(!users.containsKey(userIdentity)) throw new NotFoundException("User", userIdentity);
        if(!roles.containsKey(roleIdentity)) throw new NotFoundException("Role", roleIdentity);
        User user = users.get(userIdentity);
        Role role = roles.get(roleIdentity);
        user.addRole(role);
        return user;
    }

    public User removeUserFromRole(String userIdentity, String roleIdentity){
        if(!users.containsKey(userIdentity)) throw new NotFoundException("User", userIdentity);
        if(!roles.containsKey(roleIdentity)) throw new NotFoundException("Role", roleIdentity);
        User user = users.get(userIdentity);
        Role role = roles.get(roleIdentity);
        user.removeRole(role);
        return user;
    }

    public Boolean checkUserAccess(String userIdentity, String resourceIdentity, ActionType actionType){
        if(!users.containsKey(userIdentity)) throw new NotFoundException("User", userIdentity);
        if(!resources.containsKey(resourceIdentity)) throw new NotFoundException("Resource", resourceIdentity);
        User user = users.get(userIdentity);
        Resource resource = resources.get(resourceIdentity);
        RolePermission rolePermission = new RolePermission(actionType, resource);
        return user.checkAccess(rolePermission);
    }
}
