package com.jpavlich.astvisualizer;

import com.jpavlich.astvisualizer.nodes.ASTNode;
import com.jpavlich.astvisualizer.nodes.Addition;
import com.jpavlich.astvisualizer.nodes.Multiplication;
import com.jpavlich.astvisualizer.nodes.NumericValue;
import com.jpavlich.astvisualizer.nodes.Program;

public class VisualizationTest {

	public static void main(String[] args) {
		Program root = new Program();
		
		root.add(new Addition(new Multiplication(new NumericValue(5), new NumericValue(8)), new NumericValue(4)));
		root.add(new Multiplication(new NumericValue(1), new Addition(new NumericValue(2), new NumericValue(6))));

		new ASTVisualizer(root, ASTNode.class).visualize();

	}


}
