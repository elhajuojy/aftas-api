package ma.aftas.aflasclubapi.exception.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
public class BadRequestException extends RuntimeException{


    String message ;
    Map<String , String> details ;


    public BadRequestException(String message){
        super(message);
        this.message = message;
    }

    public BadRequestException(String message, Map<String , String > details ){
        super(message);
        this.message = message;
        this.details = details;

    }
}
