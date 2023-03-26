package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.user.User;
import com.studentbox.api.models.user.UserModel;

import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class UserMapper {
    private UserMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }
    public static List<UserModel> mapAllToModel(List<User> users){
        return users
                .stream()
                .map(UserMapper::mapToModel)
                .toList();
    }
    public static UserModel mapToModel(User user){
        return new UserModel(user);
    }
}
