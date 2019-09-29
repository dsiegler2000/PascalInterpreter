package ast;

import java.util.ArrayList;
import java.util.List;

public class Compound extends Node {
	
	private List<Node> children;
	
	public Compound(){
		
		children = new ArrayList<>();
		
	}
	
	public List<Node> getChildren(){
		
		return children;
		
	}

}
