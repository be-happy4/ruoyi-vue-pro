package cn.iocoder.yudao.module.rela.service.people.bo;

import java.time.LocalDateTime;

public record RelationOp(
        Long id,
        RelationType type,
        Person from,
        Person to,
        LocalDateTime start,
        LocalDateTime end
) {
}
