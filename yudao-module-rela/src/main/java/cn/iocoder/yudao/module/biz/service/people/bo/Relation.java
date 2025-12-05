package cn.iocoder.yudao.module.biz.service.people.bo;

import java.time.LocalDateTime;

public record Relation(
        Long id,
        RelationType type,
        Person from,
        Person to,
        LocalDateTime start,
        LocalDateTime end
) {
    public Relation(
            Long id,
            RelationType type,
            Person from,
            Person to) {
        this(id, type, from, to, LocalDateTime.MIN, LocalDateTime.MAX);
    }
}
