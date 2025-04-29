import java.util.Random;
import java.util.concurrent.*;

public class SimulationDriver {
    public static void main(String[] args) {
        ControlTower tower = new ControlTower();
        Random random = new Random();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Aircraft[] aircraft = new Aircraft[10];
        for (int i = 0; i < 10; i++) {
            int type = random.nextInt(3);
            switch (type) {
                case 0 -> aircraft[i] = new PassengerPlane("P" + i);
                case 1 -> aircraft[i] = new CargoPlane("C" + i);
                case 2 -> aircraft[i] = new Helicopter("H" + i);
            }
            tower.registerAircraft(aircraft[i]);
        }

        executor.scheduleAtFixedRate(() -> {
            int action = random.nextInt(100);

            if (action < 10) {
                Aircraft maydayPlane = aircraft[random.nextInt(aircraft.length)];
                System.out.println("\n>> " + maydayPlane.getId() + " sends MAYDAY!");
                maydayPlane.send("MAYDAY", tower);
            } else {
                Aircraft a = aircraft[random.nextInt(aircraft.length)];
                if (!tower.requestRunway(a)) {
                    System.out.println(">> " + a.getId() + " waiting...");
                } else {
                    System.out.println(">> " + a.getId() + " using runway.");
                }
                tower.releaseRunway();
            }

        }, 0, 2, TimeUnit.SECONDS);

        executor.schedule(() -> {
            executor.shutdown();
            System.out.println("\nSimulation finished.");
        }, 30, TimeUnit.SECONDS);
    }
}