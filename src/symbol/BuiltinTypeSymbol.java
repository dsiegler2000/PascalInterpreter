package symbol;

public class BuiltinTypeSymbol extends Symbol {

	public BuiltinTypeSymbol(String name) {
	
		super(name, SymbolType.BUILTIN_TYPE);

	}

	@Override
	public String toString(){
		
		return "<BuiltinTypeSymbol(name=" + name + ")>";
		
	}
	
}
