package SQLExceptionPractice;

public class DatabaseException extends RuntimeException{

    DatabaseException(String message,Throwable cause){
        super(message , cause);
    }

}
