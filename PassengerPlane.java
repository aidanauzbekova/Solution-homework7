public class PassengerPlane extends Aircraft {
    public PassengerPlane(String id) {
        super(id);
    }

    @Override
    public void receive(String message) {
        System.out.println("PassengerPlane " + id + " received: " + message);
    }
}