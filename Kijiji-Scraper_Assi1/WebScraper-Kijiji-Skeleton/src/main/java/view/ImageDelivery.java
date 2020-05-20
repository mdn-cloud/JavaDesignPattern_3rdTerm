package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mukta
 */
@WebServlet(name = "KijijiImage", urlPatterns = {"/image/*"})
public class ImageDelivery extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
        
        ServletContext cntx= request.getServletContext();
      // Get the absolute path of the image
      String filename =  System.getProperty("user.home")+"/KijijiImages/" + request.getPathInfo().substring(1);
      // retrieve mimeType dynamically
      String mime = cntx.getMimeType(filename);
      if (mime == null) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return;
      }

      response.setContentType(mime);
      File file = new File(filename);
      response.setContentLength((int)file.length());

     try( FileInputStream in = new FileInputStream(file);
      OutputStream out = response.getOutputStream()){ 

      // Copy the contents of the file to the output stream
       byte[] buf = new byte[1024];
       int count = 0;
       while ((count = in.read(buf)) >= 0) {
         out.write(buf, 0, count);
      }
         
     }catch(IOException ex){
         log(ex.getMessage(), ex);
     }

}
 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
