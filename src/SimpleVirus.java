import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleVirus {

    public static void main(String[] args) {
        replicate(); // Trigger the replication process
        System.out.println("Virus executed successfully.");
    }

    private static void replicate() {
        try {
            // Obtain the path to this Java file
            String filePath = SimpleVirus.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            // Create a copy of this file
            String copyPath = filePath.replace("SimpleVirus.class", "SimpleVirusCopy.class");
            File source = new File(filePath);
            File destination = new File(copyPath);
            copyFile(source, destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void copyFile(File source, File destination) throws IOException {
        // Read the content of the source file
        String content = "";
        // Write the content to the destination file
        try (FileWriter writer = new FileWriter(destination)) {
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
