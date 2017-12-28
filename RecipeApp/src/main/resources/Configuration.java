
public class Configuration {

	/**
	 * 
	 * <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
		    destroy-method="close">
		    <property name="driverClassName" value="${jdbc.driver}" />
		    <property name="url" value="${jdbc.url}" />
		    <property name="username" value="${jdbc.username}" />
		    <property name="password" value="${jdbc.password}" />
  </bean>
  
  <bean class="com.shengwang.demo.HelloWorldBean">
    <constructor-arg value="${hellow.world.name}" />
  </bean>

  
  <!-- ====================================================== -->
  <!-- import different variables according to active profile -->
  <!-- ====================================================== -->

  <beans profile="development">
    <context:property-placeholder
      ignore-resource-not-found="true"
      location="classpath*:/META-INF/development.properties" />
  </beans>
  
  <beans profile="test">
    <context:property-placeholder
      ignore-resource-not-found="true"
      location="classpath*:/META-INF/test.properties" />
  </beans>

  <beans profile="production">
    <context:property-placeholder
      ignore-resource-not-found="true"
      location="classpath*:/META-INF/production.properties" />
  </beans>

	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 							[Programmatically via WebApplicationInitializer interface]
	 * 
	 * 		@Configuration
			public class MyWebApplicationInitializer implements WebApplicationInitializer 
			{
				    @Override
				    public void onStartup(ServletContext servletContext) throws ServletException 
				    {
				        servletContext.setInitParameter("spring.profiles.active", "dev");
				    }
			}
			
			
			
								[Programmatically via ConfigurableEnvironment]
			
			
							@Autowired
				private ConfigurableEnvironment env;
				...
				env.setActiveProfiles("someProfile");
			

	 *							 [Context Parameter in web.xml]
	 *
	 *
			<context-param>
						    <param-name>contextConfigLocation</param-name>
						    <param-value>/WEB-INF/app-config.xml</param-value>
			</context-param>
			<context-param>
							<param-name>spring.profiles.active</param-name>
						    <param-value>dev</param-value>
			</context-param>
			
			
			
			
			
			
			
			Any bean that does not specify a profile belongs to “default” profile.

			Spring also provides a way to set the default profile when no other
		 profile is active – by using the “spring.profiles.default” property.
			
			
			
			
	 *
	 *
	 *
	 * 
	 * 
	 * 
	 * **/
	
	
	
	
	
	
	
	
	
	
	
}
