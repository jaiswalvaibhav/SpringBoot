# Spring Configuration Styles

Spring offers **3 types of configuration styles**:
1. **XML-based configurations**
2. **Annotations-based configurations**
3. **Java-based configurations**

---------------

## 📄 Annotations-based Configurations:

### Terms:
- **Bean**: A Java object managed by Spring.

- **IoC (Inversion of Control)**: Spring manages the wiring (connecting components of a package having multiple classes).

- **DI (Dependency Injection)**: Spring provides dependencies to your classes.

- **Spring MVC**: a web framework inside the larger Spring ecosystem, specifically designed to build web applications using the Model-View-Controller (MVC) pattern. 
  - Spring MVC follows the MVC Pattern:
  - 1) Model → the data and business logic.
  - 2) View → the UI (HTML, JSP, Thymeleaf, JSON).
  - 3) Controller → the part that handles requests and coordinates Model & View.
  - Spring MVC Provides:
  - ✅ A front controller: DispatcherServlet
  - ✅ URL routing & mapping
  - ✅ Binding HTTP requests to Java objects (@RequestParam, @PathVariable, @ModelAttribute)
  - ✅ Validation & error handling
  - ✅ Flexible view rendering (HTML, JSON, XML, etc.)
  - In summary, Spring MVC = the web framework that handles HTTP requests, routing, and responses.

- In a Spring Boot application, Spring MVC is the core web engine, while Boot simply auto-configures it and runs it on an embedded server.

- What is JSP in spring boot?
  - In the context of Spring Boot, JSP (JavaServer Pages) is a technology used to create dynamic web pages. It allows embedding Java code within HTML or XML pages, making it easier to generate dynamic content in response to user interactions. Spring Boot, being a popular framework for building efficient and scalable Java applications, can be integrated with JSP to create such web applications.

- **Flow of Spring Data JPA: Spring Data JPA → JPA → Hibernate → EntityManager → TypedQuery**

- **JPA (Java Persistence API)**
  - A specification (interface) in Java EE / Jakarta EE. 
  - Defines how to map Java objects to database tables (ORM — Object-Relational Mapping). 
  - Standard API with interfaces like:
    - EntityManager 
    - Query / TypedQuery
    - EntityTransaction
  - JPA defines an interface (EntityManager) — just a contract. 
  - Hibernate is an implementation of JPA — it provides a concrete class that implements EntityManager.

- **Hibernate**
  - An implementation of JPA (and more). 
  - Provides the actual working code behind JPA. 
  - Hibernate:
    - Implements the JPA interfaces (EntityManager, etc.). 
    - Adds extra features (like better caching, HQL, criteria API, etc.).

