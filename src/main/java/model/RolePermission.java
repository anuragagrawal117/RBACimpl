package model;

import enums.ActionType;
import lombok.*;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class RolePermission {

    private @NonNull ActionType actionType;

    private @NonNull Resource resource;

    public boolean equals(Object obj){
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RolePermission)) {
            return false;
        }
        RolePermission rolePermission = (RolePermission) obj;
        return actionType.equals(rolePermission.actionType()) && resource.equals(rolePermission.resource());
    }

}
