#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package};
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {

	private static final String EXTENSION = "${fileExtension}";

	public static void main(String[] args) throws IOException {
		String program = args.length > 1 ? args[1] : "test/test." + EXTENSION;

		System.out.println("Interpreting file " + program);

		${classPrefix}Lexer lexer = new ${classPrefix}Lexer(new ANTLRFileStream(program));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		${classPrefix}Parser parser = new ${classPrefix}Parser(tokens);

		${classPrefix}Parser.StartContext tree = parser.start();

		${classPrefix}CustomVisitor visitor = new ${classPrefix}CustomVisitor();
		visitor.visit(tree);

		System.out.println("Interpretation finished");

	}

}
