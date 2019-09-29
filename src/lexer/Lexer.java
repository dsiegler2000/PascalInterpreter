package lexer;

import java.util.HashMap;
import java.util.Map;

import parsing.ParsingException;
import tokens.Token;
import tokens.TokenType;

public class Lexer {
	
	public static final char NULL_CHAR = 'ø';
	
	public static final Map<String, Token> RESERVED_KEYWORDS = new HashMap<>();
	
	static{
		
		RESERVED_KEYWORDS.put("PROGRAM", new Token(TokenType.PROGRAM, "PROGRAM"));
		RESERVED_KEYWORDS.put("VAR", new Token(TokenType.VAR, "PROGRAM"));
		RESERVED_KEYWORDS.put("DIV", new Token(TokenType.INTEGER_DIV, "DIV"));
		RESERVED_KEYWORDS.put("INTEGER", new Token(TokenType.INTEGER, "INTEGER"));
		RESERVED_KEYWORDS.put("REAL", new Token(TokenType.REAL, "REAL"));
		RESERVED_KEYWORDS.put("BEGIN", new Token(TokenType.BEGIN, "BEGIN"));
		RESERVED_KEYWORDS.put("END", new Token(TokenType.END, "END"));
		RESERVED_KEYWORDS.put("PROCEDURE", new Token(TokenType.PROCEDURE, "PROCEDURE"));
		
	}
	
	private String text;
	
	private int pos;
	
	private char currentChar;
	
	public Lexer(String text){
		
		this.text = text;
		
		this.pos = 0;
		
		currentChar = text.charAt(pos);
		
	}

	public void advance() {

		pos++;

		if (pos > text.length() - 1) {

			currentChar = NULL_CHAR;

		}

		else {

			currentChar = text.charAt(pos);

		}

	}
	
	private char peek(){
		
		int peekPos = pos + 1;
		
		if(peekPos > text.length() - 1){
			
			return NULL_CHAR;
			
		}
		
		else{
			
			return text.charAt(peekPos);
			
		}
		
	}
	
	private Token id(){
		
		String result = "";
		
		while(currentChar != NULL_CHAR && Character.isLetterOrDigit(currentChar) || currentChar == '_'){
			
			result += currentChar;
			advance();
			
		}
		
		Token token = RESERVED_KEYWORDS.get(result.toUpperCase());
		
		if(token == null){
			
			token = new Token(TokenType.ID, result);
			
		}
		
		return token;
		
	}

	private void skipWhitespace() {

		while (currentChar != NULL_CHAR && Character.isWhitespace(currentChar)) {

			advance();

		}

	}
	
	private void skipComment(){
		
		while(currentChar != '}'){
			
			advance();
			
		}
		
		advance();
		
	}

	private Token number() {

		String result = "";
		
		Token token = null;

		while (currentChar != NULL_CHAR && Character.isDigit(currentChar)) {

			result += currentChar;
			advance();

		}
		
		if(currentChar == '.'){
			
			result += currentChar;
			advance();
			
			while(currentChar != NULL_CHAR && Character.isDigit(currentChar)){
				
				result += currentChar;
				advance();
				
			}
			
			token = new Token(TokenType.REAL_CONST, Float.parseFloat(result));
			
		}
		
		else{
			
			token = new Token(TokenType.INTEGER_CONST, Integer.parseInt(result));
			
		}

		return token;

	}

	public Token getNextToken() {

		while (currentChar != NULL_CHAR) {
			
			if(currentChar == '{'){
				
				advance();
				skipComment();
				
				continue;
				
			}
						
			if (Character.isWhitespace(currentChar)) {
				
				skipWhitespace();
				
				continue;

			}

			if (Character.isDigit(currentChar)) {
				
				return number();

			}
			
			if(Character.isAlphabetic(currentChar) || currentChar == '_'){
								
				return id();
				
			}
			
			if(currentChar == ':' && peek() == '='){
				
				advance();
				advance();
				
				return new Token(TokenType.ASSIGN, ":=");
				
			}
			
			if(currentChar == ':'){
				
				advance();
				
				return new Token(TokenType.COLON, ':');
				
			}
			
			if(currentChar == ','){
				
				advance();
				
				return new Token(TokenType.COMMA, ',');
				
			}
			
			switch (currentChar) {
			
			case ';':
				advance();
				return new Token(TokenType.SEMI, ';');
				
			case '.':
				advance();
				return new Token(TokenType.DOT, '.');

			case '+':
				advance();
				return new Token(TokenType.PLUS, '+');

			case '-':
				advance();
				return new Token(TokenType.MINUS, '-');

			case '/':
				advance();
				return new Token(TokenType.FLOAT_DIV, '/');

			case '*':
				advance();
				return new Token(TokenType.MULTIPLY, '*');
				
			case '(':
				advance();
				return new Token(TokenType.LPAREN, '(');
				
			case ')':
				advance();
				return new Token(TokenType.RPAREN, ')');

			}

			throw new ParsingException();

		}

		return new Token(TokenType.EOF, NULL_CHAR);

	}

}
