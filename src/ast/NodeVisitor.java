package ast;

public class NodeVisitor {
	
	public Object visit(Node node){
		
		if(node instanceof BinOp){
			
			return visitBinOp((BinOp) node);
			
		}
		
		else if(node instanceof Num){
			
			return visitNum((Num) node);
			
		}
		
		else if(node instanceof UnaryOp){
			
			return visitUnaryOp((UnaryOp) node);
			
		}
		
		else if(node instanceof Compound){
			
			return visitCompound((Compound) node);
			
		}
		
		else if(node instanceof EmptyOp){
			
			return visitEmptyOp((EmptyOp) node);
			
		}
		
		else if(node instanceof Assign){
			
			return visitAssign((Assign) node);
			
		}
		
		else if(node instanceof Var){
			
			return visitVar((Var) node);
			
		}
		
		else if(node instanceof Program){
			
			return visitProgram((Program) node);
			
		}
		
		else if(node instanceof Block){
			
			return visitBlock((Block) node);
			
		}
		
		else if(node instanceof VarDecl){
			
			return visitVarDecl((VarDecl) node);
			
		}
		
		else if(node instanceof Type){
			
			return visitType((Type) node);
			
		}
		
		else if(node instanceof ProcedureDecl){
			
			return visitProcedureDecl((ProcedureDecl) node);
			
		}
		
		else{
			
			return new Object();
			
		}
		
	}
	
	protected Object visitBinOp(BinOp node){
		
		return null;
		
	}
	
	protected Object visitNum(Num node){
		
		return null;
		
	}
	
	protected Object visitUnaryOp(UnaryOp node){
		
		return null;
		
	}
	
	protected Object visitCompound(Compound node){
		
		return null;
		
	}
	
	protected Object visitEmptyOp(EmptyOp node){
		
		return null;
		
	}
	
	protected Object visitAssign(Assign node){
		
		return null;
		
	}
	
	protected Object visitVar(Var node){
		
		return null;
		
	}
	
	protected Object visitProgram(Program node){
		
		return null;
		
	}
	
	protected Object visitBlock(Block node){
		
		return null;
		
	}

	protected Object visitVarDecl(VarDecl node){
		
		return null;
		
	}

	protected Object visitType(Type node){
		
		return null;
		
	}
	
	protected Object visitProcedureDecl(ProcedureDecl node){
		
		return null;
		
	}

}
