package com.example.framework;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import com.example.framework.annotation.GetMapping;

public class RouteResolver {

    private Map<String, Method> routes = new HashMap<>();

    // Scanner le package pour récupérer les méthodes annotées
    public void scanPackage(String packageName) throws Exception {
        // Pour l'exemple, on fait un scan “statique”
        Class<?> cls = Class.forName("com.example.demo.HelloController");
        for(Method m : cls.getDeclaredMethods()){
            if(m.isAnnotationPresent(GetMapping.class)){
                GetMapping map = m.getAnnotation(GetMapping.class);
                routes.put(map.value(), m);
            }
        }
    }

    public Object resolve(String path) throws Exception {
        Method method = routes.get(path);
        if(method == null) return "Page non trouvée";

        Object controller = method.getDeclaringClass().getDeclaredConstructor().newInstance();
        return method.invoke(controller);
    }
}
