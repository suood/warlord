#spring源码
## AnnotationConfigApplicationContext

1. 

```java 
public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry 

/**
 * Create a new AnnotationConfigApplicationContext, deriving bean definitions
 * from the given annotated classes and automatically refreshing the context.
 * @param annotatedClasses one or more annotated classes,
 * e.g. {@link Configuration @Configuration} classes
 */
public AnnotationConfigApplicationContext(Class<?>... annotatedClasses) {
    this();
    register(annotatedClasses);
    refresh();
}
```   
<p>继承 GenericApplicationContext类，实现AnnotationConfigRegistry 接口。
下面的方法是 通过传入注解类的可变参数创建一个  AnnotationConfigApplicationContext对象
通常传入的类是使用@Configuration注解或者 Configuration 的子注解
</p>

```java
/**
 * Create a new AnnotationConfigApplicationContext that needs to be populated
 * through {@link #register} calls and then manually {@linkplain #refresh refreshed}.
 */
public AnnotationConfigApplicationContext() {
    this.reader = new AnnotatedBeanDefinitionReader(this);
    this.scanner = new ClassPathBeanDefinitionScanner(this);
}
```
<p>
创建AnnotatedBeanDefinitionReader 注解Bean定义阅读器
创建ClassPathBeanDefinitionScanner 类路径Bean类定义扫描器
</p>
