// AppManager에서 DrawingPanel과 PainterPanel을 관리할 수 있다.
public class AppManager 
{
	private static AppManager s_instance;		
	private SimplePainterView _view;			
	private DrawingPanelView _drawView;
	
	
	//메소드
	public void setView(SimplePainterView view) { _view = view;}
	public SimplePainterView getView() { return _view;}
	
	public void setDrawView(DrawingPanelView view) { _drawView = view;}
	public DrawingPanelView getDrawView() { return _drawView; };
	
	
	
	public static AppManager getInstance() 
	{
		if(s_instance == null) s_instance = new AppManager();
			return s_instance;
	}//getInstance()
	
}// AppManager class
