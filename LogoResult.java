import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;


public class LogoResult {
	private HashMap<String, String> decision;
	private String picSymbol;
	public boolean onFlag=false;
	public int pX;
	public int pY;
	public BufferedImage result;
	public LogoResult(){
		decision=new HashMap<>();
		decision.put("lampell", "QRicon/lampell.png");
		decision.put("bookeal", "QRicon/bookeal.png");
		decision.put("feapple", "QRicon/feapple.png");
		decision.put("RCcar", "QRicon/RCcar.png");
		decision.put("bucha", "QRicon/bucha.png");
		decision.put("bullhorn", "QRicon/bullhorn.png");
		decision.put("magcard", "QRicon/magcard.png");
		decision.put("gun", "QRicon/gun.png");
		decision.put("glass", "QRicon/glass.png");
		decision.put("high", "QRicon/high.png");
		decision.put("mid", "QRicon/mid.png");
		decision.put("low", "QRicon/low.png");
	}
	public void setPic(String eleName){
		if(decision.containsKey(eleName)){
			picSymbol=eleName;
			positionSet();
			onFlag=true;
			System.out.println(eleName);
		}else{
			System.out.println("fail to load");
		}
	}
	public void exportPic(){
		if(onFlag){
			String patch=decision.get(picSymbol);
			try {
				result=ImageIO.read(new File(patch));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void positionSet(){
		switch(picSymbol){
			case "lampell":
				pX=140;
				pY=0;
			break;
			case "bookeal":
				pX=140;
				pY=0;
			break;
			case "feapple":
				pX=140;
				pY=0;
			break;
			case "RCcar":
				pX=140;
				pY=163;
			break;
			case "bucha":
				pX=140;
				pY=163;
			break;
			case "bullhorn":
				pX=140;
				pY=163;
			break;
			case "magcard":
				pX=7;
				pY=0;
			break;
			case "gun":
				pX=7;
				pY=0;
			break;
			case "glass":
				pX=7;
				pY=0;
			break;
			case "high":
				pX=0;
				pY=163;
			break;
			case "mid":
				pX=0;
				pY=163;
			break;
			case "low":
				pX=0;
				pY=163;
			break;
		}
	}
}
