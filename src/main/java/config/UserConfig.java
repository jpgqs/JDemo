package config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

import controller.UserController;
import entity.User;


public class UserConfig extends JFinalConfig {
    /**
     * 此方法用来配置 JFinal 常量值
     */
    public void configConstant(Constants me) 
    {
        //设置视图类型
        me.setViewType(ViewType.JSP);
        //读取外部文件
        PropKit.use("config.properties");
        me.setInjectDependency(true);
    }

    /**
     * 此方法用来配置 JFinal 访问路由
     */
    public void configRoute(Routes me) {
        //更改路由
    	//me.setBaseViewPath("/jDemo");
        me.add("/user", UserController.class);
    }

    /**
     * 此方法用来配置 JFinal 的 Plugin
     */
    public void configPlugin(Plugins me) {
        // TODO Auto-generated method stub
        //输入库连接池
       // C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("username"), PropKit.get("password"));
        //ORM Activerecord
       // ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
       // arp.setShowSql(true);
      //  arp.addMapping("user", User.class);
      //  me.add(c3p0Plugin);
      //  me.add(arp);
      DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("username"), PropKit.get("password").trim());
      		me.add(druidPlugin);
              ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
              arp.addMapping("user", "id", User.class);
              me.add(arp);
    }

    /**
     * 此方法用来配置 JFinal  的全局拦截器
     */
    public void configInterceptor(Interceptors me) 
    {
        // TODO Auto-generated method stub
    }
    /**
     * 此方法用来配置 JFinal 的 Handler
     */
    public void configHandler(Handlers me)
    {
    	ContextPathHandler path = new ContextPathHandler("base_path");
        me.add(path);

    }

    public void configEngine(Engine me) 
    {
    		me.setDevMode(true);
    		me.addSharedFunction("/user/_paginate.html");
    }
    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 80, "/", 5);
        System.out.println("成功");
    }

    

}

