package cn.psvmc.utils;

import java.util.List;

/**
 * Created by PSVMC on 16/7/4.
 */
public class ZJStringUtils {

    public static String joinStr(List<String> strList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strList.size(); i++) {
            if (i == strList.size() - 1) {
                sb.append(strList.get(i));
            } else {
                sb.append(strList.get(i));
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
