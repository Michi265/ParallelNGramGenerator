import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;


public class Worker extends Thread{

    private Master master;

    public Worker(Master master) {

        this.master = master;
    }


    public void run() {

        while (master.isBookavaible()) {
            try {
                Integer bookId = master.dailibro();
                File file = new File("/home/michela/IdeaProjects/ParallelNGramGenerator/src/books/www.gutenberg.org/robot/"+ bookId);
                //File file = new File("/home/michela/IdeaProjects/ParallelNGramGenerator/src/"+ bookId.toString());

                BufferedReader br = new BufferedReader(new FileReader(file));
                String st;


                File fileOut = new File("/home/michela/IdeaProjects/ParallelNGramGenerator/src/output");

                FileWriter fw = new FileWriter(fileOut);
                BufferedWriter bw = new BufferedWriter(fw);

                while ((st = br.readLine()) != null) {

                        List<String> ngrams = new ArrayList<String>();
                        for (int i = 0; i < st.length() - 2 + 1; i++) {
                            ngrams.add(st.substring(i, i + 2));
                            //System.out.println(st.substring(i, i + 2));
                            bw.write(st.substring(i, i + 2));
                            bw.newLine();
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
