/**
 * FileName:	PlayerScore.java
 *
 * 日期:  		2012.8.22
 * 作者:			元兒～
 * Version:     v1.0
 * 更新資訊: 
 * └─ v1.0 -2012.8.22
 *    ├─ 改使用Eclipse作為這支程式的開發 
 *    └─ 將這個class包成package
 * Description: 計分板，計算分數用的
 *
 */
package game.score;

public class PlayerScore {
	private int score=0;
	public PlayerScore(){reset_score();}
	public void to_score(int toNum){score += toNum;}
	public int get_score(){return score;}
	public void reset_score(){score = 0;} //將分數歸零
}
