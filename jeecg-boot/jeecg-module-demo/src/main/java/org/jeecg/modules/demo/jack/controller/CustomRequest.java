package org.jeecg.modules.demo.jack.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

public class CustomRequest extends HttpServletRequestWrapper {

    public static String SUPER_QUERY_PARAMS = "";

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public CustomRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Map<String,String[]> getParameterMap() {
        Map<String, String[]> modifiableMap = new HashMap<>();
        modifiableMap.putAll(super.getParameterMap());
        modifiableMap.put("superQueryParams", new String[]{SUPER_QUERY_PARAMS});
        return modifiableMap;
    }
}
