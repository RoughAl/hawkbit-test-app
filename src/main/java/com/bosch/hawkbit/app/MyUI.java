/**
 * Copyright (c) 2011-2015 Bosch Software Innovations GmbH, Germany. All rights reserved.
 */
package com.bosch.hawkbit.app;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.vaadin.spring.events.EventBus.SessionEventBus;

import com.bosch.sp.server.eventbus.EventSubscriber;
import com.bosch.sp.server.eventbus.event.EntityEvent;
import com.bosch.sp.server.ui.DispatcherRunnable;
import com.bosch.sp.server.ui.HawkbitUI;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.VaadinSession.State;
import com.vaadin.server.WrappedSession;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;

@SpringUI
@Push( value = PushMode.AUTOMATIC, transport = Transport.WEBSOCKET )
@EventSubscriber
public class MyUI extends HawkbitUI {

   /**
    *
    */
   private static final long serialVersionUID = 1L;

   /**
    * An {@link com.google.common.eventbus.EventBus} subscriber which subscribes {@link EntityEvent}
    * from the repository to dispatch these events to the UI {@link SessionEventBus}.
    * @param event the entity event which has been published from the repository
    */
   @Override
   @Subscribe
   @AllowConcurrentEvents
   public void dispatch( final com.bosch.sp.server.eventbus.event.Event event ) {
      final VaadinSession session = getSession();
      if( session != null && session.getState() == State.OPEN ) {
         final WrappedSession wrappedSession = session.getSession();
         if( wrappedSession != null ) {
            final SecurityContext userContext = (SecurityContext) wrappedSession
               .getAttribute( HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY );
            if( eventSecurityCheck( userContext, event ) ) {
               final SecurityContext oldContext = SecurityContextHolder.getContext();
               try {
                  access( new DispatcherRunnable( eventBus, session, userContext, event ) );
               }
               finally {
                  SecurityContextHolder.setContext( oldContext );
               }
            }
         }
      }
   }

}
