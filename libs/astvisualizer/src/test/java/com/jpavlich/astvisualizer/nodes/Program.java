package com.jpavlich.astvisualizer.nodes;

import java.util.ArrayList;
import java.util.List;

public class Program implements ASTNode {

	private List<ASTNode> sentences = new ArrayList<ASTNode>();
	
	public boolean add(ASTNode e) {
		return sentences.add(e);
	}

	@Override
	public Object execute() {
		for (ASTNode sentence : sentences) {
			sentence.execute();			
		}
		return null;
	}

}
