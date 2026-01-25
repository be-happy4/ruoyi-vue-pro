package cn.iocoder.yudao.module.rela.parser.rule.ast.expr;

import cn.iocoder.yudao.module.rela.parser.rule.ast.RuleSegment;

/**
 * Expression segment.
 */
public sealed interface ExpressionSegment extends RuleSegment
        permits BinaryOperationExpression, ComplexExpressionSegment,
        SimpleExpressionSegment,
        UnaryOperationExpression {
    /**
     * Get text.
     *
     * @return text
     */
    String text();
}
