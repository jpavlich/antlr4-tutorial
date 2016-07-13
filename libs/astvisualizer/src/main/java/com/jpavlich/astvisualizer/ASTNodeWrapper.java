package com.jpavlich.astvisualizer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ASTNodeWrapper {
	private Object node;
	private ASTNodeWrapper parent;
	private List<ASTNodeWrapper> children = new ArrayList<ASTNodeWrapper>();
	private Color color;
	private String name;

	public ASTNodeWrapper(Object node, ASTNodeWrapper parent, String name, Color color) {
		super();
		this.node = node;
		if (parent != null) {
			this.parent = parent;
			parent.addChild(this);
		}
		this.name = name;
		this.color = color;
	}

	public Object getNode() {
		return node;
	}

	public ASTNodeWrapper getParent() {
		return parent;
	}

	public boolean addChild(ASTNodeWrapper e) {
		return children.add(e);
	}

	public List<ASTNodeWrapper> getChildren() {
		return children;
	}
	
	public String getText() {
		return name;
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

}
