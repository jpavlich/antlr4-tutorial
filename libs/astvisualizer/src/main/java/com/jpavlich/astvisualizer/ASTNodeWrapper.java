package com.jpavlich.astvisualizer;

import java.util.ArrayList;
import java.util.List;

public class ASTNodeWrapper {
	private Object node;
	private ASTNodeWrapper parent;
	private List<ASTNodeWrapper> children = new ArrayList<ASTNodeWrapper>();

	public ASTNodeWrapper(Object node, ASTNodeWrapper parent) {
		super();
		this.node = node;
		if (parent != null) {
			this.parent = parent;
			parent.addChild(this);
		}
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
		return node.getClass().getSimpleName();
	}

}
