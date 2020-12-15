package org.haoyc.assignment;

import org.haoyc.assignment.db.object.ProductOrder;
import org.haoyc.assignment.service.XaOrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

/**
 * @author haoyongchen
 */
public class Application {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/mybatis/db.xml");
		XaOrderService service = context.getBean(XaOrderService.class);
		Random random = new Random();

		int id = random.nextInt(100);
		int userId = random.nextInt(100);

		try {
			service.insertFailed(id, userId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		ProductOrder productOrder = null;
		try {
			productOrder =  service.testSelect(id, userId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (productOrder == null) {
			System.out.println("回滚成功！");
		} else {
			System.out.println("回滚失败！");
			service.testDelete(id, userId);
			System.out.println("清理完成！");
		}

	}

}
