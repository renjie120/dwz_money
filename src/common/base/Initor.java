package common.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 对spring的容器的一个监听器，监听spring的Spring Bean初始化的Event是：ContextRefreshedEvent。
 * 在容器加载完毕之后，进行cache的初始化！
 * @author renjie120
 * connect my:(QQ)1246910068
 *
 */
public class Initor implements ApplicationListener{

	@Autowired(required=false)  
    List<Initializable> initors;  
	
	public void onApplicationEvent(ApplicationEvent event) { 
        if(null==initors)  
        {  
            return;  
        }  
        if(event instanceof ContextRefreshedEvent)  
        {  
            for(Initializable initor:initors)  
            {  
            	System.out.println("容器加载完毕了，开始执行初始化结构.");
                initor.init();  
            }  
        }  
	}

}
