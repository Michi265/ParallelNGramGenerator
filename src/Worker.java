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
            try{
                Integer bookId = master.giveBookId();
                File file = new File("/home/caste/Scrivania/ParallelNGramGenerator/books/www.gutenberg.org/robot/"+ bookId);
                //File file = new File("/home/michela/IdeaProjects/ParallelNGramGenerator/prova/"+ bookId);
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st;
                ArrayList<String> ngrams2 = new ArrayList<String>();
                ArrayList<String> ngrams3 = new ArrayList<String>();

                while ((st = br.readLine()) != null) {
                    //create 2-gram
                        for (int i = 0; i < st.length() - 2 + 1; i++) {
                            ngrams2.add(st.substring(i, i + 2));
                    }
                    //create 3-gram
                    for (int i = 0; i < st.length() - 3 + 1; i++) {
                        ngrams3.add(st.substring(i, i + 3));
                    }
                }
                //pass n-gram to write on two different file
                master.write2gram(ngrams2);
                master.write3gram(ngrams3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
