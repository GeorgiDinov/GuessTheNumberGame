package com.georgidinov;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class NumberGeneratorImpl implements NumberGenerator {

    //== fields ==
    private final Random random = new Random();

    @Getter
    private final int maxNumber;

    @Getter
    private final int minNumber;

    //== constructors ==
    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }//end of constructor -> ctr DI

    //== public methods ==
    @Override
    public int next() {
        //example: min = 5 max = 20 --> max - min = 15 --> range 0-15 + min -> 5-20
        return this.random.nextInt(this.maxNumber - this.minNumber) + this.minNumber;
    }//end of method next

}//end of class NumberGeneratorImpl
