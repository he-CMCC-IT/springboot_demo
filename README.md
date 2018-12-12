

## springboot实现数据库的增删查改

#### 开发工具：Intellij IDEA、mysql
#### 开发环境：Java version1.8
#### 所用技术：springboot+mybatis+mysql
<br>

### springboot 项目的模块介绍

* **实体层 \springboot_demo\src\main\java\com\springboot\springboot_demo\entity 编写实体类User与数据库表中的属性元素保持一致**


          public class User {
             private int id;
             private String name;
             private String password;
             private String number;
             private String birthday;
             private String job;
             private String gender;
             ...
         }




* **映射层 \springboot_demo\src\main\java\com\springboot\springboot_demo\mapper\ 编写映射类UserMapper类和映射文件UserMapper.xml<br>xml文件是mapper的配置文件<br>Mapper用于映射SQL语句,是MyBatis操作数据库的核心特性**



               public interface UserMapper {
                  List<User> findUserByName(String name);
                  public List<User> ListUser();
                  public User insertUser(User user);
                  public int delete(int id);
                  public int Update(User user);
              }

* **业务层  \springboot_demo\src\main\java\com\springboot\springboot_demo\service\ 编写主体业务和方法类UserService <br> 该类中主要实现了增、删、改、查四种方法定义**



                    public class UserService {
                       @Autowired
                       //    @Override
                       private UserMapper userMapper;
                       public List<User> findUserByName(String name) {
                           return userMapper.findUserByName(name);
                       }
                       public User insertUser(User user) {
                           userMapper.insertUser(user);
                           return user;
                       }
                       public List<User> ListUser(){
                           return  userMapper.ListUser();
                       }
                       public int Update(User user){
                           return userMapper.Update(user);
                       }
                       public int delete(int id){
                           return userMapper.delete(id);
                       }

                   }



* **控制层  \springboot_demo\src\main\java\com\springboot\springboot_demo\controller\ 主要是负责业务模块流程的控制，调用service的方法<br>该类提供了前端请求映射地址**




               @RestController
               @RequestMapping(value = "/UserController", method = { RequestMethod.GET, RequestMethod.POST })
               public class UserController {
                   @Autowired
                   private UserService userservice;

                   @RequestMapping("/ListUser")
                   @ResponseBody
                   public List<User> ListUser(){
                      return userservice.ListUser();
                   }

                   //对数据库进行查找操作
                   @RequestMapping("/ListUserByname")
                   @ResponseBody
                   public List<User> ListUserByname(String name){
                      return userservice.findUserByName(name);
                   }

                  ...
              }



<br>

### springboot 项目启动方法

1. 自行安装mysql数据库，项目运行之前需要创建数据库和数据表，数据库名为mybatis，数据表名为User
2. springboot+mybatis\demo_1\src\main\resources\application.properties文件中,修改数据库用户名和密码为本机的用户名、密码
3. 运行




          #mysql
          spring.datasource.url = jdbc:mysql://localhost:3306/mybatis
          spring.datasource.username = root
          spring.datasource.password = 123456
          spring.datasource.driverClassName = com.mysql.jdbc.Driver
          spring.datasource.max-active=20   //指定连接池中最大的活跃连接数
          spring.datasource.max-idle=8       //指定连接池中连接的最大的空闲连接数量
          spring.datasource.min-idle=8       //指定必须保持连接的最小值
          spring.datasource.initial-size=10    //指定启动连接池时，初始建立的连接数量

          mybatis.mapper-locations= classpath:mapper/*.xml         //mapper映射文件路径
          mybatis.typeAliasesPackage= com.springboot.springboot_demo.entity      //实体路径
          #服务器端口号
          server.port=8080
          #加入demo前缀
          server.servlet.context-path=/demo



