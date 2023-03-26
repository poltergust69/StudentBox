package com.studentbox.api.utils.containers;

import static com.studentbox.api.utils.containers.ConstantsContainer.ALL_CHARACTERS;
import static com.studentbox.api.utils.containers.ConstantsContainer.RANDOM_GENERATOR;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class SharedMethodContainer {
    private SharedMethodContainer() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static String generateRandomSecretKey(){
        StringBuilder builder = new StringBuilder();

        for(int i=0; i<64; i++){
            builder.append(
                    ALL_CHARACTERS.charAt(
                            RANDOM_GENERATOR.nextInt(0,ALL_CHARACTERS.length())
                    )
            );
        }

        return builder.toString();
    }

    public static boolean isUserAuthenticated(){
        return SecurityContextHolder.getContext().getAuthentication() instanceof CustomAuthentication;
    }
}
