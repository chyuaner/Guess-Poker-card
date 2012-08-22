/**
 * FileName:	Num_rand.java
 *
 * 日期:  		2012.8.22
 * 作者:			元兒～
 * Version:     v1.0
 * 更新資訊: 
 * └─ v1.0 -2012.8.22
 *    ├─ 改使用Eclipse作為這支程式的開發 
 *    └─ 將這個class包成package
 * Description: 隨機取數字，而且取到的數字不再重複取到
 *
 */
package game.rand.num;

public class Num_rand {
	private int num_amount; //亂數數的最大值
	private boolean[] num_use; //這個數字是否已用
	public Num_rand(int num_amount){
		this.num_amount = num_amount;
		num_use = new boolean[num_amount];
	}
	public void shuffle() {
		for(int i=0;i<num_amount;i++) num_use[i] = false;
	}
	public int getNumber(int max) {
		if(!(max>num_amount)){
			int i;
			while(true)
			{
				i=(int)(Math.random()*max); //亂數取0~(max-1)範圍內的數
				if(!num_use[i]) //檢查隨機取到的數是否已用，沒用過的話→離開這個while
				{
					num_use[i] = true;
					break;
				}
			}
			return i+1;
		}
		else return -1; //若輸入的最大值超過能處理的最大值時，回傳-1
	}
}
