package com.example.projetoescola;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.projetoescola.models.CategoriaProduto;
import com.example.projetoescola.models.Produto;
import com.example.projetoescola.repositories.CategoriaProdutoRepository;
import com.example.projetoescola.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetoLojaApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired ProdutoRepository produtoRepository,
			@Autowired CategoriaProdutoRepository categoriaProdutoRepositiry
		) {
		return args -> {
			System.out.println("*** Inserir Categoria:");
			CategoriaProduto categ1 = categoriaProdutoRepositiry.inserir(new CategoriaProduto(null, "Vestuário", "Peças de roupas e sapatos."));
			CategoriaProduto categ2 = categoriaProdutoRepositiry.inserir(new CategoriaProduto(null, "Eletrônicos", "Produtos que podem queimar se forem colocados debaixo d'água."));

			produtoRepository.inserir(new Produto(null, "Notebook", 4));
			produtoRepository.inserir(new Produto(null, "Sandália", 700));

			System.out.println("*** Vincular Categoria ao Produto ***");
			Produto produto1 = produtoRepository.selecionarTodos().get(0);
			produto1.setCategoriaProduto(categ2);
			produtoRepository.inserir(produto1);

			Produto produto2 = produtoRepository.selecionarTodos().get(1);
			produto2.setCategoriaProduto(categ1);
			produtoRepository.inserir(produto2);

			List<Produto> listaProdutos = produtoRepository.selecionarTodos();
			System.out.println("*** Produtos cadastrados:");
			listaProdutos.forEach(System.out::println);

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetoLojaApplication.class, args);
	}

}
