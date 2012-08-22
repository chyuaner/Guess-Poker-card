/**
 * 撲克牌記憶遊戲
 * FileName:	main.java
 *
 * 日期:  		2012.8.22
 * 作者:			元兒～
 * Version:     v3.0
 * 更新資訊: 
 * └─ v3.0 -2012.8.22
 *    ├─ 改使用Eclipse作為這支程式的開發
 *    ├─ 把所有class通通分開成個個檔案
 *    ├─ 將圖片路徑從寫死改設為 private final String URL_IMAGE_CARD="res//image//poker_resize//card-rank_"; 並加上註解
 *    └─ 排行榜功能尚未完成，暫時先移除
 * 目前Bug: 
 * └─ v3.0 -2012.8.22
 *    └─ 尚未解決Linux下與Windows下路徑上的問題
 * 預計打算:
 * └─ 將寫死的文字拉出來成資源檔
 * 
 * Description: 撲克牌記憶遊戲
 */
//import game_class.*;
import game.card.poker.Cards;
import game.score.PlayerScore;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class main extends JFrame implements ActionListener{
	//建立自行寫的類別為物件
	PlayerScore score = new PlayerScore();
	Cards cards = new Cards();
	
	//建立UI介面的所需物件
	private JLabel score_label;
	private JButton[] button = new JButton[52];
	//private JButton paidCards_button,reDeal_button,exit_button,display_cards_button;
	private JMenuItem paidCards_button,reDeal_button,exit_button,display_cards_button,about_button;
	private JRadioButtonMenuItem display_cards_true,display_cards_false;
	private PaidCards_app paidCards_app = new PaidCards_app(); //建立物件-顯示已經翻過的牌
	
	//定義數值內容
	private boolean display_cards = false; //是否顯示偷偷看全部的牌
	private final String URL_IMAGE_CARD="res//image//poker_resize//card-rank_"; //指定圖片路徑
	
	public main(){
		super("撲克牌記憶遊戲");
		//DEBUG
		/*
		System.out.println(a.haveFile())*/;
		//=====功能表=====
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);  // 新增下拉式功能表
		JMenu gameMenu = new JMenu("遊戲(G)"); // 第一個選單
		gameMenu.setMnemonic(KeyEvent.VK_G);
			reDeal_button = new JMenuItem("開始(Alt+s)",KeyEvent.VK_S);
			reDeal_button.addActionListener(this);
			gameMenu.add(reDeal_button);
			exit_button = new JMenuItem("不玩了(Alt+e)",KeyEvent.VK_E);
			exit_button.addActionListener(this);
			gameMenu.add(exit_button);
			gameMenu.addSeparator();  // 分隔線
			about_button = new JMenuItem("關於(Alt+a)",KeyEvent.VK_A);
			about_button.addActionListener(this);
			gameMenu.add(about_button);
		menuBar.add(gameMenu); // 新增gameMenu選單
		
		JMenu viewMenu = new JMenu("顯示(V)"); // 第二個選單
		viewMenu.setMnemonic(KeyEvent.VK_G);
			viewMenu.add(new JLabel(" 顯示所有的牌"));
			ButtonGroup display_cards = new ButtonGroup();
			display_cards_true = new JRadioButtonMenuItem("是");
			display_cards_true.addActionListener(this);
			display_cards.add(display_cards_true);
			viewMenu.add(display_cards_true);
			display_cards_false = new JRadioButtonMenuItem("否");
			display_cards_false.addActionListener(this);
			display_cards.add(display_cards_false);
			viewMenu.add(display_cards_false);
			viewMenu.addSeparator();  // 分隔線
			paidCards_button = new JMenuItem("已配對的牌(Alt+d)",KeyEvent.VK_D);
			paidCards_button.addActionListener(this);
			viewMenu.add(paidCards_button);
		menuBar.add(viewMenu); // 新增viewMenu選單
		//=====視窗內容=====
		Container c = getContentPane();
			//上方的6*9部份
			JPanel jpanel_up = new JPanel(); // 建立JPanel物件
				jpanel_up.setLayout(new GridLayout(6,9,5,5));
				cards.reDeal();
				for(int i=0;i<52;i++){
					button[i] = new JButton();
					//button[i].setIcon(new ImageIcon(URL_IMAGE_CARD+cards.get_card_text(i)+".jpg"));//Debug
					button[i].setIcon(new ImageIcon(URL_IMAGE_CARD+"back.jpg")); //tmp
					button[i].setBackground(Color.white); //設定按鈕背景色彩
					//button[i].setText(""+cards.get_card_text(i)%13); //DEBUG
					//button[i].setBackground(Color.white); //DEBUG
					button[i].addActionListener(this);
					jpanel_up.add(button[i]);
				}
				score_label = new JLabel("分數: " + score.get_score());
				jpanel_up.add(score_label);
			/*//下方的"確定"、"取消"
			JPanel jpanel_down = new JPanel(); // 建立JPanel物件
				paidCards_button = new JButton("顯示(Alt+d)");
				paidCards_button.setMnemonic(KeyEvent.VK_D); //當鍵盤按下Alt+d而觸發的
				paidCards_button.addActionListener(this); //當滑鼠按下而觸發的
				jpanel_down.add(paidCards_button);
				reDeal_button = new JButton("重新發牌(Alt+r)");
				reDeal_button.setMnemonic(KeyEvent.VK_R); //當鍵盤按下Alt+r而觸發的
				reDeal_button.addActionListener(this); //當滑鼠按下而觸發的
				jpanel_down.add(reDeal_button);
				display_cards_button = new JButton("顯示所有的牌(Alt+a)");
				display_cards_button.setMnemonic(KeyEvent.VK_A); //當鍵盤按下Alt+a而觸發的
				display_cards_button.addActionListener(this); //當滑鼠按下而觸發的
				jpanel_down.add(display_cards_button);
				exit_button = new JButton("不玩了(Alt+e)");
				exit_button.setMnemonic(KeyEvent.VK_E); //當鍵盤按下Alt+e而觸發的
				exit_button.addActionListener(this); //當滑鼠按下而觸發的
				jpanel_down.add(exit_button);
			*/
		//新增兩個panel在視窗上
		c.add(jpanel_up, BorderLayout.CENTER); // 新增在中間
		//c.add(jpanel_down, BorderLayout.SOUTH); // 新增在南方
	}
	// 實作事件處理方法
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource() == paidCards_button){
			paidCards_app.setSize(500,500);  // 設定視窗尺寸
			paidCards_app.setVisible(true);  // 顯示視窗
		}
		//按下重新發牌
		else if(evt.getSource() == reDeal_button){
			cards.reDeal();
			paidCards_app.clear();
			for(int i=0;i<52;i++) {
				score.reset_score();
				button[i].setVisible(true);
				button[i].setIcon(new ImageIcon(URL_IMAGE_CARD+"back.jpg"));
				display_cards = false;
				//button[i].setText(""+cards.get_card_text(i)%13); //DEBUG
				score_label.setText("分數: " + score.get_score());
			}
		}
		//按下結束
		else if(evt.getSource() == exit_button){
			System.exit(0);
		}
		//按下關於
		else if(evt.getSource() == about_button){
			JOptionPane.showMessageDialog(
			button[0],"撲克牌記憶遊戲\n版本: V3.0\n作者: 元兒～\n日期: 2012.8.22","關於",JOptionPane.INFORMATION_MESSAGE);
			//JOptionPane繼承自button[0]
		}
		//翻開全部的牌（偷看用）
		else if(evt.getSource() == display_cards_true && !display_cards){
			for(int i=0;i<52;i++) 
				button[i].setIcon(new ImageIcon(URL_IMAGE_CARD+cards.get_card_text(i)+".jpg")); //將圖片改成正面（有數字）牌
			display_cards = true;
		}
		else if(evt.getSource() == display_cards_false && display_cards){
			for(int i=0;i<52;i++){
				if(!cards.get_card_open(i)) 
					button[i].setIcon(new ImageIcon(URL_IMAGE_CARD+"back.jpg")); //將圖片改成背面
				else button[i].setIcon(new ImageIcon(URL_IMAGE_CARD+cards.get_card_text(i)+".jpg")); //將圖片改成正面（有數字）牌
			}
			display_cards = false;
		}
		else{
			for(int i=0;i<52;i++){
				//當這張牌是已覆蓋的狀態
				if(evt.getSource() == button[i] && cards.get_card_open(i) == false){
					if(cards.get_card_open_able()){
						//button[i].setVisible(false); //DEBUG
						//button[i].setBackground(Color.black); //DEBUG
						button[i].setIcon(new ImageIcon(URL_IMAGE_CARD+cards.get_card_text(i)+".jpg")); //將圖片改成正面（有數字）牌
						cards.set_card_open(i,true);
						if(cards.card_open_num(i,cards.get_card_text(i))==2){
							score.to_score(1);
							JOptionPane.showMessageDialog(button[i],"恭喜你！獲得1分！！","撲克牌遊戲",JOptionPane.INFORMATION_MESSAGE);
							button[i].setVisible(false);
							button[cards.get_openCardButton1()].setVisible(false);
							paidCards_app.add_update(cards.get_card_paid_text());
						}
						score_label.setText("分數: " + score.get_score());
						//score_label.setText("X: " + cards.get_card_open_total()); //DEBUG-顯示已經翻開幾張牌
					}
					else{
						JOptionPane.showMessageDialog(button[i],"請先蓋牌！！","撲克牌遊戲",JOptionPane.WARNING_MESSAGE);
					}
				}
				//當這張牌是已翻開的狀態
				else if(evt.getSource() == button[i] && cards.get_card_open(i) == true){
					button[i].setIcon(new ImageIcon(URL_IMAGE_CARD+"back.jpg")); //將圖片改成背面
					cards.set_card_open(i,false);
					//score_label.setText("X: " + cards.get_card_open_total()); //DEBUG-顯示已經翻開幾張牌
					cards.card_open_num(0,0);
				}
			}
		}
	}
	public static void main (String args[]) {
		main app = new main();
		app.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		app.setSize(600,600);  // 設定視窗尺寸
		app.setVisible(true);  // 顯示視窗
	}
}

