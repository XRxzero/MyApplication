package com.example.test.model;

import java.util.Random;

public class Card {
    public int acardstack[]=new int[52];
    public int cardUi[]=new int [52];
    Card() {
        // 给52张牌赋上值
        for (int x = 1; x <= 52; x++) {
            acardstack[x-1] = x;
            cardUi[x-1]=CardUi.CardUi[x-1];
        }
        for (int x = 0; x < 52; x++)// 用随机数来洗牌
        {

                Random rand = new Random();
                int m;
                int randnum = rand.nextInt(51);
                m = acardstack[x];
                acardstack[x] = acardstack[randnum];
                acardstack[randnum] = m;

                m=cardUi[x];
                cardUi[x]=cardUi[randnum];
                cardUi[randnum]=m;



        }
    }

}

