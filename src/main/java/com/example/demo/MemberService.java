package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.entity.Member100;


@Service
public class MemberService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Autowired
	private MemberDao mdao;
	
	public void setMdao(MemberDao mdao) {
		this.mdao = mdao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member100 m = mdao.findById(username).get();
		
		if(m==null) {
			throw new UsernameNotFoundException(username);
		}
		
		
		return User.builder()
				.username(username)		//username은 이렇게 할게요
				.password(m.getPwd())	//비밀번호는 이렇게 받을게요
				.roles(m.getRole())		//권한은 이렇게 할게요
				.build();
	}

}
