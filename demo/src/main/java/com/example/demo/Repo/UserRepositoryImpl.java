package com.example.demo.Repo;

import com.example.demo.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepositoryImpl implements UserRepository {
    private final List<User> users = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();
    @Override
    public User save(User user) {
        if(user.getId() == null ){
            user.setId(idCounter.incrementAndGet());
            users.add(user);
        }else {
            boolean found = false;
            for(int i = 0 ; i < users.size() ; i++){
                if(users.get(i).getId().equals(user.getId())){
                    users.set(i , user);
                    found = true;
                    break;
                }
            }
            if(!found){
                return null ;
            }

        }

        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId() != null && user.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }
}
