import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SwitchBoardSimulator {
    private List<Appliance> appliances;
    private int exitNumber;
    private Scanner scanner;

    public SwitchBoardSimulator(int numFans, int numAcs, int numBulbs) {
        appliances = new ArrayList<>();
        for (int i = 1; i <= numFans; i++) {
            appliances.add(new Fan("Fan " + i));
        }
        for (int i = 1; i <= numAcs; i++) {
            appliances.add(new AC("AC " + i));
        }
        for (int i = 1; i <= numBulbs; i++) {
            appliances.add(new Bulb("Bulb " + i));
        }
        exitNumber = appliances.size() + 1;
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Please select an appliance:");
            for (int i = 0; i < appliances.size(); i++) {
                System.out.println((i+1) + ". " + appliances.get(i).getName() + " is " + appliances.get(i).getState());
            }
            System.out.println(exitNumber + ". Exit");
            int selection = scanner.nextInt();
            if (selection == exitNumber) {
                exit = true;
            } else if (selection > 0 && selection <= appliances.size()) {
                Appliance appliance = appliances.get(selection - 1);
                System.out.println("Please select an action:");
                System.out.println("1. Turn " + appliance.getName() + " On");
                System.out.println("2. Turn " + appliance.getName() + " Off");
                System.out.println("3. Back");
                int action = scanner.nextInt();
                switch (action) {
                    case 1:
                        appliance.turnOn();
                        break;
                    case 2:
                        appliance.turnOff();
                        break;
                    default:
                        break;
                }
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of fans: ");
        int numFans = input.nextInt();
        System.out.println("Enter number of ACs: ");
        int numAcs = input.nextInt();
        System.out.println("Enter number of bulbs: ");
        int numBulbs = input.nextInt();
        SwitchBoardSimulator switchBoard = new SwitchBoardSimulator(numFans, numAcs, numBulbs);
        switchBoard.run();
    }
}

abstract class Appliance {
    private String name;
    private boolean state;

    public Appliance(String name) {
        this.name = name;
        this.state = false;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state ? "On" : "Off";
    }

    public void turnOn() {
        state = true;
        System.out.println(name + " turned On.");
    }

    public void turnOff() {
        state = false;
        System.out.println(name + " turned Off.");
    }
}

class Fan extends Appliance {
    public Fan(String name) {
        super(name);
    }
}

class AC extends Appliance {
    public AC(String name) {
        super(name);
    }
}

class Bulb extends Appliance {
    public Bulb(String name) {
        super(name);
    }
}
