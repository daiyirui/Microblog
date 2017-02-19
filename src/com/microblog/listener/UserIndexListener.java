package com.microblog.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.microblog.biz.IUsersBiz;
import com.microblog.biz.impl.UsersBizImpl;
import com.microblog.po.Users;

public class UserIndexListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent content) {
		// TODO Auto-generated method stub
        IUsersBiz userBiz=new UsersBizImpl();
        List<Users> lisUser=userBiz.SelectByListener();
        content.getServletContext().setAttribute("userListListener", lisUser);
	}

}
