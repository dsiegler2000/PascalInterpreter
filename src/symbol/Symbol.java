package symbol;

public class Symbol {

	protected String name;
	
	protected SymbolType type;
	
	protected Object category;

	public Symbol(String name, SymbolType type) {

		this.name = name;
		this.type = type;
	
	}
	
	public String getName() {
		
		return name;
		
	}

	public SymbolType getType() {
		
		return type;
		
	}
	
}
