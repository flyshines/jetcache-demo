package com.us.example.util;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Administrator
 */
public class CommonUtil {

    /**
     * 从request中获得参数Map，并返回可读的Map.
     *
     * @param request the request
     * @return the parameter map
     */
    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map<String, String[]> properties = request.getParameterMap();
        //返回值Map
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Set<String> keySet = properties.keySet();
        for (String key : keySet) {
            String[] values = properties.get(key);
            String value = "";
            if (values != null && (values.length == 1 && !StringUtils.isEmpty(values[0])) ? true : false) {
                for (int i = 0; i < values.length; i++) {
                    if (values[i] != null && !"".equals(values[i])) {
                        value += values[i] + ",";
                    }
                }
                if (value != null && !"".equals(value)) {
                    value = value.substring(0, value.length() - 1);
                }
                // 关键字特殊查询字符转义
                if (key.equals("keywords")) {
                    value = value.replace("_", "\\_").replace("%", "\\%");
                }
                returnMap.put(key, value);
            }
        }
        return returnMap;
    }

}
