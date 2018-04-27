package top.onceio.plugins.springboot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import top.onceio.core.db.dao.impl.DaoHelper;
import top.onceio.core.db.jdbc.JdbcHelper;

@Configuration
@AutoConfigureAfter({OnceIORunner.class})
public class OnceIODaoConfiguration {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean 
	public JdbcHelper createJdbcHelper() {
		JdbcHelper jdbcHelper = new JdbcHelper();
		jdbcHelper.setDataSource(dataSource);
		return jdbcHelper;
	}
		
	@Bean 
	public DaoHelper createDaoHelper() {
		DaoHelper daoHelper = new DaoHelper();
		return daoHelper;
	}

}
