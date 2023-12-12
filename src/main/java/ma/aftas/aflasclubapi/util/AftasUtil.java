package ma.aftas.aflasclubapi.util;

import ma.aftas.aflasclubapi.web.service.impl.PodiumServiceImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

public class AftasUtil {
    public record PagePathQueryChecker(int page, int size) {
    }


    public static PagePathQueryChecker getPagePathQueryChecker(Map<String, String> queryParams) {
        PageRequest pageRequest ;
        int page = 0;
        int size = 10;
        if(queryParams.containsKey("page")){
            page = Integer.parseInt(queryParams.get("page"));
        }
        if(queryParams.containsKey("size")){

            size = Integer.parseInt(queryParams.get("size"));
        }
        PagePathQueryChecker pagePathQueryChecker = new PagePathQueryChecker(page, size);
        return pagePathQueryChecker;
    }


}
