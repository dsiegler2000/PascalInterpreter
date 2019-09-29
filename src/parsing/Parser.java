package parsing;

import java.util.ArrayList;
import java.util.List;

import ast.Assign;
import ast.BinOp;
import ast.Block;
import ast.Compound;
import ast.EmptyOp;
import ast.Node;
import ast.Num;
import ast.ProcedureDecl;
import ast.Program;
import ast.Type;
import ast.UnaryOp;
import ast.Var;
import ast.VarDecl;
import lexer.Lexer;
import tokens.Token;
import tokens.TokenType;

public class Parser {
	
	private Lexer lexer;
	
	private Token currentToken;
	
	public Parser(Lexer lexer){
		
		this.lexer = lexer;
		
		this.currentToken = lexer.getNextToken();
		
	}
	
	private void eat(TokenType tokenType) {

		if (currentToken.getType().equals(tokenType)) {

			currentToken = lexer.getNextToken();

		}

		else {

			throw new ParsingException("Excepted type of " + currentToken.getType().toString() + " and found type of " + tokenType);

		}

	}
	
	private Node program(){
		
		eat(TokenType.PROGRAM);
		Var varNode = variable();
		String programName = varNode.getValue().toString();
		eat(TokenType.SEMI);
		Block blockNode = block();
		Program programNode = new Program(programName, blockNode);
		eat(TokenType.DOT);
		
		return programNode;
		
	}
	
	private Block block(){
		
		List<Node> declarationNodes = declarations();
		Compound compoundStatementNode = compoundStatement();
		Block node = new Block(declarationNodes, compoundStatementNode);
		
		return node;
		
	}
	
	private List<Node> declarations(){
		
		List<Node> declarations = new ArrayList<>();
		
		if(currentToken.getType().equals(TokenType.VAR)){
			
			eat(TokenType.VAR);
			
			while(currentToken.getType().equals(TokenType.ID)){
				
				List<VarDecl> varDecl = variableDeclarations();
				declarations.addAll(varDecl);
				eat(TokenType.SEMI);
				
			}
			
		}
		
		while(currentToken.getType().equals(TokenType.PROCEDURE)){
			
			eat(TokenType.PROCEDURE);
			
			String procedureName = currentToken.getValue().toString();
			
			eat(TokenType.ID);
			eat(TokenType.SEMI);
			
			Block block = block();
			ProcedureDecl procedureDeclaration = new ProcedureDecl(procedureName, block);
			declarations.add(procedureDeclaration);
			
			eat(TokenType.SEMI);
			
		}
		
		return declarations;
		
	}
	
	private List<VarDecl> variableDeclarations(){
		
		List<Var> varNodes = new ArrayList<>();
		varNodes.add(new Var(currentToken));
		
		eat(TokenType.ID);
		
		while(currentToken.getType().equals(TokenType.COMMA)){
			
			eat(TokenType.COMMA);
			varNodes.add(new Var(currentToken));
			eat(TokenType.ID);
			
		}
		
		eat(TokenType.COLON);
		
		Type typeNode = typeSpec();
		
		List<VarDecl> varDeclarations = new ArrayList<>();
		
		for(Var node : varNodes){
			
			varDeclarations.add(new VarDecl(node, typeNode));
			
		}
		
		return varDeclarations;
		
	}
	
	private Type typeSpec(){
		
		Token token = currentToken;
		
		if(currentToken.getType().equals(TokenType.INTEGER)){
			
			eat(TokenType.INTEGER);
			
		}
		
		else{
			
			eat(TokenType.REAL);
			
		}
		
		Type node = new Type(token);
		
		return node;
		
	}
	
	private Compound compoundStatement(){
				
		eat(TokenType.BEGIN);
		List<Node> nodes = statementList();
		eat(TokenType.END);
		
		Compound root = new Compound();
		
		for(Node node : nodes){
			
			root.getChildren().add(node);
			
		}
		
		return root;
		
	}
	
	private List<Node> statementList(){
		
		Node node = statement();
		
		List<Node> results = new ArrayList<>();
		results.add(node);
		
		while(currentToken.getType().equals(TokenType.SEMI)){
			
			eat(TokenType.SEMI);
			results.add(statement());
			
		}
		
		if(currentToken.getType().equals(TokenType.ID)){
			
			throw new ParsingException();
			
		}
		
		return results;
		
	}
	
	private Node statement(){
		
		Node node = new EmptyOp();
		
		if(currentToken.getType().equals(TokenType.BEGIN)){
			
			node = compoundStatement();
			
		}
		
		else if(currentToken.getType().equals(TokenType.ID)){
			
			node = assignmentStatement();
			
		}

		return node;
		
	}
	
	private Node assignmentStatement(){
		
		Var left = variable();
		Token token = currentToken;
		eat(TokenType.ASSIGN);
		Node right = expression();
		Node node = new Assign(left, token, right);
		
		return node;
		
	}
	
	private Var variable(){
		
		Var node = new Var(currentToken);
		eat(TokenType.ID);
		
		return node;
		
	}
	
	private Node factor() {

		Token token = currentToken;
		
		if(token.getType().isPlusOrMinus()){
			
			eat(token.getType());
			Node node = new UnaryOp(token, factor());
			
			return node;
			
		}
		
		else if(token.getType().isNumberConst()){
			
			eat(token.getType());
			
			return new Num(token);
			
		}
		
		else if(token.getType().equals(TokenType.LPAREN)){
			
			eat(TokenType.LPAREN);
			Node node = expression();
			eat(TokenType.RPAREN);
			
			return node;
			
		}
		
		else{
			
			Node node = variable();
			
			return node;
			
		}
		
	}
	
	private Node term() {

		Node node = factor();

		while (currentToken.getType().isMultiplyOrDivide()) {

			Token token = currentToken;

			if (token.getType().isMultiplyOrDivide()) {

				eat(token.getType());

			}
			
			node = new BinOp(node, token, factor());

		}

		return node;

	}
	
	private Node expression() {

		Node node = term();

		while (currentToken.getType().isPlusOrMinus()) {

			Token token = currentToken;

			if (token.getType().isPlusOrMinus()) {

				eat(token.getType());

			}
			
			node = new BinOp(node, token, term());

		}

		return node;

	}
	
	public Node parse(){
		
		Node node = program();
		
		if(!currentToken.getType().equals(TokenType.EOF)){
			
			throw new ParsingException();
			
		}
		
		return node;
		
	}
	
}
