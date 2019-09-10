# nacos-gateway
使用阿里开源组件nacos与spring的网关组件gateway搭建的集转发与配置与服务一体的网关配置注册中心
nacos 需要额外下载，官方地址：
https://nacos.io/zh-cn/


# 部署说明
需要同时部署nacos与该项目，并且需要注意一点，当前版本中的nacos仅支持2.0.x版本的springboot与对应的springcloud；
并且2.0.6会偶尔出现未知错误（道听途说）
# 作用
1、 注册中心&配置中心
nacos提供可视化的界面去管理当前的配置与注册在这里的服务，可以轻松的实现配置更改替换，服务优雅的上下线，并在多实例的情况下自动实现负载均衡
已经实现了动态加载路由转发表与配置（项目直接下载下来无法打开，需要在配置中心添加代码中要读取的配置或者注释掉testController中的代码，或者对这个类中的代码进行改造）
2、 网关
gateway作为cloud的官推网关组件，提供了强大的功能，目前该项目实现了路由转发与token验证的样例，其他功能视需求有待补充
3、服务注册
在提供服务的项目上引入pom.xml中的如下内容
        <!-- nacos 服务发现 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
            <version>0.2.2.RELEASE</version>
        </dependency>
（

如果要使用配置中心的功能还需要额外引入
         <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
            <version>0.2.2.RELEASE</version>
        </dependency>
        
        
）
然后配置spring.cloud.nacos.discovery.server-addr:127.0.0.1:8848
启动服务之后就能在nacos的可视化界面看到刚刚上线的服务了
（

配置spring.cloud.nacos.config.server-addr:127.0.0.1:8848
然后就可以取到配置中心中的文件了
注意：配置文件与项目的对应有自己的规则，这里只提供了基础的 路径的配置


）
