package mts.homework.sivelkaev.application.model.enums;

import lombok.Getter;

@Getter
public enum FinalApplicationStatusEnum {
    CLIENT_CHECK_FAILED("CLIENT_CHECK_FAILED"),
    SUCCESS("SUCCESS"),
    REFUSED("REFUSED");

    private final String value;

    FinalApplicationStatusEnum(String value)  {
        this.value = value;
    }

    public static boolean contains(String test) {
        for (FinalApplicationStatusEnum enumValue : FinalApplicationStatusEnum.values()) {
            if (enumValue.getValue().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
