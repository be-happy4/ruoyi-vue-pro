lexer grammar BaseLexer;

COMMA    : ',';
ASSIGN   : '=';
GT       : '>';
LT       : '<';
COLON    : ':';
ARROW    : '=>';
QUESTION : '?';
BANG     : '!';
AND      : '&&';
OR       : '||';

UNDER_SCORE  : '_';
LPAREN     : '(';
RPAREN     : ')';
LBRACE     : '{';
RBRACE     : '}';
LBRACK     : '[';
RBRACK     : ']';

fragment Digit : [0-9] ;

SexLiteral : 'U' | 'M' | 'F' ;

IDENTIFIER : NAME | QUOTED_NAME;

// Quoted name allows spaces and most chars except quote/newline.
// Keep WS skipping spaces so unquoted names cannot contain spaces.
QUOTED_NAME : '"' (~["\r\n])* '"' ;

// Unquoted name: no whitespace, no delimiting punctuation
NAME : ~[ \t\r\n(),{}[\]=><!:?&|]+ ;


// Date forms
YYYYMMDD : YEAR4 MONTH2 DAY2 ;
YEAR4    : Digit Digit Digit Digit ;
MONTH2   : '1' [0-2] | '0' Digit ;
DAY2     : '3' [0-1] | Digit Digit ;

// Whitespace/comments skipped
WS: [ \t\r\n\u000C]+ -> skip;
// COMMENT: '/*' .*? '*/' -> channel(HIDDEN);
LINE_COMMENT: '#' ~[\r\n]* -> channel(HIDDEN);

