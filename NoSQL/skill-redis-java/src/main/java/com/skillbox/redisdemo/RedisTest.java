package com.skillbox.redisdemo;

import org.redisson.Redisson;
import org.redisson.api.RDeque;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RedisTest {

    private static final int USERS = 20;
    private static final String QUEUE_KEY = "USER_QUEUE";

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        RDeque<String> userQueue = redisson.getDeque(QUEUE_KEY);

        userQueue.clear();
        for (int i = 1; i <= USERS; i++) {
            userQueue.add(String.valueOf(i));
        }

        Random random = new Random();
        int iterationsSinceLastPromotion = 0;
        Set<String> shownUsers = new HashSet<>();

        while (true) {
            String currentUser = userQueue.removeFirst();

            if (!shownUsers.contains(currentUser)) {
                System.out.println("На главной странице показываем пользователя " + currentUser);
                shownUsers.add(currentUser);
                iterationsSinceLastPromotion++;


                if (random.nextInt(10) == 0 || iterationsSinceLastPromotion >= 10) {
                    String payingUser = String.valueOf(random.nextInt(USERS) + 1);
                    System.out.println("> Пользователь " + payingUser + " оплатил платную услугу");

                    // Move the paying user to the front of the queue
                    userQueue.remove(payingUser);
                    userQueue.addFirst(payingUser);

                    iterationsSinceLastPromotion = 0;
                }

                Thread.sleep(1000);
            }

            userQueue.addLast(currentUser);

            if (shownUsers.size() == USERS) {
                shownUsers.clear();
                System.out.println("--- Новая итерация ---");
            }
        }
    }
}