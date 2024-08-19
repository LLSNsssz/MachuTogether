package com.guessthesong.machutogether.service.user;

public interface UserLoginService {

    boolean authenticate(String username, String password);
}
