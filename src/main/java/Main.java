import graph.Vertex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutionException;

import graph.Graph;


public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Graph g = new Graph(5);
        g.addVertix(1, "testtopic1", "testgroup1");
        g.addVertix(2, "testtopic2", "testgroup2");
        g.addVertix(3, "testtopic3", "testgroup3");

        g.addEdge(1, 2);
        g.addEdge(2, 3);


        Stack<Vertex> ts = g.dfs(g.getVertex(1)); // 1 2 3 4 5
        List<Vertex> topoOrder = new ArrayList<>();
       // System.out.println(ts);
        while(!ts.isEmpty()) {
            topoOrder.add(ts.pop());
        }
        System.out.println(topoOrder);
       initialize(topoOrder);
    }




     private static void initialize(List<Vertex> topoOrder) throws InterruptedException, ExecutionException {
         //System.out.println(topoOrder);
         for (int i = 0; i <= 4; i++) {
            Scale1.topicpartitions1.add(new Partition(i, 0, 0));
            Scale2.topicpartitions2.add(new Partition(i, 0, 0));
            Scale5.topicpartitions5.add(new Partition(i, 0, 0));
        }
     /*   log.info("Warming for 3 minutes seconds.");
        Thread.sleep(180000);*/
        log.info("Warming for 2 minutes seconds.");
        Thread.sleep(60*2*1000);
        while (true) {
            log.info("Querying Prometheus");
            Main.QueryingPrometheus();
            log.info("Sleeping for 5 seconds");
            log.info("******************************************");
            log.info("******************************************");
            Thread.sleep(5000);
        }
    }


    static void QueryingPrometheus() throws ExecutionException, InterruptedException {
        ArrivalRates.arrivalRateTopic1();
       //ArrivalRates.arrivalRateTopic2();
        //ArrivalRates.arrivalRateTopic3();
         //ArrivalRates.arrivalRateTopic4();
        //ArrivalRates.arrivalRateTopic5();
        //arrivalRateTopic5Avg();

        if (Duration.between(Scale1.lastUpScaleDecision, Instant.now()).getSeconds() > 15) {
            //QueryRate.queryConsumerGroup();
            Scale1.scaleAsPerBinPack(Scale1.size);
        }
        if (Duration.between(Scale2.lastUpScaleDecision, Instant.now()).getSeconds() > 15) {
            //QueryRate.queryConsumerGroup();
            Scale2.scaleAsPerBinPack(Scale2.size);
        }
        if (Duration.between(Scale5.lastUpScaleDecision, Instant.now()).getSeconds() > 15) {
           // QueryRate.queryConsumerGroup();
            Scale5.scaleAsPerBinPack(Scale5.size);
        }
    }






}
