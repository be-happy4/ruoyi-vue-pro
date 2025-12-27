package cn.iocoder.yudao.module.rela.service.people.bo;

import cn.iocoder.yudao.module.rela.service.people.util.FuzzyDate;

public record Relation(
        Long id,
        RelationType type,
        Person from,
        Person to,
        FuzzyDate start,
        FuzzyDate end
) {
    public Relation(
            RelationType type,
            Person from,
            Person to) {
        this(null, type, from, to, FuzzyDate.MIN, FuzzyDate.MAX);
    }
}
