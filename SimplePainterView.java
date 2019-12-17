import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//프로그램에서 보여지는 부분을 관리한다.
public class SimplePainterView extends JPanel 
{
	/* 인스턴스 시작 */
	
	private DrawingPanelView 	drawView;
	//그리기패널이다. DrawingPanelView 클래스의 객체
	
	private JPanel				optionPanel,MenuBasePanel;
	//그리기 객체선택 및 서식지정에 관한 기능들을 출력하는 패널이다.
	
	private JPanel				menuPanel,msgPanel;
	//메뉴버튼을 출력할 그리드패널과 메세지를 출력할 메세지패널이다.
	
	public JButton[]				btnMenuArray;
	//메뉴버튼객체 _ menuPanel에서 출력이 된다.
	
	public JLabel					lblSizeNWidth;
	//객체의 크기를 지정하는 것을 안내하는 라벨
	
	public JTextField			txtSizeNWidth;
	//객체의 크기를 지정하는 텍스트필드
	
	public	JButton				btnColor;
	//색깔표를 출력하는 버튼이다.
	
	public	JCheckBox				chkFill;
	//체크박스
	
	/* 인스턴스 끝 */
	
	//Constructor
	public SimplePainterView()//hovering도 controller에 넣기 
	{
		//Panel 서식지정
		System.out.println("SimplePainterView _ Constructor msg : Created");
		setBackground(Color.white);
		setPreferredSize(new Dimension(710,710));
		setLayout(null);

		
		AppManager.getInstance().setView(this);
		System.out.println("SimplePainterView _ Constructor msg : AppManager SetView");
		
		//그리기화면 출력
		drawView = new DrawingPanelView();		
		add(drawView);
		
		
		//시스템메세지 출력패널
		msgPanel = new JPanel();
		msgPanel.setBackground(Color.white);
		msgPanel.setBounds(560,0, 145, 500);
		msgPanel.setBorder(BorderFactory.createTitledBorder("MESSAGE"));
		add(msgPanel);
		
		//시스템메뉴 출력패널
		MenuBasePanel = new JPanel();
		MenuBasePanel.setBackground(Color.white);
		MenuBasePanel.setBounds(5, 510, 700, 200);
		MenuBasePanel.setLayout(null);
		MenuBasePanel.setBorder(BorderFactory.createLineBorder(Color.green));
		add(MenuBasePanel);
		
		//메뉴 버튼그리드 패널 _ 시스템메뉴 출력패널에 삽입된다.
		menuPanel = new JPanel();
		menuPanel.setBackground(Color.white);
		menuPanel.setBounds(10, 10, 350, 175);
		menuPanel.setBorder(BorderFactory.createTitledBorder("MENU"));
		menuPanel.setLayout(new GridLayout(2,4));
		MenuBasePanel.add(menuPanel);
		
		//옵션 버튼그리드 패널
		optionPanel = new JPanel();
		optionPanel.setBackground(Color.white);
		optionPanel.setBounds(370, 10, 320, 175);
		optionPanel.setBorder(BorderFactory.createTitledBorder("OPTION"));
		MenuBasePanel.add(optionPanel);
		
		
		btnMenuArray = new JButton[8];
		
		//각 버튼객체를 메뉴 버튼그리드 패널에 삽입한다.
		for(int i=0;i<8;i++) 
		{	
			btnMenuArray[i] = new JButton(SimplePainterConstants.MENU[i]);
			btnMenuArray[i].setBackground(SimplePainterConstants.MENU_COLOR[0]);
			btnMenuArray[i].setForeground(SimplePainterConstants.MENU_COLOR[1]);
			btnMenuArray[i].addMouseListener(new Hovering());
			menuPanel.add(btnMenuArray[i]);
		}
		
		//각 객체의 크기지정 안내 라벨 : 옵션 버튼그리드 패널에서 출력한다.
		lblSizeNWidth = new JLabel("SIZE : ");
		lblSizeNWidth.setVisible(false);
		optionPanel.add(lblSizeNWidth);
		
		//각 객체의 크기지정 텍스트필드 : 옵션 버튼그리드 패널에서 출력한다.
		txtSizeNWidth = new JTextField(10);
		txtSizeNWidth.setVisible(false);
		optionPanel.add(txtSizeNWidth);
		
		//각 객체의 색깔지정 버튼 : 옵션 버튼그리드 패널에서 출력한다.
		btnColor = new JButton("COLOR");
		btnColor.setVisible(false);
		optionPanel.add(btnColor);
		
		//체크버튼
		chkFill = new JCheckBox("Fill");
		chkFill.setVisible(false);
		optionPanel.add(chkFill);
		
	}//constructor
	
	//색깔지정 버튼에 외부 액션리스너를 삽입한다.
	public void addColorButtonListener(ActionListener listener)
	{
		btnColor.addActionListener(listener);
	}//addColorButtonListener
	
	//메뉴 버튼 그리드패널에 있는 메뉴버튼들에 외부 액션리스너를 삽입한다. 예를들면 원,사각형,직선 등을 가리키는 버튼
	public void addMenuButtonListener(ActionListener listener) 
	{
		for(int i=0;i<8;i++) {
			btnMenuArray[i].addActionListener(listener);
		}
	}//addButtonListener()

	//옵션 그리드패널의 출력상태를 지정한다. flag값에 따라 출력여부가 나뉜다.
	public void setVisibleAllOptionComponents(boolean flag) 
	{
		lblSizeNWidth.setVisible(flag);
		txtSizeNWidth.setVisible(flag);
		btnColor.setVisible(flag);
		chkFill.setVisible(flag);
	}//setVisibleAllOptionComponents
	
	
	private class Hovering implements MouseListener
	{

		public void mouseEntered(MouseEvent e) {
			JButton btnevent = (JButton)e.getSource();
			btnevent.setBackground(SimplePainterConstants.MENU_COLOR[2]);
			btnevent.setForeground(SimplePainterConstants.MENU_COLOR[3]);
		}

		public void mouseExited(MouseEvent e) {
			JButton btnevent = (JButton)e.getSource();
			btnevent.setBackground(SimplePainterConstants.MENU_COLOR[0]);
			btnevent.setForeground(SimplePainterConstants.MENU_COLOR[1]);
		}

		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}

	}//Mouse Listener
	
}//SimplePainterView class
