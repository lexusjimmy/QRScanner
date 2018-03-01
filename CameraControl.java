import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import com.github.sarxos.webcam.Webcam;
public class CameraControl {
	private ArrayList <Webcam>cameras;
	private Webcam mainCamera;
	public CameraControl(){
		cameras=new ArrayList<Webcam>();
		getCamera();
	}
	private void getCamera(){
		List<Webcam> tempCamera=Webcam.getWebcams();
		for(int i=0;i<tempCamera.size();i++){
			Webcam temp=(Webcam)tempCamera.get(i);
			cameras.add(temp);
		}
	}
	public ArrayList<String> getName(){
		ArrayList<String> result=new ArrayList<String>();
		for(int i=0;i<cameras.size();i++){
			result.add(cameras.get(i).getName());
		}
		return result;
	}
	public void setCamera(int cameraNum){
		if(cameraNum<cameras.size()){
			mainCamera=cameras.get(cameraNum);
			mainCamera.setViewSize(new Dimension(640,480));
			mainCamera.open();
		}
	}
	public BufferedImage takePic(){
		String fileName="QRRaw"+new SimpleDateFormat("yyyyMMddHHmm").format(Calendar.getInstance().getTime())+".jpg";
		BufferedImage QRImage=null;
		try{
			QRImage=mainCamera.getImage();
			ImageIO.write(QRImage, "JPG", new File(fileName));
			System.out.format("photo %s has taken\n", fileName);
		}catch(IOException err){
			err.printStackTrace();
		}
		return QRImage;
	}
}
