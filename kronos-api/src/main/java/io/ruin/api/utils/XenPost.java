package io.ruin.api.utils;

import java.util.Map;

public class XenPost {


    private static final String URL = "http://localhost/integration/index.php";

    private static final String AUTH = "VO2nMrpVKOaPPWjAyq24Un3a1LKLcvki";

    public static String post(String file, Map<Object, Object> map) {
        map.put("auth", AUTH);
        map.put("file", file);
        return PostWorker.postArray(URL, map);
    }

}
