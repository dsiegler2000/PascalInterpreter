package ast;

import tokens.Token;

public class BinOp extends Node {
	
	private Node left;
	private Node right;
	
	private Token operation;
	
	public BinOp(Node left, Token op,Node right){
		
		this.left = left;
		this.right = right;
		
		this.operation = op;
		
	}

	
	public Node getLeft(){
		
		return left;
		
	}
	
	public Node getRight(){
		
		return right;
		
	}
	
	public Token getOperation(){
		
		return operation;
		
	}

}
