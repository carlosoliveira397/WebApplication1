/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servelets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "ApiLoteriaServelet", urlPatterns = {"/loteria.json"})
public class ApiLoteriaServelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        int lot = 6;
        int num[];
        num = new int[lot];
        // laco de repeticao para controle de numeros randomicos nao serem repetidos e diferentes de zero
        for(int h = 0; h < 1; h++){
            // laco de repeticao para sortear os numeros
            for(int i = 0; i < num.length; i++){
                num[i] = (int)(60*Math.random());
            }
            // laco de repeticao para controle do numero atual 
            for(int j = 0; j < lot -1; j++){
                // laco de repeticao para controle do proximo numero
                for(int k = j+1; k < lot; k++){
                    // Verifica se o numero sorteado se repete no array, ou se é zero
                    if((num[j] == num[k]) && (num[j] == 0)){
                        k = lot;
                        j = lot;
                        h = 0;
                    }
                }
            }
        }
        
        try (PrintWriter out = response.getWriter()) {
            JSONObject obj = new JSONObject();
            obj.append("Data Time", new Date().toString());
            for(int i = 0; i < num.length; i++){
                obj.append("Loteria", num[i]);
            }
            out.print(obj.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
