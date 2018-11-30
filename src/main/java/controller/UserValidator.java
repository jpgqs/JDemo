package controller;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

import entity.User;

public class UserValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		// TODO Auto-generated method stub
		validateRequiredString("user.name", "nameMsg", "请输入员工名字!");
//validateRequiredString("use.age","ageMsg","请输入员工年龄");
//		validateRequiredString("use.sex","sexMsg","请输入员工的性别");
		
	}

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
		c.keepModel(User.class);
		String actionkey = getActionKey();
		//System.out.println(actionkey);
		if(actionkey.equals("/user/submit"))
		c.render("add.jsp");
		else if(actionkey.equals("/user/update"))
		c.render("form.jsp");
	}

}
