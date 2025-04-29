import java.util.*;

public class ControlTower implements TowerMediator {
    private final Queue<Aircraft> landingQueue = new LinkedList<>();
    private final Queue<Aircraft> takeoffQueue = new LinkedList<>();
    private final List<Aircraft> allAircraft = new ArrayList<>();
    private boolean runwayAvailable = true;
    private boolean emergencyActive = false;

    public void registerAircraft(Aircraft aircraft) {
        allAircraft.add(aircraft);
    }

    @Override
    public void broadcast(String message, Aircraft sender) {
        System.out.println("\n[Tower] Broadcasting: '" + message + "' from " + sender.getId());
        if (message.equalsIgnoreCase("MAYDAY")) {
            handleEmergency(sender);
        } else {
            for (Aircraft aircraft : allAircraft) {
                if (!aircraft.equals(sender)) {
                    aircraft.receive(message);
                }
            }
        }
    }

    @Override
    public boolean requestRunway(Aircraft aircraft) {
        if (emergencyActive && !aircraft.equals(landingQueue.peek())) {
            System.out.println("[Tower] " + aircraft.getId() + " must hold: Emergency active.");
            return false;
        }

        if (runwayAvailable) {
            runwayAvailable = false;
            System.out.println("[Tower] " + aircraft.getId() + " is cleared to use runway.");
            return true;
        } else {
            System.out.println("[Tower] " + aircraft.getId() + " queued for runway.");
            landingQueue.offer(aircraft);
            return false;
        }
    }

    public void releaseRunway() {
        runwayAvailable = true;
        if (!landingQueue.isEmpty()) {
            Aircraft next = landingQueue.poll();
            System.out.println("[Tower] Granting runway to " + next.getId());
            requestRunway(next);
        }
    }

    private void handleEmergency(Aircraft emergencyAircraft) {
        System.out.println("[Tower] EMERGENCY! Clearing runway for " + emergencyAircraft.getId());
        emergencyActive = true;
        landingQueue.clear();
        landingQueue.offer(emergencyAircraft);
        for (Aircraft aircraft : allAircraft) {
            if (!aircraft.equals(emergencyAircraft)) {
                aircraft.receive("HOLD POSITION - EMERGENCY IN PROGRESS");
            }
        }
        runwayAvailable = true;
    }

    public void resolveEmergency() {
        emergencyActive = false;
        System.out.println("[Tower] Emergency resolved.");
    }
}