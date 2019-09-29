package ast;

public class Program extends Node {
	
	private String name;
	
	private Block block;
	
	public Program(String name, Block block){
		
		this.name = name;
		
		this.block = block;
		
	}
	
	public String getName(){
		
		return name;
		
	}
	
	public Block getBlock(){
		
		return block;
		
	}

}
