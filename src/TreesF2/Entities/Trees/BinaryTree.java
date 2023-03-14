package TreesF2.Entities.Trees;

import Auxiliar.MyArrayList;
import TreesF2.Algorithms.TreeBFS;
import TreesF2.Entities.Citizen;
import TreesF2.Entities.Node;
import TreesF2.Entities.Tree;

public class BinaryTree implements Tree {

    private Node root = null;   //Root node of the tree. From this node we can obtain all the other nodes.

    @Override
    public void addCitizen(Citizen citizen) {
        root = add(root, citizen, null);    // The parent node of the root will always be NULL.
    }

    @Override
    public void removeCitizen(long citizenId) {
        Citizen citizen = findCitizenById(citizenId);
        root = remove(root, citizen);
    }

    @Override
    public void printRepresentation() {
        System.out.println();

        //We print the right part of the tree
        if (root.right != null) {
            print("", root.right, true);
        }

        root.printCitizen(false, true);   // Print the star in front of the citizen just if the Node is the root.

        //We print the left part of the tree
        if (root.left != null) {
            print("", root.left, false);
        }

    }

    private void print (String stringIndentation, Node node, boolean rightNode) {
        String stringIndentationAux;

        if (node.right != null) {
            if (rightNode) {
                stringIndentationAux = stringIndentation + "      ";
            } else {
                stringIndentationAux = stringIndentation + "|     ";
            }
            print(stringIndentationAux, node.right, true);
        }

        // Join the nodes to the parents, just those who have both a child and a parent on their left.
        if (node.right == null && node.parent != null && node.parent.left == node && node.left != null) {   // (node.parent != null && node.parent.left == node) would be (node.parent.left == node)
            System.out.println(stringIndentation + "|");
        }

        System.out.print(stringIndentation);

        // Add an indentation to the last nodes of the tree (leaves)
        if (!rightNode) {
            if (node.isLeaf()) {
                System.out.println("|");    //The space is already contained in the prior print of the indentation
                System.out.print(stringIndentation);
            }
        }

        // Check if it is the last right node of a branch
        if (node.right == null && rightNode) {
            System.out.println();
            System.out.print(stringIndentation);
        }

        System.out.print("|--- ");
        node.printCitizen(false, node.equals(root));   // Print the star in front of the citizen just if the Node is the root.

        // Check if the parent of the node is on the left.
        if (node.parent.right == node && node.isLeaf()) {
            System.out.println(stringIndentation + "|");
        }

        // Check if a node only has a right child.
        if (!node.isLeaf() && node.left == null) {

            // Solves a problem with a random '|' printed.
            if (rightNode) {
                System.out.println(stringIndentation + "|");
            }
            else {
                System.out.println(stringIndentation);
            }
        }

        if (node.left != null) {
            if (!rightNode) {
                stringIndentationAux = stringIndentation + "      ";
            } else {
                stringIndentationAux = stringIndentation + "|     ";
            }
            print(stringIndentationAux, node.left, false);
        }

        // Adding an extra indentation when a leaf node has a parent node to its right.
        if (node.isLeaf() && node.parent.left == node) {
            System.out.println(stringIndentation);
        }

    }

    private Node add(Node currentNode, Citizen citizen, Node parentNode) {
        float valueToInsert;
        float currentNodeValue;

        // When the current node is null, a new node can be inserted into the position
        // (we've reached a leaf node, or it is the first node of the tree: the root)
        if (currentNode == null) {
            Node node = new Node(citizen, parentNode);
            node.height = 0;    // Set a height of zero (it is a leaf node).
            return node;
        }

        valueToInsert = citizen.getWeight();
        currentNodeValue = currentNode.getCitizenWeight();

        if (valueToInsert < currentNodeValue) {     //We go to the right child if the value that we want to insert is lower than the current node's value
            currentNode.right = add(currentNode.right, citizen, currentNode);
        } else if (valueToInsert > currentNodeValue) {      //We go to the left child if the value that we want to insert is higher than the current node's value
            currentNode.left = add(currentNode.left, citizen, currentNode);
        } else {
            currentNode.calculateHeight();
            return currentNode; //We return the currentNode if the value already exists (therefore not adding the new node as it has a duplicated value)
        }

        // Case where the node is added
        currentNode.calculateHeight();
        return currentNode;
    }

