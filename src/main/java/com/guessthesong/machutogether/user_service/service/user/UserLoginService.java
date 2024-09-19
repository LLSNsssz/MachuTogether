package com.guessthesong.machutogether.user_service.service.user;

public interface UserLoginService {

    boolean authenticate(String username, String password);
}
