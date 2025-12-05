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
    /* 未知 */
    UNKNOWN,
    /** 男 */
    MALE,
    /** 女 */
    FEMALE,
    ;

    /**
     * 性别
     */
    private final int code;

    SexEnum() {
        this.code = ordinal();
    }

    public Integer getCode() {
        return code;
    }
}
