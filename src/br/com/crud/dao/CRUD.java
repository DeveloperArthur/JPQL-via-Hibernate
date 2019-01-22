package br.com.crud.dao;

import br.com.crud.model.Categoria;
import br.com.crud.model.Cliente;
import br.com.crud.model.Compra;
import br.com.crud.model.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CRUD {
   

    public static void main(String[] args) {
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("CrudPU");
        EntityManager em = factory.createEntityManager();
        
        //cadastrando cliente / istanciando classe cliente
        Cliente cli = new Cliente();
        cli.setNome("Aluno Uninove");
        cli.setCpf("123456");
        cli.setGenero("M");
        cli.setEndereco("Rua sem nome");
        cli.setEstado("MG");
        cli.setIdade(18);
        cli.setCidade("Cataguases");
        cli.setTelefone("9 1234-1234");
        cli.setCep("12.345-678");
        
       
        Cliente cli1 = new Cliente();
        cli1.setNome("AdaFAFBninove");
        cli1.setCpf("123456");
        cli1.setGenero("M");
        cli1.setEndereco("Rua sem nome");
        cli1.setEstado("MG");
        cli1.setIdade(18);
        cli1.setCidade("Cataguases");
        cli1.setTelefone("9 1234-1234");
        cli1.setCep("12.345-678");
        
        // grava o cliente no banco de dados
        em.getTransaction().begin();
        em.persist(cli);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        em.persist(cli1);
        em.getTransaction().commit();
        
        //istanciando classe categoria, criando 2 categorias e setando descrições nelas
        Categoria categ1 = new Categoria();
        Categoria categ2 = new Categoria();
        categ1.setDescricao("Eletrônico");
        categ2.setDescricao("Vestuário");
        
        //?
        em.getTransaction().begin();
        em.persist(categ1);
        em.persist(categ2);
        em.getTransaction().commit();
        
        //istanciando classe produto, criando e cadastrando produto no banco de dados
        Produto prod1 = new Produto();
        prod1.setNome("Fone estério");
        prod1.setDecricao("Fone de Ouvido.");
        prod1.setValor(35.0);
        prod1.setCategoria(categ1);
        
        //istanciando classe produto, criando e cadastrando produto no banco de dados
        Produto prod2 = new Produto();
        prod2.setNome("Camisa Nerd");
        prod2.setDecricao("Camisa com imagem de personagem.");
        prod2.setValor(25.0);
        prod2.setCategoria(categ2);
        
        //?
        em.getTransaction().begin();
        em.persist(prod1);
        em.persist(prod2);
        em.getTransaction().commit();
        
        //istanciando classe compra, instanciando e criando uma lista
        Compra comp1 = new Compra();
        ArrayList<Produto> lista1 = new ArrayList<>();
        //adicionando na lista ? passando os dois objetos produto como parametro
        lista1.add(prod1);
        lista1.add(prod2);
        //acessando metodo compra e setando a lista de produtos, passando a lista como parametro
        comp1.setLstProdutos(lista1);
        
        //istanciando classe compra, instanciando e criando uma lista
        Compra comp2 = new Compra();
        ArrayList<Produto> lista2 = new ArrayList<>();
        //adicionando na lista ? passando os dois objetos produto como parametro
        lista2.add(prod1);
        //acessando metodo compra e setando a lista de produtos, passando a lista como parametro
        comp2.setLstProdutos(lista2);
        
        // ?
        em.getTransaction().begin();
        em.persist(comp1);
        em.persist(comp2);
        em.getTransaction().commit();
        
        // intanciando a classe lista e criando uma lista
        ArrayList<Compra> lstCompras = new ArrayList<>();
        ////adicionando na lista ? passando os dois objetos produto como parametro
        lstCompras.add(comp1);
        lstCompras.add(comp2);
        //no objeto cliente, setando as compras dele
        cli.setCompras(lstCompras);
        
        // O merge() recebe um objeto "comum", que não está no contexto de persistência, e copia as propriedades deste objeto para a verdadeira instância da entidade.
        em.getTransaction().begin();
        em.merge(cli);
        em.getTransaction().commit();
        
        //buscando clientes pelo metodo de query criando lista pra imprimir e printando
        List<Cliente> lstCliente = cli.buscarClientes(em);
        for (Cliente xnome:lstCliente){
            System.out.println("Nome: "+xnome.getNome());  
        }    
    }

}
