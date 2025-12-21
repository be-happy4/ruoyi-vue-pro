package cn.iocoder.yudao.module.biz.service.people.bo;

import cn.iocoder.yudao.module.biz.service.people.util.FuzzyDate;

import java.time.LocalDateTime;

public record Relation(
        Long id,
        RelationType type,
        Person from,
        Person to,
        FuzzyDate start,
        FuzzyDate end
) {
    public Relation(
            Long id,
            RelationType type,
            Person from,
            Person to) {
        this(id, type, from, to, FuzzyDate.MIN, FuzzyDate.MAX);
    }
}
