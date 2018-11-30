package controller;


import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

import entity.User;
import service.UserService;


public class UserController extends Controller 
{
  
	@Inject
	UserService userService ;
	//显示全部员工页面操作
    public void index(){
        
        //List<User> users=userService.findAll();
        
        //setAttr("users", users);
        Page<User> userPage=userService.paginate(getParaToInt(0, 1), 5);
        //System.out.println(userPage.getTotalPage());
        setAttr("userPage", userPage);
                
       // render("list.jsp");
        renderTemplate("list.html");
    }
   /*下面两个方法执行增加员工的操作*/
    public void add(){
        
       
        render("add.jsp");
    }
  
   @Before(UserValidator.class)
    public void submit(){
       
        User user=getModel(User.class,"user");
        user.save();
        
        redirect("/user");
    }
   //下面两个方法执行修改员工的操作
    public void edit(){
    	 Integer id=getParaToInt(0);
         if(id!=null&&id>0){
             setAttr("user", userService.findById(id));
         }
     	
         render("form.jsp");
    }
    @Before(UserValidator.class)
    public void update(){
        User user=getModel(User.class,"user");
        user.update();
        redirect("/user");
    }
    //删除操作
    public void del(){
        //得到前端传来的id，并执行sql语句
        User.dao.deleteById(getPara(0));
        render("/user");
    }
}


