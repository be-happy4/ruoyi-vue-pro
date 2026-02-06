/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

parser grammar BaseRule;

options {
    tokenVocab = ModeLexer;
}

reservedKeyword
    :
    AND
    | CASE
    | ELSE
    | END
    | FALSE
    | IN
    | NOT
    | NULL
    | OR
    | THEN
    | TRUE
    | WHEN
    ;

identifier
    : IDENTIFIER_ | unreservedWord
    ;

unreservedWord
    : IF
    ;

schemaName
    : (owner DOT_)? identifier
    ;

tableName
    : (owner DOT_)? name
    ;

columnName
    : (owner DOT_)? name
    ;

owner
    : identifier
    ;

name
    : identifier
    ;

tableNames
    : LP_? tableName (COMMA_ tableName)* RP_?
    ;

columnNames
    : LP_ columnName (COMMA_ columnName)* RP_
    ;

alias
    : identifier
    ;

primaryKey
    : PRIMARY? KEY
    ;

andOperator
    : AND
    ;

orOperator
    : OR
    ;

comparisonOperator
    : EQ_ | GTE_ | GT_ | LTE_ | LT_ | NEQ_
    ;

patternMatchingOperator
    : LIKE
    | NOT LIKE
    | ILIKE
    | NOT ILIKE
    ;

//cursorName
//    : name | hostVariable
//    ;

typeFuncNameKeyword
    : IS
    ;

aExpr
    : cExpr
    | PLUS_ aExpr
    | MINUS_ aExpr
    | aExpr PLUS_ aExpr
    | aExpr MINUS_ aExpr
    | aExpr ASTERISK_ aExpr
    | aExpr SLASH_ aExpr
    | aExpr MOD_ aExpr
    | aExpr comparisonOperator aExpr
    | NOT aExpr
    | aExpr patternMatchingOperator aExpr ESCAPE aExpr
    | aExpr patternMatchingOperator aExpr
    | aExpr IS NULL
    | aExpr IS NOT NULL
    | aExpr IS TRUE
    | aExpr IS NOT TRUE
    | aExpr IS FALSE
    | aExpr IS NOT FALSE
    | aExpr BETWEEN bExpr AND aExpr
    | aExpr NOT BETWEEN bExpr AND aExpr
    | aExpr IN inExpr
    | aExpr NOT IN inExpr
    | aExpr andOperator aExpr
    | aExpr orOperator aExpr
    ;

bExpr
    : cExpr
    | PLUS_ bExpr
    | MINUS_ bExpr
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

indirection
    : indirectionEl
    | indirection indirectionEl
    ;

optIndirection
    : optIndirection indirectionEl |
    ;

indirectionEl
    : DOT_ attrName
    | DOT_ ASTERISK_
    | LBT_ aExpr RBT_
    | LBT_ sliceBound? COLON_ sliceBound? RBT_
    ;

sliceBound
    : aExpr
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

allOp
    : op | mathOperator
    ;

op
    : (AND_
    | OR_
    | NOT_
    | MOD_
    | PLUS_
    | MINUS_
    | ASTERISK_
    | SLASH_
    | EQ_
    | NEQ_
    | GT_
    | GTE_
    | LT_
    | LTE_
    )+
    ;

mathOperator
    : PLUS_
    | MINUS_
    | ASTERISK_
    | SLASH_
    | MOD_
    | LT_
    | GT_
    | EQ_
    | LTE_
    | GTE_
    | NEQ_
    ;

anyOperator
    : allOp | colId DOT_ anyOperator
    ;

frameClause
    : (RANGE|ROWS|GROUPS) frameExtent windowExclusionClause?
    ;

frameExtent
    : frameBound
    | BETWEEN frameBound AND frameBound
    ;

frameBound
    : UNBOUNDED PRECEDING
    | UNBOUNDED FOLLOWING
    | CURRENT ROW
    | aExpr PRECEDING
    | aExpr FOLLOWING
    ;

windowExclusionClause
    : EXCLUDE CURRENT ROW
    | EXCLUDE GROUP
    | EXCLUDE TIES
    | EXCLUDE NO OTHERS
    ;

row
    : ROW LP_ exprList RP_
    | ROW LP_ RP_
    | LP_ exprList COMMA_ aExpr RP_
    ;

