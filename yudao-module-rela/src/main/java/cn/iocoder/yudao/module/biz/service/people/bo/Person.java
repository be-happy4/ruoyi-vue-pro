package cn.iocoder.yudao.module.biz.service.people.bo;

import cn.iocoder.yudao.module.system.enums.common.SexEnum;

import java.time.LocalDate;

public record Person(
        Long id,
        String name,
        SexEnum sex,
        LocalDate birthday,
        String avatar
) {

    public int compareAge(Person other) {
        return this.birthday.compareTo(other.birthday);
    }
}
