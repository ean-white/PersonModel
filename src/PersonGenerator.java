import java.util.Scanner;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;





public class PersonGenerator {



    public static void main(String[] args)
    {
	    Scanner in = new Scanner(System.in);

        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        int YOB = 0;
        boolean repeat = false;
        String rec;


        ArrayList<String> recs = new ArrayList<String>();

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");

        do {
            ID = SafeInput.getNonZeroLenString(in, "Please enter your ID");
            firstName = SafeInput.getNonZeroLenString(in, "PLease enter your first name");
            lastName = SafeInput.getNonZeroLenString(in, "Please enter your last name");
            title = SafeInput.getNonZeroLenString(in, "Please enter your title");
            YOB = SafeInput.getRangedInt(in, "Please enter your year of birth", 1, 2021);

            rec = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB + ".";

            recs.add(rec);

            repeat = SafeInput.getYNConfirm(in, "Are you done?");

        }
        while (!repeat);

        System.out.println(recs);



        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!


            for( String r : recs )
            {
                writer.write(r, 0, r.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
