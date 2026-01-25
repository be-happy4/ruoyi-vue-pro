package cn.iocoder.yudao.module.rela.parser.rule.ast.derive;

import cn.iocoder.yudao.module.rela.parser.rule.ast.rela.Relation;

public record DerivativeRelation(
        String leftEntity,
        String rightEntity,
        Relation relation
) {
}