explicitRow
    : ROW LP_ exprList RP_
    | ROW LP_ RP_
    ;

implicitRow
    : LP_ exprList COMMA_ aExpr RP_
    ;

subType
    : ANY | SOME | ALL
    ;

arrayExpr
    : LBT_ exprList RBT_
    | LBT_ arrayExprList RBT_
    | LBT_ RBT_
    ;

arrayExprList
    : arrayExpr (COMMA_ arrayExpr)*
    ;

funcArgList
    : funcArgExpr (COMMA_ funcArgExpr)*
    ;

paramName
    : typeFunctionName
    ;

funcArgExpr
    : aExpr
    | paramName CQ_ aExpr
    | paramName GTE_ aExpr
    ;

typeList
    : typeName (COMMA_ typeName)*
    ;

funcApplication
    : funcName LP_ RP_
    | funcName LP_ funcArgList RP_
    | funcName LP_ ASTERISK_ RP_
    ;

funcName
    : typeFunctionName | colId indirection
    ;

aexprConst
    : numberConst
    | STRING_
    | funcName STRING_
    | funcName LP_ funcArgList RP_ STRING_
    | constTypeName STRING_
    | TRUE
    | FALSE
    | NULL
    ;

numberConst
    : (PLUS_ | MINUS_)? NUMBER_
    ;

qualifiedName
    : colId | colId indirection
    ;

colId
    : identifier
    ;

typeFunctionName
    : identifier | unreservedWord | typeFuncNameKeyword
    ;

functionTable
    : functionExprWindowless ordinality?
    | ROWS FROM LP_ rowsFromList RP_ ordinality?
    ;

funcExpr
    : funcApplication filterClause?
    | functionExprCommonSubexpr
    ;

filterClause
    : FILTER LP_ WHERE aExpr RP_
    ;

functionExprWindowless
    : funcApplication | functionExprCommonSubexpr
    ;

ordinality
    : WITH ORDINALITY
    ;

functionExprCommonSubexpr
    : COLLATION FOR LP_ aExpr RP_
    | CURRENT_DATE
    | CURRENT_TIME
    | CURRENT_TIME LP_ NUMBER_ RP_
    | CURRENT_TIMESTAMP
    | CURRENT_TIMESTAMP LP_ NUMBER_ RP_
    | LOCALTIME
    | LOCALTIME LP_ NUMBER_ RP_
    | LOCALTIMESTAMP
    | LOCALTIMESTAMP LP_ NUMBER_ RP_
    | CURRENT_ROLE
    | CURRENT_USER
    | SESSION_USER
    | USER
    | CURRENT_CATALOG
    | CURRENT_SCHEMA
    | CAST LP_ aExpr AS typeName RP_
    | EXTRACT LP_ extractList? RP_
    | NORMALIZE LP_ aExpr RP_
    | NORMALIZE LP_ aExpr COMMA_ unicodeNormalForm RP_
    | OVERLAY LP_ overlayList RP_
    | POSITION LP_ positionList RP_
    | SUBSTRING LP_ substrList RP_
    | TREAT LP_ aExpr AS typeName RP_
    | TRIM LP_ BOTH trimList RP_
    | TRIM LP_ LEADING trimList RP_
    | TRIM LP_ TRAILING trimList RP_
    | TRIM LP_ trimList RP_
    | NULLIF LP_ aExpr COMMA_ aExpr RP_
    | COALESCE LP_ exprList RP_
    | GREATEST LP_ exprList RP_
    | LEAST LP_ exprList RP_
    ;

typeName
    : simpleTypeName optArrayBounds
    | SETOF simpleTypeName optArrayBounds
    | simpleTypeName ARRAY LBT_ NUMBER_ RBT_
    | SETOF simpleTypeName ARRAY LBT_ NUMBER_ RBT_
    | simpleTypeName ARRAY
    | SETOF simpleTypeName ARRAY
    ;

simpleTypeName
    : genericType
    | numeric
    | bit
    | character
    | constDatetime
    | constInterval optInterval
    | constInterval LP_ NUMBER_ RP_
    ;

constTypeName
    : numeric
//    | bit
    | character
    | constDatetime
    ;

exprList
    : aExpr
    | exprList COMMA_ aExpr
    ;

