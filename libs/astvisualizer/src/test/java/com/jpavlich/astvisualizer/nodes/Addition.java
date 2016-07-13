package com.jpavlich.astvisualizer.nodes;

public class Addition implements ASTNode {

	private ASTNode operator1;
	private ASTNode operator2;
	
	
	
	public Addition(ASTNode operator1, ASTNode operator2) {
		super();
		this.operator1 = operator1;
		this.operator2 = operator2;
	}



	@Override
	public Object execute() {
		return (int)operator1.execute() + (int)operator2.execute();
	}

}

