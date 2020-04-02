package org.zerock.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@ComponentScan
@Log4j
@RequestMapping("board/*")
@AllArgsConstructor
public class BoardController {
	private BoardService service; // service 객체 생성
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		log.info("list");
//		model.addAttribute("list",service.getList());
//	}
	
	 @GetMapping("/list")
	 public void list(Criteria cri, Model model) {
	
	 log.info("list: " + cri);
	 model.addAttribute("list", service.getList(cri));
	 // list()는 pageMaker라는 이름으로 PageDTO 객체를 만들어서 Model에 담아준다.
	 // 전체데이터 수가 필요한데 임의로 13 지정해줌.
	 // model.addAttribute("pageMaker", new PageDTO(cri, 13)); 
	 
	 int total = service.getTotal(cri);
	 log.info("total: "+total);
	 model.addAttribute("pageMaker", new PageDTO(cri, total));
	
	 }
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register: "+ board);
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list"; // board/list로 다시 리다이렉트.
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) { // 추가적으로 Criteria를 파라미터로 받고 전달.
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify :"+ board);
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getPageNum());
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("remove..." + bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getPageNum());
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
}
