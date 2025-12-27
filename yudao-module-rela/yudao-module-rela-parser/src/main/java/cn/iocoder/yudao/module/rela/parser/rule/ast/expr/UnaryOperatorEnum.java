package cn.iocoder.yudao.module.rela.parser.rule.ast.expr;

import lombok.Getter;

public enum UnaryOperatorEnum {
    Positive("+"),
    Negative("-"),
    Not("NOT"),
    ;
    @Getter
    private final String operator;

    UnaryOperatorEnum(String operator) {
        this.operator = operator;
    }
}
