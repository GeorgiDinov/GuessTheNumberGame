package com.georgidinov;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    //== constants ==
    private static final String MAIN_MESSAGE = "game.main.message";
    private static final String WIN_MESSAGE = "game.win";
    private static final String LOOSE_MESSAGE = "game.loose";
    private static final String INVALID_RANGE_MESSAGE = "game.invalid.range";
    private static final String FIRST_GUESS_MESSAGE = "game.first.guess";
    private static final String HIGHER_MESSAGE = "game.higher";
    private static final String LOWER_MESSAGE = "game.lower";
    private static final String REMAINING_GUESSES_LEFT = "game.remaining";

    //== fields ==
    private final Game game;
    private final MessageSource messageSource;

    //== constructors ==
    @Autowired
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }//end of constructor


    //== init ==
    @PostConstruct
    public void init() {
        log.info("game = {}", this.game);
    }//end of method report


    //== public methods ==
    @Override
    public String getMainMessage() {
        return this.getMessage(MAIN_MESSAGE, this.game.getSmallest(), this.game.getBiggest());
    }//end of method getMainMessage

    @Override
    public String getResultMessage() {
        if (this.game.isGameWon()) {
            return this.getMessage(WIN_MESSAGE, this.game.getNumber());
        } else if (this.game.isGameLost()) {
            return this.getMessage(LOOSE_MESSAGE, this.game.getNumber());
        } else if (!this.game.isValidNumberRange()) {
            return this.getMessage(INVALID_RANGE_MESSAGE);
        } else if (this.game.getRemainingGuesses() == this.game.getGuessCount()) {
            return this.getMessage(FIRST_GUESS_MESSAGE);
        } else {
            String direction = this.getMessage(LOWER_MESSAGE);
            if (this.game.getGuess() < this.game.getNumber()) {
                direction = this.getMessage(HIGHER_MESSAGE);
            }
            return this.getMessage(REMAINING_GUESSES_LEFT, direction, this.game.getRemainingGuesses());
        }
    }//end of method getResultMessage


    //==private methods ==
    private String getMessage(String code, Object... args) {
        //code takes the property name , args all the needed arguments
        //LocaleContextHolder.getLocale() - default locale
        // settings(currently in application.properties in the web module)
        return this.messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }//end of method getMessage

}//end of class MessageGeneratorImpl
