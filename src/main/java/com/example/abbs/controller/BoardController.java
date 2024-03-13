package com.example.abbs.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.abbs.entity.Board;
import com.example.abbs.entity.Like;
import com.example.abbs.entity.Reply;
import com.example.abbs.service.BoardService;
import com.example.abbs.service.LikeService;
import com.example.abbs.service.ReplyService;
import com.example.abbs.util.JsonUtil;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired private BoardService boardService;
	@Autowired private ReplyService replyService;
	@Autowired private LikeService likeService;
	@Autowired private JsonUtil jsonUtil;
	@Value("${spring.servlet.multipart.location}") private String uploadDir;
	
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
	public String insert() {
		
		return "board/insert";
	}
	
	@PostMapping("/insert")
	public String insertBoard(String title, String content, MultipartHttpServletRequest req,
			HttpSession session, Model model) {
		String uid = (String) session.getAttribute("sessUid");
		List<MultipartFile> uploadFileList = req.getFiles("files");

		List<String> fileList = new ArrayList<>();
		for (MultipartFile part: uploadFileList)
		{
			// 첨부 파일이 없는 경우 넘김 - application/octet-stream
			if (part.getContentType().contains("octet-stream"))
				continue;
			
			String filename = part.getOriginalFilename();
			String uploadPath = uploadDir + "upload/" + filename;
			
			try {
				part.transferTo(new File(uploadPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
			fileList.add(filename);
		}
		
		String files = jsonUtil.list2Json(fileList);
		
		Board board = new Board(title, content, uid, files);
		boardService.insertBoard(board);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/detail/{bid}/{uid}")
	public String detail(@PathVariable int bid, @PathVariable String uid, String option,
			HttpSession session, Model model) {
		
		// 본인이 조회한 경우 조회수 미증가
		String sessUid = (String) session.getAttribute("sessUid");
		if (!uid.equals(sessUid) && (option == null || option.equals("")))
			boardService.increaseViewCount(bid);
		
		Board board = boardService.getBoard(bid);
		String jsonFiles = board.getFiles();
		if (!(jsonFiles == null || jsonFiles.equals("")))
		{
			List<String> fileList = jsonUtil.json2List(jsonFiles);
			model.addAttribute("fileList", fileList);
		}
		model.addAttribute("board", board);
		
		Like like = likeService.getLike(bid, sessUid);
		if (like == null)
			session.setAttribute("likeCliked", 0);
		else
			session.setAttribute("likeCliked", like.getValue());
		model.addAttribute("count", board.getLikeCount());
		
		List<Reply> replyList = replyService.getReplyList(bid);
		model.addAttribute("replyList", replyList);
		
		return "board/detail";
	}
	
	@GetMapping("/delete/{bid}")
	public String delete(@PathVariable int bid, HttpSession session) {
		boardService.deleteBoard(bid);
		return "redirect:/board/list?p=" + session.getAttribute("currentBoardPage");
	}
	
	@PostMapping("/reply")
	public String reply(int bid, String uid, String comment, HttpSession session) {
		String sessUid = (String) session.getAttribute("sessUid");
		int isMine = (sessUid.equals(uid)) ? 1 : 0;
		Reply reply = new Reply(comment, sessUid, bid, isMine);
		
		replyService.insertReply(reply);
		boardService.increaseReplyCount(bid);
		
		return "redirect:/board/detail/" + bid + "/" + uid + "?option=DNI";
	}
	
	// AJAX 처리 - 타임리프에서 세팅하는 값을 변경하기 위한 방법
	@GetMapping("/like/{bid}")
	public String like(@PathVariable int bid, HttpSession session, Model model) {
		String sessUid = (String) session.getAttribute("sessUid");
		
		Like like = likeService.getLike(bid, sessUid);
		if (like == null)
		{
			likeService.insertLike(new Like(sessUid, bid, 1));
			session.setAttribute("likeClicked", 1);
		}
		else
		{
			int value = likeService.toggleLike(like);
			session.setAttribute("likeClicked", value);
		}
		int count = likeService.getLikeCount(bid);
		boardService.updateLikeCount(bid, count);
		
		model.addAttribute("count", count);
		return "board/detail::#likeCount";
	}
}
