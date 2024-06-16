import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;
    private static final Logger logger = LogManager.getLogger(CustomerStorage.class);
    public static final String PHONE_REGEX = "^\\+7\\d{10}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    public static final String EXAMPLE_TEXT = "example: Василий Петров vasily.petrov@gmail.com +79215637722";
    private static final int INDEX_NAME = 0;
    private static final int INDEX_SURNAME = 1;
    private static final int INDEX_EMAIL = 2;
    private static final int INDEX_PHONE = 3;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public class InvalidDataFormatException extends RuntimeException {
        public InvalidDataFormatException(String message) {
            super(message);
        }
    }

    public class InvalidPhoneNumberException extends RuntimeException {
        public InvalidPhoneNumberException(String message) {
            super(message);
        }
    }

    public class InvalidEmailFormatException extends RuntimeException {
        public InvalidEmailFormatException(String message) {
            super(message);
        }
    }

    public void addCustomer(String data) {
        logger.info("Reading info");

        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new InvalidDataFormatException("Incorrect format. Expected: <First Name> <Last Name> <Email> <Phone Number>");
        }

        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        String email = components[INDEX_EMAIL];
        String phone = components[INDEX_PHONE];

        if (!phone.matches(PHONE_REGEX)) {
            throw new InvalidPhoneNumberException("Please enter a correct Russian phone number (example: +79215637722)");
        }
        if (!email.matches(EMAIL_REGEX)) {
            throw new InvalidEmailFormatException("Please enter a correct email address (example: example@gmail.com)");
        }

        storage.put(name, new Customer(name, phone, email));
        logger.info("Finished reading info");
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }

    public boolean customerExists(String name) {
        return storage.containsKey(name);
    }
}
