package com.mytests.spring.springmongoreactive;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;


public interface UserReactiveRepo extends ReactiveCrudRepository<User, BigInteger> {

    // gutter is present for all not-annotated methods but fails to run
    Flux<User> findByAge(int age);

    // method name is not parsed - error on gutter icon pressing
    Flux<User> findByNameMatchesRegex(String name);

    Flux<User> searchByNameAndAge(Mono<String> name, Mono<Integer> age);

    Mono<Boolean> existsByAge(int age);

    Mono<User> findTop1ByAgeLessThan(Mono<Integer> age);

    Mono<String> findFirstNameByAgeGreaterThan(int age);

    Flux<User> getByAgeIn(Flux<Integer> age);

    // gutter is present due to the conventional name
    @Query("{ 'name' : ?0 }")
    Flux<User> findByName(String name);

    // gutter is absent
    @Query("{ 'age' : ?0 }")
    Flux<User> customQuery(int age);

}