    private Node remove (Node currentNode, Citizen citizen) {

        if (currentNode == null) {
            return null;
        }

        //We go to the left child if the value that we want to delete is higher than the current node's value
        if (citizen.getWeight() > currentNode.getCitizenWeight()) {
            currentNode.left = remove(currentNode.left, citizen);
            currentNode.calculateHeight(); // Re-calculate the height of the current node.
            return currentNode;
        }

        //We go to the right child if the value that we want to delete is lower than the current node's value
        if (citizen.getWeight() < currentNode.getCitizenWeight()) {
            currentNode.right = remove(currentNode.right, citizen);
            currentNode.calculateHeight(); // Re-calculate the height of the current node.
            return currentNode;
        }

        // Node to delete found
        if (citizen.getWeight() == currentNode.getCitizenWeight()) {

            //If the node does not have children, we return null (replacing this node with null in the parent)
            if (currentNode.right == null && currentNode.left == null) {
                return null;
            }

            //If the node only has one child, we return the child (replacing this node with the node's child in the parent)
            if (currentNode.right == null) {
                currentNode.left.parent = currentNode.parent;
                currentNode.left.calculateHeight(); // Re-calculate the height of the current node.
                return currentNode.left;
            }

            if (currentNode.left == null) {
                currentNode.right.parent = currentNode.parent;
                currentNode.right.calculateHeight(); // Re-calculate the height of the current node.
                return currentNode.right;
            }
            /////////////////////////////////
        }

        //If the node has two children, we need to reorganize the tree.

        //We will need to replace the node with another node that has a suitable value.
        //Knowing the value of the node that we want to delete, we will choose the node with the
        //next biggest value as a substitute. To choose this node, we will first go to the left node
        //(which has a greater value) and then find the lowest value in the subtree. This value will
        //be the next biggest value that we were searching for

        Node tempNode = findMinNode(currentNode.left); //Finds the node with the lowest value in the subtree (given an origin/root node)
        currentNode.setCitizen(tempNode.getCitizen());  //We change the node's citizen information; effectively eliminating the older node.
        currentNode.left = remove(currentNode.left, tempNode.getCitizen()); //We delete the node that had been chosen as a substitute. If we did not delete it, it would be duplicated in the tree.

        currentNode.calculateHeight(); // Re-calculate the height of the current node.
        return currentNode;
    }

    @Override
    public Citizen findCitizenById (long citizenId) {
        return TreeBFS.findCitizenById(root, citizenId);
    }

    //Given a starting node, searches for the right node that has the lowest value
    private Node findMinNode(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    @Override
    public void findCitizensInRange(float max, float min) {

        MyArrayList<Citizen> witches = new MyArrayList<>();
        findCitizensInRange(max, min, root, witches);

        // Print all the witches (in case there is any)
        if (witches.size() > 0) {

            // Take into account if there is only one witch discovered (or +1 one)
            if (witches.size() == 1) {
                System.out.println("S'ha descobert " + witches.size() + " bruixa!");
            }
            else {
                System.out.println("S'han descobert " + witches.size() + " bruixes!");
            }

            // Print all the witches information
            for (Citizen witch : witches) {
                witch.printInfo(true, true);
            }
        }
        else {
            System.out.println("No s'ha descobert cap bruixa.");
        }
    }

    public void findCitizensInRange(float max, float min, Node node, MyArrayList<Citizen> witches) {

        // Check if exploring the nodes with a lower value than the current node is interesting: the current node value is over Minimum Value.
        if (node.right != null && node.getCitizenWeight() >= min) {
            findCitizensInRange(max, min, node.right, witches);
        }

        // Print the node if it meets the requirements: the Citizen's weight is between the limits / bounds (it's a Witch).
        if (min <= node.getCitizenWeight() && max >= node.getCitizenWeight()) {
            witches.add(node.getCitizen());
        }

        // Check if exploring the nodes with a higher value than the current node is interesting: the current node value is below Maximum Value.
        if (node.left != null && node.getCitizenWeight() <= max ) {
            findCitizensInRange(max, min, node.left, witches);
        }
    }

}
