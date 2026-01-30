package cn.iocoder.yudao.module.rela.parser.rule.ast.derive;

import cn.iocoder.yudao.module.rela.parser.rule.ast.value.IdentifierValue;

public record DerivativeRelation(
        IdentifierValue leftId,
        IdentifierValue relationTypeName,
        IdentifierValue rightId
) {
}
