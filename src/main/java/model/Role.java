package model;

import enums.ActionType;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;

@Accessors(fluent = true)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Role {

    private @NonNull String identity;

    private @NonNull String name;

    private @NonNull ArrayList<RolePermission> rolePermissions;

    public Boolean checkAccess(RolePermission rolePermission){
        return rolePermissions.contains(rolePermission);
    }
}
