package top.onceio.plugins.springboot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import top.onceio.beans.BeansEden;
import top.onceio.db.dao.impl.DaoHelper;
import top.onceio.db.jdbc.JdbcHelper;


@Component
@Order(1)
public class OnceIORunner implements ApplicationRunner {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcHelper jdbcHelper;
	@Autowired
	private DaoHelper daoHelper;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		BeansEden.get().store(DataSource.class, null, dataSource);
		BeansEden.get().store(JdbcHelper.class, null, jdbcHelper);
		BeansEden.get().store(DaoHelper.class, null, daoHelper);
		BeansEden.get().resovle("sb.model");
	}

}