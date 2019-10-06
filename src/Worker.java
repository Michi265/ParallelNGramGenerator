import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Worker extends Thread{

    private Master master;
    private String input;

    public Worker(Master master,String input)
    {
        this.master = master;
        this.input = input;
    }

    public void run() {
        while (master.isBookavaible()) {
            try{
                Integer bookId = master.giveBookId();
                File file = new File(input + "/"+ bookId);
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st;
                ArrayList<String> ngrams2 = new ArrayList<String>();
                ArrayList<String> ngrams3 = new ArrayList<String>();

                while ((st = br.readLine()) != null) {
                    st = st.replace(" ", "");
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
