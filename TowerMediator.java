public interface TowerMediator {
    void broadcast(String message, Aircraft sender);
    boolean requestRunway(Aircraft aircraft);
}