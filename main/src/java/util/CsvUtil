package tugasmandiri.contactmanager.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tugasmandiri.contactmanager.Model.contact;

public class CsvUtil {
    private static final String FILE_PATH = "contacts.csv";

    public static List<contact> readContacts() throws IOException {
        List<contact> contacts = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            contacts.add(new contact(fields[0], fields[1], fields[2]));
        }
        reader.close();
        return contacts;
    }

    public static void writeContacts(List<contact> contacts) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        for (contact contact : contacts) {
            writer.write(contact.getName() + "," + contact.getEmail() + "," + contact.getPhone());
            writer.newLine();
        }
        writer.close();
    }
}
