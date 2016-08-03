package ua.qa.adressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ua.qa.adressbook.model.ContactData;
import ua.qa.adressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by polkota on 02.08.2016.
 */
public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Gontact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws Exception {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jcommander = new JCommander(generator);

        try {
            jcommander.parse(args);
        } catch (ParameterException ex){
            jcommander.usage();
            return;
        }

        generator.run();

    }

    private void run() throws Exception {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")){
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")){
            saveAsXml (contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }


    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
       try(Writer writer = new FileWriter(file)) {
           for (ContactData contact : contacts) {
               writer.write(String.format("%s;%s;%s\n", contact.getFirstname(), contact.getLastname()));
           }
       }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("David %s", i))
                    .withLastname(String.format("Green %s", i)));
        }
        return contacts;
    }
}


