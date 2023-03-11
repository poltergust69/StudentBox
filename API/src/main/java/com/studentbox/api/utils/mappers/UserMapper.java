package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.user.User;
import com.studentbox.api.models.user.UserModel;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static List<UserModel> mapAllToModel(List<User> users){
        return users.stream().map(UserMapper::mapToModel).toList();
    }
    public static UserModel mapToModel(User user){
        return new UserModel(user);
    }
}
