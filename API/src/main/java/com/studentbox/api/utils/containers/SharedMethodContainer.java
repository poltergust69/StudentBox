package com.studentbox.api.utils.containers;

import com.studentbox.api.exception.NotAuthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public class SharedMethodContainer {
    public static final Logger logger = LoggerFactory.getLogger(SharedMethodContainer.class);

    public static String getCurrentAuthorizedUser(){
        try{
            return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw new NotAuthenticatedException();
        }
    }
}
