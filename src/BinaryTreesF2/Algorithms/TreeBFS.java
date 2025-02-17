package BinaryTreesF2.Algorithms;

import Auxiliar.MyQueue;
import BinaryTreesF2.Entities.Citizen;
import BinaryTreesF2.Entities.Node;

public class TreeBFS {
	public static Citizen findCitizenById (Node rootNode, long citizenId) {

		// Just search for nodes if there are any in the tree.
		if (rootNode != null) {
			MyQueue<Node> myQueue = new MyQueue<>();
			myQueue.add(rootNode);

			while (!myQueue.isEmpty()) {
				Node newNode = myQueue.poll();

				// Check if newNode has the same ID.
				for (Citizen nodeCitizen : newNode.getCitizens()) {
					if (nodeCitizen.sameID(citizenId)) {
						return nodeCitizen;
					}
				}

				// Add left child to the queue.
				if (newNode.left != null) {
					myQueue.add(newNode.left);
				}

				// Add right child to the queue.
				if (newNode.right != null) {
					myQueue.add(newNode.right);
				}
			}
		}

		return null;
	}
}
