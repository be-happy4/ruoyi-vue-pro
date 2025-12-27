parser grammar RuleParser;

options {
  tokenVocab=BaseLexer;
}

root
    : ruleList EOF
    ;

ruleList
    : rule+
    ;

rule
    : epxr ARROW derivativeRelationList
    ;

epxr
:
;

derivativeRelationList
    : derivativeRelation (COMMA derivativeRelation)*
    ;
derivativeRelation
    : personInfo ASSIGN personInfo
    ;



