package com.example.test.model;

public class MainGame {


    public static int whoplay=0;
    public MainGame(Player me ,Player[] robot) {
        // 创建玩家和3个AI机器人

        Card cardstack = new Card();// 创建牌堆

        // 下面4个for循环给4个player发牌
        for (int x = 0; x < 13; x++) {
            me.setcard(x, cardstack.acardstack[x]);
            me.setHandcardUi(x, cardstack.cardUi[x]);
        }
        for (int x = 0; x < 13; x++) {
            robot[0].setcard(x, cardstack.acardstack[x+13]);
            robot[0].setHandcardUi(x, cardstack.cardUi[x+13]);
        }
        for (int x = 0; x < 13; x++) {
            robot[1].setcard(x, cardstack.acardstack[x+26]);
            robot[1].setHandcardUi(x, cardstack.cardUi[x+26]);
        }
        for (int x = 0; x < 13; x++) {
            robot[2].setcard(x, cardstack.acardstack[x+39]);
            robot[2].setHandcardUi(x, cardstack.cardUi[x+39]);
        }
        me.sortcard();//下面4行给每个人手牌排序
        robot[0].sortcard();
        robot[1].sortcard();
        robot[2].sortcard();

        // 用来记录是到谁的出牌回合，方片3开始
        for(int x=0;x<52;x++)//找方片3
        {
            if(cardstack.acardstack[x]==1)
                whoplay=x/13;
        }


        // 开始进入打牌循环（有人手牌为0则退出循环）

//游戏结束，玩家此时可以选择继续游戏或退出
    }
}
