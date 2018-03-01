import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Background {
	private String patch;
	private ArrayList<String> allPath;
	public BufferedImage got;
	public Background(){
		allPath=new ArrayList<String>();
		allPath.add("background/01.jpg");
		allPath.add("background/02.jpg");
	}
	public void setBackground(int backNum){
		patch=allPath.get(backNum);
	}
	public void exportPic(){
		
		try {
			got = ImageIO.read(new File(patch));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return mainBack;
	}
}