- **EntityManager**
  - A JPA interface used to:
    - Manage entities (create, read, update, delete)
    - Create queries (Query, TypedQuery, or CriteriaQuery`).
    - Manage transactions (if not using Spring-managed transactions).
  - Think of it as the “main interface” to interact with the persistence context (session).

- **TypedQuery**
  - A JPA interface representing a type-safe query.
  - Created by EntityManager to run JPQL (Java Persistence Query Language) or Criteria queries.
  - Returns strongly-typed results.

-**Spring Data JPA**
  - Not a JPA provider like Hibernate. Spring Data JPA uses Hibernate as a default JPA provider. It is an abstraction layer developed on top of JPA.
  - If you want DAO or Repository layer on Relational DBs in your SpringBoot project, then you can use Spring Data JPA
  - Simplifies JPA by:
    1) Providing CrudRepository, JpaRepository, etc. 
    2) Eliminates the amount of boilerplate code (like EntityManager, TypedQuery, etc.) required to implement data access object (DAO) layer. 
    3) Automatically generating queries from method names or @Query.
  - Spring Data JPA is not a JPA provider. It simply "hides" the Java Persistence API (and the JPA provider) behind its repository abstraction.
  - Spring Data JPA has a JPARepository interface which internally uses all the JPA provided methods (like Flush, Save, Delete, FindAll) and all these methods uses EntityManager or TypeQuery API internally to perform different operations on the DB.

| Layer              | What it does                  | Example                 |
| ------------------ | ----------------------------- | ----------------------- |
| 🌐 JPA             | API (interfaces, rules)       | Defines `EntityManager` |
| ⚙️ Hibernate       | Implementation of JPA         | Provides working code   |
| 🖋️ EntityManager  | Interface to interact with DB | `.persist()`, `.find()` |
| 🔍 TypedQuery      | Type-safe query object        | `.getResultList()`      |
| 🪄 Spring Data JPA | Spring abstraction on JPA     | `UserRepository`        |

| Concept         | Analogy                        |
| --------------- | ------------------------------ |
| JPA             | Interface or blueprint         |
| Hibernate       | Concrete implementation        |
| EntityManager   | Tool to perform operations     |
| TypedQuery      | A specific query               |
| Spring Data JPA | Automation that hides the tool |

---

## 🔗 List of Annotations:

### 1️⃣ `@Component`
- Marks a class as a **Spring Bean / Component**.
- Tells Spring to auto-detect, create, and manage this class as a bean.
- By default, the bean name will be the class name with the **first letter in lowercase**.  
  e.g., `PizzaController` → bean name = `pizzaController`

---

### 2️⃣ `@Autowired`
- Automatically injects a bean into another bean.
- Can be used for:
    - Constructor Injection
    - Setter Injection
    - Field Injection

---

### 3️⃣ `@Qualifier`
- Used with `@Autowired` to resolve ambiguity  while injecting bean to controller when multiple beans of the same type exist.
- Specifies which bean to inject.

---

### 4️⃣ `@Primary`
- We use @Primary annotation to give higher preference to a bean when there are multiple beans
  of the same type.    
- Marks one bean as the default when multiple beans of the same type are available.

---

### 5️⃣ `@SpringBootApplication`
- Shortcut for:
    - `@Configuration`
    - `@EnableAutoConfiguration`
    - `@ComponentScan`
- Marks the main Spring Boot configuration class.

---

### 6️⃣ `@Bean` & `@Configuration`
- This way is an alternative of @Component on a class which is an Java based configuration. 
- `@Configuration` marks a class that contains Spring bean definitions.
- `@Bean` marks a method that returns an object to be registered as a bean using Java based configuration. By default, the bean name is same as method name. We can
specify bean name using @Bean(name = "beanName").
- `@Bean` annotation provides initMethod and destroyMethod attributes to perform certain actions after bean initialization or before bean destruction by a container
---

### 7️⃣ Spring Stereotype Annotations - `@Controller`, `@Service`, `@Repository`
- Specializations of or Derived from  `@Component`:
    - `@Controller`: Marks a web controller class and creates bean of the controller class
    - `@Service`: Marks a service/business logic class  and creates bean
    - `@Repository`: Marks a data access class (DAO layer) and creates bean

---

### 8️⃣ `@Lazy`
- Eager or Lazy govern when the bean is created;
- Marks a bean to be created lazily (when first needed), rather than at default startup.
- By default, Spring creates all singleton beans eagerly at the startup / bootstrapping of the application context.
- You can load the Spring beans lazily (on-demand) using @Lazy annotation
- @Lazy annotation can used with **@Configuration, @Component and @Bean annotations**
- Eager intialization is recommended: to avoid and detect all possible errors immediately rather than at runtime.
- If lazy initialisation starts an erroneous bean, it'll break the application in the middle of running the application

**Summary:**
- BeanDefinition (config) → Always loaded at startup, no matter what.
- Bean instance (actual object) → Lazy or eager decides when it’s created.
---

### 9️⃣ `@Scope`
- Defines the scope of a bean (singleton, prototype, request, session, etc.).
- The latest version of the Spring framework defines 6 types of scopes:
  • singleton
  • prototype
  • request
  • session
  • application
  • websocket
- The last four scopes mentioned, request, session, application and websocket, are only available in a web-aware application.
- @Scope annotation is used to define a scope of the bean
- We use @Scope to define the scope of a @Component class or a @Bean definition.
- Singleton and Prototype govern the no. of instances of bean created
- BeanDefinition (config) → Always loaded at startup, no matter what.
- Singleton: only one instance of the bean is created and shared across the entire application. This is the default scope.
- Prototype: a new instance of the bean is created every time it is requested.
---

### 🔧 Configuration & Properties

### 🔟 `@Value`
- Spring @Value annotation is used to assign default values to variables and method arguments.
- @Value annotation is mostly used to inject values for specific property key from the properties file or env variables

### 1️⃣1️⃣ `@PropertySource` / `@PropertySources`
- Spring @PropertySource annotation is used to provide application.properties file to Spring Environment.
- Spring @PropertySources annotation is used to provide multiple properties files to Spring Environment.
- This annotation is used with @Configuration classes.
- Spring @PropertySource annotation is repeatable, means you can have multiple PropertySource on a Configuration class.
- We can use @Value annotation and Environment class to read, the Property file
- Avoid using same parameter variables in different .properties file, else there will be a clash and below order of overwrite will be followed.
- **Order in which SpringBoot reads properties:**
- 1️⃣ Command-line arguments
- 2️⃣ application.properties / application.yml
- 3️⃣ @PropertySource files (if explicitly loaded later in configuration)
- 4️⃣ Default values in code
- Thus, if the variable name is same in application.properties and customapp.properties, then customapp.properties will be used.
- Important Note: The primary application.properties can be read as a .yaml file as well, whereas any other custom .properties cant be read from .yaml file
- We can also use "Environment" object to read .properties files


### 1️⃣2️⃣ `@ConfigurationProperties`
- Binds a group of properties (from `.properties` or `.yaml`) to a Java object.

---

## 🌐 Web & REST Annotations

### 1️⃣3️⃣ `@Controller`
- `@Controller`: Marks a controller class.
- Spring provides @Controller annotation to make a Java class as a Spring MVC controller.
- Controller in Spring MVC web application is a component that handles incoming HTTP requests after DispatcherServlet takes request coming from Tomcat Server
- @Controller annotation is simply a specialization of the @Component class, which allows us to auto-detect implementation classes through the classpath scanning.
- We typically use @Controller in combination with a @RequestMapping annotation for request handling methods.
- **Request flow**:
  - 1️⃣ Request comes in → Tomcat handles it 
  - 2️⃣ Passes it to DispatcherServlet (Spring MVC front controller)
  - 3️⃣ DispatcherServlet finds the appropriate controller method (/hello)
  - 4️⃣ Executes controller method → returns response

### '@ResponseBody`
- `@ResponseBody`: Writes the return value of a method directly to the HTTP response body.
- To develop RESTFul web services using Spring MVC, we need to use @Controller and @ResponseBody annotations.
```java
@ResponseStatus(HttpStatus.CREATED)
@PostMapping("/user")
public User createUser() {
    return new User("John");
}
```

### 1️⃣4️⃣ `@RestController`
- Shortcut for `@Controller` + `@ResponseBody`.
- Used for REST APIs.

---

### Request Mapping Annotations

#### 1️⃣5️⃣ `@RequestMapping`
- @RequestMapping Maps HTTP web requests to the controller class as well as handler methods.
- By default, @RequestMapping is a GET request
- Examples:
  - @RequestMapping with Class
  - @RequestMapping with Method
  - @RequestMapping with Multiple URI
  - @RequestMapping with HTTP Method
  - @RequestMapping with Produces and Consumes
    - ✅ consumes — what content types this endpoint accepts from the client (Content-Type header).
    - ✅ produces — what content types this endpoint can return (Accept header).
- If a request consumes json and xml & produces json and xml:
  - You can send a POST request with body as either JSON or XML: Content-Type: application/json or Content-Type: application/xml
  - And the response will be either JSON or XML, depending on what you set in the Accept header: Accept: application/json or Accept: application/xml
- Eg. <pre>
  POST /api/greet
  Content-Type: application/json
  Accept: application/xml
  {
    "name": "Vaibhav"
  }</pre>

#### 1️⃣6️⃣ `@GetMapping`
- GET HTTP request is used to get a single or multiple resources
- Shortcut for `@RequestMapping(method = RequestMethod.GET)`.

#### 1️⃣7️⃣ `@PostMapping` & `@RequestBody`
- POST request is responsible for creating a resource
- `@PostMapping`: Maps HTTP POST requests to specific handler methods
- Shortcut: @RequestMapping(method = RequestMethod.POST)
- `@RequestBody`: Binds the HTTP request body sent by client to a method parameter by converting the request to a java object

#### 1️⃣8️⃣ `@PutMapping`
- PUT is used to update a resource
- Maps HTTP PUT requests to specific handler methods
- Shortcut: @RequestMapping(method = RequestMethod.PUT)

#### 1️⃣9️⃣ `@DeleteMapping`
- Maps HTTP DELETE requests to specific handler methods
- Shortcut: @RequestMapping(method = RequestMethod.DELETE)

### 🔷 `ResponseEntity`
- Wrapper for the entire HTTP response: **status code, headers, and body**.
- Gives full control over the HTTP response.
- Useful for building expressive and RESTful APIs.

#### Features of `ResponseEntity` and why was it needed:
- ✅ If you just need to always return a fixed status → @ResponseStatus is fine and clean whereas if you need dynamic status or headers → use ResponseEntity.
- ✅ Set custom HTTP status codes (e.g., 201 Created, 404 Not Found).
- ✅ Add custom headers.
- ✅ Return a body, or no body.
- ✅ Works well with content negotiation and REST principles.

#### Examples:

#### 1) Basic — Body with default status (200 OK)
```java
  @GetMapping("/hello")
  public ResponseEntity<String> hello() {
      return ResponseEntity.ok("Hello World");
  }
```
#### 2) Custom Status and Headers
```java
@PostMapping("/create")
public ResponseEntity<String> create() {
  HttpHeaders headers = new HttpHeaders();
  headers.add("X-Custom-Header", "myvalue");

  return new ResponseEntity<>("Created!", headers, HttpStatus.CREATED);
}

```
#### 3) No Body — Status Only
```java
@DeleteMapping("/delete")
public ResponseEntity<Void> delete() {
    return ResponseEntity.noContent().build(); // HTTP 204
}
```

### `Comparison b/w RequestBody and ResponseStatus`

- Scenario: Create a new user
  1) Using @ResponseStatus
  
  ```java
  @RestController
  @RequestMapping("/users")
  public class UserController {
  
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED) // always 201 if method executes
     public User createUser(@RequestBody User user) {
         validate(user); // throws if invalid
         return userService.save(user);
     }
  }
  ```
  📋 Behavior:
  ✅ Always returns: HTTP 201 Created
  Body: the saved User
  If something goes wrong (e.g., invalid input), we need to throw a custom exception with its own @ResponseStatus to set a different code — which is static & declared elsewhere.
  E.g.:
  ```java
  @ResponseStatus(HttpStatus.CONFLICT)
  public class UserAlreadyExistsException extends RuntimeException {}
  ```
  So you have to design & annotate exceptions for every alternate response.
  
  🌟 Using ResponseEntity
  ```java
  @RestController
  @RequestMapping("/users")
  public class UserController {
  
     @PostMapping
     public ResponseEntity<?> createUser(@RequestBody User user) {
         if (!isValid(user)) {
             return ResponseEntity
                     .badRequest()
                     .body("Invalid user data");
         }
  
         if (userService.exists(user)) {
             return ResponseEntity
                     .status(HttpStatus.CONFLICT)
                     .body("User already exists");
         }
  
         User saved = userService.save(user);
  
         URI location = URI.create("/users/" + saved.getId());
         return ResponseEntity
                 .created(location) // HTTP 201 + Location header
                 .body(saved);
     }
  }
  ```
  📋 Behavior:
  ✅ Can dynamically decide:
  400 if input is invalid
  409 if already exists
  201 if created, with Location header and body
  All of this logic is in one place and does not require pre-defined exception classes or annotations.
  
  | Feature                  | `@ResponseStatus`              | `ResponseEntity`              |
  | ------------------------ | ------------------------------ | ----------------------------- |
  | 📄 Status code           | Static, fixed (via annotation) | Dynamic, set at runtime       |
  | 📄 Headers               | No                             | Yes                           |
  | 📄 Body                  | Yes                            | Yes                           |
  | 📄 Logic-based responses | ❌ Needs custom exceptions      | ✅ Inline & flexible           |
  | 📄 Simplicity            | ✅ Clean, declarative           | ❌ Slightly more verbose       |
  | 📄 Best for              | Simple, predictable outcomes   | Complex, conditional outcomes |


