package ast;

public class ProcedureDecl extends Node {
	
	private String name;
	
	private Block block;

	public ProcedureDecl(String name, Block block) {

		this.name = name;
		this.block = block;
		
	}

	public String getName() {
		
		return name;
		
	}

	public Block getBlock() {
		
		return block;
		
	}

}
