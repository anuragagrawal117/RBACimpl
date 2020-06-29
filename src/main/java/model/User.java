package model;

import enums.ActionType;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.HashMap;

@Accessors(fluent = true)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class User {

    private @NonNull String identity;

    private @NonNull String name;

    private HashMap<String, Role> roles = new HashMap<>();

    public void addRole(Role role){
        roles.put(role.identity(), role);
    }

    public void removeRole(Role role){
        roles.remove(role.identity());
    }

    public Boolean checkAccess(RolePermission rolePermission){
        for(Role role: roles.values()){
            if(role.checkAccess(rolePermission)) return true;
        }
        return false;
    }

}
