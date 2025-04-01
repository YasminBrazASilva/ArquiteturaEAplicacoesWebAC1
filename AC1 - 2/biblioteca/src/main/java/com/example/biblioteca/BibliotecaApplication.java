package com.example.biblioteca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.biblioteca.models.Autor;
import com.example.biblioteca.models.Livro;
import com.example.biblioteca.repositories.AutorRepository;
import com.example.biblioteca.repositories.LivroRepository;

@SpringBootApplication
public class BibliotecaApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired LivroRepository livroRepository,
			@Autowired AutorRepository autorRepository
		) {
		return args -> {
			System.out.println("*** Inserir Autores");
			Autor aut1 = autorRepository.save(new Autor(null, "Ana Beatriz"));
			Autor aut2 = autorRepository.save(new Autor(null, "Alan Costa"));
			Autor aut3 = autorRepository.save(new Autor(null, "Daniel Harkman"));
			Autor aut4 = autorRepository.save(new Autor(null, "Galileu Galilei"));

			livroRepository.save(new Livro(null, "Harry Potter", 4.00));
			livroRepository.save(new Livro(null, "Nárnia", 700.01));
			livroRepository.save(new Livro(null, "Horizon", 700.00));

			System.out.println("*** Vincular Autor ao Livro ***");
			Livro livro1 = livroRepository.findAll().get(0);
			livro1.setAutor(aut2);
			livroRepository.save(livro1);

			Livro livro2 = livroRepository.findAll().get(1);
			livro2.setAutor(aut2);
			livroRepository.save(livro2);

			Livro livro3 = livroRepository.findAll().get(2);
			livro3.setAutor(aut3);
			livroRepository.save(livro3);


			Double var1 = 500.0;
			List<Livro> listaLivros1 = livroRepository.encontrarLivrosComPrecoMaiorQue(var1);
			System.out.println("*** Livros com valor maior que: " + var1);
			listaLivros1.forEach(System.out::println);
			System.out.println();

			Double var2 = 700.0;
			List<Livro> listaLivros2 = livroRepository.encontrarLivrosComPrecoMenorOuIgualQue(var2);
			System.out.println("*** Livros com preço menor ou igual que:" + var2) ;
			listaLivros2.forEach(System.out::println);
			System.out.println();

			String var3 = "H";
			List<Livro> listaLivros3 = livroRepository.encontrarLivrosQueComecamCom(var3);
			System.out.println("*** Livros que começam com: " + var3);
			listaLivros3.forEach(System.out::println);
			System.out.println();

			String var4 = "A";
			List<Autor> listaAutores1 = autorRepository.encontrarAutoresQueComecamCom(var4);
			System.out.println("*** Autores que começam com: " + var4);
			listaAutores1.forEach(System.out::println);
			System.out.println();

			Long var5 = 2L;
			List<Autor> listaAutores2 = autorRepository.encontrarAutorPorId(var5);
			System.out.println("*** Autor com o id: " + var5);
			listaAutores2.forEach(System.out::println);
			System.out.println();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

}
