
public class QREdge {
	private int iniX;
	private int iniY;
	private int currX;
	private int currY;
	private int width;
	private int height;
	private int level;
	private int placeName;
	private static final int offsetX=10;
	private static final int offsetY=10;
	public QREdge(int place,int size){
		level=size;
		changeSize();
		iniValue(place);
		placeName=place;
		currX=iniX;
		currY=iniY;
	}
	private void iniValue(int place){
		switch(place){
			case 0:
				iniX=100;
				iniY=10;
			break;
			case 1:
				iniX=300;
				iniY=10;
			break;
			case 2:
				iniX=100;
				iniY=200;
			break;
			case 3:
				iniX=300;
				iniY=200;
			break;
		}
	}
	public void setSize(int size){
		if(size<3&&size>-1){
			level=size;
		}else{
			level=0;
		}
	}
	public void nextX(int step){
		int limitX=0;
		switch(placeName){
			case 0:
				limitX=200;
			break;
			case 1:
				limitX=400;
			break;
			case 2:
				limitX=200;
			break;
			case 3:
				limitX=400;
			break;
		}
		if(currX<limitX){
			currX=iniX+offsetX*step;
		}
	}
	public void nextY(int step){
		int limitY=0;
		switch(placeName){
			case 0:
				limitY=50;
			break;
			case 1:
				limitY=50;
			break;
			case 2:
				limitY=240;
			break;
			case 3:
				limitY=240;
			break;
		}
		if(currY<limitY){
			currY=iniY+offsetY*step;
		}
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public int getCurrX(){
		return currX;
	}
	public int getCurrY(){
		return currY;
	}
	private void changeSize(){
		switch(level){
			case 0:
				width=190;
				height=190;
			break;
			case 1:
				width=200;
				height=200;
			break;
			case 2:
				width=210;
				height=210;
			break;
		}
	}
}
