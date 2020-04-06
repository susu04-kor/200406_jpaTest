package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

@Controller
public class ProductController {
	
	@Autowired
	private ProductDao dao;

	public void setDao(ProductDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	private CategoryDao cdao;

	public void setCdao(CategoryDao cdao) {
		this.cdao = cdao;
	}
	
	@GetMapping("/insert")
	public String insertForm(Model model) {
		model.addAttribute("clist", cdao.findAll());
		return "insert";
	}
	
	@PostMapping("/insert")
	public String insertProduct(Product p) {
		dao.save(p);
		//return "insert";
		return "redirect:/list";				//insert 성공했을 때 redirect해서 list로 가도록
	}
	
//	@GetMapping("/list")
//	public void list(Model model) {//상태 유지를 위해 model를 변수로 갖기
//		model.addAttribute("title", "비트 쇼핑몰 상품목록");
//		model.addAttribute("list", dao.findAll());	//model에다가 addAttribute로 상태유지
//	}
	
	@RequestMapping("/list")
	public void list(Model model, @RequestParam(value="cid", defaultValue="0") int cid) {//상태 유지를 위해 model를 변수로 갖기
		System.out.println("카테고리 번호: " +cid);		
		if(cid != 0) {
			model.addAttribute("list", cdao.getOne(cid).getProducts());
		}else {
			model.addAttribute("list", dao.findAll());
		}
	
		model.addAttribute("title", "비트 쇼핑몰 상품목록");
		model.addAttribute("list", dao.findAll());	//model에다가 addAttribute로 상태유지
	}
	
	@GetMapping("/delete")
	public String delete(int id) {
		dao.deleteById(id);
		return "redirect:/list";
	}
	
	@GetMapping("/edit")
	public void edit(int id, Model model) {
		model.addAttribute("p", dao.getOne(id));	//상태유지
		//dao.getOne(id) - 데이터를 하나만 줌( 상품 번호 )
		//dao.findById(id) - 배열 형태로 반환함 - 이거 쓰면 안됨
	}
	
	
	
	
}
