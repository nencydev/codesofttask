import java.io.*;
import java.util.*;

class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress + "\n";
    }

    public String getName() {
        return null;
    }
}

class AddressBook implements Serializable {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public void saveToFile(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(contacts);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            contacts = (List<Contact>) ois.readObject();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();

        try {
            addressBook.loadFromFile("addressbook.dat");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous data found.");
        }

        while (true) {
            System.out.println("Address Book System");
            System.out.println("1. Add Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. Display All Contacts");
            System.out.println("4. Save and Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter Email Address: ");
                    String emailAddress = scanner.nextLine();

                    Contact newContact = new Contact(name, phoneNumber, emailAddress);
                    addressBook.addContact(newContact);
                    System.out.println("Contact added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Name to search: ");
                    String searchName = scanner.nextLine();
                    Contact foundContact = addressBook.searchContact(searchName);
                    if (foundContact != null) {
                        System.out.println("Contact found:\n" + foundContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 3:
                    List<Contact> allContacts = addressBook.getAllContacts();
                    if (allContacts.isEmpty()) {
                        System.out.println("No contacts to display.");
                    } else {
                        System.out.println("All Contacts:");
                        for (Contact contact : allContacts) {
                            System.out.println(contact);
                        }
                    }
                    break;

                case 4:
                    try {
                        addressBook.saveToFile("addressbook.dat");
                        System.out.println("Data saved. Exiting...");
                    } catch (IOException e) {
                        System.out.println("Error saving data.");
                    }
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}