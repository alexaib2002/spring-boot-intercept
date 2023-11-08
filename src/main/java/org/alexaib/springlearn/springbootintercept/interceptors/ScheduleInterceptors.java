package org.alexaib.springlearn.springbootintercept.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

public class ScheduleInterceptors implements HandlerInterceptor {

    @Value("${config.schedule.start}")
    private int scheduleStart;
    @Value("${config.schedule.end}")
    private int scheduleEnd;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (scheduleStart <= hour && hour <= scheduleEnd) {
            request.setAttribute("message",
                    new StringBuilder("Welcome to customer service!\n")
                            .append("Our opening hours are from ")
                            .append(scheduleStart)
                            .append(" to ")
                            .append(scheduleEnd)
                            .toString());
            return true;
        }
        response.sendRedirect(request.getContextPath().concat("/closed"));
        request.setAttribute("message",
                new StringBuilder("Sorry, we're currently offline...\n")
                        .append("Our opening hours are from")
                        .append(scheduleStart)
                        .append(" to ")
                        .append(scheduleEnd)
                        .toString());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String message = (String) request.getAttribute("message");
        if (modelAndView != null)
            modelAndView.addObject("message", message);
    }
}
