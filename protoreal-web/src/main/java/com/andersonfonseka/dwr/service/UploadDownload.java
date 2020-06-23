package com.andersonfonseka.dwr.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import com.andersonfonseka.IComponent;
import com.andersonfonseka.IPage;
import com.andersonfonseka.dao.ComponentRepositoryFactory;
import com.andersonfonseka.dao.PageRepository;
import com.andersonfonseka.dao.Repository;

public class UploadDownload extends Controller {
	
	private Repository<IComponent> componentRepository = ComponentRepositoryFactory.getComponentRepository();
	
	public Map<String, String> uploadFiles(BufferedImage images, String componentId, HttpSession session) {
		
		Map<String, String> result = new HashMap<String, String>();
		
		String resultado = "";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {

			ImageIO.write(images, "jpg", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			
			resultado = Base64.getEncoder().encodeToString(imageInByte);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PageRepository pageRepository =  new PageRepository();
		
		IPage page = (IPage) session.getAttribute("page");
		
		IComponent component = componentRepository.get(componentId);
		
		Method method;
		try {
			method = component.getClass().getMethod("setFile", String.class);
			method.invoke(component, resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		componentRepository.edit(component);
		
		page = pageRepository.getFull(page.getUuid());
		
		result.put("data", page.doRender());
		result.put("components", getComponents(page));
			
		return result;
		
		
	}

}
