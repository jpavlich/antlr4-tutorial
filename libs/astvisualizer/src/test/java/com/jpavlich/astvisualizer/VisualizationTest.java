package com.jpavlich.astvisualizer;

import com.jpavlich.astvisualizer.nodes.ASTNode;
import com.jpavlich.astvisualizer.nodes.Addition;
import com.jpavlich.astvisualizer.nodes.NumericValue;

public class VisualizationTest {

	public static void main(String[] args) {
		ASTNode root = new Addition(new NumericValue(2), new NumericValue(4));

		new ASTVisualizer(root, ASTNode.class).visualize();

	}


}
