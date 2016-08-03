package ua.qa.adressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ua.qa.adressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by polkota on 01.08.2016.
 */
public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws Exception {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jcommander = new JCommander(generator);

        try {
            jcommander.parse(args);
        } catch (ParameterException ex){
            jcommander.usage();
            return;
        }
        //int count = Integer.parseInt(args[0]);
        // File file = new File(args[1]);
        generator.run();

    }

    private void run() throws Exception {
        List<GroupData> groups = generateGroups(count);
        if (format.equals("csv")){
            saveAsCsv(groups, new File(file));
        } else if (format.equals("xml")){
            saveAsXml (groups, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }


    }

    private void saveAsXml(List<GroupData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        //xstream.alias("group", GroupData.class);
        String xml = xstream.toXML(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
                  }
    }

    private void saveAsCsv(List<GroupData> groups, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file)) {
            for (GroupData group : groups) {
                writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
            }
        }
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("test %s", i))
                    .withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i)));
        }
        return groups;
    }
}
