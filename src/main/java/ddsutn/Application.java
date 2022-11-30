package ddsutn;

import ddsutn.component.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.ServletContext;

@SpringBootApplication
public class Application implements WebApplicationInitializer {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void onStartup(ServletContext sc) {
    AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
    root.register(SecurityConfig.class);

    sc.addListener(new ContextLoaderListener(root));

    sc.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
        .addMappingForUrlPatterns(null, false, "/*");
  }
}