extractList
    : extractArg FROM aExpr
    ;

extractArg
    : YEAR
    | QUARTER
    | MONTH
    | WEEK
    | DAY
    | HOUR
    | MINUTE
    | SECOND
    | identifier
    ;

genericType
    : typeFunctionName typeModifiers? | typeFunctionName attrs typeModifiers?
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

character
    : characterWithLength | characterWithoutLength
    ;

characterWithLength
    : characterClause LP_ NUMBER_ RP_
    ;

characterWithoutLength
    : characterClause
    ;

characterClause
    : CHARACTER VARYING?
    | CHAR VARYING?
    | VARCHAR
    ;

optFloat
    : LP_ NUMBER_ RP_ |
    ;

attrs
    : DOT_ attrName | attrs DOT_ attrName
    ;

attrName
    : colLable
    ;

colLable
    : identifier
    | colNameKeyword
    | typeFuncNameKeyword
    | reservedKeyword
    ;

bit
    : bitWithLength | bitWithoutLength
    ;

bitWithLength
    : BIT VARYING? LP_ exprList RP_
    ;

bitWithoutLength
    : BIT VARYING?
    ;

constInterval
    : INTERVAL
    ;

optInterval
    : YEAR
    | MONTH
    | DAY
    | HOUR
    | MINUTE
    | intervalSecond
    | YEAR TO MONTH
    | DAY TO HOUR
    | DAY TO MINUTE
    | DAY TO intervalSecond
    | HOUR TO MINUTE
    | HOUR TO intervalSecond
    | MINUTE TO intervalSecond
    |
    ;

optArrayBounds
    : optArrayBounds LBT_ RBT_
    | optArrayBounds LBT_ NUMBER_ RBT_
    |
    ;

intervalSecond
    : SECOND
    | SECOND LP_ NUMBER_ RP_
    ;

unicodeNormalForm
    : NFC | NFD | NFKC | NFKD
    ;

trimList
    : aExpr FROM exprList
    | FROM exprList
    | exprList
    ;

overlayList
    : aExpr overlayPlacing substrFrom substrFor
    | aExpr overlayPlacing substrFrom
    ;

overlayPlacing
    : PLACING aExpr
    ;

substrFrom
    : FROM aExpr
    ;

substrFor
    : FOR aExpr
    ;

positionList
    : bExpr IN bExpr |
    ;

substrList
    : aExpr substrFrom substrFor
    | aExpr substrFor substrFrom
    | aExpr substrFrom
    | aExpr substrFor
    | exprList
    |
    ;

rowsFromItem
    : functionExprWindowless columnDefList
    ;

rowsFromList
    : rowsFromItem (COMMA_ rowsFromItem)*
    ;

columnDefList
    : AS LP_ tableFuncElementList RP_
    ;

tableFuncElementList
    : tableFuncElement (COMMA_ tableFuncElement)*
    ;

tableFuncElement
    : colId typeName collateClause?
    ;

collateClause
    : COLLATE EQ_? anyName
    ;

anyName
    : colId | colId attrs
    ;

aliasClause
    : AS colId LP_ nameList RP_
    | AS colId
    | colId LP_ nameList RP_
    | colId
    ;

nameList
    : name | nameList COMMA_ name
    ;

funcAliasClause
    : aliasClause
    | AS LP_ tableFuncElementList RP_
    | AS colId LP_ tableFuncElementList RP_
    | colId LP_ tableFuncElementList RP_
    ;

allOrDistinct
    : ALL | DISTINCT
    ;

nullsOrder
    : NULLS FIRST
    | NULLS LAST
    ;

distinctClause
    : DISTINCT
    | DISTINCT ON LP_ exprList RP_
    ;

distinct
    : DISTINCT
    ;

optClass
    : anyName |
    ;

reloptions
    : LP_ reloptionList RP_
    ;

reloptionList
    : reloptionElem (COMMA_ reloptionElem)*
    ;

reloptionElem
    : alias EQ_ defArg
    | alias
    | alias DOT_ alias EQ_ defArg
    | alias DOT_ alias
    ;

defArg
    : funcType
    | reservedKeyword
    | NUMBER_
    | STRING_
    | NONE
    ;

