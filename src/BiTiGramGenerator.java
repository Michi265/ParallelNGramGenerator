public class BiTiGramGenerator {

        public static void main(String[] args) throws InterruptedException {

            long starTime = System.nanoTime();

            String input = args[0];
            String output = args[1];
            int a = Runtime.getRuntime().availableProcessors();
            //System.out.println(a);

            Master master = new Master(input, output);
            Worker[] workers = new Worker[a];

            for (int i = 0; i <a; i++) {
                workers[i] = new Worker(master,input,1);
                workers[i].start();
            }
            for (int i = 0; i <a; i++) {
                workers[i].join();
                System.out.println("Tempo lavoro WORKER n. "+i+" : "+ workers[i].giveTime()/1000000000 + " s");
            }

            long endTime = System.nanoTime();
            long totalTime = (endTime - starTime);
            System.out.println("Tempo esecuzione programma: " + totalTime/1000000000 + " s");
            System.out.println("Tempo totale per scrivere bigrammi: " + master.bigramWriteTime/1000000000 + " s");
            System.out.println("Tempo totale per scrivere trigrammi: " + master.trigramWriteTime/1000000000 + " s");
        }
    }