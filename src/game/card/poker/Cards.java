/**
 * FileName:	Cards.java
 *
 * 日期:  		2012.9.2
 * 作者:			元兒～
 * Version:     v1.0.1
 * 更新資訊: 
 * ├─ v1.0.1 -2012.9.2
 * │  └─ 小幅修改reDeal()裡的card_text[i] = n.getNumber(52,true);（因應Num_rand類別的更新）
 * └─ v1.0 -2012.8.22
 *    ├─ 改使用Eclipse作為這支程式的開發 
 *    └─ 將這個class包成package
 * Description: 一整個class就是一副52張的撲克牌
 *
 */
package game.card.poker;

import game.rand.num.Num_rand;

//全部發下去的卡片
public class Cards{
	private Num_rand n = new Num_rand(52); //使用取亂數用的
	private int[] card_text = new int[52]; //card_text[第?個按鈕]=撲克牌的序號
	private boolean[] card_open = new boolean[52]; //card_open[第?個按鈕]=是否已被翻開
	private int card_open_total = 0; //總共有?張牌被翻開
	private boolean card_open_able = true; //是否可翻開牌
	private int openCardNum1 = 0,openCardButton1 =0,openCard1; //檢查兩張牌是否一樣用的
	
	private int card_paid_total = 0;
	private int[][] card_paid_text = new int[26][];
	
	public void reDeal(){
		n.shuffle();
		for(int i=0;i<52;i++){
			card_text[i] = n.getNumber(52,true);
			card_open[i] = false;
		}
		card_open_total = 0;
		card_open_able=true;
		card_paid_total=0;
	}
	public int get_card_text(int theNum){return card_text[theNum];}
	public boolean get_card_open(int theNum){return card_open[theNum];}
	public void set_card_open(int theNum,boolean tf){
		if(tf==true) card_open_total+=1;
		else if(tf==false) card_open_total-=1;
		card_open[theNum] = tf;
		//如果已經翻開兩張，必須全部覆蓋才能再翻
		if(card_open_total<2 && card_open_able) card_open_able=true;
		else if(card_open_total==0) card_open_able=true;
		else card_open_able=false;
		}
	//public int get_card_open_total(){return card_open_total;}
	public boolean get_card_open_able(){
		return card_open_able;
	}
	public int card_open_num(int cardButton,int cardNum){
		/* 判斷兩張牌點數是否一樣...
		 * 傳回數值：
		 *   0：不作用
		 *   1：兩張牌都不一樣
		 *   2：兩張牌點數一樣
		*/
		int cardNum_tmp=cardNum;
		if(cardNum!=0 && cardNum%13==0) cardNum=13;
		else cardNum=cardNum%13; //過濾為牌的點數
		//如果牌是覆蓋回去的話
		if(cardNum==0){openCardNum1 = 0; openCardButton1 =0; return 0;}
		//如果這是翻開的第二張牌
		else if(openCardNum1 != 0 && (openCardNum1 == cardNum)){
			//存入已配對(card_paid_text)的陣列裡
			card_paid_text[card_paid_total] = new int[2];
			card_paid_text[card_paid_total][0] = openCard1;
			card_paid_text[card_paid_total][1] = cardNum_tmp;
			card_paid_total++;
			//處理原來的部份
			openCardNum1=0; //把第一張牌的數清掉
			card_open_total -= 2; //把以發過牌的量扣掉目前的兩張牌
			card_open_able=true;
			return 2;
			//PS.不清掉openCardButton1的值是為了讓接以下的程式能覺掉要清掉哪個按鈕
		}
		else if(openCardNum1 != 0 && (openCardNum1!=cardNum)){openCardNum1=0;return 1;}
		//如果這是翻開的第一張牌
		else{
			openCard1 = cardNum_tmp;
			openCardNum1 = cardNum; 
			openCardButton1 = cardButton; 
			return 0;
		}
	}
	public int get_openCardButton1(){return openCardButton1;}
	public int[][] get_card_paid_text(){return card_paid_text;}
	public int get_card_paid_total(){return card_paid_total;}
}
