package cn.iocoder.yudao.module.rela.parser.rule.ast.rela;

import cn.iocoder.yudao.module.rela.enums.Sex;
import cn.iocoder.yudao.module.rela.model.FuzzyDate;

import java.time.LocalDateTime;

public record Person(
        String name,
        Sex sex,
        LocalDateTime birthdate
) {
}
