package kr.mem.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;
import kr.mem.pojo.Controller;
import kr.mem.pojo.MemberDeleteController;
import kr.mem.pojo.MemberInsertCtroller;
import kr.mem.pojo.MemberInsertFormController;
import kr.mem.pojo.MemberListController;

public class MemberFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		// 1. � ��û���� �ľ��ϴ� �۾�-> *.do
		String reqUrl = request.getRequestURI();// ���û�� �ߴ��� URL����
		// System.out.println(reqUrl); ->/mvc1/delete.do
		String ctxPath = request.getContextPath();
		// System.out.println(ctxPath); ->/mvc1
		// Ŭ���̾�Ʈ�� ��û�� ���
		String command = reqUrl.substring(ctxPath.length());
		System.out.println(command);
		// �� ��û�� ���� ó�� �ϱ�(�б��۾�)
		Controller controller = null;
		String nextView = null;
		HandlerMapping mappings = new HandlerMapping(); //��ü����
		controller =mappings.getController(command); //Ű���� �޾Ƽ� controller�� �־� �ش�
		nextView =controller.requestHandle(request, response); 
		
		
		//��鷯����****�߿�!!!!!!!!!!!!!!!!!!!!
		// list.do --->MemberListController
		// insert.do --->MemberInsertController
		// insertForm.do --->MemberInsertFormController
		// delete.do --->MemberDeleteContrller
		/*
		if (command.equals("/list.do")) {
			controller = new MemberListController();
			nextView = controller.requestHandle(request, response);
		} else if (command.equals("/insert.do")) {
			controller = new MemberInsertCtroller();
			nextView = controller.requestHandle(request, response);
		} else if (command.equals("/insertForm.do")) {
			controller = new MemberInsertFormController();
			nextView = controller.requestHandle(request, response);
		} else if (command.equals("/delete.do")) {
			controller = new MemberDeleteController();
			nextView = controller.requestHandle(request, response);
		}
		*/
		
		//view �������� �����ϴ� �κ�
		if (nextView != null) {
			if (nextView.indexOf("redirect:") != -1) {
				String[] sp = nextView.split(":"); // sp[0]:sp[1]
				response.sendRedirect(sp[1]);// :0
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/"+nextView);//"/WEB-INF/views/" ����� ��θ� �ٿ��ش�
				rd.forward(request, response);

			}
		}
	}

}
