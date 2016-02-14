package stand.lemonade.config;

import java.io.File;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Properties;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;
import javax.validation.Validation;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.github.toastshaman.dropwizard.auth.jwt.JWTAuthFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.hubspot.dropwizard.guice.GuiceBundle;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.configuration.ConfigurationFactory;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import stand.lemonade.entities.User;

public class LemonadeApplication extends Application<LemonadeConfiguration> {

	private GuiceBundle<LemonadeConfiguration> guiceBundle;

	public static final String JPA_UNIT = "d1";

	private static String configFilename;

	public static void main(String[] args) throws Exception {

		for (int i = 0; i < args.length; i++) {
			if (args[i].endsWith(".yml")) {
				configFilename = args[i];
			}
		}

		new LemonadeApplication().run(args);
	}

	@Override
	public String getName() {
		return "Lemonade Stand";
	}

	@Override
	public void initialize(Bootstrap<LemonadeConfiguration> bootstrap) {

		LemonadeConfiguration configuration = createConfiguration(configFilename);
		Properties jpaProperties = createDatabasePropertiesFromConfiguration(configuration);

		JpaPersistModule jpaPersistModule = new JpaPersistModule(JPA_UNIT);
		jpaPersistModule.properties(jpaProperties);

		guiceBundle = GuiceBundle.<LemonadeConfiguration> newBuilder().addModule(new LemonadeGuiceModule())
				.addModule(jpaPersistModule).enableAutoConfig("stand.lemonade")
				.setConfigClass(LemonadeConfiguration.class).build();

		bootstrap.addBundle(guiceBundle);

	}

	@Override
	public void run(LemonadeConfiguration configuration, Environment environment) {

		environment.servlets().addFilter("persistFilter", guiceBundle.getInjector().getInstance(PersistFilter.class))
				.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

		Properties properties = createDatabasePropertiesFromConfiguration(configuration);
		JpaPersistModule jpaPersistModule = new JpaPersistModule(JPA_UNIT);
		jpaPersistModule.properties(properties);
		Injector injector = Guice.createInjector(jpaPersistModule, new LemonadeGuiceModule());
		injector.getInstance(PersistService.class).start();

		Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
		filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
		filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
		filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
		filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
		filter.setInitParameter(CrossOriginFilter.EXPOSED_HEADERS_PARAM,
				"Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,Location");
		filter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "*");

		environment.jersey().register(AuthFactory.binder(new JWTAuthFactory<>(new LemonadeAuthenticator(), "lemonade",
				User.class, LemonadeAuthenticator.VERIFIER, LemonadeAuthenticator.TOKEN_PARSER)));

		
	}

	/**
	 * Creates Configuration class directly from yml file name
	 * 
	 * @param configFilename
	 * @return
	 */
	public static LemonadeConfiguration createConfiguration(String configFilename) {
		ConfigurationFactory<LemonadeConfiguration> factory = new ConfigurationFactory<>(LemonadeConfiguration.class,
				Validation.buildDefaultValidatorFactory().getValidator(), Jackson.newObjectMapper(), "");
		LemonadeConfiguration configuration;
		try {
			configuration = factory.build(new File(configFilename));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return configuration;
	}

	/**
	 * Extracts all DB properties from Configuration file
	 * 
	 * @param localConfiguration
	 * @return
	 */
	public static Properties createDatabasePropertiesFromConfiguration(LemonadeConfiguration localConfiguration) {

		LemonadeConfiguration.DatabaseConfiguration databaseConfiguration = localConfiguration
				.getDatabaseConfiguration();
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

}