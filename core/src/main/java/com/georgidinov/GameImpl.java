package com.georgidinov;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Getter
@Component
public class GameImpl implements Game {

    //== fields ==
    private int number;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    @Setter
    private int guess;

    @Getter(AccessLevel.NONE)
    private final NumberGenerator numberGenerator;

    private final int guessCount;

    //== constructors ==
    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }//end of constructors

    //== init ==
    @PostConstruct
    @Override
    public void reset() {
        this.smallest = this.numberGenerator.getMinNumber();
        this.guess = this.numberGenerator.getMinNumber();
        this.remainingGuesses = this.guessCount;
        this.biggest = numberGenerator.getMaxNumber();
        this.number = numberGenerator.next();
        log.debug("the number is {}", this.number);
    }//end of method reset

    @PreDestroy
    public void preDestroy() {
        log.info("in Game preDestroy()");
    }//end of method preDestroy

    //== public methods ==
    @Override
    public void check() {
        this.checkValidNumberRange();
        if (this.validNumberRange) {
            if (this.guess > this.number) {
                this.biggest = this.guess - 1;
            }
            if (this.guess < this.number) {
                this.smallest = this.guess + 1;
            }
        }
        this.remainingGuesses--;
    }//end of method check

    @Override
    public boolean isGameWon() {
        return guess == number;
    }//end of method isGameWon

    @Override
    public boolean isGameLost() {
        return !this.isGameWon() && this.remainingGuesses <= 0;
    }//end of method isGameLost

    //== private methods ==
    private void checkValidNumberRange() {
        this.validNumberRange =
                (this.guess >= this.smallest) && (this.guess <= this.biggest);
    }//end of method checkValidNumberRange

}//end of class GameImpl
