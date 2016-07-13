package com.jpavlich.astvisualizer;

import java.util.List;

import org.abego.treelayout.util.AbstractTreeForTreeLayout;

public class AbstractTreeForASTLayout extends AbstractTreeForTreeLayout<ASTNodeWrapper>{

	public AbstractTreeForASTLayout(ASTNodeWrapper root) {
		super(root);
	}

	@Override
	public List<ASTNodeWrapper> getChildrenList(ASTNodeWrapper node) {
		return node.getChildren();
	}

	@Override
	public ASTNodeWrapper getParent(ASTNodeWrapper node) {
		return node.getParent();
	}

}
