package by.itacademy.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecurityManager {

    static Map<String,List<String>>MAP=new HashMap<String,List<String>>(){
        {
            put("ADMIN", Arrays.asList("delete","saveGroup","savePrescription","saveMedicine"));
        }
    };

    public static boolean isUserHasAccess (String role,String uri){
        return MAP.containsKey(role)&&MAP.get(role).stream().anyMatch(it->uri.contains(uri));
    }

}

