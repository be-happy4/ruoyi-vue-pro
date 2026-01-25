package cn.iocoder.yudao.module.rela.parser.rule.ast;

import cn.iocoder.yudao.module.rela.parser.rule.ast.derive.DerivativeRelations;

public record Rule(RuleSegment expr, DerivativeRelations dr) {
}
