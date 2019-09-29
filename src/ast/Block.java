package ast;

import java.util.List;

public class Block extends Node {
	
	private List<Node> declarations;
	
	private Compound compoundStatement;

	public Block(List<Node> declarations, Compound compoundStatement) {

		this.declarations = declarations;
		this.compoundStatement = compoundStatement;
	
	}

	public List<Node> getDeclarations() {
		
		return declarations;
		
	}

	public Compound getCompoundStatement() {
		
		return compoundStatement;
		
	}
	
}
