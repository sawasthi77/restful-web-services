package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    //we need to store user details in the database
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;
    static {
        users.add(new User(++usersCount, "Sam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Walter", LocalDate.now().minusYears(50)));
        users.add(new User(++usersCount, "Joe", LocalDate.now().minusYears(25)));
    }

    public List<User> findAll(){
        return users;
    }

    public User saveUser(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
