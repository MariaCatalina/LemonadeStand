package stand.lemonade.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

import stand.lemonade.config.LemonadeConfiguration.DatabaseConfiguration;

public class LemonadeApplicationTest {

	private static Properties createDatabasePropertiesFromConfiguration() {

		LemonadeConfiguration.DatabaseConfiguration databaseConfiguration = getDbConfigForTests();
		List<String> propertiesList = new ArrayList<>();
		propertiesList.add("hibernate.dialect");
		propertiesList.add("hibernate.show_sql");
		propertiesList.add("hibernate.hbm2ddl.auto");
		propertiesList.add("hibernate.dialect");
		propertiesList.add("hibernate.archive.autodetection");
		propertiesList.add("hibernate.connection.driver_class");
		propertiesList.add("hibernate.username");
		propertiesList.add("hibernate.password");

		Properties properties = new Properties();
		properties.setProperty("javax.persistence.jdbc.url", databaseConfiguration.getUrl());
		properties.setProperty("javax.persistence.jdbc.user", databaseConfiguration.getUser());
		properties.setProperty("javax.persistence.jdbc.password", databaseConfiguration.getPassword());
		for (String p : propertiesList) {
			String val = databaseConfiguration.getProperties().get(p);
			if (val != null) {
				properties.setProperty(p, val);
			}
		}

		return properties;
	}

	private static DatabaseConfiguration getDbConfigForTests() {
		DatabaseConfiguration dbConfig = new DatabaseConfiguration();
		dbConfig.setPassword("sql");
		dbConfig.setUrl("jdbc:postgresql://127.0.0.1:5432/lemonadestand");
		dbConfig.setUser("postgres");
		
		dbConfig.getProperties().put("hibernate.hbm2ddl.auto", "update");
		
		return dbConfig;
	}

	public static Injector createInjector() {
		Properties properties = createDatabasePropertiesFromConfiguration();
		JpaPersistModule jpaPersistModule = new JpaPersistModule(LemonadeApplication.JPA_UNIT);
		jpaPersistModule.properties(properties);
		Injector injector = Guice.createInjector(jpaPersistModule, new LemonadeGuiceModule());
		injector.getInstance(PersistService.class).start();
		return injector;
	}
}
