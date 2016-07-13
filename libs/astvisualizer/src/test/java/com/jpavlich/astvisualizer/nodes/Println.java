package com.jpavlich.astvisualizer.nodes;

public class Println implements ASTNode {

	private ASTNode elementToPrint;
	
	
	
	public Println(ASTNode elementToPrint) {
		super();
		this.elementToPrint = elementToPrint;
	}



	@Override
	public Object execute() {
		System.out.println(elementToPrint.execute());
		return null;
	}

}
