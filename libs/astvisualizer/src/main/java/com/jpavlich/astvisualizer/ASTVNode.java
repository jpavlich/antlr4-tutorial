package com.jpavlich.astvisualizer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ASTVNode {
	private Object node;
	private ASTVNode parent;
	private List<ASTVNode> children = new ArrayList<ASTVNode>();
	private Color bgColor;
	private Color fgColor;
	String name;
	private int yOffset;

	public ASTVNode(Object node, ASTVNode parent, String name, Color bgColor, Color fgColor, int yOffset) {
		super();
		this.node = node;
		if (parent != null) {
			this.parent = parent;
			parent.addChild(this);
		}
		this.name = name;
		this.bgColor = bgColor;
		this.fgColor = fgColor;
		this.yOffset = yOffset;
	}

	public Object getNode() {
		return node;
	}

	public ASTVNode getParent() {
		return parent;
	}

	public boolean addChild(ASTVNode e) {
		return children.add(e);
	}

	public List<ASTVNode> getChildren() {
		return children;
	}
	
	public String getText() {
		return name;
	}
	
	
	
	public String getName() {
		return name;
	}


	public Color getBgColor() {
		return bgColor;
	}

	public Color getFgColor() {
		return fgColor;
	}

	public int getyOffset() {
		return yOffset;
	}
	
	

}
