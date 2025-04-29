public class CargoPlane extends Aircraft {
    public CargoPlane(String id) {
        super(id);
    }

    @Override
    public void receive(String message) {
        System.out.println("CargoPlane " + id + " received: " + message);
    }
}