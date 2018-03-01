import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Hashtable;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class QRAnalyser {
	private BufferedImage mainPic;
	public QRAnalyser(BufferedImage qrPic){
		mainPic=qrPic;
		
	}
	private BufferedImage slice(BufferedImage src,int x,int y,int width,int height){
		BufferedImage piece=new BufferedImage(width, height, src.getType());
		Graphics2D copier= piece.createGraphics();
		copier.setColor(Color.WHITE);
		copier.fillRect(0, 0, width, height);
		copier.drawImage(src, 0, 0, width, height, x, y, x+width, y+height, null);
		copier.dispose();
		return piece;
	}
	
	private String analyze(int placeNum){
		BufferedImage placeGet=mainPic;
		QREdge edge=new QREdge(placeNum,1);
		String result="fail";
		for(int i=0;i<12;i++){
			if(!result.contains("fail")){
				//System.out.println("jumpOut");
				break;
			}
			for(int j=0;j<4;j++){
				//System.out.println(placeNum+":"+edge.getCurrX()+","+edge.getCurrY());
				placeGet=mainPic.getSubimage(edge.getCurrX(), edge.getCurrY(), edge.getWidth(), edge.getHeight());
				result=decode(placeGet);
				if(!result.contains("fail")){
					//System.out.println("jumpOut");
					break;
				}
				edge.nextY(j);
			}
			edge.nextX(i);
		}
		return result;
	}
	
	private String decode(BufferedImage qrTrans){
		String finalResult="fail";
		try{
			LuminanceSource source=new BufferedImageLuminanceSource(qrTrans);
			BinaryBitmap bitmap=new BinaryBitmap(new HybridBinarizer(source));
			Result result;
			Hashtable hint=new Hashtable<>();
			hint.put(DecodeHintType.CHARACTER_SET, "utf-8");
			result=new MultiFormatReader().decode(bitmap, hint);
			finalResult=result.getText();
			System.out.println("the information is "+finalResult);
		}catch(ReaderException fr){
			System.out.println(fr.toString());
		}
		return finalResult;
	}
	
	public ArrayList<String> readSrc(){
		ArrayList<String> analysis=new ArrayList<String>();
		for(int i=0;i<4;i++){
			String temp=analyze(i);
			/*try{
			ImageIO.write(splitRead, "JPG", new File(fileName));
			}catch(IOException e){
				e.printStackTrace();
			}*/
			//System.out.format("file %s has split\n", fileName);
			analysis.add(temp);
		}
		return analysis;
	}
}
