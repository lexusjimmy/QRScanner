import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Example of how to take single picture on button press.
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class Control {
	public static CameraControl mainCam;
	private static JFrame window;
	public static void main(String[] args) {
		mainCam=new CameraControl();
		window = new JFrame();
		checkCamera();
		JButton button = new JButton(new AbstractAction("Snapshot") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				
				drawResult(qrRead());
			}
		});

		window.add(button);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);

	}
	
	private static void checkCamera(){
		ArrayList<String> cameraName=mainCam.getName();
		int camNum=-1;
		for(int i=0;i<cameraName.size();i++){
			if(cameraName.get(i).contains("USB2.0 Camera")){
				camNum=i;
			}
		}
		if(camNum>-1){
			mainCam.setCamera(camNum);
		}else{
			System.out.print("correct camera haven't found");
		}
	}
	private static ArrayList<String> qrRead(){
		QRAnalyser reader=new QRAnalyser(mainCam.takePic());
		return reader.readSrc();
	}
	private static void drawResult(ArrayList<String> meaning){
		ArrayList<LogoResult> faster=new ArrayList<LogoResult>();
		for(int i=0;i<4;i++){
			faster.add(new LogoResult());
			LogoResult temp=faster.get(i);
			temp.setPic(meaning.get(i));
			if(temp.onFlag){
				temp.exportPic();
			}
		}
		String fileName="final"+new SimpleDateFormat("yyyyMMddHHmm").format(Calendar.getInstance().getTime())+".jpg";
		BufferedImage finalImage;
		try{
			finalImage=merge(faster);
			ImageIO.write(finalImage, "PNG", new File(fileName));
			System.out.format("Image %s has generated\n", fileName);
		}catch(IOException err){
			err.printStackTrace();
		}
	}
	private static BufferedImage merge(ArrayList<LogoResult> drawer){
		int num=0;
		for(int i=0;i<drawer.size();i++){
			LogoResult remp=drawer.get(i);
			if(remp.onFlag){
				num=i;
				break;
			}
		}
		BufferedImage output=new BufferedImage(275,359,drawer.get(num).result.getType());
		Graphics2D illus=output.createGraphics();
		for(int j=0;j<drawer.size();j++){
			if(drawer.get(j).onFlag){
				illus.drawImage(drawer.get(j).result,drawer.get(j).pX,drawer.get(j).pY,null);
			}
		}
		illus.dispose();
		return output;
	}
}
