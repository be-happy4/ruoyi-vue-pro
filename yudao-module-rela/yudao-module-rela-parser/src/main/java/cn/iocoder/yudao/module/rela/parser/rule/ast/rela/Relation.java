package cn.iocoder.yudao.module.rela.parser.rule.ast.rela;

import java.time.LocalDateTime;

public record Relation(
        Long id,
        RelationType type,
        String idFrom,
        String idTo,
        LocalDateTime start,
        LocalDateTime end) {
}
