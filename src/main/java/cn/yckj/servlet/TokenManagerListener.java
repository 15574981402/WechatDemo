package cn.yckj.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import weixin.popular.support.TokenManager;

public class TokenManagerListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//WEB瀹瑰櫒 鍒濆鍖栨椂璋冪敤
		TokenManager.init("wx39ac19816b9bb92a", "4307de092110f508ab6026e65c971e25");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//WEB瀹瑰櫒  鍏抽棴鏃惰皟鐢�
		TokenManager.destroyed();
	}
}
