package com.Day28;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBook {
    private static ArrayList<AddressBookContact> list = new ArrayList<AddressBookContact>();

    // Add contact details in AddressBookContact ::
    public void AddContactsDetails() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the First Name => ");
        String firstName = userInput.nextLine();
        System.out.println("Enter the Last Name => ");
        String lastName = userInput.nextLine();
        System.out.println("Enter the Address => ");
        String address = userInput.nextLine();
        System.out.println("Enter the City => ");
        String city = userInput.nextLine();
        System.out.println("Enter the State => ");
        String state = userInput.nextLine();
        System.out.println("Enter the Zip Code => ");
        int zip = userInput.nextInt();
        System.out.println("Enter the Phone Number => ");
        long phoneNumber = userInput.nextLong();
        System.out.println("Enter the Email => ");
        String email = userInput.nextLine();
        email = userInput.nextLine();
        AddressBookContact details = new AddressBookContact(firstName, lastName, address, city, state, zip, phoneNumber,
                email);
        list.add(details);
        details.display();
    }

    //Edit contact details in AddressBookContact::
    private void editContact() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter firstname to edit contact => ");
        String name = userInput.nextLine();
        for (AddressBookContact search : list) {
            if (name.equalsIgnoreCase(search.getFirstName())) {
                System.out.println("Enter the updated first name => ");
                String firstName = userInput.next();
                search.setFirstName(firstName);
                System.out.println("Enter the updated last name => ");
                String lastName = userInput.next();
                search.setLastName(lastName);
                System.out.println("Enter the updated address => ");
                String address = userInput.next();
                search.setAddress(address);
                System.out.println("Enter the updated city => ");
                String city = userInput.next();
                search.setCity(city);
                System.out.println("Enter the updated state => ");
                String state = userInput.next();
                search.setState(state);
                System.out.println("Enter the updated zipcode => ");
                int zip = userInput.nextInt();
                search.setZip(zip);
                System.out.println("Enter the updated phoneNumber => ");
                long phoneNumber = userInput.nextInt();
                search.setPhoneNumber(phoneNumber);
                System.out.println("Enter the updated emailID => ");
                String email = userInput.next();
                search.setEmail(email);
                search.display();
            } else {
                System.out.println("Entered name not found in the AddressBook");
            }
        }
    }

    //Delete contact details in AddressBookContact ::
    private void deleteContact() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter firstname to Delete Contact => ");
        String name = userInput.nextLine();
        for (AddressBookContact search : list) {
            if (name.equalsIgnoreCase(search.getFirstName())) {
                System.out.println("Entered name found in the Address Book, deleting contact");
                list.remove(search);
            } else {
                System.out.println("Entered name not found in the Address Book");
            }
        }
    }

    //Check duplicate entry in AddressBookContact::
    private void checkDuplicateEntry() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter firstname to Check duplicate entry Contact: ");
        String name = userInput.nextLine();
        for (AddressBookContact search : list) {
            if (name.equalsIgnoreCase(search.getFirstName())) {
                System.out.println("Entered name found in the Address Book");
            } else {
                System.out.println("Entered name not found in the Address Book");
            }
        }
    }

    //Search person in AddressBookContact::
    private void searchPerson() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter city to search person entry in Contact: ");
        String city = sc.nextLine();
        for (AddressBookContact search : list) {
            if (city.equalsIgnoreCase(search.getCity())) {
                System.out.println("Entered city found in the Address Book");
                search.display();
            } else {
                System.out.println("Entered city not found in the Address Book");
            }
        }
    }

    // View persons by state wise ::
    private void viewPersons() {
        Collections.sort(list, (o1, o2) -> (o1.getState().compareTo(o2.getState())));
        for (AddressBookContact search : list) {
            System.out.println("The person name is " + search.getFirstName() + " from " + search.getState() + " State");
        }
    }

    // View persons contact number by state wise ::
    private void viewPersonsContactNumber() {
        Collections.sort(list, (o1, o2) -> (o1.getState().compareTo(o2.getState())));
        for (AddressBookContact search : list) {
            System.out.println(" ");
            System.out.println("Person name :: " + search.getFirstName() + "|| Phone no :: " + search.getPhoneNumber()
                    + "|| State :: " + search.getState());
        }
    }

    // View Address book by persons name ::
    private void viewAddressBook_by_PersonsName() {
        Collections.sort(list, (o1, o2) -> (o1.getFirstName().compareTo(o2.getFirstName())));
        for (AddressBookContact search : list) {
            System.out.println(" ");
            System.out.println("|| lastName :: " + search.getLastName() + "|| Address :: " + search.getAddress()
                    + "|| City :: " + search.getCity() + "|| State :: " + search.getState() + "|| zipcode :: "
                    + search.getZip() + "|| Phone no :: " + search.getPhoneNumber() + "|| email-ID :: "
                    + search.getEmail() + "Person name :: " + search.getFirstName());
        }
    }

    // View Address book by by City,State or Zip ::
    private void viewAddressBook_by_City_Sate() {
        Comparator<AddressBookContact> compareByCitySate = Comparator.comparing(AddressBookContact::getCity)
                .thenComparing(AddressBookContact::getState);

        List<AddressBookContact> sortedviewAddressBook_by_City_Sate = list.stream().sorted(compareByCitySate)
                .collect(Collectors.toList());
        for (AddressBookContact result : list) {
            System.out.println("|| FirstName :: " + result.getFirstName() + "|| lastName :: " + " "
                    + result.getLastName() + "|| Address :: " + result.getAddress() + "|| zipcode :: " + result.getZip()
                    + "|| Phone no :: " + result.getPhoneNumber() + "|| email-ID :: " + result.getEmail()
                    + "|| City :: " + result.getCity() + "|| State :: " + result.getState());
        }
    }

    //Read/Write Address Book with Persons Contact as CSV File ::
    private void IO_File() throws IOException {
        FileWriter csvWriter = new FileWriter("addressBook.csv");
        csvWriter.append("firstName");
        csvWriter.append(",");
        csvWriter.append("lastName");
        csvWriter.append(",");
        csvWriter.append("address");
        csvWriter.append(",");
        csvWriter.append("city");
        csvWriter.append(",");
        csvWriter.append("state");
        csvWriter.append(",");
        csvWriter.append("Zip Code");
        csvWriter.append(",");
        csvWriter.append("Phone no");
        csvWriter.append(",");
        csvWriter.append("email-ID");
        csvWriter.append("\n");
        for (AddressBookContact rowData : list) {
            csvWriter.append(String.join(",",
                    rowData.getFirstName() + "," + rowData.getLastName() + "," + rowData.getAddress() + ","
                            + rowData.getCity() + "," + rowData.getState() + "," + rowData.getZip() + ","
                            + rowData.getPhoneNumber() + "," + rowData.getEmail()));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    //Read/Write Address Book with Persons Contact as JSON File ::
    public void JSON_File() throws FileNotFoundException {
        List<List<String>> input = new ArrayList<List<String>>();
        Scanner userInput = new Scanner(new File("addressBook.csv"));
        while (userInput.hasNextLine()) {
            Scanner lineSc = new Scanner(userInput.nextLine()).useDelimiter("\t");
            List<String> line = new ArrayList<String>();
            while (lineSc.hasNext()) {
                line.add(lineSc.next());
            }
            input.add(line);
        }
        System.out.println(input);
    }

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        HashMap<String, AddressBook> addressBooks = new HashMap<>();
        AddressBook book1 = new AddressBook();
        AddressBook book2 = new AddressBook();
        AddressBook book3 = new AddressBook();
        addressBooks.put("AddressBook1", book1);
        addressBooks.put("AddressBook2", book2);
        addressBooks.put("AddressBook3", book3);
        System.out.println("Choose your Address Book => AddressBook(1)/ AddressBook(2)/ AddressBook(3)");
        int chooseAddressBook = userInput.nextInt();
        System.out.println("Choose your Address Book => Add/Edit/Delete/DuplicateEntry/SearchPerson");
        while (chooseAddressBook >= 1) {
            System.out.println(
                    "Enter Your Choice => Add Contacts (1)/Edit Contacts (2)/Delete Contacts (3)/ Check Duplicate Entry (4)/ Search person (5)");
            int choice = userInput.nextInt();
            switch (chooseAddressBook) {
                case 1:
                    if (choice == 1) {
                        book1.AddContactsDetails();
                    } else if (choice == 2) {
                        book1.editContact();
                    } else if (choice == 3) {
                        book1.deleteContact();
                    } else if (choice == 4) {
                        book1.checkDuplicateEntry();
                    } else if (choice == 5) {
                        book1.searchPerson();
                    }
                    break;
                case 2:
                    if (choice == 1) {
                        book2.AddContactsDetails();
                    } else if (choice == 2) {
                        book2.editContact();
                    } else if (choice == 3) {
                        book2.deleteContact();
                    } else if (choice == 4) {
                        book2.checkDuplicateEntry();
                    } else if (choice == 5) {
                        book2.searchPerson();
                    }
                    break;
                case 3:
                    if (choice == 1) {
                        book3.AddContactsDetails();
                    } else if (choice == 2) {
                        book3.editContact();
                    } else if (choice == 3) {
                        book3.deleteContact();
                    } else if (choice == 4) {
                        book3.checkDuplicateEntry();
                    } else if (choice == 5) {
                        book3.searchPerson();
                    }
                    break;
                default:
                    System.out.println("Give proper input");
                    break;
            }
            System.out.println("1) AddressBook 1");
            System.out.println("2) AddressBook 2");
            System.out.println("3) AddressBook 3");
            System.out.println("0) Exit");
            AddressBook myobj = new AddressBook();
            myobj.viewPersons();
            myobj.viewPersonsContactNumber();
            myobj.viewAddressBook_by_PersonsName();
            myobj.viewAddressBook_by_City_Sate();

            try {
                myobj.IO_File();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                myobj.JSON_File();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            chooseAddressBook = userInput.nextInt();
        }
    }
}
