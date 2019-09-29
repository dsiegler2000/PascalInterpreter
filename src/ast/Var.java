package ast;

import tokens.Token;

public class Var extends Node {
	
	private Token token;
	
	private Object value;
	
	public Var(Token token){
		
		this.token = token;
		
		this.value = token.getValue();
		
	}
	
	public Token getToken(){
		
		return token;
		
	}
	
	public Object getValue(){
		
		return value;
		
	}

}
