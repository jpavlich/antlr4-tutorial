package com.jpavlich.astvisualizer.nodes;

public class NumericValue implements ASTNode {
	int value;
	
	
	
	
	public NumericValue(int value) {
		super();
		this.value = value;
	}




	@Override
	public Object execute() {
		return value;
	}

}
