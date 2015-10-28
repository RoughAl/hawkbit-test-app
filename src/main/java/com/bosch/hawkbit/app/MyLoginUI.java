/**
 * Copyright (c) 2011-2015 Bosch Software Innovations GmbH, Germany. All rights reserved.
 */
package com.bosch.hawkbit.app;

import com.bosch.sp.server.ui.login.HawkbitLoginUI;
import com.bosch.sp.server.ui.themes.HawkbitTheme;
import com.vaadin.spring.annotation.SpringUI;

@SpringUI( path = HawkbitTheme.LOGIN_UI_PATH )
public class MyLoginUI extends HawkbitLoginUI {

   private static final long serialVersionUID = 1L;

}
