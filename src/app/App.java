package app;

import data.DataUtil;

public class App {

	public static void main(String[] args) {

		DataUtil dataUtil = new DataUtil();
		dataUtil.loadHotel();
		AppBoundary boundary = new AppBoundary();
		boundary.display();

	}

}
