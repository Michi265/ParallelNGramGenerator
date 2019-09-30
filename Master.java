import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.CheckedOutputStream;

public class Master {

    private long counter = 1;
    File fileOut2;
    FileWriter fw2;
    BufferedWriter bw2;

    File fileOut3;
    FileWriter fw3;
    BufferedWriter bw3;


    public Master(){
        try {
            Stream<Path> files = Files.list(Paths.get("/home/michela/IdeaProjects/ParallelNGramGenerator/books/www.gutenberg.org/robot")); //return the number of books
            //Stream<Path> files = Files.list(Paths.get("/home/michela/IdeaProjects/ParallelNGramGenerator/prova"));
            this.counter = files.count();

            this.fileOut2 = new File("/home/michela/IdeaProjects/ParallelNGramGenerator/output2");
            this.fw2 = new FileWriter(this.fileOut2);
            this.bw2 = new BufferedWriter(this.fw2);

            this.fileOut3 = new File("/home/michela/IdeaProjects/ParallelNGramGenerator/output3");
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
               //System.out.println("uscito");
         return true;
    }

    public synchronized Integer giveBookId()  {
        counter --;
        Integer bookId = (int) counter;
        //System.out.println(counter);
        return bookId;
    }

    //write 2-gram

    public synchronized void write2gram(ArrayList<String> ngrams2) throws Exception{

        //try {
            int j=1;
            for (int i=0; i < ngrams2.size();i++){
                bw2.write(ngrams2.get(i)+",");
                //bw.write(" ");
                if(j%60==0) {
                    bw2.newLine();
                }
                j++;
                //bw.write(System.getProperty( "line.separator" ));
            }


        //}catch (Exception e){}

    }

    //write 3-gram

    public synchronized void write3gram(ArrayList<String> ngrams3) throws Exception{

        //try {
            int j=1;
            for (int i=0; i < ngrams3.size();i++){
                this.bw3.write(ngrams3.get(i)+",");
                //bw.write(" ");
                if(j%40==0) {
                    bw3.newLine();
                }
                j++;
            }


      //  }catch (Exception e){}
    }


}
