package com.sxm.framework.common;

import java.util.Locale;
import java.util.Map;

import com.sxm.framework.dto.Capability;
import com.sxm.framework.dto.Envirnoment;
import com.sxm.framework.dto.handler.CapabilityHandler;
import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.dto.handler.UserHandler;
import com.sxm.framework.utility.Messages;
import com.sxm.framework.utility.PropertyElementReader;

/**
 * TestSession class provides all the test configuration information required for all
 * tests to work and run
 * 
 * @author subramanyamp
 *
 */
public class TestSession {
	
	private Capability capability = CapabilityHandler.getInstance()
			.getCapability();
	private Envirnoment env = EnvirnomentHandler.getInstance().getEnvirnoment();

	private PropertyElementReader propertyElementReader = PropertyElementReader
			.getInstance(env.getLocale());
	
	public Map getUsers() {
        return users;
    }

    public void setUsers(Map users) {
        this.users = users;
    }

    private Map users = UserHandler.getInstance(env.getLocale(), env.getEnv()).getUserMap();
	private Messages messages = new Messages("localization.Messages", new Locale("en","US"));

	public Capability getCapability() {
		return capability;
	}

	public void setCapability(Capability capability) {
		this.capability = capability;
	}

	public Envirnoment getEnv() {
		return env;
	}

	public void setEnv(Envirnoment env) {
		this.env = env;
	}

	public PropertyElementReader getPropertyElementReader() {
		return propertyElementReader;
	}

	public void setPropertyElementReader(
			PropertyElementReader propertyElementReader) {
		this.propertyElementReader = propertyElementReader;
	}

	public Messages getMessages() {
		return messages;
		
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}
}