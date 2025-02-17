package Auxiliar.GraphicComponents;

import BinaryTreesF2.Entities.Citizen;
import BinaryTreesF2.Entities.Node;
import BinaryTreesF2.Entities.Trees.TreeType;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

public class TreePainter extends JPanel {
	private final Node root;
	private static final int RADIUS = 25;
	private final static int TOP_MARGIN = 100;	// Above this top margin will appear the Title text, and below the first node will be printed.
	private final int INITIAL_NODE_POSITION;
	private final int HORIZONTAL_NODES_DISTANCE;
	private final int VERTICAL_NODES_DISTANCE = 100;
	private final static Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
	private final static String BST_TITLE = "Binary Search BSTTree Visual Representation";
	private final static String AVL_TITLE = "Balanced Binary Search BSTTree Visual Representation";
	private final TreeType treeType;

	public TreePainter(Node root, int windowWidth, int windowHeight, TreeType treeType) {
		this.root = root;
		this.INITIAL_NODE_POSITION = windowWidth / 2;
		this.HORIZONTAL_NODES_DISTANCE = windowWidth / 4;
		this.treeType = treeType;

		setPreferredSize(new Dimension(windowWidth, windowHeight));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw the title of the visual representation.
		g.setFont(TITLE_FONT);
		int[] titleCords;

		// Write the apropiate title depending on the type of Binary BSTTree.
		if (treeType == TreeType.BINARYTREE) {
			titleCords = TextCentering.centerText(g, BST_TITLE, INITIAL_NODE_POSITION, TOP_MARGIN, getHeight());
			g.drawString(BST_TITLE, titleCords[0], TOP_MARGIN - 50);
		}
		else {
			titleCords = TextCentering.centerText(g, AVL_TITLE, INITIAL_NODE_POSITION, TOP_MARGIN, getHeight());
			g.drawString(AVL_TITLE, titleCords[0], TOP_MARGIN - 50);
		}

		// Check if there is at least one node in the tree to start showing it visually.
		if (root != null) {

			// Set the first node in the top center of the screen and divide each child with a relative division depending on the screen size.
			drawTreeNode(g, INITIAL_NODE_POSITION, TOP_MARGIN, HORIZONTAL_NODES_DISTANCE, root);
		}
	}

	private void drawTreeNode(Graphics g, int xPos, int yPos, int horizontalDistance, Node node) {

		// Draw the circle that represents the current node, include all its citizens and the weight of the node.
		Citizen[] citizens = node.getCitizens();
		String[] names = new String[citizens.length];

		for (int i = 0; i < citizens.length; i++) {
			names[i] = citizens[i].getName();
		}

		CircleText circle = new CircleText(names, node.getCitizenWeight(), xPos, yPos, RADIUS, node == root);
		circle.paintComponent(g);

		// Draw the left child of the current node (in case there is).
		if (node.left != null) {

			// Calculate the position of the node child (where it will be located, but is still not found).
			int x2 = xPos - horizontalDistance;
			int y2 = yPos + VERTICAL_NODES_DISTANCE;

			// Draw the left line that joins the parent node with its child node.
			g.drawLine(xPos - RADIUS, yPos, x2, yPos);
			g.drawLine(x2, yPos, x2, y2 - RADIUS);

			drawTreeNode(g, x2, y2, horizontalDistance / 2, node.left);
		}

		// Draw the left child of the current node (in case there is).
		if (node.right != null) {

			// Calculate the position of the node child (where it will be located, but is still not found).
			int x2 = xPos + horizontalDistance;
			int y2 = yPos + VERTICAL_NODES_DISTANCE;

			// Draw the right line that joins the parent node with its child node.
			g.drawLine(xPos + RADIUS, yPos, x2, yPos);
			g.drawLine(x2, yPos, x2, y2 - RADIUS);

			drawTreeNode(g, x2, y2, horizontalDistance / 2, node.right);
		}
	}
}
