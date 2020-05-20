/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.FileUtility;
import entity.Category;
import entity.Image;
import entity.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CategoryLogic;
import logic.ImageLogic;
import logic.ItemLogic;
import scraper.kijiji.Kijiji;

/**
 *
 * @author Mukta
 */
@WebServlet(name = "KijijiView", urlPatterns = {"/Kijiji"})
public class KijijiView extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet KijijiView</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style\\KijijiStyle.css\">");
            out.println("</head>");
            out.println("<body>");

            ItemLogic iLogic = new ItemLogic();
            List<Item> iList = iLogic.getWithCategory(1);
                      
            for (int i = 0; i < iList.size(); i++) {
                String price;
                if(iList.get(i).getPrice() == null){
                    price = "Null";
                }else{
                    price = iList.get(i).getPrice().toString();
                }
                out.println("<div class=\"center-column\">\n" +
                            "<div class=\"item\">\n" +
                            "<div class=\"image\">\n" +
                            "<img src=\""+ "image/" + iList.get(i).getId() +".jpg"+ "\" style=\"max-width: 250px;\n" +
                            "max-height: 200px;\" />\n" +
                            "</div>\n" +
                            "<div class=\"details\">\n" +
                            "<div class=\"title\">\n" +
                            "<a href=\""+ iList.get(i).getUrl()  +"\"\n" +
                            "target=\"_blank\">" +iList.get(i).getTitle()+"</a>\n" +
                            "</div>\n" +
                            "<div class=\"price\">\n" +
                             price+"\n" +
                            "</div>\n" +
                            "<div class=\"date\">\n" +
                             iList.get(i).getDate().toString()+"\n" +
                            "</div>\n" +
                            "<div class=\"location\">\n" +
                             iList.get(i).getLocation()+"\n" +
                            "</div>\n" +
                            "<div class=\"description\">\n" +
                             iList.get(i).getDescription()+"\n" +
                            "</div>\n" +
                            "</div>\n" +
                            "</div>\n" +
                            "</div>");
            
                    
            }

            out.println("</body>");
                    out.println("</html>");
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoryLogic cL = new CategoryLogic();
        Kijiji k = new Kijiji();
        k.downloadPage(cL.getWithId(1).getUrl());
        k.findAllItems();
        ImageLogic imL = new ImageLogic();
        k.proccessItems((item) -> {
            ItemLogic iL = new ItemLogic();
            if (iL.getWithId(Integer.parseInt(item.getId())) == null && imL.getWithPath(System.getProperty("user.home")+"/KijijiImages/"+ item.getId()+".jpg")==null) {

                FileUtility.downloadAndSaveFile(item.getImageUrl(), System.getProperty("user.home")+"/KijijiImages/", item.getId()+".jpg");
                
                Map<String, String[]> cMap = new HashMap<>();
                cMap.put(CategoryLogic.ID, new String[]{String.valueOf(cL.getWithId(1).getId())});
                cMap.put(CategoryLogic.TITLE, new String[]{cL.getWithId(1).getTitle()});
                cMap.put(CategoryLogic.URL, new String[]{cL.getWithId(1).getUrl()});
                Category c = cL.createEntity(cMap);

                
                Map<String, String[]> iMap = new HashMap<>();
                iMap.put(ImageLogic.PATH, new String[]{System.getProperty("user.home")+"/KijijiImages/"+ item.getId()+".jpg"});
                iMap.put(ImageLogic.NAME, new String[]{item.getImageName()});
                iMap.put(ImageLogic.URL, new String[]{item.getImageUrl()});
                Image i = imL.createEntity(iMap);

                imL.add(i);

                Map<String, String[]> itMap = new HashMap<>();
                itMap.put(ItemLogic.ID, new String[]{item.getId()});
                itMap.put(ItemLogic.TITLE, new String[]{item.getTitle()});
                itMap.put(ItemLogic.URL, new String[]{item.getUrl()});
                itMap.put(ItemLogic.DESCRIPTION, new String[]{item.getDescription()});
                itMap.put(ItemLogic.LOCATION, new String[]{item.getLocation()});
                itMap.put(ItemLogic.PRICE, new String[]{item.getPrice()});
                itMap.put(ItemLogic.DATE, new String[]{item.getDate()});

                Item kijijiItem = iL.createEntity(itMap);
                kijijiItem.setCategory(c);
                kijijiItem.setImage(i);
                iL.add(kijijiItem);
            }

        });
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
