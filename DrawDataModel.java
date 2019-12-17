import java.awt.Color;
import java.awt.Point;

public class DrawDataModel 
{
	public int 			nDrawMode;		//그리기 모드
	public Point 			pt1,pt2;			//좌표값
	public int 			nSizeNWidth;		//너비
	public Color 			color;				//색깔
	public boolean 		bFill;				//체크박스
	
	//constructor
	public DrawDataModel() 
	{
		nDrawMode = SimplePainterConstants.NONE;
		pt1 = new Point();
		pt2 = new Point();
		nSizeNWidth = 1;
		color = Color.black;
		bFill = false;
	}//constructor
	
	public DrawDataModel(DrawDataModel obj) 
	{
		nDrawMode = obj.nDrawMode;
		pt1 = obj.pt1;
		pt2 = obj.pt2;
		nSizeNWidth = obj.nSizeNWidth;
		color = obj.color;
		bFill = obj.bFill;
	}//constructor
	
}//DrawDataModel class
