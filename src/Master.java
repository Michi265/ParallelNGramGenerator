import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Master{

    private long counter = 1;
    public long bigramWriteTime;
    public long trigramWriteTime;
    private BufferedWriter bw2;
    private BufferedWriter bw3;

    public Master(String input, String output){
        try {

            //Acquisizione numero file
            Stream<Path> files = Files.list(Paths.get(input));
            this.counter = files.count();

            //Inizializzazione buffer scrittura
            File fileOut2 = new File(output + "/output2Parallel");
            FileWriter fw2 = new FileWriter(fileOut2);
            this.bw2 = new BufferedWriter(fw2);
            File fileOut3 = new File(output + "/output3Parallel");
            FileWriter fw3 = new FileWriter(fileOut3);
            this.bw3 = new BufferedWriter(fw3);

        }catch (Exception e){
           System.out.println("Database initialization error");
        }
    }

    //Verifica se ci sono ancora file da processare
    public synchronized boolean isBookavaible() {
        if (counter == 1)
            return false;
        else
            return true;
    }

    //Restituisce id del libro/i da processare
    public synchronized Integer[] giveBookId(int p)  {

        Integer[] booksId=new Integer[p];
        if(counter > p) {
            for(int k=0;k<p;k++) {
                booksId[k] = new Integer((int)counter);
                counter--;
            }
        }
        else{
            while(counter!=0){
                booksId[(int)counter] = new Integer((int)counter);
                counter--;
            }
        }
        return booksId;
    }

    //Scrive 2-gram processati da 1 worker nel file di output
    public synchronized void write2gram(String ngrams2) throws Exception{
        long starTime = System.nanoTime();
        //int j=1;
        //for (String s : ngrams2) {
        this.bw2.write(ngrams2);
        //ogni 60 bi grammi va a capo
            //if (j % 60 == 0) {
        bw2.newLine();
            //}
            //j++;
        //}
        long endTime = System.nanoTime();
        long totalTime = (endTime - starTime);
        bigramWriteTime += totalTime;
    }

    //Scrivi 3-gram
    public synchronized void write3gram(String ngrams3) throws Exception{
        long starTime = System.nanoTime();
        //int j=1;
        //for (String s : ngrams3) {
        this.bw3.write(ngrams3);
            //ogni 40 bi grammi va a capo
            //if (j % 40 == 0) {
        bw3.newLine();
            //}
            //j++;
        //}
        long endTime = System.nanoTime();
        long totalTime = (endTime - starTime);
        trigramWriteTime += totalTime;
    }
}