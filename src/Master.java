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
    private String input;
    private String output;
    File fileOut2;
    FileWriter fw2;
    BufferedWriter bw2;

    File fileOut3;
    FileWriter fw3;
    BufferedWriter bw3;


    public Master(String input, String output){
        this.input=input;
        this.output=output;
        try {
            Stream<Path> files = Files.list(Paths.get(input)); //return the number of books
            this.counter = files.count();

            this.fileOut2 = new File(output+"/output2Parallel");
            this.fw2 = new FileWriter(this.fileOut2);
            this.bw2 = new BufferedWriter(this.fw2);

            this.fileOut3 = new File(output+"/output3Parallel");
            this.fw3 = new FileWriter(this.fileOut3);
            this.bw3 = new BufferedWriter(this.fw3);
        }catch (Exception e){
           System.out.println("Database initialization error");
        }
    }

    public synchronized boolean isBookavaible() {
        if (counter == 1)
           return false;
        else
         return true;
    }

    public synchronized Integer[] giveBookId()  {

        Integer[] booksId=new Integer[10];
        if(counter > 3) {
            for(int k=0;k<3;k++) {
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

    //write 2-gram

    public synchronized void write2gram(ArrayList<String> ngrams2) throws Exception{

            int j=1;
            for (int i=0; i < ngrams2.size();i++){
                bw2.write(ngrams2.get(i)+",");
                if(j%60==0) {
                    bw2.newLine();
                }
                j++;
            }
    }

    //write 3-gram

    public synchronized void write3gram(ArrayList<String> ngrams3) throws Exception{

            int j=1;
            for (int i=0; i < ngrams3.size();i++){
                this.bw3.write(ngrams3.get(i)+",");
                if(j%40==0) {
                    bw3.newLine();
                }
                j++;
            }
    }
}