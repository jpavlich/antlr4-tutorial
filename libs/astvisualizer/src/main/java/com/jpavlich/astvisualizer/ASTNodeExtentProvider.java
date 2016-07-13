package com.jpavlich.astvisualizer;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;

import org.abego.treelayout.NodeExtentProvider;

public class ASTNodeExtentProvider implements NodeExtentProvider<ASTVNode> {

	private Font font;
	
	

	public ASTNodeExtentProvider(Font font) {
		super();
		this.font = font;
	}

	@Override
	public double getWidth(ASTVNode treeNode) {
		
		return Toolkit.getDefaultToolkit().getFontMetrics(font).stringWidth(treeNode.getName()) + 10;
	}

	@Override
	public double getHeight(ASTVNode treeNode) {
		return Toolkit.getDefaultToolkit().getFontMetrics(font).getHeight() * treeNode.getName().split("\n").length + 5;
	}


	
}
