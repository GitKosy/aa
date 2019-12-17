
import javax.swing.JFrame;

//프로그램의 심장과도 같은 존재이다.
public class SimplePainter 
{
	public static void main(String[] args) {
		System.out.println("SimplePainter _ main msg : program start");
		
		//프레임 생성
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
