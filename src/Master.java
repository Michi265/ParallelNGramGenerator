import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Master {

    //private boolean bookavaible = false;
    private long counter = 1;
    private Integer bookId;



    public Master(){

        try {
            Stream<Path> files = Files.list(Paths.get("/home/michela/IdeaProjects/ParallelNGramGenerator/src/books/www.gutenberg.org/robot"));
            this.counter = files.count();

        }catch (Exception e){
           System.out.println("error");
        }

    }

    public synchronized boolean isBookavaible() {
        if (counter == 1)
           return false;
        else
            return true;

    }

    public synchronized Integer dailibro()  {
        counter --;
        bookId = (int) counter;
        //File file = new File("/home/michela/IdeaProjects/ParallelNGramGenerator/src/books/www.gutenberg.org/robot/"+ bookId);

        System.out.println(counter);

        return bookId;
    }


}
