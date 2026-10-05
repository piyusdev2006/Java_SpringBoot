package com.piyus.servlet;

import com.piyus.model.User;
import com.piyus.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    // tight coupling
    private UserService userService = new UserService();
    @Override

    public void doPost(HttpServletRequest req,
                       HttpServletResponse res) throws IOException
    {
        // parameter se value humesha string me value deta hai humein convert krni padegi interger me
        Integer id  = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");

        if(id == null || email == null || name == null || mobile == null){
            res.setStatus(400);
            res.setContentType("application/json");
            res.getWriter().write("{\n" +
                    "  \"message\" : \"some fields are missing\"\n" +
                    "}");
        }

        User user = new User(id, name, email, mobile);

        User createdUser = userService.createUser(user);

        // 201 : resource created

        res.setStatus(201);
        res.setContentType("appliction/json");
        res.getWriter().write("{\n" +
                "  \"message\" : \"User Added Success\"\n" +
                "}");


    }
    public void doGet(HttpServletRequest req,
                      HttpServletResponse res) throws IOException
    {
        String idParam  = (req.getParameter("id"));

        if(idParam == null){
            // get all users
            List<User> users = userService.getAllUsers();

            res.setStatus(200);
            res.setContentType("application/json");
            res.getWriter().write(usersToJson(users));

            return;
        }

        Integer id = Integer.parseInt(idParam);

        User userResp = userService.getUserById(id);

        if(userResp == null){
            res.setStatus(404);
            res.setContentType("application/json");

        }

        // return user
        res.setStatus(200);
        res.setContentType("appliction/json");
        res.getWriter().write(userToJson(userResp));


    }
    public void doDelete(HttpServletRequest req,
                         HttpServletResponse res)
    {

    }
    public void doPut(HttpServletRequest req,
                      HttpServletResponse res)
    {

    }

    private String userToJson(User user){
        return "{\n" +
                "  \"id\" : " + user.getId() + ",\n" +
                "  \"email\" : " + user.getEmail() + ",\n" +
                "  \"name\" : "+ user.getName()+",\n" +
                "  \"mobile\" : "+ user.getMobile() + "\n" +
                "}";
    }

    private String usersToJson(List<User> users){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for(int i = 0; i < users.size(); i++){
            stringBuilder.append(userToJson(users.get(i)));

            if(i < users.size() - 1){
                stringBuilder.append(",");

            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
