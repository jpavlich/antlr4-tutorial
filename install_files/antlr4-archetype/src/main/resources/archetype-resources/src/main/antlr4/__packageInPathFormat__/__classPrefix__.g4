grammar ${classPrefix};

start
:
	'hello' 'world'
;

WS
:
	[ \t\r\n]+ -> skip
;