package com.example.test.model;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
public class Player {
	public  boolean passClick=false;
	private int handcardnum;// 剩余多少张手牌

	private TextView pass=null;


	private int sharecardnum=0;
	private int[] handcard=new int[13];// 发牌的时候用的

	private int[] handcardUi=new int [13];

	private int[] createShareCard;
	private int[] sharecard=new int [5];

	private int[] cardtype=new int [2];

	private ImageButton Card[]=new ImageButton[13];

	private ImageView[] shareCardView=new ImageView[5];

	private Rules rules=new Rules();

	private boolean isSelect[]=new boolean[13];

	private ShowCard showCard=new ShowCard();

	public Player(int handcard[],ImageView shareCardView[],int handcardUi[]){
		arrayset(this.handcard,handcard);
		arrayset(this.handcardUi,handcardUi);
		handcardnum=13;
		sharecardnum=0;
		for(int i=0;i<shareCardView.length;i++){
			this.shareCardView[i]=shareCardView[i];
		}
	}
	public void setImageButton(ImageButton[] Card){
			for(int i=0;i<Card.length;i++){
				this.Card[i]=Card[i];
		}
	}
	public void setHandcardUi(int a,int b){
		handcardUi[a]=b;
	}
	public void setIsSelect(boolean[] isSelect){
		for(int i=0;i<13;i++){
			this.isSelect[i]=isSelect[i];
		}
	}
	public int[] getHandcard(){
		return handcard;
	}

	public void setcard(int wich, int num) {// 发牌时for循环来获得手牌

		handcard[wich] = num;
	}
	public void setPass(TextView pass){
		this.pass=pass;
	}
	public int getHandcardnum() {//封装
		return handcardnum;
	}

	public int[] getHandcardUi() {//封装
		return handcardUi;
	}
	public void setType(int sharecard[]){//确定所选择的牌是什么牌型
		cardtype[0]=rules.judgetype(sharecard)[0];
		cardtype[1]=rules.judgetype(sharecard)[1];
	}
	public int[] getType(){//获得封装的牌类型
		return cardtype;
	}


	public void sortcard()// 发牌后将手牌排序(并且排列手牌的UI图片
	{
		int temp,i,j;
		for(i=0;i<12;i++){
			for(j=0;j<12-i;j++){
				if(handcard[j]>handcard[j+1]){
					temp=handcard[j];
					handcard[j]=handcard[j+1];
					handcard[j+1]=temp;

					temp=handcardUi[j];
					handcardUi[j]=handcardUi[j+1];
					handcardUi[j+1]=temp;
				}
			}
		}
	}

	public boolean playcards(boolean[] isSelect,ImageButton[] Card) {// 点击Pass跳过到下一位玩家，点击牌则用Rules里的规则判断是否符合类型，若符合则outputcards()
		// if(passnum==3),必须选择打出牌
		// select outputcards() or pass();
		// 选择pass还是出牌，若出牌，则利用Rules类判断是否能出
		if(Rules.passnum==3){//不能够点pass的情况
				Rules.deskcard[0]=0;
				Rules.deskcard[1]=0;
				Rules.passnum=0;
		}	//能点pass的情况

		if(Rules.passnum!=3) {
			if (outputcards1(isSelect) == true) {
				sharecardnum = ShowCard.playerShowCard(isSelect, shareCardView, Card, handcard);
				handcardnum -= sharecardnum;
				Rules.passnum=0;
				return true;
			} else {return false;}
		}

		return false;
}

