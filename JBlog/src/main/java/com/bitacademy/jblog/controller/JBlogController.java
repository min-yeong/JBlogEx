package com.bitacademy.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bitacademy.jblog.exception.UsersDaoException;
import com.bitacademy.jblog.repository.BlogsVo;
import com.bitacademy.jblog.repository.CategorysVo;
import com.bitacademy.jblog.repository.PostsVo;
import com.bitacademy.jblog.repository.UsersVo;
import com.bitacademy.jblog.service.BlogsService;
import com.bitacademy.jblog.service.CategorysService;
import com.bitacademy.jblog.service.PostsService;
import com.bitacademy.jblog.service.UsersService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class JBlogController {
	private static final Logger logger = LoggerFactory.getLogger(JBlogController.class);
	@Autowired
	UsersService usersService;
	@Autowired
	BlogsService blogsService;
	@Autowired
	CategorysService categoryServiceImpl;
	@Autowired
	PostsService postServiceImpl;
	
	// 기본 폼
	@RequestMapping("/")
	public ModelAndView jblog() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", "Hello");
		mav.setViewName("home");
		return mav;
	}
	
	// 회원가입 폼 
	@RequestMapping(value="users/join", method=RequestMethod.GET)
	public String joinForm(@ModelAttribute UsersVo vo) {
		return "users/joinform";
	}
	 
	// 가입 절차 처리 
	@RequestMapping(value="users/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UsersVo vo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors) {
				logger.error("Object Error : "+error);
			}
			model.addAllAttributes(result.getModel());
			return "users/joinform";
		}
		boolean isSuccess = false;
		try {
			isSuccess = usersService.join(vo);
		} catch(UsersDaoException e) {
			System.err.println("UserDao 오류 : "+ e.getMessage());
			System.err.println("오류 발생 당시의 객체 정보 : "+e.getVo());
		}
		System.out.println("vo 값 확인 : "+vo);
		isSuccess = blogsService.join(vo.getUserNo());
		if(isSuccess) return "redirect:/users/joinsuccess";
		else return "redirect:/users/join";
	}
		
	// 가입 성공시 페이지
	@RequestMapping("users/joinsuccess")
	public String joinSuccess() {
		return "users/joinsuccess";
	}
	
	// 아이디 중복 체크
	@RequestMapping("/users/checkId")
	public Object checkId(@RequestParam String id) {
		UsersVo vo = usersService.getUser(id);
		boolean isExists = vo != null;
		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
		map.put("exists", isExists);
		
		return map;
	}
	
	// 로그인 폼
	@RequestMapping(value="users/login", method=RequestMethod.GET) 
	public String loginForm() {
		return "users/loginform";
	}
		
	// 로그인 기능
	@RequestMapping(value="users/login", method=RequestMethod.POST)
	public String login(@RequestParam(value="id", required=false) String id, @RequestParam(value="password", required=false) String password,
		 HttpSession session) {
		if(id.length()==0 || password.length()==0) {
			System.err.println("로그인 할 수 없음");
			return "redirect:/users/login";
		}
		UsersVo authUser = usersService.getUser(id, password);
		if(authUser == null) return "redirect:/users/login";
		else {
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		}
	}
	
	// 로그아웃 
	@RequestMapping(value="users/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	// 내블로그 폼
	@RequestMapping(value="/{id}", method=RequestMethod.GET) 
	public String myblogForm(@PathVariable String id, Model model) {
		BlogsVo vo = blogsService.getBlog(id);
		logger.debug("VO:" + vo);
		model.addAttribute("vo", vo);
		
		List<PostsVo> pvo = postServiceImpl.getPost(vo.getUserNo());
		model.addAttribute("pvo", pvo);
		PostsVo fvo = postServiceImpl.firstPost(vo.getUserNo());
		model.addAttribute("fvo", fvo);
		logger.debug("fvo:" + fvo);
		List<CategorysVo> list = categoryServiceImpl.getList(vo.getUserNo());
		model.addAttribute("list", list);
		return "users/myblogform";
	}
	
	// 블로그관리
	@RequestMapping(value="/{id}/admin/basic", method=RequestMethod.GET)
	public String blogmanage(@PathVariable String id, Model model) {
		BlogsVo vo = blogsService.getBlog(id);
		logger.debug("VO:" + vo);
		model.addAttribute("vo", vo);
		return "admin/basic";
	}
	
	// 이미지 처리
	@RequestMapping(value="/{id}/admin/basic", method=RequestMethod.POST)
	public String image(@PathVariable String id, Model model) {
		BlogsVo vo = blogsService.getBlog(id);
		logger.debug("VO:" + vo);
		model.addAttribute("vo", vo);
	
		return "admin/basic";
		}

	// 카테고리관리
	@RequestMapping(value="/{id}/admin/category")
	public String blogcategory(@PathVariable String id, Model model) {
		BlogsVo vo = blogsService.getBlog(id);
		logger.debug("VO:" + vo);
		model.addAttribute("vo", vo);
		
		List<CategorysVo> list = categoryServiceImpl.getList(vo.getUserNo());
		logger.debug("Category List : "+list.toString());
		model.addAttribute("list", list);
		return "admin/category";
	}
	
	// 카테고리 작성폼
	@RequestMapping(value="/{id}/admin/category", method=RequestMethod.POST)
	public String categoryAction(@PathVariable String id, Model model, @ModelAttribute CategorysVo vo, HttpSession session) {
		BlogsVo blogsvo = blogsService.getBlog(id);
		logger.debug("VO:" + blogsvo);
		model.addAttribute("vo", blogsvo);
		
		UsersVo user = (UsersVo)session.getAttribute("authUser");
		logger.debug("authser:" + user);
		vo.setUserNo(blogsvo.getUserNo());
		boolean isSuccess = categoryServiceImpl.write(vo);
		return "admin/category";
	}
	
	// 카테고리 삭제
	@RequestMapping(value="/{id}/admin/delete")
	public String categorydelete(@PathVariable String id, Model model) {
		BlogsVo blogsvo = blogsService.getBlog(id);
		logger.debug("VO:" + blogsvo);
		model.addAttribute("vo", blogsvo);
		
		boolean isSuccess = categoryServiceImpl.delete(blogsvo.getUserNo());
		return "admin/delete";
	}
	
	// 게시글폼 
	@RequestMapping(value="/{id}/admin/write", method=RequestMethod.GET)
	public String blogAction(@PathVariable String id, Model model) {
		BlogsVo blogsvo = blogsService.getBlog(id);
		logger.debug("VO:" + blogsvo);
		model.addAttribute("vo", blogsvo);
		
		List<CategorysVo> list = categoryServiceImpl.getList(blogsvo.getUserNo());
		logger.debug("Category List : "+list.toString());
		model.addAttribute("list", list);
		return "admin/write";
	}
			
	// 게시글 작성폼 
	@RequestMapping(value="/{id}/admin/write", method=RequestMethod.POST)
	public String blogwrite(@PathVariable String id, Model model, @ModelAttribute PostsVo vo) {
		BlogsVo blogsvo = blogsService.getBlog(id);
		model.addAttribute("vo", blogsvo);
		
		boolean isSuccess = postServiceImpl.postwrite(vo);
		logger.debug("VO:" + vo);
		return "admin/write";
	}
}
