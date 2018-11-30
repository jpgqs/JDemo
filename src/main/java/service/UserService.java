package service;

import java.util.List;


import com.jfinal.plugin.activerecord.Page;

import entity.User;

public class UserService {
	public List<User>findAll(){
	List<User> users=User.dao.find("select * from user");
	return users;
    //封装发到前端
	}
    //将数据在list页面中渲染出来
	public User findById(int id){
		return User.dao.findById(id);
	}
	public Page<User> paginate(int pageNumber, int pageSize) {
		return User.dao.paginate(pageNumber, pageSize, "select *", "from user order by id asc");
	}
    
}
