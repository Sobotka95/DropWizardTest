package de.sobotka.dropwizard;

import de.sobotka.dropwizard.health.TemplateHealthCheck;
import de.sobotka.dropwizard.resources.DropWizardResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropWizardApplication extends Application<DropWizardConfiguration> {
	
    public static void main(String[] args) throws Exception {
        new DropWizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<DropWizardConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DropWizardConfiguration configuration, Environment environment) {
    	
    		final DropWizardResource resource = new DropWizardResource(
    	        configuration.getTemplate(),
    	        configuration.getDefaultName()
    	    );
    		final TemplateHealthCheck healthCheck =
    		        new TemplateHealthCheck(configuration.getTemplate());
    		    environment.healthChecks().register("template", healthCheck);
    	    environment.jersey().register(resource);
    	    
    }

}