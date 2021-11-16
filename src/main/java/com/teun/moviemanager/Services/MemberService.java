package com.teun.moviemanager.Services;

import com.teun.moviemanager.Models.Member;
import com.teun.moviemanager.Repositories.IMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private IMemberRepository repository;

    public Member getUser(Member user){
       return repository.getById(user.getId());
    }
}
