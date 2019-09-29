package ast;

import tokens.Token;

public class Assign extends Node {

	private Var left;
	private Node right;
	private String name;

	private Token token;

	public Assign(Var left, Token op, Node right) {

		this.left = left;
		this.right = right;

		this.token = op;

	}

	public Var getLeft() {

		return left;

	}

	public Node getRight() {
		
		return right;
		
	}
	
	public String getName(){
		
		return name;
		
	}

	public Token getToken() {
		
		return token;
		
	}

}
