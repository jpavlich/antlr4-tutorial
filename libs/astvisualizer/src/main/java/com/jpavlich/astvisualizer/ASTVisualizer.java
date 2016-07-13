package com.jpavlich.astvisualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.abego.treelayout.TreeLayout;
import org.abego.treelayout.util.DefaultConfiguration;

/**
 * Hello world!
 *
 */
public class ASTVisualizer {

	private Object root;
	Class<?> superclass;
	boolean separateAttributes;

	public ASTVisualizer(Object root, Class<?> superclass, boolean separateAttributes) {
		super();
		this.root = root;
		this.superclass = superclass;
		this.separateAttributes = separateAttributes;
	}

	public void visualize() {
		try {
			ASTNodeWrapper rootNode = createTree(root, null, "");
			if (rootNode == null)
				return;
			AbstractTreeForASTLayout tree = new AbstractTreeForASTLayout(rootNode);

			// setup the tree layout configuration
			double gapBetweenLevels = 50;
			double gapBetweenNodes = 10;
			DefaultConfiguration<ASTNodeWrapper> configuration = new DefaultConfiguration<ASTNodeWrapper>(
					gapBetweenLevels, gapBetweenNodes);

			Font font = new JLabel().getFont();
			// Create a panel that draws the nodes and edges and show the panel
			JFrame f = new JFrame();
			
			JComponent container = (JComponent) f.getContentPane();
			container.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

			// create the NodeExtentProvider for TextInBox nodes
			ASTNodeExtentProvider nodeExtentProvider = new ASTNodeExtentProvider(font);

			// create the layout
			TreeLayout<ASTNodeWrapper> treeLayout = new TreeLayout<ASTNodeWrapper>(tree, nodeExtentProvider,
					configuration);

			ASTPane astPane = new ASTPane(treeLayout, font);
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

	private ASTNodeWrapper createTree(Object obj, ASTNodeWrapper parent, String fieldName) throws Exception {

		if (superclass.isAssignableFrom(obj.getClass())) {
			ASTNodeWrapper node = new ASTNodeWrapper(obj, parent, obj.getClass().getSimpleName(), Color.orange);
			for (Field f : obj.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				if (separateAttributes) {
					ASTNodeWrapper fieldNode = new ASTNodeWrapper(f, node, f.getName(), Color.lightGray);
					Object child = f.get(obj);
					createTree(child, fieldNode, f.getName());
				} else {
					node.setName(f.getName() + ":" + node.getName());
					Object child = f.get(obj);
					createTree(child, node, f.getName());
				}

			}
			return node;
		} else if (Collection.class.isAssignableFrom(obj.getClass())) {
			ASTNodeWrapper node;
			if (separateAttributes) {
				node = parent;
			} else {
				node = new ASTNodeWrapper(obj, parent, fieldName + ":" + obj.getClass().getSimpleName(), Color.orange);
			}
			Collection<?> list = (Collection<?>) obj;
			for (Object elem : list) {
				createTree(elem, node, "");
			}
			return node;
		}
		return null;

	}
}
