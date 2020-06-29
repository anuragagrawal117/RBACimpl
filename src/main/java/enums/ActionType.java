package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum ActionType {

    READ(0),
    WRITE(1),
    DELETE(2);

    private final Integer type;
}
