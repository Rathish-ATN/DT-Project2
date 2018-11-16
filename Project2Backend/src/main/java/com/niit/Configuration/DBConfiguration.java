package com.niit.Configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Model.BlogComment;
import com.niit.Model.BlogPost;
import com.niit.Model.Chat;
import com.niit.Model.Friend;
import com.niit.Model.Job;
import com.niit.Model.Notification;
import com.niit.Model.ProfilePicture;
import com.niit.Model.User;

@Configuration
@EnableTransactionManagement
public class DBConfiguration {
	public DBConfiguration() {
		System.out.println("Database is Instantiated in DBConfiguration");
	}

	@Bean // Creating Data Source Bean for Database Connection.
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("Project2");
		dataSource.setPassword("root");
		return dataSource;
	}

	@Bean // Creating Session Factory Bean for
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder lsf = new LocalSessionFactoryBuilder(getDataSource());
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		lsf.addProperties(hibernateProperties);
		Class<?> classes[] = new Class[] {User.class,BlogComment.class,BlogPost.class,Chat.class,Friend.class,Job.class,Notification.class,ProfilePicture.class};// class objects of all entities
		return lsf.addAnnotatedClasses(classes).buildSessionFactory();
	}

	@Bean
	public HibernateTransactionManager hibTransManagement() {
		return new HibernateTransactionManager(sessionFactory());
	}
}
