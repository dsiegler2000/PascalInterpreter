package parsing;

public class ParsingException extends RuntimeException {

	private static final long serialVersionUID = 7015241169152855964L;

	public ParsingException(){
		
		super("Error parsing the input!");
		
	}
	
	public ParsingException(String message){
		
		super(message);
		
	}
	
}
