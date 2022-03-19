package br.com.adamastor.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.adamastor.forum.controller.dto.TopicoDto;
import br.com.adamastor.forum.modelo.Curso;
import br.com.adamastor.forum.modelo.Topico;

@RestController
public class TopicoController {

	@RequestMapping("/topicos")
	public List<TopicoDto> lista(){
		Curso c1 = new Curso("Spring boot", "Programação");
		Topico t1 = new Topico("Título 1", "Mensagem 1", c1);
		Topico t2 = new Topico("Título 2", "Mensagem 2", c1);
		Topico t3 = new Topico("Título 3", "Mensagem 3", c1);
		Topico t4 = new Topico("Título 4", "Mensagem 4", c1);

		return TopicoDto.converter(Arrays.asList(t1, t2, t3, t4));
	}
}
