package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.tasks;
import utils.DBUtil;

/**
 * Servlet implementation class DestroyServlet
 */
@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        System.out.println("delete");
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            System.out.println("delete_if");
            EntityManager em = DBUtil.createEntityManager();

            //該当IDのタスクを取得
            tasks t = em.find(tasks.class, (Integer)request.getSession().getAttribute("task_id"));

            //削除
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
            em.close();

            //セッション上の不要なデータを削除
            request.getSession().removeAttribute("task_id");

           //リダイレクト
            response.sendRedirect(request.getContextPath() + "/index");
        }

    }

}
