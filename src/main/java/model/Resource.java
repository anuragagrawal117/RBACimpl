package model;

import lombok.*;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Resource {

    private @NonNull String identity;

    private @NonNull String name;

    public boolean equals(Object obj){
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Resource)) {
            return false;
        }
        Resource resource = (Resource) obj;
        return identity.equals(resource.identity());
    }

}
