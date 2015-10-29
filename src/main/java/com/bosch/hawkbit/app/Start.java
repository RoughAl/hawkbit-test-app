/**
 * Copyright (c) 2011-2015 Bosch Software Innovations GmbH, Germany. All rights reserved.
 */
package com.bosch.hawkbit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.bosch.hawkbit.autoconfigure.security.EnableHawkbitManagedSecurityConfiguration;
import com.bosch.sp.server.RepositoryApplicationConfiguration;
import com.bosch.sp.server.amqp.AmqpConfiguration;

/**
 * @author Michael Hirsch
 *
 */
@SpringBootApplication
@Import( { RepositoryApplicationConfiguration.class, AmqpConfiguration.class } )
@EnableHawkbitManagedSecurityConfiguration
public class Start {

   /**
    * Main method to start the spring-boot application.
    * @param args the VM arguments.
    */
   public static void main( final String[] args ) {
      SpringApplication.run( Start.class, args );
   }
}
