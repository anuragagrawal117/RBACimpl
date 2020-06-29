package exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String type, String identity){
        super(type + " with identity " + identity +  " not found!");
    }

}
