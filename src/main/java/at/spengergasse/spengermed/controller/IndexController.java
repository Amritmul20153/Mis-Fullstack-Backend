package at.spengergasse.spengermed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Set;

@Controller
public class IndexController {

    private final RequestMappingHandlerMapping handlerMapping;

    @Autowired
    public IndexController(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String showEndpoints() {
        StringBuilder html = new StringBuilder("<html><head><title>Available Endpoints</title></head><body>");
        html.append("<h1>Available Endpoints:</h1>");
        html.append("<ul>");

        this.handlerMapping.getHandlerMethods().forEach((key, value) -> {
            Set<String> patterns = key.getPatternsCondition() != null ? key.getPatternsCondition().getPatterns() : key.getPatternValues();
            patterns.forEach(pattern -> {
                // Assuming you want to list GET methods only; adjust as necessary
                if (key.getMethodsCondition().getMethods().toString().contains("GET")) {
                    html.append("<li><a href='").append(pattern).append("'>").append(pattern).append("</a>");
                }
            });
        });

        html.append("</ul></body></html>");
        return html.toString();
    }
}
