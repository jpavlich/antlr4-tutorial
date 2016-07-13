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
 * This class displays an abstract syntax tree. The nodes of the
 * tree can be any subclasses of a particular class or interface.
 * This class begins at the root object and finds the children by
 * reflectively visiting all of the declared attributes in the root class  
 * (inherited attributes are not visited). The process repeats
 * recursively for all the children.
 *
 */
public class ASTVisualizer {

	private Object root;
	Class<?> superclass;

	/** Creates an instance of the AST visualizer.
	 * @param root The object that is the root of the tree that needs to be visualized
	 * @param superclass The superclass of all of the objects that will be visualized. 
	 * Any object that is not a subclass of superclass will not be displayed in the tree.
	 * Be careful to not choose classes or interfaces that are too general (e.g. Object),
	 * since the visualizer may enter an infinite recursion.
	 */
	public ASTVisualizer(Object root, Class<?> superclass) {
		super();
		this.root = root;
		this.superclass = superclass;
	}

	/** Shows the window that displays the tree
	 * @param width Width of the window
	 * @param height Height of the window
	 */
	public void visualize(int width, int height) {
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
			scrollPane.setPreferredSize(new Dimension(width, height));
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
