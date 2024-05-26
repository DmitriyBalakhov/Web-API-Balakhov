package org.example.entityfactory;

import org.example.entity.User;
import org.example.entity.UserLoginData;
import org.example.entity.UsersProfileData;
import org.example.entityenum.UserEntity;

public class UserFactory {

public static User createUser() {
return new User(
        UserEntity.NAME.getFieldName(),
        UserEntity.LAST_NAME.getFieldName(),
        UserEntity.EMAIL.getFieldName(),
        UserEntity.PASSWORD.getFieldName(),
        UserEntity.REPEAT_PASSWORD.getFieldName());
}

    public static UserLoginData loginUser() {
        return new UserLoginData(
            "test@test.com",
            "Qwerty12345",
            "false");
    }

    public static UsersProfileData updateUserProfile() {
        return new UsersProfileData(
                "Jack's photo.jpg",
                "Jack",
                "Black",
                "1990-01-01T01:01:01.000Z",
                "USA"
                );
    }



}
