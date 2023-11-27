package locations;

public class ExitWithCode extends ExitWithLock {
    private final String CODE;
    private final String CODE_MESSAGE_OK = "The exit has been unlocked.";
    private final String CODE_MESSAGE_WRONG = "The entered code is incorrect.";
    private final String CODE_UNLOCK_UNAUTHORIZED = "The exit is not open.";

    private boolean has_code_been_entered = false;

    public ExitWithCode(Location newLoc, String CODE, String description) {
        super(newLoc, description);
        this.CODE = CODE;
    }

    public String enterCode(String code) {
        if (code == this.CODE) {
            this.has_code_been_entered = true;
            return this.CODE_MESSAGE_OK;
        } else {
            return this.CODE_MESSAGE_WRONG;
        }
    }

    @Override
    public void unlock() throws Exception{
        if (this.has_code_been_entered)
            super.unlock();
        else
            throw new Exception(this.CODE_UNLOCK_UNAUTHORIZED);
    }
}
