package com.jpavlich.astvisualizer;

import java.util.List;

import org.abego.treelayout.util.AbstractTreeForTreeLayout;

public class AbstractTreeForASTLayout extends AbstractTreeForTreeLayout<ASTVNode>{

	public AbstractTreeForASTLayout(ASTVNode root) {
		super(root);
	}

	@Override
	public List<ASTVNode> getChildrenList(ASTVNode node) {
		return node.getChildren();
	}

	@Override
	public ASTVNode getParent(ASTVNode node) {
		return node.getParent();
	}

}
