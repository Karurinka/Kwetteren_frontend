package Kwetter;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.Application;

public class KwetterApp extends Application
{

  public KwetterApp() {
    // Create JAX-RS application.

    final Application application = new ResourceConfig()
      .packages("org.glassfish.jersey.examples.linking")
      .register(DeclarativeLinkingFeature.class);
  }
}
