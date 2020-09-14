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
		// 1. 어떤 요청인지 파악하는 작업-> *.do
		String reqUrl = request.getRequestURI();// 어떤요청을 했는지 URL정보
		// System.out.println(reqUrl); ->/mvc1/delete.do
		String ctxPath = request.getContextPath();
		// System.out.println(ctxPath); ->/mvc1
		// 클라이언트가 요청한 명령
		String command = reqUrl.substring(ctxPath.length());
		System.out.println(command);
		// 각 요청에 따라 처리 하기(분기작업)
		Controller controller = null;
		String nextView = null;
		HandlerMapping mappings = new HandlerMapping(); //객체생성
		controller =mappings.getController(command); //키값을 받아서 controller에 넣어 준다
		nextView =controller.requestHandle(request, response); 
		
		
		//헨들러매핑****중요!!!!!!!!!!!!!!!!!!!!
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
		
		//view 페이지로 연동하는 부분
		if (nextView != null) {
			if (nextView.indexOf("redirect:") != -1) {
				String[] sp = nextView.split(":"); // sp[0]:sp[1]
				response.sendRedirect(sp[1]);// :0
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/"+nextView);//"/WEB-INF/views/" 공통된 경로를 붙여준다
				rd.forward(request, response);

			}
		}
	}

}
