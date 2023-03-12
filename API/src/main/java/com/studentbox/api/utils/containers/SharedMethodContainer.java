package com.studentbox.api.utils.containers;

import com.studentbox.api.common.CustomAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SharedMethodContainer {
    public static boolean isUserAuthenticated(){
        return SecurityContextHolder.getContext().getAuthentication() instanceof CustomAuthentication;
    }
}
