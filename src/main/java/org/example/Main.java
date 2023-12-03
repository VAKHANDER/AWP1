package org.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("jsp/auth.jsp").forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("Login");
        String password = req.getParameter("Password");
        Boolean check = Boolean.FALSE;
        String[] parts = new String[0];
        try {
            File file = new File("C:\\Java\\Servlet2\\MainServlet\\src\\main\\resources\\BD.txt");
            Scanner scanner = new Scanner(file);

            List<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
            scanner.close();

            for (String line : lines) {
                parts = line.split(":");
                if (Objects.equals(login, parts[0]) && Objects.equals(password, parts[1])){
                    check = Boolean.TRUE;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("check", check);
        req.getRequestDispatcher("jsp/posted_auth.jsp").forward(req, resp);
    }
}