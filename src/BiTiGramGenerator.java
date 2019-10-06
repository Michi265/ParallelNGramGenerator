public class BiTiGramGenerator {

        public static void main(String[] args) throws InterruptedException {

            long starTime = System.nanoTime();

            String input = args[0];
            String output = args[1];

            Master master = new Master(input, output);
            //TODO fare tanti thread quanti sono i core del processore
            Worker[] workers = new Worker[8];

            for (int i = 0; i < 8; i++) {
                workers[i] = new Worker(master,input);
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