package com.creatosix.cms.activator;

import com.creatosix.cms.assistant.CmsAssistant;
import com.creatosix.cms.assistant.impl.CmsAssistantService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class CmsAssistantActivator implements BundleActivator {

	private ServiceRegistration registration;
	
	@Override
	public void start(BundleContext context) throws Exception {

		registration = context.registerService(CmsAssistant.class.getName(), new CmsAssistantService(), null);
		System.out.println("###########Service Registered Successfully##############");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		registration.unregister();
		System.out.println("###########Service Unregistered##############");
		
	}

}