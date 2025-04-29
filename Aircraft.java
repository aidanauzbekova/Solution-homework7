public abstract class Aircraft {
    protected String id;

    public Aircraft(String id) {
        this.id = id;
    }

    public abstract void receive(String message);

    public void send(String message, TowerMediator mediator) {
        mediator.broadcast(message, this);
    }

    public String getId() {
        return id;
    }
}