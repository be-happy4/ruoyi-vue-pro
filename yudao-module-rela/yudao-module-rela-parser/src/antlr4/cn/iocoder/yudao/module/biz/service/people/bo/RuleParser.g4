parser grammar RuleParser;

import BaseRule;

root
    : ruleList EOF
    ;

ruleList
    : rule+
    ;

rule
    : aExpr ARROW derivativeRelationList
    ;

identifier
    : IDENTIFIER_ | unreservedWord
    ;

andOperator
    : AND | AND_
    ;

orOperator
    : OR | OR_
    ;

comparisonOperator
    : EQ_ | GTE_ | GT_ | LTE_ | LT_ | NEQ_
    ;
inExpr
    : selectWithParens | LP_ exprList RP_
    ;

caseExpr
    : CASE caseArg? whenClauseList caseDefault? END
    ;

whenClauseList
    : whenClause+
    ;

whenClause
    : WHEN aExpr THEN aExpr
    ;

indirectionEl
    : DOT_ attrName
    | DOT_ ASTERISK_
    | LBT_ aExpr RBT_
    | LBT_ sliceBound? COLON_ sliceBound? RBT_
    ;

indirection
    : indirectionEl
    | indirection indirectionEl
    ;

caseDefault
    : ELSE aExpr
    ;

caseArg
    : aExpr
    ;

columnref
    : colId
    | colId indirection
    ;

colId
    : identifier
    ;

attrName
    : colLable
    ;

//qualOp
//    : OPERATOR LP_ anyOperator RP_
//    : jsonOperator
//    | geometricOperator
//    | OPERATOR LP_ anyOperator RP_
//    ;

colLable
    : identifier
//    | colNameKeyword
//    | typeFuncNameKeyword
    | reservedKeyword
    ;

aexprConst
    : numberConst
    | STRING_
    | BEGIN_DOLLAR_STRING_CONSTANT DOLLAR_TEXT* END_DOLLAR_STRING_CONSTANT
//    | funcName STRING_
//    | funcName LP_ funcArgList sortClause? RP_ STRING_
//    | constTypeName STRING_
    | TRUE
    | FALSE
    | NULL
    ;

typeModifiers
    : LP_ exprList RP_
    ;

numeric
    : INT | INTEGER | SMALLINT | BIGINT| REAL | FLOAT optFloat | DOUBLE PRECISION | DECIMAL typeModifiers? | DEC typeModifiers? | NUMERIC typeModifiers? | BOOLEAN | FLOAT8 | FLOAT4 | INT2 | INT4 | INT8
    ;

constDatetime
    : TIMESTAMP LP_ NUMBER_ RP_ timezone?
    | TIMESTAMP timezone?
    | TIME LP_ NUMBER_ RP_ timezone?
    | TIME timezone?
    | DATE
    ;

timezone
    : WITH TIME ZONE
    | WITHOUT TIME ZONE
    ;

optFloat
    : LP_ NUMBER_ RP_ |
    ;

constTypeName
    : numeric
//    | bit
//    | character
    | constDatetime
    ;

exprList
    : aExpr
    | exprList COMMA_ aExpr
    ;

numberConst
    : (PLUS_ | MINUS_)? NUMBER_
    ;

aExpr
    : cExpr
//    | aExpr TYPE_CAST_ typeName
//    | aExpr COLLATE anyName
    | aExpr AT TIME ZONE aExpr
    | PLUS_ aExpr
    | MINUS_ aExpr
    | aExpr PLUS_ aExpr
    | aExpr MINUS_ aExpr
    | aExpr ASTERISK_ aExpr
    | aExpr SLASH_ aExpr
    | aExpr MOD_ aExpr
    | aExpr CARET_ aExpr
    | aExpr AMPERSAND_ aExpr
    | aExpr VERTICAL_BAR_ aExpr
//    | aExpr qualOp aExpr
//    | qualOp aExpr
//    | aExpr qualOp
    | aExpr comparisonOperator aExpr
    | NOT aExpr
//    | aExpr patternMatchingOperator aExpr ESCAPE aExpr
//    | aExpr patternMatchingOperator aExpr
    | aExpr IS NULL
    | aExpr ISNULL
    | aExpr IS NOT NULL
    | aExpr NOTNULL
//    | row OVERLAPS row
    | aExpr IS TRUE
    | aExpr IS NOT TRUE
    | aExpr IS FALSE
    | aExpr IS NOT FALSE
//    | aExpr IS UNKNOWN
//    | aExpr IS NOT UNKNOWN
//    | aExpr IS DISTINCT FROM aExpr
//    | aExpr IS NOT DISTINCT FROM aExpr
//    | aExpr IS OF LP_ typeList RP_
//    | aExpr IS NOT OF LP_ typeList RP_
//    | aExpr BETWEEN ASYMMETRIC? bExpr AND aExpr
//    | aExpr NOT BETWEEN ASYMMETRIC? bExpr AND aExpr
//    | aExpr BETWEEN SYMMETRIC bExpr AND aExpr
//    | aExpr NOT BETWEEN SYMMETRIC bExpr AND aExpr
    | aExpr IN inExpr
    | aExpr NOT IN inExpr
//    | aExpr subqueryOp subType selectWithParens
//    | aExpr subqueryOp subType LP_ aExpr RP_
//    | UNIQUE selectWithParens
//    | aExpr IS DOCUMENT
//    | aExpr IS NOT DOCUMENT
//    | aExpr IS NORMALIZED
//    | aExpr IS unicodeNormalForm NORMALIZED
//    | aExpr IS NOT NORMALIZED
//    | aExpr IS NOT unicodeNormalForm NORMALIZED
    | aExpr andOperator aExpr
    | aExpr orOperator aExpr
    | DEFAULT
    ;

bExpr
    : cExpr
//    | bExpr TYPE_CAST_ typeName
    | PLUS_ bExpr
    | MINUS_ bExpr
//    | bExpr qualOp bExpr
//    | qualOp bExpr
//    | bExpr qualOp
    | bExpr IS DISTINCT FROM bExpr
    | bExpr IS NOT DISTINCT FROM bExpr
    | bExpr IS OF LP_ typeList RP_
    | bExpr IS NOT OF LP_ typeList RP_
    | bExpr IS DOCUMENT
    | bExpr IS NOT DOCUMENT
    ;

cExpr
    : columnref
    | aexprConst
    | PARAM indirectionEl?
    | LP_ aExpr RP_ optIndirection
    | caseExpr
    | funcExpr
    | selectWithParens
    | selectWithParens indirection
    | EXISTS selectWithParens
    | ARRAY selectWithParens
    | ARRAY arrayExpr
    | explicitRow
    | implicitRow
    | GROUPING LP_ exprList RP_
    ;


derivativeRelationList
    : derivativeRelation (COMMA derivativeRelation)*
    ;
derivativeRelation
    : personInfo ASSIGN personInfo
    ;



