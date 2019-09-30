public class BiTiGramGenerator {

    public static void main(String[] args) throws InterruptedException {
        Master master = new Master();
        Worker[] workers = new Worker[8];


        for (int i=0; i<8; i++) {
            workers[i] = new Worker(master);
            workers[i].start();
        }

    }


}
