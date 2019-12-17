import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//���α׷����� �������� �κ��� �����Ѵ�.
public class SimplePainterView extends JPanel 
{
	/* �ν��Ͻ� ���� */
	
	private DrawingPanelView 	drawView;
	//�׸����г��̴�. DrawingPanelView Ŭ������ ��ü
	
	private JPanel				optionPanel,MenuBasePanel;
	//�׸��� ��ü���� �� ���������� ���� ��ɵ��� ����ϴ� �г��̴�.
	
	private JPanel				menuPanel,msgPanel;
	//�޴���ư�� ����� �׸����гΰ� �޼����� ����� �޼����г��̴�.
	
	public JButton[]				btnMenuArray;
	//�޴���ư��ü _ menuPanel���� ����� �ȴ�.
	
	public JLabel					lblSizeNWidth;
	//��ü�� ũ�⸦ �����ϴ� ���� �ȳ��ϴ� ��
	
	public JTextField			txtSizeNWidth;
	//��ü�� ũ�⸦ �����ϴ� �ؽ�Ʈ�ʵ�
	
	public	JButton				btnColor;
	//����ǥ�� ����ϴ� ��ư�̴�.
	
	public	JCheckBox				chkFill;
	//üũ�ڽ�
	
	/* �ν��Ͻ� �� */
	
	//Constructor
	public SimplePainterView()//hovering�� controller�� �ֱ� 
	{
		//Panel ��������
		System.out.println("SimplePainterView _ Constructor msg : Created");
		setBackground(Color.white);
		setPreferredSize(new Dimension(710,710));
		setLayout(null);

		
		AppManager.getInstance().setView(this);
		System.out.println("SimplePainterView _ Constructor msg : AppManager SetView");
		
		//�׸���ȭ�� ���
		drawView = new DrawingPanelView();		
		add(drawView);
		
		
		//�ý��۸޼��� ����г�
		msgPanel = new JPanel();
		msgPanel.setBackground(Color.white);
		msgPanel.setBounds(560,0, 145, 500);
		msgPanel.setBorder(BorderFactory.createTitledBorder("MESSAGE"));
		add(msgPanel);
		
		//�ý��۸޴� ����г�
		MenuBasePanel = new JPanel();
		MenuBasePanel.setBackground(Color.white);
		MenuBasePanel.setBounds(5, 510, 700, 200);
		MenuBasePanel.setLayout(null);
		MenuBasePanel.setBorder(BorderFactory.createLineBorder(Color.green));
		add(MenuBasePanel);
		
		//�޴� ��ư�׸��� �г� _ �ý��۸޴� ����гο� ���Եȴ�.
		menuPanel = new JPanel();
		menuPanel.setBackground(Color.white);
		menuPanel.setBounds(10, 10, 350, 175);
		menuPanel.setBorder(BorderFactory.createTitledBorder("MENU"));
		menuPanel.setLayout(new GridLayout(2,4));
		MenuBasePanel.add(menuPanel);
		
		//�ɼ� ��ư�׸��� �г�
		optionPanel = new JPanel();
		optionPanel.setBackground(Color.white);
		optionPanel.setBounds(370, 10, 320, 175);
		optionPanel.setBorder(BorderFactory.createTitledBorder("OPTION"));
		MenuBasePanel.add(optionPanel);
		
		
		btnMenuArray = new JButton[8];
		
		//�� ��ư��ü�� �޴� ��ư�׸��� �гο� �����Ѵ�.
		for(int i=0;i<8;i++) 
		{	
			btnMenuArray[i] = new JButton(SimplePainterConstants.MENU[i]);
			btnMenuArray[i].setBackground(SimplePainterConstants.MENU_COLOR[0]);
			btnMenuArray[i].setForeground(SimplePainterConstants.MENU_COLOR[1]);
			btnMenuArray[i].addMouseListener(new Hovering());
			menuPanel.add(btnMenuArray[i]);
		}
		
		//�� ��ü�� ũ������ �ȳ� �� : �ɼ� ��ư�׸��� �гο��� ����Ѵ�.
		lblSizeNWidth = new JLabel("SIZE : ");
		lblSizeNWidth.setVisible(false);
		optionPanel.add(lblSizeNWidth);
		
		//�� ��ü�� ũ������ �ؽ�Ʈ�ʵ� : �ɼ� ��ư�׸��� �гο��� ����Ѵ�.
		txtSizeNWidth = new JTextField(10);
		txtSizeNWidth.setVisible(false);
		optionPanel.add(txtSizeNWidth);
		
		//�� ��ü�� �������� ��ư : �ɼ� ��ư�׸��� �гο��� ����Ѵ�.
		btnColor = new JButton("COLOR");
		btnColor.setVisible(false);
		optionPanel.add(btnColor);
		
		//üũ��ư
		chkFill = new JCheckBox("Fill");
		chkFill.setVisible(false);
		optionPanel.add(chkFill);
		
	}//constructor
	
	//�������� ��ư�� �ܺ� �׼Ǹ����ʸ� �����Ѵ�.
	public void addColorButtonListener(ActionListener listener)
	{
		btnColor.addActionListener(listener);
	}//addColorButtonListener
	
	//�޴� ��ư �׸����гο� �ִ� �޴���ư�鿡 �ܺ� �׼Ǹ����ʸ� �����Ѵ�. ������� ��,�簢��,���� ���� ����Ű�� ��ư
	public void addMenuButtonListener(ActionListener listener) 
	{
		for(int i=0;i<8;i++) {
			btnMenuArray[i].addActionListener(listener);
		}
	}//addButtonListener()

	//�ɼ� �׸����г��� ��»��¸� �����Ѵ�. flag���� ���� ��¿��ΰ� ������.
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
