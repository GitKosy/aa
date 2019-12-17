import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

//그리기 패널 클래스
public class DrawingPanelView extends JPanel 
{
	private ArrayList<DrawDataModel> dataList;		
	//그려진 객체들의 정보를 담고 있는 DrawDataModel의 배열이다.
	//그려진 객체들이 화면상에 남아있게끔한다. ( 기록관리 )
	
	private DrawDataModel drawData;
	//당장 그려지는 객체를 받기 위한 DrawDataModel 객체이다.
	
	private DrawListener drawL;
	//그림을 그리기 위한 리스너 객체
	
	private SimplePainterView _view;
	
	//생성자
	public DrawingPanelView() 
	{
		//패널의 기본서식지정
		setBackground(Color.white);
		setBounds(0,0,550,500);
		setBorder(BorderFactory.createTitledBorder("DRAW"));
		
		//그리기와 관련된 객체들을 생성
		dataList = new ArrayList<DrawDataModel>();
		drawData = new DrawDataModel();
		
		AppManager.getInstance().setDrawView(this);
		_view = AppManager.getInstance().getView();
		
		
		//DrawingPanelView에 리스너를 삽입한다.
		drawL = new DrawListener();
		addMouseListener(drawL);
		addMouseMotionListener(drawL);
		
		//_view = AppManager.getInstance().getView();			//문장이 중복인데 무슨의미인지를 몰라서 주석처리
		
	}//constructor

	
	public void paintComponent(Graphics page) 
	{
		super.paintComponent(page);
		
		
		switch(drawData.nDrawMode)		//현재 그려지는 객체에 대한 분기 
		{
			case SimplePainterConstants.LINE:
				Graphics2D page2 = (Graphics2D)page;
				page2.setStroke(new BasicStroke(drawData.nSizeNWidth));
				page.setColor(drawData.color);
				page.drawLine(drawData.pt1.x, drawData.pt1.y, drawData.pt2.x, drawData.pt2.y);
				break;
				
			case SimplePainterConstants.DOT:				
				page.fillOval(drawData.pt1.x, drawData.pt1.y, drawData.nSizeNWidth, drawData.nSizeNWidth);
				page.setColor(drawData.color);
				break;
		}//switch
		
		//기존에 그려진 객체들을 출력한다.
		for (DrawDataModel data:dataList)
		{
			page.setColor(data.color);			
			switch(data.nDrawMode)
			{
				case SimplePainterConstants.DOT:
					page.setColor(data.color);
					page.fillOval(data.pt1.x, data.pt1.y, data.nSizeNWidth, data.nSizeNWidth);
					break;
				
				case SimplePainterConstants.LINE:	
					Graphics2D page2 = (Graphics2D)page;
					page2.setStroke(new BasicStroke(data.nSizeNWidth));
					page.setColor(data.color);
					page.drawLine(data.pt1.x, data.pt1.y, data.pt2.x, data.pt2.y);
					break;
				
				default :
					System.out.println("범위 밖");
			}//switch
		}//for
	}//paintComponent
	
	//현재 그려지는 객체의 색깔을 지정한다.
	public void setDrawColor(Color color)
	{
		drawData.color = color;
	}//setDrawColor
	
	//현재 그려지는 객체의 색깔을 반환한다.
	public Color getDrawColor()
	{
		return drawData.color;
	}//getDrawColor
	
	//현재 그리기 모드값을 지정받는다.
	public void setDrawMode(int mode)
	{
		drawData.nDrawMode=mode;
	}//setDrawMode
	
	
	private class DrawListener implements MouseListener , MouseMotionListener
	{
		public void mouseClicked(MouseEvent e) 
		{	
			if(drawData.nDrawMode == SimplePainterConstants.DOT)
			{
				drawData.pt1 = e.getPoint();   // 좌표지정
				drawData.nSizeNWidth = Integer.parseInt(_view.txtSizeNWidth.getText());
				dataList.add(new DrawDataModel(drawData));
				repaint();
			}//if	
		}//mouseClicked
		
		public void mousePressed(MouseEvent e) 
		{	
			if(drawData.nDrawMode == SimplePainterConstants.LINE)
			{
				drawData.pt1 = e.getPoint();
				drawData.nSizeNWidth = Integer.parseInt(_view.txtSizeNWidth.getText());
			}
		}//mousePressed
		
		public void mouseDragged(MouseEvent e) 
		{
			if(drawData.nDrawMode == SimplePainterConstants.LINE)
			{
				drawData.pt2 = e.getPoint();
				repaint();
			}	
		}//mouseDragged
		
		public void mouseReleased(MouseEvent e) 
		{
			if(drawData.nDrawMode == SimplePainterConstants.LINE)
			{
				drawData.pt2 = e.getPoint();
				dataList.add(new DrawDataModel(drawData));
				repaint();
			}
		}//mouseeReleased

		public void mouseMoved(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
	}//Listener
	
	
	
}//DrawingPanelView class
