package com.microblog.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.microblog.dao.IUserDao;
import com.microblog.dao.impl.UserDaoImpl;
import com.microblog.po.Users;

public class UserIndexListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent content) {
		// TODO Auto-generated method stub
        IUserDao userBiz=new UserDaoImpl();
        List<Users> lisUser=userBiz.FindByListener();
        content.getServletContext().setAttribute("userListListener", lisUser);
	}

}
