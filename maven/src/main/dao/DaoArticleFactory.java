package dao;

public class DaoArticleFactory {
	
	private static DaoArticle singleton=null;
	
	public static DaoArticle getInstance() {
		if(singleton == null) {
			singleton = new DaoArticleJdbc();
		}
		return singleton;
	}

}
