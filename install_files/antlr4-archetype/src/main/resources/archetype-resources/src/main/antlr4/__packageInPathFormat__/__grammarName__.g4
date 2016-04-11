grammar ${grammarName};

start
:
	'hello' 'world'
;

WS
:
	[ \t\r\n]+ -> skip
;