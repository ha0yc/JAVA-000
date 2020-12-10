package org.haoyc.assignment;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.haoyc.assignment.db.dao.ProductOrderMapper;
import org.haoyc.assignment.db.object.ProductOrder;
import org.haoyc.assignment.service.OrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

public class Application {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/mybatis/db.xml");
		OrderService service = new OrderService(context.getBean(ProductOrderMapper.class));
		Random random = new Random();

		int id = random.nextInt(100);
		int userId = random.nextInt(100);

		service.testInsert(id, userId);
		ProductOrder productOrder = service.testSelect(id, userId);
		System.out.println(ToStringBuilder.reflectionToString(productOrder, ToStringStyle.JSON_STYLE));
		service.testDelete(id, userId);
	}

}
