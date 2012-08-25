/**
 * 撲克牌記憶遊戲
 * FileName:	PaidCards_app.java
 *
 * 日期:  		2012.8.25
 * 作者:			元兒～
 * Version:     v3.2 -1.2
 * 更新資訊: 
 * ├─ v1.2 -2012.8.25
 * │  └─ 將原本使用的ResourceBundle類別物件改成字型撰寫的Language物件，讓程式在在語言檔無法正常載入的情況下還可以繼續執行
 * ├─ v1.1 -2012.8.22
 * │  ├─ 在code上增加些會出現什麼字的註解
 * │  └─ 將所有文字拉出成資源檔（繁體中文、英文）
 * └─ v1.0 -2012.8.22
 *    ├─ 改使用Eclipse作為這支程式的開發
 *    ├─ 把PaidCards_app 有class分開成個檔案
 *    └─ 將圖片路徑從寫死改設為 private final String URL_IMAGE_CARD="res//image//poker_resize//card-rank_"; 並加上註解
 * Description: 顯示已經翻過的牌
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.multiple_languages.Language;


//PaidCards_app類別為 顯示已經翻過的牌
class PaidCards_app extends JFrame implements ActionListener{
	//定義數值內容
	private final String URL_IMAGE_CARD="res/image/poker_resize/card-rank_"; //指定圖片路徑
	private final String URL_GET_STRING="messages";
	//建立多國語言
	private Language lang = new Language("messages");
	//建立UI介面的所需物件
	private Container c = getContentPane();
	private JPanel displayCards_jpanel = new JPanel();
	private JLabel[][] see = new JLabel[26][];
	private JButton back_button;
	//宣告初始變數
	private int card_paid_total = 0;
	public PaidCards_app(){	
		//介面設計
		JPanel title_jpanel = new JPanel();
			JLabel title_label = new JLabel(lang.getString("PaidCards_app.ui.paid_cards_title")); //顯示"以下是已湊成一對"文字
			title_jpanel.add(title_label);
			displayCards_jpanel.setLayout(new GridLayout(5,1,2,2));
		back_button = new JButton(lang.getString("PaidCards_app.ui.back")+"(Alt+b)"); //新增"返回"按鈕
		back_button.setMnemonic(KeyEvent.VK_B); //當鍵盤按下Alt+d而觸發的
		back_button.addActionListener(this); //當滑鼠按下而觸發的
		//新增所有panel在視窗上
		c.add(title_jpanel, BorderLayout.NORTH);
		c.add(displayCards_jpanel, BorderLayout.CENTER);
		c.add(back_button, BorderLayout.SOUTH);
	}
	public void add_update(int[][] card_paid_text){
				//displayCards_jpanel.add(new JLabel(""+card_paid_text[card_paid_total][0]+" "+card_paid_text[card_paid_total][1]));
				see[card_paid_total] = new JLabel[2];
				see[card_paid_total][0]=new JLabel(new ImageIcon(URL_IMAGE_CARD+card_paid_text[card_paid_total][0]+".jpg"));
				see[card_paid_total][1]=new JLabel(new ImageIcon(URL_IMAGE_CARD+card_paid_text[card_paid_total][1]+".jpg"));
				/*displayCards_jpanel.add(new JLabel(new ImageIcon(URL_IMAGE_CARD+card_paid_text[card_paid_total][0]+".jpg")));
				displayCards_jpanel.add(new JLabel(new ImageIcon(URL_IMAGE_CARD+card_paid_text[card_paid_total][1]+".jpg")));*/
				displayCards_jpanel.add(see[card_paid_total][0]);
				displayCards_jpanel.add(see[card_paid_total][1]);
				card_paid_total++;
	}
	public void clear(){
		for(int i=0;i<card_paid_total;i++){
			displayCards_jpanel.remove(see[i][0]);
			displayCards_jpanel.remove(see[i][1]);
		}
		card_paid_total=0;
	}
	// 實作事件處理方法
	public void actionPerformed(ActionEvent evt){
		this.setVisible(false);
		/*c.remove(displayCards_jpanel);
		c.add(displayCards_jpanel, BorderLayout.CENTER);*/
	}
}