	public boolean  outputcards1(boolean[] isSelect) {//可以Pass的情况
		//判断牌型是否合理
		int num=0;
		for(int i=0;i<13;i++){
				if(isSelect[i]==true){
				sharecard[num]=handcard[i];
				num++;
				}
		}
		if(num!=0){
			createShareCard=new int [num];
		}
		for(int i=0;i<num;i++){
			createShareCard[i]=sharecard[i];
		}
		Arrays.sort(createShareCard);
		setType(createShareCard);
		if(rules.takeout(cardtype,Rules.deskcard)==true){
			Rules.deskcard[0]=cardtype[0];
			Rules.deskcard[1]=cardtype[1];
			return true;
		}

		// 出牌后passnum置为0，
		// 打出的牌记录一下，并在下轮前一直显示。
		else return false;
	}
	public void AiShowCard(){
		pass.setVisibility(View.INVISIBLE);
		int[] a = new int[6];
		int num=0;
		a[0] = 0;
		for(int i=0;i<5;i++){
			shareCardView[i].setVisibility(View.INVISIBLE);
		}
		if (Rules.passnum == 3)// 当3个人跳过的时候，返回最小的单张；
		{
			Rules.deskcard[0]=0;
			Rules.deskcard[1]=0;
			Rules.passnum=0;
		}
		switch (Rules.deskcard[0]) {
			case 0:
				for(int i=0;i<13;i++){
				if(handcard[i]!=-2){
					a[0]=1;
					a[1]=handcard[i];
					Rules.deskcard[0]=1;
					Rules.deskcard[1]=handcard[i];
					shareCardView[i].setImageResource(handcardUi[i]);
					shareCardView[i].setVisibility(View.VISIBLE);
					handcard[i]=-2;
					handcardnum--;
					break;
				}
			}
				break;
			case 1: {
				for (int x = 0; x < 13; x++){// 找最小打得过的一张
					if (handcard[x] > Rules.deskcard[1]&&handcard[x]!=-2) {
						a[0] = 1;
						a[1] = handcard[x];
						Rules.deskcard[0]=1;
						Rules.deskcard[1]=handcard[x];
						break;
					}
				}
				break;
			}
			case 2: {
				for (int x = 0; x < handcard.length - 1; x++){// 找最小打得过的一对
					if (handcard[x+1] > Rules.deskcard[1])
						if (rules.two(handcard[x], handcard[x + 1])[0] != 0) {
							a[0] = 2;
							a[1] = handcard[x];
							a[2] = handcard[x + 1];
							Rules.deskcard[0]=2;
							Rules.deskcard[1]=handcard[x+1];
							break;
						}
				}
				break;
			}

			case 3: {
				for (int x = 0; x < handcard.length - 2; x++)// 找最小打得过的3条
					if (handcard[x+2] > Rules.deskcard[1])
						if (rules.three(handcard[x], handcard[x + 1], handcard[x + 2])[0] != 0) {
							a[0] = 3;
							a[1] = handcard[x];
							a[2] = handcard[x + 1];
							a[3] = handcard[x + 2];
							Rules.deskcard[0]=3;
							Rules.deskcard[1]=handcard[x+2];
							break;
						}
			}
			case 5: {//通过穷举法来找出可出的顺子
				for (int first = 0; first < handcard.length - 4; first++)
					for (int second = first + 1; second < handcard.length - 3; second++)
						for (int third = second + 1; third < handcard.length - 2; third++)
							for (int fouth = third + 1; fouth < handcard.length - 1; fouth++)
								for (int fifth = fouth + 1; fifth < handcard.length; fifth++) {
									int[] inmyfor = { handcard[first], handcard[second], handcard[third],
											handcard[fouth], handcard[fifth] };
									if (rules.takeout(inmyfor, Rules.deskcard)) {
										a[0] = rules.judgetype(inmyfor)[0];
										a[1] = handcard[first];
										a[2] = handcard[second];
										a[3] = handcard[third];
										a[4] = handcard[fouth];
										a[5] = handcard[fifth];
										Rules.deskcard[0]=5;
										Rules.deskcard[1]=handcard[fifth];
										break;
									}
								}
			}
		}
		if(a[0]==0){
			pass();
			return;
		}
		for(int i=1;i<6;i++){
			for(int j=0;j<13;j++){
				if(handcard[j]!=-2&&handcard[j]==a[i]){
					sharecard[num]=handcardUi[j];
					handcard[j]=-2;
					num++;
				}
			}
		}
		if(num==1){
			handcardnum-=num;
			shareCardView[1].setImageResource(sharecard[0]);
			shareCardView[1].setVisibility(View.VISIBLE);
		}
		else {
			for(int i=0;i<num;i++){
			shareCardView[i].setImageResource(sharecard[i]);
			shareCardView[i].setVisibility(View.VISIBLE);
		}
		handcardnum-=num;
		}
		Rules.passnum=0;
	}

	public void pass() {
		Rules.passnum++;// pass后记一下是否3人都pass了
		if(pass!=null)
			pass.setVisibility(View.VISIBLE);
		for(int i=0;i<5;i++){
			shareCardView[i].setVisibility(View.INVISIBLE);
		}
		return;// 选择pass的话就直接return跳过了
	}

	public void arrayset(int array1[],int array2[]){
		for(int i=0;i<13;i++){
			array1[i]=array2[i];
		}
	}
}
