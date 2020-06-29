package exception;

public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException(String type, String identity){
        super(type + " with identity " + identity +  " already exists!");
    }
}
