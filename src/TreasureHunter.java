/**
 * This class is responsible for controlling the Treasure Hunter game.<p>
 * It handles the display of the menu and the processing of the player's choices.<p>
 * It handles all of the display based on the messages it receives from the Town object.
 */
import java.util.Scanner;

public class TreasureHunter
{
    //Instance variables
    private Town currentTown;
    private Hunter hunter;
    private boolean hardMode;
    private boolean easyMode;
    private double discount;
    private Treasure treasure;

    //Constructor
    /**
     * Constructs the Treasure Hunter game.
     */
    public TreasureHunter()
    {
        // these will be initialized in the play method
        currentTown = null;
        hunter = null;
        hardMode = false;
        easyMode=false;
        discount=1;
        treasure=new Treasure();
    }

    // starts the game; this is the only public method
    public void play()
    {
        welcomePlayer();
        enterTown();
        showMenu();
    }

    /**
     * Creates a hunter object at the beginning of the game and populates the class member variable with it.
     */
    private void welcomePlayer()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to TREASURE HUNTER!");
        System.out.println("Going hunting for the big treasure, eh?");
        System.out.print("What's your name, Hunter? ");
        String name = scanner.nextLine();

        // set hunter instance variable


        System.out.print("Choose a mode (E)asy, (H)ard, (N)ormal: ");
        String hard = scanner.nextLine();
        if (hard.equals("H") || hard.equals("h"))
        {
            hardMode = true;
            hunter = new Hunter(name, 10);
        }
        if(hard.equals("E")||hard.equals("e")){
            hunter = new Hunter(name, 20);
            easyMode = true;
            discount=0.5;
        }
        if(hard.equals("N")||hard.equals("n")){
            hunter=new Hunter(name,10);
        }
    }

    /**
     * Creates a new town and adds the Hunter to it.
     */
    private void enterTown()
    {
        double markdown = 0.25;
        double toughness = 0.4;
        if (hardMode)
        {
            // in hard mode, you get less money back when you sell items
            markdown = 0.5;

            // and the town is "tougher"
            toughness = 0.75;
        }
        if(easyMode){
            markdown=1;
            toughness=0.25;
        }

        // note that we don't need to access the Shop object
        // outside of this method, so it isn't necessary to store it as an instance
        // variable; we can leave it as a local variable
        Shop shop = new Shop(markdown,discount);

        // creating the new Town -- which we need to store as an instance
        // variable in this class, since we need to access the Town
        // object in other methods of this class
        currentTown = new Town(shop, toughness);


        // calling the hunterArrives method, which takes the Hunter
        // as a parameter; note this also could have been done in the
        // constructor for Town, but this illustrates another way to associate
        // an object with an object of a different class
        currentTown.hunterArrives(hunter);
    }

    /**
     * Displays the menu and receives the choice from the user.<p>
     * The choice is sent to the processChoice() method for parsing.<p>
     * This method will loop until the user chooses to exit.
     */
    private void showMenu()
    {
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!(choice.equals("X") || choice.equals("x")))
        {
            System.out.println();
            System.out.println(currentTown.getLatestNews());
            System.out.println("***");
            System.out.println(hunter);
            System.out.println(currentTown);
            System.out.println("(B)uy something at the shop.");
            System.out.println("(S)ell something at the shop.");
            System.out.println("(M)ove on to a different town.");
            System.out.println("(L)ook for trouble!");
            System.out.println("(H)unt for treasure.");
            System.out.println("Give up the hunt and e(X)it.");
            System.out.println();
            System.out.print("What's your next move? ");
            choice = scanner.nextLine();
            processChoice(choice);
        }
    }

    /**
     * Takes the choice received from the menu and calls the appropriate method to carry out the instructions.
     * @param choice The action to process.
     */
    private void processChoice(String choice)
    {
        switch (choice) {
            case "B":
            case "b":
            case "S":
            case "s":
                currentTown.enterShop(choice);
                break;
            case "M":
            case "m":
                if (currentTown.leaveTown()) {
                    //This town is going away so print its news ahead of time.
                    System.out.println(currentTown.getLatestNews());
                    enterTown();
                }
                break;
            case "L":
            case "l":
                currentTown.lookForTrouble();
                break;
            case "X":
            case "x":
                System.out.println("Fare thee well, " + hunter.getHunterName() + "!");
                break;
            case "H":
            case "h":
                if (!currentTown.getSearched()) {
                    System.out.print("\n"+treasure.getTreasure());
                    currentTown.setSearched();
                    System.out.print(treasure.hasAll());
                    currentTown.setPrintMessage("");
                    hunter.setTreasures(treasure.returnTreasure());
                }
                else{
                    System.out.print("\nYou have already searched this town.");
                    currentTown.setPrintMessage("");
                }
                break;
            default:
                System.out.println("Yikes! That's an invalid option! Try again.");
                break;
        }
    }
}