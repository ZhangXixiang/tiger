package com.zoo.tiger.me.util;

import org.slf4j.MDC;

/**
 * @author Tiger
 */
public class MdcUtils {


    public static void setTraceID(String traceID) {
        MDC.put("traceID", traceID);
    }

    public static String getTraceID() {
        return MDC.get("traceID");
    }

    public static void setBusinessName(String businessName) {
        MDC.put("businessName", businessName);
    }


    public static void setStartTime(long startTime) {
        MDC.put("startTime", String.valueOf(startTime));
    }

    public static String getStartTime() {
        return MDC.get("startTime");
    }

    public static void setCost(long cost) {
        MDC.put("cost", String.valueOf(cost));
    }

    public static void clear() {
        MDC.clear();
    }

}
