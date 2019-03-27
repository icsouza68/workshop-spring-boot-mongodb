package com.ivansouza.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivansouza.workshopmongo.domain.Post;
import com.ivansouza.workshopmongo.repository.PostRepository;
import com.ivansouza.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle (String text){
//		return repo.findByTitleContainingIgnoreCase(text);
		return repo.findByTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		// o ajuste abaixo é necessário porque a comparação será 'MENOR OU IGUAL'
		// e a data gerada é da meia-noite daquele dia
		maxDate = new Date(maxDate.getTime() + 24*60*60*1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
}
