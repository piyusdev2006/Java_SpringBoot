package com.piyus.FilterInSpringBoot.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

//@Component
public class ResponseBodyFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        // wrapping the httpResponse
        ContentCachingResponseWrapper wrappedResponse =
                new ContentCachingResponseWrapper(httpResponse);

        // ab controller iss wrappedResponse me original response likhega
        chain.doFilter(request, wrappedResponse);

        // jo bhi response controller aur dispatcherServlet build krke
        // httpServletResponse.getWriter().write() me likh rha hoga
        // wo stream form me hoga aur wo content byteArray me collect karenge

        byte[] originalResponseBody = wrappedResponse.getContentAsByteArray();
        String originalBody = new String(originalResponseBody);
        String modifiedBod = """
                {
                    "originalResponse" : %s,
                    "appName" : "Student Daily Routine Tracker App"
                }
                """.formatted(originalBody);

        // purane values ko reset kar dega v
        wrappedResponse.resetBuffer();

        // need to write in our wrapper
        wrappedResponse.getWriter().write(modifiedBod);

        // wrapped response ko actual response me copy kar dega
        wrappedResponse.copyBodyToResponse();

        // abhi server se  ye response dilwana hai{
        //                    "originalResponse" : %s,
        //                    "appName" : "Student Daily Routine Tracker App"
        //                }
        // ek nye dto se return karayenge

    }
}


// at last the string looks like : this is new way of java 15 :- text block
// in which """ """ we have to write inside it and %s becomes variable
// aur us variable ko bharne ke liye likhenge .formatted(<fillwithwhatuwant>)
//{
//    "originalResponse": {
//        "name" : "Naveen",
//        "message" : "Hi",
//      },
//     "appName" : "Student Daily Routine Tracker App"
//}