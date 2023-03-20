package Menu.TreesBruixes;

import Menu.Menu;
import Parsers.DatasetLoaderF2;
import TreesF2.Entities.Citizen;
import TreesF2.Entities.Tree;
import TreesF2.Entities.Trees.AVLTree;
import TreesF2.Entities.Trees.BinaryTree;
import TreesF2.Entities.Trees.TreeType;

public class BruixesMenuLogic {

    private static Tree tree;

    public static void showAddCitizen() {
        long id = Menu.askForInteger("\nIdentificador de l'habitant: ", 0, Integer.MAX_VALUE);
        String name = Menu.askForString("Nom de l'habitant: ");
        float weight = Menu.askForFloat("Pes de l'habitant: ", 1, 200);
        String kingdom = Menu.askForString("Regne de l'habitant: ");

        tree.addCitizen(new Citizen(id, name, weight, kingdom));

        System.out.println("\n" + name + " ens acompanyarà a partir d'ara.");
    }

    public static void showRemoveCitizen() {
        long id = Menu.askForInteger("\nIdentificador de l'habitant: ", 0, Integer.MAX_VALUE);
        Citizen citizen = tree.findCitizenById(id);

        //Only if the citizen appears in the tree we execute the delete function
        if (citizen != null) {
            tree.removeCitizen(id);
            System.out.println("\n" + citizen.getName() + " ha estat transformat en un grill.");
        } else {
            System.out.println("\nL'ID introduït no correspon a cap habitant de l'arbre.");
        }

    }

    public static void showTreeRepresentation() {
        tree.printRepresentation();
    }

    public static void showWitchIdentification() {
        tree.printRepresentation();
    }

    public static void showBatuda() {
        float max, min;

        System.out.println();
        min = Menu.askForFloat("Pes mínim: ", 0.00f, Float.MAX_VALUE);
        max = Menu.askForFloat("Pes màxim: ", min, Float.MAX_VALUE);
        System.out.println();

        tree.findCitizensInRange(max, min);

    }

    // We only create the tree if it was not already created before
    public static void checkIfTreeCreated(TreeType treeType) {

        // If tree is not created yet, instantiate one.
        if (tree == null) {

            // "BinaryTree" class, which uses a Binary Search Tree (not balanced) structure.
            if (treeType == TreeType.BINARYTREE) {
                tree = new BinaryTree();
            }
            else {
                // "AVLTree" class, which uses Balanced Tree structure.
                tree = new AVLTree();
            }
            DatasetLoaderF2.loadCitizens(Menu.TREES_DATASET, tree);
        }
    }
}
