package symbol;

import ast.Assign;
import ast.BinOp;
import ast.Block;
import ast.Compound;
import ast.Node;
import ast.NodeVisitor;
import ast.Program;
import ast.UnaryOp;
import ast.Var;
import ast.VarDecl;

public class SymbolTableBuilder extends NodeVisitor {
	
	private SymbolTable symbolTable;
	
	public SymbolTableBuilder(){
		
		symbolTable = new SymbolTable();
		
		symbolTable.initBuiltins();
		
	}
	
	public SymbolTable getSymbolTable(){
		
		return symbolTable;
		
	}

	@Override
	protected Object visitBinOp(BinOp node) {

		visit(node.getLeft());
		visit(node.getRight());
		
		return null;
		
	}

	@Override
	protected Object visitUnaryOp(UnaryOp node) {

		visit(node.getExpression());
		
		return null;
		
	}

	@Override
	protected Object visitCompound(Compound node) {

		for(Node n : node.getChildren()){
			
			visit(n);
			
		}
		
		return null;
		
	}

	@Override
	protected Object visitAssign(Assign node) {

		Object varName = node.getLeft().getValue();
		Symbol varSymbol = symbolTable.lookup(varName);
		
		if(varSymbol == null){
			
			throw new SyntaxException(varName + " is not defined!");
			
		}
		
		visit(node.getRight());
		
		return null;
		
	}

	@Override
	protected Object visitVar(Var node) {

		Object varName = node.getValue();
		Symbol varSymbol = symbolTable.lookup(varName);
		
		if(varSymbol == null){
			
			throw new IllegalStateException(varName + " is not defined!");
			
		}
		
		return null;
		
	}

	@Override
	protected Object visitProgram(Program node) {

		visit(node.getBlock());
		
		return null;
		
	}

	@Override
	protected Object visitBlock(Block node) {

		for(Node decl : node.getDeclarations()){
			
			visit(decl);
			
		}
		
		visit(node.getCompoundStatement());
		
		return null;
		
	}
	
	@Override
	protected Object visitVarDecl(VarDecl node) {

		Object typeName = node.getTypeNode().getValue();
		Symbol typeSymbol = symbolTable.lookup(typeName);
		Object varName = node.getVarNode().getValue();
		VarSymbol varSymbol = new VarSymbol(varName.toString(), typeSymbol);
		symbolTable.insert(varSymbol);
		
		return null;
		
	}

}
