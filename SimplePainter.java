
import javax.swing.JFrame;

//���α׷��� ������� ���� �����̴�.
public class SimplePainter 
{
	public static void main(String[] args) {
		System.out.println("SimplePainter _ main msg : program start");
		
		//������ ����
		JFrame frame = new JFrame("SIMPLE PAINTER");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);									
		
		
		SimplePainterView primary = new SimplePainterView();
		SimplePainterController	controller = new SimplePainterController();
		
		
		frame.getContentPane().add(primary);
		frame.pack();
		frame.setVisible(true);
	}

}
