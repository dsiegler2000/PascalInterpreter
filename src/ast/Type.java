package ast;

import tokens.Token;

public class Type extends Node {
	
	private Token token;
	
	private Object value;
	
	public Type(Token token){
		
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
