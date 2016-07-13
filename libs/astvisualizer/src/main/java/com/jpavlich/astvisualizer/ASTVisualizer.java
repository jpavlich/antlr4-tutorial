package com.jpavlich.astvisualizer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.abego.treelayout.TreeLayout;
import org.abego.treelayout.util.DefaultConfiguration;
import org.abego.treelayout.util.FixedNodeExtentProvider;

/**
 * Hello world!
 *
 */
public class ASTVisualizer {

	private Object root;
	Class<?> superclass;

	public ASTVisualizer(Object root, Class<?> superclass) {
		super();
		this.root = root;
		this.superclass = superclass;
	}

	public void visualize() {
		try {
			ASTNodeWrapper rootNode = createTree(root, null);
			AbstractTreeForASTLayout tree = new AbstractTreeForASTLayout(rootNode);

			// setup the tree layout configuration
			double gapBetweenLevels = 50;
			double gapBetweenNodes = 10;
			DefaultConfiguration<ASTNodeWrapper> configuration = new DefaultConfiguration<ASTNodeWrapper>(
					gapBetweenLevels, gapBetweenNodes);

			// create the NodeExtentProvider for TextInBox nodes
			FixedNodeExtentProvider<ASTNodeWrapper> nodeExtentProvider = new FixedNodeExtentProvider<ASTNodeWrapper>(
					150, 50);

			// create the layout
			TreeLayout<ASTNodeWrapper> treeLayout = new TreeLayout<ASTNodeWrapper>(tree, nodeExtentProvider,
					configuration);

			// Create a panel that draws the nodes and edges and show the panel
			ASTPane astPane = new ASTPane(treeLayout);
			JFrame f = new JFrame();
			JComponent container = (JComponent) f.getContentPane();
			container.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			container.add(astPane);
			f.pack();
			f.setLocationRelativeTo(null);
			f.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}

			});
			f.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private ASTNodeWrapper createTree(Object obj, ASTNodeWrapper parent) throws Exception {
		ASTNodeWrapper node = new ASTNodeWrapper(obj, parent);
		for (Field f : obj.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			if (superclass.isAssignableFrom(f.getType())) {
				Object child = f.get(obj);
				createTree(child, node);
			}
		}

		return node;
	}
}
