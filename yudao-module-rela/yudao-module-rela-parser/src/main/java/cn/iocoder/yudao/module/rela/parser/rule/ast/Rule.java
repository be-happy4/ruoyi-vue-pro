package cn.iocoder.yudao.module.rela.parser.rule.ast;

import java.util.List;

public record Rule(RuleSegment expr, List<DerivativeRelation> relaList) {
}
