package ast;

import tokens.Token;

public class UnaryOp extends Node {
	
	private Token operation;
	
	private Node expression;
	
	public UnaryOp(Token op, Node expr){
		
		operation = op;
		
		expression = expr;
		
	}
	
	public Token getOperation() {
		
		return operation;
		
	}

	public Node getExpression() {
		
		return expression;
		
	}

}
