package ast;

import tokens.Token;
import tokens.TokenType;

public class Num extends Node {
	
	private Token token;
	
	private Number value;
	
	public Num(Token t){
		
		if(t.getType().equals(TokenType.INTEGER) || t.getType().equals(TokenType.INTEGER_CONST) || t.getType().equals(TokenType.REAL_CONST)){
			
			token = t;
			value = (Number) t.getValue();
			
		}
		
		else{
						
			throw new IllegalArgumentException("The passed token is not an integer");
			
		}
		
	}
	
	public Token getToken(){
		
		return token;
		
	}
	
	public Number getValue(){
		
		return value;
		
	}

}
