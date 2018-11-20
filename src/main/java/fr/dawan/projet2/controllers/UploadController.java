package fr.dawan.projet2.controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.dawan.projet2.dao.ContactDao;

/**
 * Servlet implementation class ContactController
 */
@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024*1024*2, //2Mo
maxFileSize = 1024*1024*10, //10Mo
maxRequestSize = 1024*1024*50) //50Mo
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String SAVE_DIR = "uploads";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Chemin absolu de l'appli web
		File fileSaveDir;
		try {
			String appPath = request.getServletContext().getRealPath("/");
			String savePath = appPath + File.separator + SAVE_DIR;
			
			fileSaveDir = new File(savePath);
			if(!fileSaveDir.exists()) fileSaveDir.mkdir();
			
			System.out.println(fileSaveDir);
			
			for (Part part : request.getParts()) {
				String fileName = extractFileName(part);
				
				if (fileName.equals("")) throw new Exception("Erreur");
				
				// récupération de l'absolute path
				fileName = new File(fileName).getName();
				
				String dest = savePath + File.separator + fileName;
				part.write(dest);	
				
				ContactDao.importCsv(dest);
				
				request.setAttribute("resultUpload", "Upload effectué : " + fileSaveDir.getAbsolutePath());
			}
		} catch (Exception e) {
			MyLogger.myRootLogger.error("Erreur doPost Upload" , e);
		}
		
		Refresh(request, response);
	}

	private void Refresh(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("listeC", ContactDao.findAll());
		request.getRequestDispatcher("backoffice/admin.jsp").forward(request, response);
	}

	/**
	 * 
	 * @param part
	 * @return
	 */
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		
		for (String s : items) {
			if (s.trim().startsWith("filename"))
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
		}
		
		return "";
	}
}
