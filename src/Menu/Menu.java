package Menu;

import Menu.Bruixes.BruixesMenuOptions;
import Menu.Orenetes.OrenetesMenuOptions;

import java.util.Scanner;

public class Menu {

    public final static String separator = System.lineSeparator();
    public static String GRAPHS_DATASET;
    public static String TREES_DATASET;

    public static final String MAIN_MENU = separator + "'`^\\ The Hashy Grail /^´'" + separator+separator+
            "1. Sobre orenetes i cocos (Grafs)" +separator +
            "2. Caça de bruixes (Arbres binaris de cerca)" + separator+
            "3. PER ESPECIFICAR" + separator+
            "4. PER ESPECIFICAR" + separator + separator +
            "5. Exit" + separator;
    public static final String ORENETES_MENU = separator + "A. Exploració del regne" + separator +
            "B. Detecció de trajectes habituals" + separator +
            "C. Missatgeria premium" + separator + separator +
            "D. Tornar enrere" + separator;

    public static final String BRUIXES_MENU = separator + "A. Afegir habitant" + separator +
            "B. Eliminar habitant" + separator +
            "C. Representació visual" + separator +
            "D. Identificació de bruixes" + separator +
            "E. Batuda" + separator + separator +
            "F. Tornar enrere" + separator;

    public static final String EXIT = "\u001B[31m "+separator+"Aturant The Hashy Grail..."+separator+" \u001B[0m";


    // Shows main menu of the program and asks to choose an option.
    public static MainMenuOptions showMainMenu() {
        do {
            System.out.println(separator + MAIN_MENU);
            int option = askForInteger("Esculli una opció: ", 1, 5);

            // Ejecutar la opción escogida.
            switch (option) {
                case 1 -> {
                    return MainMenuOptions.ORENETES;
                }
                case 2 -> {
                    return MainMenuOptions.BRUIXES;
                }
                case 3 -> {
                    return MainMenuOptions.OPTION_3;
                }
                case 4 -> {
                    return MainMenuOptions.OPTION_4;
                }
                case 5 -> {
                    return MainMenuOptions.EXIT;
                }
            }
        } while (true);
    }

    // Sub-menu for the ORENETES option (selected previously in the main menu).
    public static OrenetesMenuOptions showOrenetesMenu(String graph_dataset) {
        GRAPHS_DATASET = graph_dataset;

        System.out.println(separator + ORENETES_MENU);
        String option = askForCharacter("Quina funcionalitat vol executar? ");

        switch (option) {
            case "A" -> { return OrenetesMenuOptions.KINGDOM_EXPLORATION; }
            case "B" -> { return OrenetesMenuOptions.COMMON_ROUTES; }
            case "C" -> { return OrenetesMenuOptions.PREMIUM_MESSAGING; }
            default -> { return OrenetesMenuOptions.PREVIOUS_MENU; }
        }
    }

    // Sub-menu for the WITCHES option (selected previously in the main menu).
    public static BruixesMenuOptions showWitchesMenu(String trees_dataset) {
        TREES_DATASET = trees_dataset;

        System.out.println(separator + BRUIXES_MENU);
        String option = askForCharacter("Quina funcionalitat vol executar? ");

        switch (option) {
            case "A" -> { return BruixesMenuOptions.ADD_CITIZEN; }
            case "B" -> { return BruixesMenuOptions.REMOVE_CITIZEN; }
            case "C" -> { return BruixesMenuOptions.VISUAL_REPRESENTATION; }
            case "D" -> { return BruixesMenuOptions.WITCHES_IDENTIFICATION; }
            case "E" -> { return BruixesMenuOptions.BATUDA; }
            default -> { return BruixesMenuOptions.EXIT; }
        }
    }

    // Asks for an option number, and checks if a wrong input is entered
    public static int askForInteger(String askMessage, int min, int max) {
        int option = min - 1;   // Asegura que la condición del bucle se siga cumpliendo.
        String errorMessage = "Error: Introdueix un número entre " + min + " i " + max + "." + separator;

        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(askMessage);
                option = Integer.parseInt(sc.nextLine());

                if (option < min || option > max) {
                    System.out.println(errorMessage);
                }

            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }

        } while (option < min || option > max);

        return option;
    }

    public static String askForCharacter(String askMessage) {
        String option = ""; // Assegura que la condición del bucle se siga cumpliendo.
        String errorMessage = "Error: Introdueix un caràcter entre \"A\", \"B\", \"C\" o \"D\"." + separator;

        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(askMessage);
                option = sc.nextLine();
                option = option.toUpperCase();

                if (!(option.equals("A") || option.equals("B") || option.equals("C") || option.equals("D"))) {
                    System.out.println(errorMessage);
                }

            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }

        } while (!(option.equals("A") || option.equals("B") || option.equals("C") || option.equals("D")));

        return option;
    }

    public static boolean askForBoolean(String askMessage) {
        String option = "";
        String errorMessage = "Error: Introdueix \"SI\" o \"NO\"." + separator;

        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(askMessage);
                option = sc.nextLine();
                option = option.toUpperCase();

                if (!(option.equals("SI") || option.equals("NO"))) {
                    System.out.println(errorMessage);
                }

            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }

        } while (!(option.equals("SI") || option.equals("NO")));

        return option.equals("SI");
    }
}
