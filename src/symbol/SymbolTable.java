package symbol;

import java.util.LinkedHashMap;
import java.util.Map;

public class SymbolTable {
	
	private Map<Object, Symbol> symbols;
	
	public SymbolTable(){
		
		symbols = new LinkedHashMap<>();
		
	}
	
	public void initBuiltins(){
		
		for(BuiltinType t : BuiltinType.values()){
			
			insert(new BuiltinTypeSymbol(t.toString()));
			
		}
		
	}
	
	public void insert(Symbol symbol){
		
		symbols.put(symbol.getName(), symbol);
		
	}
	
	public Symbol lookup(Object name){
				
		return symbols.get(name.toString());
		
	}
	
	@Override
	public String toString(){
		
		return "Symbols: " + symbols.values();
		
	}

}
