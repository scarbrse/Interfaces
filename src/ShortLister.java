import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author wulft
 *
 * Use the thread safe NIO IO library to read a file
 */
public class ShortLister {

    public static ArrayList<Object> collectAll(ArrayList<Object> objects, Filter w) {
        ArrayList<Object> words = new ArrayList<Object>();

        for (Object x : objects) {
            if (w.accept(x)) {
                words.add(x);
            }
        }
        return words;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try
        {
            // uses a fixed known path:
            //  Path file = Paths.get("c:\\My Documents\\data.txt");

            // use the toolkit to get the current working directory of the IDE
            // Not sure if the toolkit is thread safe...
            File workingDirectory = new File(System.getProperty("user.dir"));

            // Typiacally, we want the user to pick the file so we use a file chooser
            // kind of ugly code to make the chooser work with NIO.
            // Because the chooser is part of Swing it should be thread safe.
            chooser.setCurrentDirectory(workingDirectory);
            // Using the chooser adds some complexity to the code.
            // we have to code the complete program within the conditional return of
            // the filechooser because the user can close it without picking a file

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                // Typical java pattern of inherited classes
                // we wrap a BufferedWriter around a lower level BufferedOutputStream
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                Scanner myReader = new Scanner(file);
                ArrayList<Object> wordss = new ArrayList<>();

                //looping and adding each word to list

                while(myReader.hasNext()) {
                    wordss.add(myReader.next());
                }
                myReader.close();

                wordss = collectAll(wordss, new ShortWordFilter());

                System.out.println("Words from the text file: ");
                for (Object var : wordss) {
                    System.out.println(var);
                }

            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}