package com.example.test.model;
//检查打出的牌符不符合规则
//所有牌的类型与大小将用一个数组（2个int值记录下来）
public class Rules {
	public static int passnum = 3;// 记录是否3个人都跳过了，若是则下一轮必须出牌而不能跳过
	public static int[] deskcard=new int[2];


	// 对子
	int[] two(int num1,int num2) {
		int[] a = new int[2];
		if ((num1-1) / 4 == (num2-1) / 4) {
			a[0] = 2;
			a[1] = num2;
		} // 是对子则返回类型2和对子的大小
		else {
			a[0] = 0;
			a[1] = 0;
		}
		return a;
	}

	// 三条不带
	int[] three(int num1, int num2, int num3) {
		int[] a = new int[2];
		if ((num1-1) / 4 == (num2-1) / 4 && (num1-1) / 4 == (num3-1) / 4) {
			a[0] = 3;
			a[1] = num3;
		} else {
			a[0] = 0;
			a[1] = 0;
		}
		return a;
	}

	//顺子、三带二、四带一、同花顺
	int[] five(int num1, int num2, int num3, int num4, int num5) {
		int[] a = new int[2];
		if (((num5-1) / 4) - 1 == (num4-1) / 4 && ((num5-1) / 4 )- 2 == (num3-1) / 4 && ((num5-1) / 4 )- 3 == (num2-1)/4//
				&& (num5-1) / 4 - 4 == (num1-1)/4)// 顺子情况
		{
			if(num5 % 4 == num4 % 4 && num5 % 4 == num3 % 4 && num5 % 4 == num2 % 4 && num5 % 4 == num1 % 4) {//同花顺情况
				if (num5<49) {
				a[0] = 9;
				a[1] = num5;//顺子，没有2参与的情况下，返回最大的那张
				}
				else if (num5>48&&num4<45){//有2参与且没有A参与，此时的顺子一定为23456,所以返回第四张
					a[0]=9;
					a[1]=num4;
				}
				else if(num5>48&&num4>45&&num4<49){//有2和A参与的情况下，顺子只可能是A2345	此时返回第三张
					a[0]=9;
					a[1]=num3;
				}
			} // 类型9是同花顺
			else {
				if (num5<49) {
					a[0] = 5;
					a[1] = num5;//顺子，没有2参与的情况下，返回最大的那张
				}
				else if (num5>48&&num4<45){//有2参与且没有A参与，此时的顺子一定为23456,所以返回第四张
					a[0]=5;
					a[1]=num4;
				}
				else if(num5>48&&num4>45&&num4<49){//有2和A参与的情况下，顺子只可能是A2345	此时返回第三张
					a[0]=5;
					a[1]=num3;
				}
			} // 类型5是普通顺子
		}

		else if (num5 % 4 == num4 % 4 && num5 % 4 == num3 % 4 && num5 % 4 == num2 && num5 % 4 == num1)// 同花
		{
			a[0] = 6;
			a[1] = num5;
		} // 同花判定
		else if ((num1-1) / 4 == (num3-1) / 4 && (num4-1) / 4 == (num5-1) / 4)// 三带二判定		改过了 原来错误
		{
			a[0] = 7;
			a[1] = num3;
		} else if ((num1-1) / 4 == (num2-1) / 4 && (num3-1) / 4 == (num5-1) / 4)// 三带二判定     改过了 原来错误
		{
			a[0] = 7;
			a[1] = num5;
		} else if ((num1-1) / 4 == (num4-1) / 4)// 四带一判定
		{
			a[0] = 8;
			a[1] = num4;
		} else if ((num5-1) / 4 == (num2-1) / 4)// 四带一判定
		{
			a[0] = 8;
			a[1] = num5;
		} else {
			a[0] = 0;
			a[1] = 0;
		}
		return a;
	}

	//简单的比大小
	Boolean compare(int a, int b) {
		if (a > b)
			return true;
		else
			return false;
	}

	public int[] judgetype(int[] card) {// 用数组将打出的手牌传入,判断是什么类型
		int[] a = new int[2];
		a[0] = 0;
		a[1] = 0;
		switch (card.length) {
			case 1: {// 单张手牌，a[0]返回1，a[1]返回其大小
				a[0] = 1;
				a[1] = card[0];
				return a;
			}
			case 2: {
				if (two(card[0], card[1])[0] == 0) {
					return a;
				} else
					return two(card[0], card[1]);
			}
			case 3: {
				if (three(card[0], card[1], card[2])[0] == 0) {
					return a;
				} else
					return three(card[0], card[1], card[2]);

			}
			case 5: {
				if (five(card[0], card[1], card[2], card[3], card[4])[0] == 0) {
					return a;
				} else
					return five(card[0], card[1], card[2], card[3], card[4]);
			}
		}
		return a;

	}

	// 传入两个数组到这个函数，以比较手牌与桌面的牌，其中desktype是2个int值（类型与大小）而不是之前的牌的5个int值
	public  Boolean takeout(int[] handcard, int[] desktype) {
		if(desktype[0]==0) {//牌桌上没有牌
			if(handcard[0]==0)
				return false;
			else return true;
		}
		/*else if (desktype[0] < 5 && judgetype(handcard)[0] != desktype[0])// 首先比较牌的类型是否相同(顺子另外比)，不同则直接false并提示错误
			return false;
		else {
			if (judgetype(handcard)[0] >= 5)// 牌的类型相同则比较它们的大小,不同的话比较类型大小就可以比出
			{
				if ((judgetype(handcard)[0] == desktype[0] && compare(judgetype(handcard)[1], desktype[1]))
						|| judgetype(handcard)[0] > desktype[0])
					return true;
			}
			if(desktype[0]<5&&desktype[0]!=0){
				if(desktype[1]>handcard[1])
					return false;
				else return true;
			}

		}*/
		else if(desktype[0]<5&&desktype[0]!=0){
			if(desktype[0]==handcard[0]){
				if(handcard[1]>desktype[1])
					return true;
				else return false;
			}
		}
		else if(desktype[0]>=5&&desktype[0]==handcard[0]){
			if(desktype[1]<handcard[1])
				return true;
			else return false;
		}
		return false;
	}
	public void score(int[]newdeskcard){//记录桌子上的新的牌
		deskcard[0]=newdeskcard[0];
		deskcard[1]=newdeskcard[1];
	}

}
