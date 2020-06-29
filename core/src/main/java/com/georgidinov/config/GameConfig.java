package com.georgidinov.config;

import com.georgidinov.GuessCount;
import com.georgidinov.MaxNumber;
import com.georgidinov.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.georgidinov")
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    //== fields ==
    @Value("${game.maxNumber:20}")
    private int maxNumber;

    @Value("${game.guessCount:5}")
    private int guessCount;

    @Value("${game.minNumber:5}")
    private int minNumber;

    // == bean methods ==
    @Bean
    @MaxNumber
    public int maxNumber() {
        return this.maxNumber;
    }//end of bean method maxNumber

    @Bean
    @GuessCount
    public int guessCount() {
        return this.guessCount;
    }

    @Bean
    @MinNumber
    public int minNumber() {
        return this.minNumber;
    }

}//end of class GameConfig
