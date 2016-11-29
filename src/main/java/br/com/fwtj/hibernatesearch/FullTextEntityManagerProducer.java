package br.com.fwtj.hibernatesearch;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

public class FullTextEntityManagerProducer {

    FullTextEntityManager fullTextEntityManager = null;

    public void inicia(EntityManager entityManager) throws InterruptedException{
        // RETORNA UMA ENTIDADE DE PESQUISA
        fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        // INDEXA E ESPERA TERMINAR
        fullTextEntityManager.createIndexer().startAndWait();
    }

    public void para() {
        // FECHA O fullTextEntityManager E O entityManager QUE ELE RECEBEU
        fullTextEntityManager.close();
    }

    public List<Produto> pesquisaProduto(String texto) {
        // CRIA A QUERY COM O MODELO ESCOLHIDO
        QueryBuilder builder = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Produto.class).get();

        // CRIA A QUERY DO LUCENE JA COM O TEXTO ESCOLHIDO
        org.apache.lucene.search.Query luceneQuery = builder
                .keyword()
                .onFields("nome", "descricao","fabricante.nome")
                .matching(texto)
                .createQuery();

        // CRIAR UMA QUERY DO JPA QUE RECEBE A QUERY DO LUCENE
        javax.persistence.Query jpaQuery = fullTextEntityManager
                .createFullTextQuery(luceneQuery, Produto.class);

        // TRANSFORMA A QUERY EM LISTA DE PRODUTOS
        List<Produto> resultList = jpaQuery.getResultList();
        
        // RETORNA A LISTA
        return resultList;
    }

}
