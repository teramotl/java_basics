import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        try {
            while (true) {
                String command = scanner.nextLine();
                String[] tokens = command.split("\\s+", 2);

                logger.info("User input: {}", command);

                switch (tokens[0]) {
                    case "add":
                        if (tokens.length < 2) {
                            throw new IllegalArgumentException("provide data");
                        }
                        executor.addCustomer(tokens[1]);
                        break;
                    case "list":
                        executor.listCustomers();
                        break;
                    case "remove":
                        if (tokens.length < 2) {
                            throw new IllegalArgumentException("provide name");
                        }
                        String nameToRemove = tokens[1];
                        if (executor.customerExists(nameToRemove)) {
                            executor.removeCustomer(nameToRemove);
                            System.out.println(nameToRemove + " has been removed.");
                        } else {
                            System.out.println("Error: No one found with the name " + nameToRemove);
                        }
                        executor.removeCustomer(tokens[1]);
                        break;
                    case "count":
                        System.out.println("There are " + executor.getCount() + " customers");
                        break;
                    case "help":
                        System.out.println(helpText);
                        break;
                    default:
                        logger.error("Wrong command!");
                        System.out.println(COMMAND_ERROR);
                        break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("error: {}", e.getMessage());
            System.out.println("error: " + e.getMessage());
            System.out.println(helpText);
        } catch (Exception e) {
            logger.error("Unexcpected error: {}", e.getMessage());
            System.out.println("unexpected error occured. please try again");
            System.out.println(helpText);
        }
    }
}
