package view;

import common.ValidationException;
import entity.Item;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CategoryLogic;
import logic.ImageLogic;
import logic.ItemLogic;

/**
 *
 * @author Mukta
 */
@WebServlet(name = "CreateItem", urlPatterns = {"/CreateItem"})
public class CreateItem extends HttpServlet {

    private String errorMessage = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Create Feed</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div style=\"text-align: center;\">");
            out.println("<div style=\"display: inline-block; text-align: left;\">");
            out.println("<form method=\"post\">");

            ItemLogic aL = new ItemLogic();

            List<String> colNamList = aL.getColumnNames();
            List<String> colCodeList = aL.getColumnCodes();

            for (int i = 0; i < colNamList.size(); i++) {
                out.println(colNamList.get(i) + ":<br>");
                out.printf("<input type=\"text\" name=\"%s\" value=\"\"><br>", colCodeList.get(i));
                out.println("<br>");
            }

            out.println("<input type=\"submit\" name=\"view\" value=\"Add and View\">");
            out.println("<input type=\"submit\" name=\"add\" value=\"Add\">");
            out.println("</form>");
            if (errorMessage != null && !errorMessage.isEmpty()) {
                out.println("<p color=red>");
                out.println("<font color=red size=4px>");
                out.println(errorMessage);
                out.println("</font>");
                out.println("</p>");
            }
            out.println("<pre>");
            out.println("Submitted keys and values:");
            out.println(toStringMap(request.getParameterMap()));
            out.println("</pre>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String toStringMap(Map<String, String[]> values) {
        StringBuilder builder = new StringBuilder();
        values.forEach((k, v) -> builder.append("Key=").append(k)
                .append(", ")
                .append("Value/s=").append(Arrays.toString(v))
                .append(System.lineSeparator()));
        return builder.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log("GET");
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log("POST");

        try {
            ItemLogic iLogic = new ItemLogic();
            String id = request.getParameter(ItemLogic.ID);

            if (!id.equals("") && iLogic.getWithId(Integer.parseInt(id)) == null) {
                Item item = iLogic.createEntity(request.getParameterMap());
                item.setCategory(new CategoryLogic().getWithId(1));

                item.setImage(new ImageLogic().getWithId(1));

                iLogic.add(item);

            } else {
                errorMessage = "ID: \"" + id + "\" already exists";
            }
        } catch (ValidationException e) {
            errorMessage = e.getMessage();
        }

        if (request.getParameter("add") != null) {
            processRequest(request, response);
        } else if (request.getParameter("view") != null) {
            response.sendRedirect("ItemTable");
        }
    }

    @Override
    public String getServletInfo() {
        return "Create a Item Entity";
    }

    private static final boolean DEBUG = true;

    public void log(String msg) {
        if (DEBUG) {
            String message = String.format("[%s] %s", getClass().getSimpleName(), msg);
            getServletContext().log(message);
        }
    }

    public void log(String msg, Throwable t) {
        String message = String.format("[%s] %s", getClass().getSimpleName(), msg);
        getServletContext().log(message, t);
    }
}
