package com.example.test.model;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.test.R;

public class ShowCard {
    public ShowCard(){

    }


    public static ImageButton[] cardTmp=new ImageButton[13];
    public static int  playerShowCard(boolean[] isSelect, ImageView[] shareCard, ImageButton[] card,int[] handCard){
        int numNow,numCardSelect;
        numNow=0;
        numCardSelect=0;
        int cardLast=0;//剩下的牌
        int cardReplaceNum;//记录重新排列位置
        int cardLastTmp;//记录剩下需要重排的牌数
        int[] cardVisibilityTmp=new int[13];
        int[] handcardchange=new int[13];//手牌也要重新排序
        int   hancardtmp=0;
        Drawable[] cardDrawableTmp=new Drawable[13];
        //ImageButton[] cardTmp=new ImageButton[13];//记录一下原本的牌
        for(int i=0;i<5;i++){//每一次出牌应该都影藏一次牌，不然会很奇怪
            shareCard[i].setVisibility(View.INVISIBLE);
        }
        //提前记录牌数
        for(int i=0;i<13;i++) {
            if(isSelect[i]==true){
                numCardSelect++;
            }
        }
        if(numCardSelect>5)return 1;//打出失败
        for(int i=0;i<13;i++){
            if(isSelect[i]==true) {//for循环来判断每一张牌是否会被选中，选中的就显示在桌子上（可能有更好的算法
                shareCard[(5-numCardSelect)/2+numNow].setImageDrawable(card[i].getDrawable());//这里我把原来的numNow改成了(5-numCardSelect)/2+numNow
                card[i].setVisibility(View.INVISIBLE);
                shareCard[(5-numCardSelect)/2+numNow].setVisibility(View.VISIBLE);
                numNow++;
                //出了的牌，要消失，而且我已经把他们影藏掉了，所以必须得将他们的select状态变为false
                isSelect[i]=false;
            }
            else//看看没选中的牌还有多少是没隐藏的
            {
                if (card[i].getVisibility()==View.VISIBLE)
            {
                cardLast++;
            }
            }
        }

        //记录原来的牌的可见性，图片
        for(int j=0;j<13;j++)
        {
            cardVisibilityTmp[j]=card[j].getVisibility();
            cardDrawableTmp[j]=card[j].getDrawable();
            handcardchange[j]=handCard[j];
        }
        cardReplaceNum=(13-cardLast)/2;
        cardLastTmp=cardLast;
        for(int j=0;j<13;j++)
        {
            if (cardVisibilityTmp[j] == View.VISIBLE) {
                cardLastTmp--;
                card[cardReplaceNum].setImageDrawable(cardDrawableTmp[j]);
                handCard[cardReplaceNum]=handcardchange[j];
                cardReplaceNum++;

            }
            if (cardLastTmp == 0) break;
        }
        //因为牌的位置移动了，必须更新各状态,并且得更新不同的图片
        for(int j=0;j<(13-cardLast)/2;j++)
        {
            card[j].setVisibility(View.INVISIBLE);
        }
        for(int j=(13-cardLast)/2;j<(13-cardLast)/2+cardLast;j++)
        {
            card[j].setVisibility(View.VISIBLE);
        }
        for(int j=(13-cardLast)/2+cardLast;j<13;j++)
        {
            card[j].setVisibility(View.INVISIBLE);
        }
        for(int j=0;j<13;j++)
        {
            card[j].setTranslationY(0);
            isSelect[j]=false;
        }
        return numCardSelect;
    }
    public static void AiShowCard (int CardNum,ImageView[] cardShow,int[] cardShare,int[] handcard){
        for(int i=0;i<13;i++){

        }
    }
}
