package com.andersonfonseka.dwr.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

public class UploadDownload {

	public Map<String, String> uploadFiles(BufferedImage uploadImage, String componentId, HttpServletRequest request) {
		
		Map<String, String> result = new HashMap<String, String>();
		
		String resultado = "";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {

			ImageIO.write(uploadImage, "jpg", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			
			resultado = Base64.getEncoder().encodeToString(imageInByte);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		result.put("id", componentId);
		result.put("imagem", "data:image/png;base64," + resultado + "");
		
		return result;
	}

}
