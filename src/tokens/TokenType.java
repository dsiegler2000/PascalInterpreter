package tokens;

public enum TokenType {
	
	INTEGER,
	REAL,
	PLUS,
	MINUS,
	FLOAT_DIV,
	MULTIPLY,
	INTEGER_DIV,
	LPAREN,
	RPAREN,
	WHITESPACE,
	BEGIN,
	END,
	ID,
	ASSIGN,
	COLON,
	COMMA,
	SEMI,
	DOT,
	PROGRAM,
	VAR,
	REAL_CONST,
	INTEGER_CONST,
	PROCEDURE,
	EOF;
	
	public boolean isOperation(){
		
		return this.equals(PLUS) || this.equals(MINUS) || this.equals(MULTIPLY) || this.equals(INTEGER_DIV);
		
	}
	
	public boolean isMultiplyOrDivide(){
		
		return this.equals(MULTIPLY) || this.equals(INTEGER_DIV) || this.equals(FLOAT_DIV);
		
	}

	public boolean isPlusOrMinus(){
		
		return this.equals(PLUS) || this.equals(MINUS);
		
	}
	
	public boolean isNumberConst(){
		
		return this.equals(INTEGER_CONST) || this.equals(REAL_CONST);
		
	}
	
}
