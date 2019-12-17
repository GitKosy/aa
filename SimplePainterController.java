import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

public class SimplePainterController {
	
	
	SimplePainterView _view;
	DrawingPanelView _drawView;
	
	public SimplePainterController() 
	{
		this._view = AppManager.getInstance().getView();
		this._view.addMenuButtonListener(new MenuButtonListener());
		this._view.addColorButtonListener(new ColorButtonListener());
		
		this._drawView = AppManager.getInstance().getDrawView();
		
	}
	
	public SimplePainterController(SimplePainterView view) {
		this._view = view;
		this._view.addMenuButtonListener(new MenuButtonListener());
	}//constructor
	
	private class ColorButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			Object obj = e.getSource();
			
			if(obj==_view.btnColor)
			{
				Color c = JColorChooser.showDialog(_view, "»ö±ò¼±ÅÃ±â", _drawView.getDrawColor());
				_drawView.setDrawColor(c);
			}
		}
		
	}
	
	private class MenuButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			
			_view.setVisibleAllOptionComponents(true);
			
			for(int i =0 ; i<8; i++) {
				if(obj == _view.btnMenuArray[i]) {
					_drawView.setDrawMode(i);
					
					switch(i) {
					case SimplePainterConstants.DOT:
						_view.lblSizeNWidth.setText("SIZE");
						_view.chkFill.setVisible(false);
					break;
					case SimplePainterConstants.LINE:
						_view.lblSizeNWidth.setText("WIDTH");
						_view.chkFill.setVisible(false);
					break;
					case SimplePainterConstants.RECT:
						//lblSizeNWidth.setText("WIDTH");
					//break;
					case SimplePainterConstants.OVAL:
						_view.lblSizeNWidth.setText("WIDTH");
					break;
					}//switch
				}//if
				
			}//for
		}//actionPerformed
		
	}

}// SimplePainterController class
