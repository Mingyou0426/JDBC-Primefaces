package com.sistemes.view;

import com.sistemes.Application;
import com.sistemes.codi.ArticlesCodi;
import com.sistemes.models.ArticlesModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="articleeditView")
@RequestScoped
public class ArticleeditView {

	private ArticlesModel articles = null;
	
	public ArticleeditView(){
		super();
		articles = new ArticlesModel();
	}
	
	
	public void articlesSave(){
		ArticlesCodi negoci = new ArticlesCodi(Application.getConnexio());
		negoci.update(articles);
		try {
			Application.getConnexio().getConnection().commit();
		} catch ( Exception e ) {
			System.out.println(e.getMessage());
		}
	}


	public ArticlesModel getArticles() {
		return articles;
	}


	public void setArticles(ArticlesModel articles) {
		this.articles = articles;
	}
	
	

}
