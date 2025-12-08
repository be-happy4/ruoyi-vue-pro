parser grammar PersonParser;

options {
  tokenVocab=BaseLexer;
}

personInfo
    : personName (LPAREN personAttr? RPAREN)?
    ;

personAttr
    : SexLiteral (COMMA birthday?)?
    ;

personName
    : IDENTIFIER
    ;

birthday
    : YYYYMMDD
    ;
