package hu.ttk;

import hu.ttk.ui.cv.CVWindow;

public class Start {

	public static void main(String[] args) {
		CVWindow wnd= new CVWindow("CV", 1000, 500);
		wnd.initUI();
		wnd.setVisible(true);
	}

}
