package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.tasks;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String token = request.getParameter("_token");
        if(token != null && token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            tasks t = em.find(tasks.class, (Integer)request.getSession().getAttribute("task_id"));

            String content = request.getParameter("task");
            //タスクの更新
            t.setContent(content);

          //日時の設定
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setUpdated_at(currentTime);

            //コミット
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            //セッション上の不要になったデータを削除
            request.getSession().removeAttribute("task_id");

            //リダイレクト
            response.sendRedirect(request.getContextPath() + "/index");

        }

    }

}
