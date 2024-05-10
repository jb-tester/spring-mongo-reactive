package com.mytests.spring.springmongoreactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserReactiveService {

    @Autowired
    private UserReactiveRepo userReactiveRepo;

    public void display(){
        System.out.println("=== reactive repo: existsByAge(30) ===");
        Mono<Boolean> rez1 = userReactiveRepo.existsByAge(30);
        System.out.println(rez1.block());

        System.out.println("=== reactive repo: findByNameAndAge('name1',50) ===");
        Flux<User> users_flux1 = userReactiveRepo.searchByNameAndAge(Mono.just("name1"),Mono.just(50));
        displayFoundUsers(users_flux1);

        System.out.println("=== reactive repo: findFirstNameByAgeGreaterThan(20) ===");
        Mono<String> rez2 = userReactiveRepo.findFirstNameByAgeGreaterThan(20);
        System.out.println(rez2.block());

        System.out.println("=== reactive repo: findTop1ByAgeLessThan(60) ===");
        Mono<User> mrez = userReactiveRepo.findTop1ByAgeLessThan(Mono.just(60));
        System.out.println(mrez.block());


        System.out.println("=== reactive repo: @Query: findByName('name1') ===");
        Flux<User> users_flux2 = userReactiveRepo.findByName("name1");
        displayFoundUsers(users_flux2);
        System.out.println("=== reactive repo: @Query: customQuery('age') ===");
        Flux<User> users_flux3 = userReactiveRepo.customQuery(50);
        displayFoundUsers(users_flux3);

        System.out.println("=== flux users of (int age) ");
        int age = 30;
        Flux<User> users4 = userReactiveRepo.findByAge(age);
        displayFoundUsers(users4);

        System.out.println("=== flux users of (flux age) ");
        List<Integer> agesList = List.of(30, 50);
        Flux<User> users5 = userReactiveRepo.getByAgeIn(Flux.fromIterable(agesList));
        displayFoundUsers(users5);

        System.out.println("=== find by regexp: find");
        Flux<User> users6 =userReactiveRepo.findByNameMatchesRegex("name?");
        displayFoundUsers(users6);

        System.out.println("**********************************");
    }
    private void displayFoundUsers(Flux<User> users1) {
        for (User user : Objects.requireNonNull(users1.collectList().block())) {
            System.out.println(user.toString());
        }
    }
}
