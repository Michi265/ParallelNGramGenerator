import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Master {

    private long counter = 1;

    public Master(){
        try {
            Stream<Path> files = Files.list(Paths.get("/home/michela/IdeaProjects/ParallelNGramGenerator/src/books/www.gutenberg.org/robot"));
            this.counter = files.count();
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

    public synchronized Integer dailibro()  {
        counter --;
        Integer bookId = (int) counter;
        System.out.println(counter);
        return bookId;
    }


}
