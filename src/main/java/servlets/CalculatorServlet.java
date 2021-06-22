package servlets;

import Model.Calculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/calc")
public class CalculatorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //show calculator page
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>SIMPLE CALCULATOR<br><br><br></head>");
        out.println("<body>");
        out.println("<form method = 'post' action = 'calc'>");
        out.println("enter the first number:<br>");
        out.println("<input type = 'text' name='number1'><br><br>");
        out.println("enter the second number:<br>");
        out.println("<input type = 'text' name='number2'><br><br>");
        out.println("enter the operation:<br><br>");
        out.println("<input type ='radio' name = 'op' value = '+'>add<br>");
        out.println("<input type = 'radio' name = 'op' value = '-'>sub<br>");
        out.println("<input type = 'radio' name = 'op' value = '*'>mul<br>");
        out.println("<input type = 'radio' name = 'op' value = '/'>div<br><br>");
        out.println("<input type = 'submit' name = 'result' value = 'submit'><br>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //show result page
        PrintWriter out = response.getWriter();
        Double number1 = null, number2 = null;
        String operator = request.getParameter("op");
        try {
            number1 = Double.parseDouble(request.getParameter("number1"));
            number2 = Double.parseDouble(request.getParameter("number2"));
        } catch (NumberFormatException e) {
            number1 = null;
            number2 = null;
        }

        HttpSession session = request.getSession();
        List<Calculator> listCalculations = (List<Calculator>) session.getAttribute("list");

        if (listCalculations == null) {
            listCalculations = new ArrayList<Calculator>();
        }

        listCalculations.add(new Calculator(number1, number2, operator));
        session.setAttribute("list", listCalculations);

        out.println("<html>");
        out.println("<head><style>table,th,td{ border: 1px solid black;}</style>" +
                "SIMPLE CALCULATOR<br><br><br></head>");
        out.println("<body>");
        out.println("<form method = 'post' action = 'calc'>");
        out.println("enter the first number:<br>");
        out.println("<input type = 'text' name='number1'><br><br>");
        out.println("enter the second number:<br>");
        out.println("<input type = 'text' name='number2'><br><br>");
        out.println("enter the operation:<br><br>");
        out.println("<input type ='radio' name = 'op' value = '+'>add<br>");
        out.println("<input type = 'radio' name = 'op' value = '-'>sub<br>");
        out.println("<input type = 'radio' name = 'op' value = '*'>mul<br>");
        out.println("<input type = 'radio' name = 'op' value = '/'>div<br><br>");
        out.println("<input type = 'submit' name = 'result' value = 'submit'><br>");
        out.println("<strong>The Result of " + number1 + operator + number2 + "=" +
                listCalculations.get(listCalculations.size() - 1).operation() + "</strong>");
        out.println("<table>");
        out.println("<tr><th>first</th><th>operation</th><th>second</th><th>result</th></tr>");

        for (int i = 0; i < listCalculations.size(); i++) {
            out.println("<tr><th>" + listCalculations.get(i).getNum1() +
                    "</th><th>" + listCalculations.get(i).getOperator() + "</th><th>" +
                    listCalculations.get(i).getNum2() + "</th><th>"
                    + listCalculations.get(i).operation() + "</th></tr>");
        }
        out.println("</body>");
        out.println("</html>");

        out.flush();
    }

}
