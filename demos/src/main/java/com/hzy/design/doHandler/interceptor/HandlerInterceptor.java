package com.hzy.design.doHandler.interceptor;

import com.hzy.design.doHandler.service.HandlerService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

public class HandlerInterceptor {
    private static Map<Integer, HandlerService> handlers = new HashMap<>();
    {
        //Spring 实现,获取所有实现类,然后便利赋给handlers
        // Map<String, PointHandlerService> noticeHandlerBeans = applicationContext.getBeansOfType(PointHandlerService.class);
        ServiceLoader<HandlerService> loader = ServiceLoader.load(HandlerService.class);
        Iterator<HandlerService> iterator = loader.iterator();
        while(iterator.hasNext()){
            HandlerService service = iterator.next();
            handlers.put(service.getType(),service);
        }
    }
    public String deal(Integer type){
        HandlerService service = handlers.get(type);
        String result = service.deal();
        System.out.println(result);
        return result;
    }
}
