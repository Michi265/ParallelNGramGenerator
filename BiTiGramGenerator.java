public class BiTiGramGenerator {


        public static void main(String[] args) throws InterruptedException {

            long starTime = System.nanoTime();
            Master master = new Master();
            //TODO fare tanti thread quanti sono i core del processore
            Worker[] workers = new Worker[8];

            for (int i = 0; i < 8; i++) {
                workers[i] = new Worker(master);
                workers[i].start();
            }
            for (int i = 0; i < 8; i++) {
                workers[i].join();
            }
            long endTime = System.nanoTime();
            long totalTime = endTime - starTime;
            System.out.println(totalTime);
        }

    }


