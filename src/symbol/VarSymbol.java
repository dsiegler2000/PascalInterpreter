package symbol;

public class VarSymbol extends Symbol {
	
	private Symbol varType;

	public VarSymbol(String name, Symbol type) {
		
		super(name, SymbolType.VAR_SYMBOL);
		
		varType = type;

	}
	
	public Symbol getVarType(){
		
		return varType;
		
	}
	
	@Override
	public String toString(){
		
		return "<" + name + "(name=" + name + ", type=" + varType.toString() + ")>";
		
	}

}
