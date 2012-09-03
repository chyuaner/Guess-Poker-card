/**
 * 多國語言的取文字類別
 * FileName:	MultipleLanguages.java
 *
 * 日期:  		2012.9.3
 * 作者:			元兒～
 * Version:     v3.2
 * 更新資訊: 
 * ├─ v3.2 -2012.9.3
 * │  └─ 將fileName改成FILE_NAME常數型態（不再允許修改）
 * ├─ v3.1 -2012.8.25
 * │  └─ 小幅修改Debug的輸出樣式
 * └─ v3.0 -2012.8.25
 *    ├─ 以之前自行練習的多國語言為基底
 *    ├─ 加入try、catch → 若語言檔載入失敗，還不至於整個FC
 *    └─ 加入DEBUG功能，若將DEBUG常數值指定為true → if載入錯誤就輸出錯誤訊息
 * 
 * Description:	這個class主要是改良內建的 ResourceBundle ，
 * 	讓程式如果在語言檔無法正常載入的情況下還可以繼續執行，
 * 	如果需要除錯的話，只要把DEGUG常數值改為true即可，錯誤訊息將會在終端機輸出。
 * 	一個物件為一個多國語言文字檔，在建立此物建時，必須指定一個對應的語言檔
 * 
 * 使用方法範例: 
 *  	建立語言物件txt，使用text.properties
 * 			Language txt = new Language("text");
 * 		建立語言物件txt，指定繁體中文語言，使用text_zh_TW.properties
 * 			Language txt = new Language("text","zh","TW");
 * 		指定為目前作業系統的地區別
 * 			txt.setDefaultLanguage();
 * 		指定為選擇的地區別（參數代入: 語言,地區）
 * 			txt.setLanguage();
 * 
 * 參考資料: 
 * 	使用 ResourceBundle:
 * 		http://caterpillar.onlyfun.net/Gossip/JavaGossip-V2/ResourceBundle.htm
 * 	Java與多國語言的應用:
 * 		http://www.interinfo.com.tw/edoc/ch12/frontline.htm
 * 
 *	 使用 Properties
 * 		http://caterpillar.onlyfun.net/Gossip/JavaGossip-V2/Properties.htm
 *	 
 * 	如果您想要提供繁體中文的訊息，由於訊息資源檔必須是ISO-8859-1編碼，所以對於非西方語系的處理，必須先將之轉換為Java Unicode Escape格式，例如您可以先在訊息資源檔中寫下以下的內容
 * 	使用JDK的工具程式native2ascii來轉換，例如：
 * 	native2ascii -encoding Big5 messages_zh_TW.txt messages_zh_TW.properties
 */
package tools.multiple_languages;

import java.util.Locale;
import java.util.ResourceBundle;


public class Language {	
	private static final boolean DEBUG = false;
	private final String FILE_NAME;
	
	private Locale currentLocale;
	private ResourceBundle resource;
	
	//此物件建立時...
	public Language(String fileName){
		FILE_NAME = fileName;
		setDefaultLanguage();
	}
	//此物件建立時（有代入語言參數）
	public Language(String fileName,String ln1,String ln2){
		FILE_NAME = fileName;
		setLanguage(ln1,ln2);
	}
	
	//指定為目前作業系統的地區別
	public boolean setDefaultLanguage() {
		try{
			//取得目前作業系統的地區別
			currentLocale = Locale.getDefault();
			resource = ResourceBundle.getBundle(FILE_NAME,currentLocale);
			return true;
		}
		catch(Exception ex){
			if(DEBUG){
				System.out.print("Language錯誤: \n");
				ex.printStackTrace();
				System.out.println("無法載入預設語言檔\n");
			}
			return false;
		}
	}
	//指定為選擇的地區別（參數代入: 語言,地區）
	public void setLanguage(String ln1,String ln2) {
		try{
			//指定地區別
			currentLocale = new Locale(ln1, ln2);
			resource = ResourceBundle.getBundle(FILE_NAME,currentLocale);
		}
		catch(Exception ex){
			if(DEBUG){
				System.out.print("Language錯誤: \n");
				ex.printStackTrace();
				System.out.println("無法載入" + ln1+","+ln2 + "語言檔\n");
			}
		}
	}
	
	//取得語言檔裡的文字（參數代入key值）
	public String getString(String key) {
		try{
			return resource.getString(key);
		}
		catch(Exception ex){
			if(DEBUG) System.out.println("Language錯誤: 無法顯示\"" + key + "\"語言檔");
			return key;
		}
	}
	
}
