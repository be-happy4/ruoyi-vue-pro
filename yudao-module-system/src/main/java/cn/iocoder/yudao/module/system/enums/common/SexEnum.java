package cn.iocoder.yudao.module.system.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别的枚举值
 *
 * @author 芋道源码
 */
@AllArgsConstructor
public enum SexEnum {
    UNKNOWN,
    MALE,
    FEMALE,
    ;

    /**
     * 性别
     */
    private final int code;
    @Getter
    private final String abbreviation;

    SexEnum() {
        this.code = ordinal();
        this.abbreviation = name().substring(0, 1);
    }

    public Integer getCode() {
        return code;
    }
}
