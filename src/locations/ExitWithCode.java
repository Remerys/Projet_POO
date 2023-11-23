package locations;

public class ExitWithCode extends ExitWithLock {
    private final String CODE;
    private final String CODE_MESSAGE_OK = "La sortie a été déverrouillée.";
    private final String CODE_MESSAGE_WRONG = "Le CODE entré est mauvais.";        
    private final String CODE_UNLOCK_UNAUTHORIZED = "La sortie n'est pas ouverte";
    
    private boolean has_code_been_entered = false;

    public ExitWithCode(Location newLoc, String CODE, String description) {
        super(newLoc, description);
        this.CODE = CODE;
    }

    public String enterCODE(String CODE) {
        if (CODE == this.CODE) {
            has_code_been_entered = true;
            return CODE_MESSAGE_OK;
        } else {
            return CODE_MESSAGE_WRONG;
        }
    }

    @Override
    public void unlock() throws Exception{
        if (has_code_been_entered) 
            super.unlock();
        else 
            throw new Exception(CODE_UNLOCK_UNAUTHORIZED);
    }
}
