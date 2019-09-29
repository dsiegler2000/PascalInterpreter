package interpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ast.Assign;
import ast.BinOp;
import ast.Block;
import ast.Compound;
import ast.Node;
import ast.NodeVisitor;
import ast.Num;
import ast.Program;
import ast.UnaryOp;
import ast.Var;
import lexer.Lexer;
import parsing.Parser;
import symbol.SymbolTableBuilder;
import tokens.TokenType;

public class Interpreter extends NodeVisitor {

	private static final Map<String, Object> GLOBAL_SCOPE = new HashMap<>();

	private Parser parser;

	public Interpreter(Parser parser) {

		this.parser = parser;

	}

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scan = new Scanner(new File("src/test.pas"));

		StringBuilder builder = new StringBuilder();

		while (scan.hasNextLine()) {

			builder.append(scan.nextLine());

		}
		
		scan.close();

		SymbolTableBuilder symbolTableBuilder = new SymbolTableBuilder();
		symbolTableBuilder.visit(new Parser(new Lexer(builder.toString())).parse());
		;

		Lexer lexer = new Lexer(builder.toString());
		Parser parser = new Parser(lexer);
		Interpreter interpreter = new Interpreter(parser);
		interpreter.interpret();

		System.out.println("Global Memory Contents");
		System.out.println(GLOBAL_SCOPE.toString());
				
	}

	@Override
	protected Object visitBinOp(BinOp node) {

		switch (node.getOperation().getType()) {

		case PLUS:
			return (float) (((Number) visit(node.getLeft())).floatValue()
					+ ((Number) visit(node.getRight())).floatValue());

		case MINUS:
			return (float) (((Number) visit(node.getLeft())).floatValue()
					- ((Number) visit(node.getRight())).floatValue());

		case MULTIPLY:
			return (float) (((Number) visit(node.getLeft())).floatValue()
					* ((Number) visit(node.getRight())).floatValue());

		case INTEGER_DIV:
			return (float) (((Number) visit(node.getLeft())).floatValue()
					/ ((Number) visit(node.getRight())).floatValue());

		case FLOAT_DIV:
			return (float) (((Number) visit(node.getLeft())).floatValue()
					/ ((Number) visit(node.getRight())).floatValue());

		default:
			return 0;

		}

	}

	@Override
	protected Object visitNum(Num node) {

		return node.getValue();

	}

	@Override
	protected Object visitUnaryOp(UnaryOp node) {

		TokenType op = node.getOperation().getType();

		if (op.equals(TokenType.PLUS)) {

			return +(int) visit(node.getExpression());

		}

		else if (op.equals(TokenType.MINUS)) {

			return -(int) visit(node.getExpression());

		}

		return null;

	}

	@Override
	protected Object visitCompound(Compound node) {

		for (Node child : node.getChildren()) {

			visit(child);

		}

		return null;

	}

	@Override
	protected Object visitAssign(Assign node) {

		String varName = ((Var) node.getLeft()).getValue().toString();
		GLOBAL_SCOPE.put(varName.toUpperCase(), visit(node.getRight()));

		return null;

	}

	@Override
	protected Object visitVar(Var node) {

		String varName = node.getValue().toString();
		Object value = GLOBAL_SCOPE.get(varName.toUpperCase());

		if (value == null) {

			throw new IllegalArgumentException(varName + " isn't defined!");

		}

		else {

			return value;

		}

	}

	public Node interpret() {

		Node tree = parser.parse();

		visit(tree);

		return tree;

	}

	@Override
	protected Object visitProgram(Program node) {

		visit(node.getBlock());

		return null;

	}

	@Override
	protected Object visitBlock(Block node) {

		for (Node declaration : node.getDeclarations()) {

			visit(declaration);

		}

		visit(node.getCompoundStatement());

		return null;

	}

}
