package ast;

public class VarDecl extends Node {
	
	private Var varNode;
	
	private Type typeNode;

	public VarDecl(Var varNode, Type typeNode){
		
		this.varNode = varNode;
		
		this.typeNode = typeNode;
		
	}

	public Var getVarNode() {
		
		return varNode;
		
	}

	public Type getTypeNode() {
		
		return typeNode;
		
	}
	
}
