package top.onceio.plugins.springboot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import top.onceio.core.beans.BeansEden;
import top.onceio.core.db.dao.impl.DaoHelper;
import top.onceio.core.db.jdbc.JdbcHelper;

@Configuration
@Component
@Order(1)
public class OnceIORunner implements ApplicationRunner {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcHelper jdbcHelper;
	@Autowired
	private DaoHelper daoHelper;
	
	@Value("${onceio.scan.packages}")
	private String packages;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		BeansEden.get().store(DataSource.class, null, dataSource);
		BeansEden.get().store(JdbcHelper.class, null, jdbcHelper);
		BeansEden.get().store(DaoHelper.class, null, daoHelper);

		System.out.println(packages);
		BeansEden.get().resovle(new String[] {"conf"},packages.split(";"));
		
		
	}

}