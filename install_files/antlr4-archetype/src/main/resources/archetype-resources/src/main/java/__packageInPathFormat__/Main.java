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

		${grammarName}Lexer lexer = new ${grammarName}Lexer(new ANTLRFileStream(program));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		${grammarName}Parser parser = new ${grammarName}Parser(tokens);

		${grammarName}Parser.StartContext tree = parser.start();

		${grammarName}CustomVisitor visitor = new ${grammarName}CustomVisitor();
		visitor.visit(tree);

		System.out.println("Interpretation finished");

	}

}
