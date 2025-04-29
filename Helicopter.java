public class Helicopter extends Aircraft {
    public Helicopter(String id) {
        super(id);
    }

    @Override
    public void receive(String message) {
        System.out.println("Helicopter " + id + " received: " + message);
    }
}