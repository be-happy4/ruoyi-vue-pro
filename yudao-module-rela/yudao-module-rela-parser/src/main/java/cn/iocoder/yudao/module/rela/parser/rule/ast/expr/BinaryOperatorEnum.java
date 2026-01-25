package cn.iocoder.yudao.module.rela.parser.rule.ast.expr;

import lombok.Getter;

@Getter
public enum BinaryOperatorEnum {
    Times(60, "*"),
    Div(60, "/"),
    Plus(50, "+"),
    Minus(50, "-"),
    Concat(40, "||"),
    Equal(30, "="),
    NotEqual(30, "!="),
    // IsDistinctFrom(30),
    // IsNotDistinctFrom(30),
    In(30, "IN"),
    NotIn(30, "NOT IN"),
    GreaterThan(30, ">"),
    GreaterThanEqual(30, ">="),
    LessThan(30, "<"),
    LessThanEqual(30, "<="),
    //Overlaps(30),
    And(20, "AND"),
    Or(10, "OR"),
    ;

    private final int precedence;
    private final String operator;

    BinaryOperatorEnum(int precedence, String operator) {
        this.precedence = precedence;
        this.operator = operator;
    }
}