funcType
    : typeName
    | typeFunctionName attrs MOD_ TYPE
    | SETOF typeFunctionName attrs MOD_ TYPE
    ;

selectWithParens
    : DEFAULT_DOES_NOT_MATCH_ANYTHING
    ;

dataType
    : dataTypeName dataTypeLength? characterSet? collateClause? | dataTypeName LP_ STRING_ (COMMA_ STRING_)* RP_ characterSet? collateClause?
    ;

dataTypeName
    : INT | INT2 | INT4 | INT8 | SMALLINT | INTEGER | BIGINT | DECIMAL | NUMERIC | REAL | FLOAT | FLOAT4 | FLOAT8 | DOUBLE PRECISION | SMALLSERIAL | SERIAL | BIGSERIAL
    | VARCHAR | CHARACTER | CHAR | TEXT | NAME | BYTEA | TIMESTAMP | DATE | TIME | INTERVAL | BOOLEAN | ENUM | POINT
    | LINE | LSEG | BOX | PATH | POLYGON | CIRCLE | CIDR | INET | MACADDR | MACADDR8 | BIT | VARBIT | TSVECTOR | TSQUERY | XML
    | JSON | INT4RANGE | INT8RANGE | NUMRANGE | TSRANGE | TSTZRANGE | DATERANGE | ARRAY | identifier | constDatetime | typeName
    ;

dataTypeLength
    : LP_ NUMBER_ (COMMA_ NUMBER_)? RP_
    ;

characterSet
    : (CHARACTER | CHAR) SET EQ_? ignoredIdentifier
    ;

ignoredIdentifier
    : identifier (DOT_ identifier)?
    ;

ignoredIdentifiers
    : ignoredIdentifier (COMMA_ ignoredIdentifier)*
    ;

signedIconst
    : NUMBER_
    | PLUS_ NUMBER_
    | MINUS_ NUMBER_
    ;

booleanOrString
    : TRUE
    | FALSE
    | ON
    | nonReservedWord
    | STRING_
    ;

nonReservedWord
    : identifier
    | unreservedWord
    | colNameKeyword
    | typeFuncNameKeyword
    ;

colNameKeyword
    : BETWEEN
    | BIGINT
    | BIT
    | BOOLEAN
    | CHAR
    | CHARACTER
    | COALESCE
    | DEC
    | DECIMAL
    | EXISTS
    | EXTRACT
    | FLOAT
    | GREATEST
    | GROUPING
    | INOUT
    | INT
    | INTEGER
    | INTERVAL
    | LEAST
    | NATIONAL
    | NCHAR
    | NONE
    | NULLIF
    | NUMERIC
    | OUT
    | OVERLAY
    | POSITION
    | PRECISION
    | REAL
    | ROW
    | SETOF
    | SMALLINT
    | SUBSTRING
    | TIME
    | TIMESTAMP
    | TREAT
    | TRIM
    | VALUES
    | VARCHAR
    ;

databaseName
    : colId
    ;

varName
    : colId
    | varName DOT_ colId
    ;

varList
    : varValue (COMMA_ varValue)*
    ;

varValue
    : booleanOrString | numericOnly
    ;

numericOnly
    : NUMBER_
    | PLUS_ NUMBER_
    | MINUS_ NUMBER_
    ;

optColumnList
    : LP_ columnList RP_
    ;

columnElem
    : colId
    ;

columnList
    : columnElem (COMMA_ columnElem)*
    ;

definition
    : LP_ defList RP_
    ;

defList
    : defElem (COMMA_ defElem)*
    ;

defElem
    : (colLabel EQ_ defArg) | colLabel
    ;

colLabel
    : identifier
    | unreservedWord
    | colNameKeyword
    | typeFuncNameKeyword
    | reservedKeyword
    ;

keyActions
    : keyUpdate
    | keyDelete
    | keyUpdate keyDelete
    | keyDelete keyUpdate
    ;

keyDelete
    : ON DELETE keyAction
    ;

keyUpdate
    : ON UPDATE keyAction
    ;

keyAction
    : NO ACTION
    | RESTRICT
    | CASCADE
    | SET NULL
    | SET DEFAULT
    ;

booleanValue
    : TRUE | ON | FALSE | OFF | NUMBER_
    ;
