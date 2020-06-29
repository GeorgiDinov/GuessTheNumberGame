package com.georgidinov.service;

import com.georgidinov.Game;
import com.georgidinov.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    //== fields ==
    private final Game game;
    private final MessageGenerator messageGenerator;

    //== constructors ==
    @Autowired
    public GameServiceImpl(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }//end of constructor

    //== init ==
    @PostConstruct
    public void init() {
        log.info("init method in GameServiceImpl called game = {}, messageGenerator = {}",
                this.game, this.messageGenerator);
        log.info("number = {}", this.game.getNumber());
        log.info("mainMessage = {}", this.messageGenerator.getMainMessage());
    }//end of method init

    //== public methods ==
    @Override
    public boolean isGameOver() {
        return this.game.isGameLost() || this.game.isGameWon();
    }//end of method isGameOver

    @Override
    public String getMainMessage() {
        return this.messageGenerator.getMainMessage();
    }//end of method getMainMessage

    @Override
    public String getResultMessage() {
        return this.messageGenerator.getResultMessage();
    }//end of method getResultMessage

    @Override
    public void checkGuess(int guess) {
        this.game.setGuess(guess);
        this.game.check();
    }//end of method checkGuess

    @Override
    public void reset() {
        this.game.reset();
    }//end of method reset

}//end of class GameServiceImpl
