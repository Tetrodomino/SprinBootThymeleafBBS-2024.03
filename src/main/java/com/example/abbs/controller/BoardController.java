package com.example.abbs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.abbs.entity.Board;
import com.example.abbs.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired private BoardService boardService;
	
	// page 값을 주소 파라메터로는 p로 하고, 입력하지 않을 때의 디폴트는 1로 설정
	// field나 query의 경우도 이하 동일한 방식으로 작동
	@GetMapping("/list")
	public String list(@RequestParam(name="p", defaultValue="1") int page,
			@RequestParam(name="f", defaultValue="title") String field,
			@RequestParam(name="q", defaultValue="") String query,
			HttpSession session, Model model) {
		
		List<Board> boardList = boardService.getBoardList(page, field, query);
		
		// 현재 페이지에 따른 보여줄 페이지 수 구하기
		// totalPageCount = 총 게시글의 수
		// totalPages = 총 페이지 수
		// startPage = 현재 페이지에 보여줄 시작 페이지 번호
		// endPage = 현재 페이지에 보여줄 끝 페이지 번호
		int totalPageCount = boardService.getBoardCount(field, query);
		int totalPages = (int) Math.ceil(totalPageCount / (double) BoardService.LIST_PER_PAGE);
		int startPage = (int) Math.ceil((page - 0.5) / BoardService.PAGE_PER_SCREEN - 1)
				* BoardService.PAGE_PER_SCREEN + 1;
		int endPage = Math.min(totalPages, startPage + BoardService.PAGE_PER_SCREEN - 1);
		List<String> pageList = new ArrayList<>();
		
		for (int i = startPage; i <= endPage; i++)
			pageList.add(String.valueOf(i));
		
		session.setAttribute("currentBoardPage", page);
		model.addAttribute("boardList", boardList);
		model.addAttribute("field", field);
		model.addAttribute("query", query);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageList", pageList);
		
		return "board/list";
	}
	
	@GetMapping("/insert")
	public String insert(HttpSession session, Model model) {
		
		return "board/insert";
	}
	
	@PostMapping("/insert")
	public String insertBoard(String title, String content, HttpSession session, Model model) {
		String uid = (String) session.getAttribute("sessUid");
		System.out.println(uid);
		Board board = new Board(title, content, uid, null);
		boardService.insertBoard(board);
		
		return "redirect:/board/list";
	}
}
