package ma.aftas.aflasclubapi.util;

import ma.aftas.aflasclubapi.web.service.impl.PodiumServiceImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

public class AftasUtil {
    public record PagePathQueryChecker(int page, int size) {
    }


    public static PagePathQueryChecker getPagePathQueryChecker(Map<String, String> queryParams) {
        PageRequest pageRequest ;
        int page;
        int size;
        if(queryParams.containsKey("page") && queryParams.containsKey("size")){
            page = Integer.parseInt(queryParams.get("page"));
            size = Integer.parseInt(queryParams.get("size"));
        }else{
            page = 0;
            size = 10;
        }
        PagePathQueryChecker pagePathQueryChecker = new PagePathQueryChecker(page, size);
        return pagePathQueryChecker;
    }


}
