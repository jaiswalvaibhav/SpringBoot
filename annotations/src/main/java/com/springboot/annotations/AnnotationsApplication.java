package com.springboot.annotations;

import com.springboot.annotations.repository.MyRepository;
import com.springboot.annotations.controller.JavaDIPizzaController;
import com.springboot.annotations.controller.MyController;
import com.springboot.annotations.controller.PizzaController;
import com.springboot.annotations.lazy.LazyLoader;
import com.springboot.annotations.propertysource.PropertySourceDemo;
import com.springboot.annotations.scope.PrototypeBean;
import com.springboot.annotations.scope.SingletonBean;
import com.springboot.annotations.service.MyService;
import com.springboot.annotations.service.VegPizza;
import com.springboot.annotations.value.ValueAnnotationDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnnotationsApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(AnnotationsApplication.class, args);
		System.out.println();
		//SpringApplication.run asks Spring Boot to:
		//-> Start the Spring Application Context (it returns application context object);Application context acts as a spring IoC
		//-> Create all beans and inject dependencies
		//-> Start the embedded web server (if it’s a web app)
		//-> Run your application

		// @Component created Annotation based PizzaController spring bean, we'll try to fetch the pizza beans (@Component or @Bean) from Spring container

		System.out.println("---Topic: @Bean, @Component and @Autowired in Annotation and Java based configuration---");
		PizzaController pizzaController = context.getBean(PizzaController.class);//Tries to access default or primary bean

		// If no custom name is given to Component bean
//		PizzaController pizzaController = (PizzaController) context.getBean("pizzaController");
		// If custom name is given to Component bean
		// Accessing bean from Spring IoC container created by Annotation
//		PizzaController pizzaController = (PizzaController) context.getBean("pizza");
		System.out.println(pizzaController.getPizza() +" -> DI of NonVegPizza bean with @Autowired (via Annotation) in a Controller bean with @Component (via Annotation)");

		//Accessing bean from Application context which acts as Spring IoC container created by Java configuration
//		VegPizza vegPizza = context.getBean(VegPizza.class);
		VegPizza vegPizza = (VegPizza) context.getBean("vegPizzaBean");
		System.out.println(vegPizza.getPizza()+" -> DI of VegPizza bean with @Bean (via Java based config) in a  Controller bean with @Component (via Annotation)");


		// @Bean created Java based JavaDIPizzaController spring bean,  we'll try to fetch the pizza bean (@Bean) from Spring container
		JavaDIPizzaController javaDIPizzaController = context.getBean(JavaDIPizzaController.class);
		System.out.println(javaDIPizzaController.getPizza() +" -> DI of JainPizza bean with @Bean (via Java based config) in a Controller bean with @Bean (via Java based config)");
		System.out.println();

		System.out.println("---Topic:Stereotype Annotations---");
		//Accessing Stereotype MyController bean
		MyController myController = context.getBean(MyController.class);
		System.out.println(myController.hello());
		MyService myService = context.getBean(MyService.class);
		System.out.println(myService.hello());
		MyRepository myRepository = context.getBean(MyRepository.class);
		System.out.println(myRepository.hello());


		System.out.println("---Topic: LazyLoader---");
		//At this point, the application context will not have spring bean for LazyLoader
		//So, it will create here on demand when we will try to get the LazyLoader bean
		LazyLoader lazyLoader = context.getBean(LazyLoader.class); //The constructor of this LazyLoader bean is call now. W/o Lazy, it will be called at startup like EagerLoader
		System.out.println();

		//Scope of Bean
		System.out.println("---Topic: Scope of Bean---");

		SingletonBean singletonBean1 = context.getBean(SingletonBean.class);
		System.out.println(singletonBean1.hashCode());//Print hashcode
		SingletonBean singletonBean2 = context.getBean(SingletonBean.class);
		System.out.println(singletonBean2.hashCode());//Print hashcode
		SingletonBean singletonBean3 = context.getBean(SingletonBean.class);
		System.out.println(singletonBean3.hashCode());//Print hashcode
		//Above code prints "SingletoneBean .." only once i.e. object is created only once and accessed each time
		//Same hashcode is printed all 3 times

		PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);
		System.out.println(prototypeBean1.hashCode());//Print hashcode
		PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);
		System.out.println(prototypeBean2.hashCode());//Print hashcode
		PrototypeBean prototypeBean3 = context.getBean(PrototypeBean.class);
		System.out.println(prototypeBean3.hashCode());//Print hashcode
		//Above code prints "SPrototypeBean .." 3 times i.e. the object is created each time
		//Different hashcode is printed each time object is accessed
		System.out.println();


		//Value Annotation
		System.out.println("---Topic: Value Annotation---");
		ValueAnnotationDemo valueAnnotationDemo = context.getBean(ValueAnnotationDemo.class);
		System.out.println(valueAnnotationDemo.getDefaultName());
		System.out.println(valueAnnotationDemo.getMailHost());
		System.out.println(valueAnnotationDemo.getEmail());
		System.out.println(valueAnnotationDemo.getJavaHome());
		System.out.println(valueAnnotationDemo.getHomeDirectory());
		System.out.println();


		//Property Source Usage
		System.out.println("---Topic: Property Source Usage from multiple properties file---");
		System.out.println("-Values from mail.properties-");
		PropertySourceDemo propertySourceDemo = context.getBean(PropertySourceDemo.class);
		System.out.println(propertySourceDemo.getHost()+"\n"+propertySourceDemo.getEmail()+"\n"+propertySourceDemo.getPassword());
		System.out.println("-Values from messages.properties-");
		System.out.println(propertySourceDemo.getAppName()+"\n"+propertySourceDemo.getAppDescription());
		System.out.println();


		//At the end of all code execution or process
		//PreDestroy Bean Steps
//		System.out.println("---Perform required steps before destroying Beans---");

	}


}
