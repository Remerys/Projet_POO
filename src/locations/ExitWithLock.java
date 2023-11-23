package locations;

public abstract class ExitWithLock extends Exit {
    private boolean is_locked ;

    public ExitWithLock(Location newLoc, String description) {
        super(newLoc, description);
        this.is_locked = true;
    }

    public void unlock() throws Exception {
        this.is_locked = false;
    }

    @Override
    public Location exit() throws Exception {
        if (this.is_locked) {
            return super.exit();
        } else {
            throw new Exception("exit : The door is locked !");
        }
    }
}
