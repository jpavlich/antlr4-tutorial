package com.jpavlich.astvisualizer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import org.abego.treelayout.TreeLayout;
import org.abego.treelayout.util.DefaultConfiguration;

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
			ASTVNode rootNode = createTree(root, null, "");
			if (rootNode == null)
				return;
			AbstractTreeForASTLayout tree = new AbstractTreeForASTLayout(rootNode);

			// setup the tree layout configuration
			double gapBetweenLevels = 50;
			double gapBetweenNodes = 10;
			DefaultConfiguration<ASTVNode> configuration = new DefaultConfiguration<ASTVNode>(gapBetweenLevels,
					gapBetweenNodes);

			Font font = new JLabel().getFont();
			// Create a panel that draws the nodes and edges and show the panel
			JFrame f = new JFrame();

			JComponent container = (JComponent) f.getContentPane();
			container.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

			// create the NodeExtentProvider for TextInBox nodes
			ASTNodeExtentProvider nodeExtentProvider = new ASTNodeExtentProvider(font);

			// create the layout
			TreeLayout<ASTVNode> treeLayout = new TreeLayout<ASTVNode>(tree, nodeExtentProvider, configuration);

			ASTPane astPane = new ASTPane(treeLayout, font);
			JScrollPane scrollPane = new JScrollPane(astPane);
			scrollPane.setPreferredSize(new Dimension(800, 600));
			scrollPane.setBorder(BorderFactory.createEmptyBorder());
			container.add(scrollPane);
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

	private ASTVNode createTree(Object obj, ASTVNode parent, String fieldName) throws Exception {

		if (superclass.isAssignableFrom(obj.getClass())) {
			ASTVNode node = new ASTVNode(obj, parent, obj.getClass().getSimpleName(), Color.orange, Color.black, 0);
			for (Field f : obj.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				Object child = f.get(obj);
				if (f.getType().isPrimitive()) {
					node.name += "\n" + f.getName() + " = " + child;
				} else {
					ASTVNode fieldNode = new ASTVNode(f, node, f.getName(), new Color(0, 0, 0, 0),
							new Color(0, 0, 0, 0), 0);
					createTree(child, fieldNode, f.getName());
				}

			}
			return node;
		} else if (Collection.class.isAssignableFrom(obj.getClass())) {
			Collection<?> list = (Collection<?>) obj;
			for (Object elem : list) {
				createTree(elem, parent, "");
			}
			return parent;
		}
		return null;

	}
}
