package cn.iocoder.yudao.module.biz.service.people.bo;

import cn.iocoder.yudao.module.biz.service.people.util.FuzzyDate;
import cn.iocoder.yudao.module.system.enums.common.SexEnum;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public record Person(
        Long id,
        String name,
        SexEnum sex,
        FuzzyDate birthday,
        @Nullable
        String avatar
) {
    static final Comparator<Person> AGE_COMPARATOR = Comparator.comparing(
            Person::birthday,
            Comparator.nullsLast(Comparator.naturalOrder()));

    public int compareAge(Person other) {
        return AGE_COMPARATOR.compare(this, other);
    }

    @Override
    public String toString() {
        return "(" + name + "," + sex.getAbbreviation() + "," + birthday.format() + ")";
    }
}
