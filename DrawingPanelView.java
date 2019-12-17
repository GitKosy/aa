import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

//�׸��� �г� Ŭ����
public class DrawingPanelView extends JPanel 
{
	private ArrayList<DrawDataModel> dataList;		
	//�׷��� ��ü���� ������ ��� �ִ� DrawDataModel�� �迭�̴�.
	//�׷��� ��ü���� ȭ��� �����ְԲ��Ѵ�. ( ��ϰ��� )
	
	private DrawDataModel drawData;
	//���� �׷����� ��ü�� �ޱ� ���� DrawDataModel ��ü�̴�.
	
	private DrawListener drawL;
	//�׸��� �׸��� ���� ������ ��ü
	
	private SimplePainterView _view;
	
	//������
	public DrawingPanelView() 
	{
		//�г��� �⺻��������
		setBackground(Color.white);
		setBounds(0,0,550,500);
		setBorder(BorderFactory.createTitledBorder("DRAW"));
		
		//�׸���� ���õ� ��ü���� ����
		dataList = new ArrayList<DrawDataModel>();
		drawData = new DrawDataModel();
		
		AppManager.getInstance().setDrawView(this);
		_view = AppManager.getInstance().getView();
		
		
		//DrawingPanelView�� �����ʸ� �����Ѵ�.
		drawL = new DrawListener();
		addMouseListener(drawL);
		addMouseMotionListener(drawL);
		
		//_view = AppManager.getInstance().getView();			//������ �ߺ��ε� �����ǹ������� ���� �ּ�ó��
		
	}//constructor

	
	public void paintComponent(Graphics page) 
	{
		super.paintComponent(page);
		
		
		switch(drawData.nDrawMode)		//���� �׷����� ��ü�� ���� �б� 
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
		
		//������ �׷��� ��ü���� ����Ѵ�.
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
					System.out.println("���� ��");
			}//switch
		}//for
	}//paintComponent
	
	//���� �׷����� ��ü�� ������ �����Ѵ�.
	public void setDrawColor(Color color)
	{
		drawData.color = color;
	}//setDrawColor
	
	//���� �׷����� ��ü�� ������ ��ȯ�Ѵ�.
	public Color getDrawColor()
	{
		return drawData.color;
	}//getDrawColor
	
	//���� �׸��� ��尪�� �����޴´�.
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
				drawData.pt1 = e.getPoint();   // ��ǥ����
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
