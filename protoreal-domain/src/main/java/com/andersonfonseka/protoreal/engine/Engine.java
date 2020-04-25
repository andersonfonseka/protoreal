package com.andersonfonseka.protoreal.engine;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class Engine {

	private VelocityEngine ve = new VelocityEngine();
	private Template template;
	private VelocityContext context = new VelocityContext();
	private String templateName;
	
	public static final String DESIGN = "design";
	public static final String PROPERTIES = "properties";

	public Engine(String templateName) {

		this.templateName = templateName;
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

		Properties props = new Properties();
		props.put("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
		props.put("runtime.log.logsystem.log4j.category", "velocity");
		props.put("runtime.log.logsystem.log4j.logger", "velocity");

		ve.init(props);
	}

	public void putOnContext(String key, Object value) {
		this.context.put(key, value);
	}

	public String execute(String option) {
		StringWriter writer = new StringWriter();

		this.template = ve.getTemplate("templates/" + option + "/" + this.templateName);
		this.template.merge(context, writer);
	

		return writer.toString().replace("*", "").trim();
	}

	

}