### Request Parameters

#### 2️⃣0️⃣ `@PathVariable`
- This annotation is used on a method argument to bind the value of a URI template variable to a method argument.

#### 2️⃣1️⃣ `@RequestParam`
- Binds request's query parameters or form parameters to a method parameter.

---

## 🚀 Bootstrapping & Scanning

### `@SpringBootApplication`
- It comprises @SpringBootConfiguration (which comprises of @Configuration), @EnableAutoConfiguration and @ComponentScan

### `@SpringBootConfiguration`
- We can do these 3 types of configurations in a springboot application: XML, Java, Annotation
- For Java based configuration, we use @Configuration like we made AppConfig file in config folder
- So, we use @SpringBootConfiguration to provide java based configuration

### 2️⃣2️⃣ `@EnableAutoConfiguration`
- Enables Spring Boot’s auto-configuration classes for SpringBoot application.
- Whenever our application finds spring-starter web dependency in our classpath then 
- When you start a Spring Boot application:
  1) It scans your classpath (your Maven/Gradle dependencies)
  2) Figures out which technologies you’re using (like Tomcat, Thymeleaf, JPA, etc.)
  3) And automatically configures beans for those, so you don’t have to write boilerplate config.

- Example:
  For 'spring-boot-starter-web':
  ```xml
  <dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  ```
  📋 Spring Boot sees that you have:
  Spring MVC on the classpath
  Embedded Tomcat available
  
  ✅ So it automatically:
  Configures a DispatcherServlet
  Starts an embedded Tomcat server
  Sets up sensible defaults (e.g., / mapped to index page)
  You don’t need to write any XML or Java config for these yourself.

### 2️⃣3️⃣ `@ComponentScan`
- Scans the base package and sub-package(s) for Spring components (beans).
- It scans the specified package (and its subpackages) for classes annotated with:
  ✅ @Component
  ✅ @Service
  ✅ @Repository
  ✅ @Controller
  ✅ or any custom stereotype annotations.
- Example: What happens here?
  1) Spring scans the package com.example.myapp and all its subpackages.
  2) Finds all classes annotated with @Component (or variants).
  3) Registers those as beans in the Spring ApplicationContext (and then we can use context.getBean(ClassName.class) to fetch the bean)

---