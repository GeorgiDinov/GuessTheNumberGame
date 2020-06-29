package com.georgidinov.controller;

import com.georgidinov.service.GameService;
import com.georgidinov.util.AttributeNames;
import com.georgidinov.util.GameMappings;
import com.georgidinov.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class GameController {

    //== fields ==
    private final GameService gameService;

    //== constructors ==
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }//end of constructor

    //== request methods ==
    @GetMapping(GameMappings.PLAY)
    public String play(Model model) {
        model.addAttribute(AttributeNames.MAIN_MESSAGE, this.gameService.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE, this.gameService.getResultMessage());
        log.info("model = {}", model);
        if (gameService.isGameOver()) {
            return ViewNames.GAME_OVER;
        }
        return ViewNames.PLAY;
    }//end of method play

    @PostMapping(GameMappings.PLAY)
    public String processMessage(@RequestParam int guess) {
        log.info("guess = {}", guess);
        this.gameService.checkGuess(guess);
        return GameMappings.REDIRECT_PLAY;
    }//end of method processMessage

    @GetMapping(GameMappings.RESTART)
    public String restart() {
        this.gameService.reset();
        return GameMappings.REDIRECT_PLAY;
    }//end of method restart

}//end of class GameController
