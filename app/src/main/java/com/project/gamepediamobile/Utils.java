package com.project.gamepediamobile;

import java.util.List;

public class Utils {
    public static String joinListToString(List<?> list) {
        StringBuilder sb = new StringBuilder();
        for (Object item : list) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(item.toString());
        }
        return sb.toString();
    }
}
