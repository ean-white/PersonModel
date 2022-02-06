import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;
import java.util.Scanner;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;


public class PersonReader {



    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        ArrayList<String> lines = new ArrayList<>();

        try
        {

            File workingDirectory = new File(System.getProperty("user.dir"));


            chooser.setCurrentDirectory(workingDirectory);


            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));


                int line = 0;

                System.out.println(String.format("%-6s", "ID#")
                        + String.format("%-10s", "FirstName")
                        + String.format("%-10s", "Lastname")
                        + String.format("%-6s", "Title")
                        + String.format("%-6s", "YOB"));
                System.out.println("==================================");

                while(reader.ready())
                {
                    rec = reader.readLine();
                    String[] inputData = rec.split(",");
                    System.out.println(String.format("%-6s", inputData[0])
                            + String.format("%-10s", inputData[1])
                            + String.format("%-10s", inputData[2])
                            + String.format("%-6s", inputData[3])
                            + String.format("%-6s", inputData[4]));
                }
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



    }
}
