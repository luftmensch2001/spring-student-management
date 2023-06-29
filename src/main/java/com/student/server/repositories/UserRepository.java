package com.student.server.repositories;

import com.student.server.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
public class UserRepository {
    private static final String HASH_KEY = "User";
    @Autowired
    private RedisTemplate template;
    public User findByUserName(String userName) {
        List<User> allUsers = template.opsForHash().values(HASH_KEY);
        for (int i=0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            if (user.getUserName().equals(userName)) return user;
        }
        return null;
    }

    public User save(User newUser) {
//        Find current max id
        List<User> listUser = template.opsForHash().values(HASH_KEY);
        int maxId = 0;
        if (listUser.size() > 0)
            maxId = listUser.stream().max(Comparator.comparing(User::getUserId)).get().getUserId();
//        Increase id for new user
        newUser.setUserId(maxId + 1);
//        Save to cache
        template.opsForHash().put(HASH_KEY, maxId + 1, newUser);
        return newUser;
    }
}
