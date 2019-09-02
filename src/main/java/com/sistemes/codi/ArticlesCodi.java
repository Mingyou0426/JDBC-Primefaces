package com.sistemes.codi;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import com.sistemes.models.ArticlesModel;
import com.sistemes.filtres.ArticlesFiltre;
import com.sistemes.llistes.ArticlesLlista;
import com.sistemes.NamedParameterStatement;
import com.sistemes.CadenaConnexio;
import com.sistemes.ClassConnexio;

public class ArticlesCodi {
	ClassConnexio connexio= null;

	public ArticlesCodi(ClassConnexio connexio){
		this.connexio = connexio;
	}

	public ArticlesModel select(int id){
		ArticlesModel element = null;
		ArticlesFiltre filtre = new ArticlesFiltre();
		ArticlesLlista llista;
		filtre.setId(id);
		llista = this.select(filtre);
		if ((llista != null) && (llista.size()>0)){
			element = llista.get(0);
		}
		return element;
	}

	public ArticlesLlista select(ArticlesFiltre filtre){
		ArticlesLlista llista=null;
		String sql = GeneraSentenciaSelect(filtre);

		NamedParameterStatement select;
		ArticlesModel element = null;
		try {
			select = new NamedParameterStatement(this.connexio.getConnection(),sql);
			PassaParametresSelect(select,filtre);
			ResultSet rs = select.executeQuery();
			if (rs != null){
				llista = new ArticlesLlista();
				while (rs.next()) {
					element = new ArticlesModel();
					OmplirAtributs(rs,element);
					llista.add(element);
				}
			}
			select.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return llista;
	}

	public int insert(ArticlesModel element){
		String sql = " insert into ARTICLES "+
					"(ID,NAME,PRICE)"+
			"values"+
					"(:ID,:NAME,:PRICE)";
		NamedParameterStatement insert;
		try {
			insert = new NamedParameterStatement(this.connexio.getConnection(),sql);
			PassaParametres(insert,element);
			insert.execute();
			insert.close();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int insert(ArticlesLlista llista){
		 if ((llista != null) && (llista.size()>0)) {
			for(ArticlesModel element : llista){
				this.insert(element);
			}
		}
		return 1;
	}

	public int update(ArticlesModel element){
		String sql = " update ARTICLES "+
						"					set ID = :ID "+
						"						, NAME = :NAME "+
						"						, PRICE = :PRICE "+
							"			where ID = :ID " ;
		NamedParameterStatement update;
		try {
			update = new NamedParameterStatement(this.connexio.getConnection(),sql);
			PassaParametres(update,element);
			update.execute();
			update.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	public int update(ArticlesLlista llista){
		 if ((llista != null) && (llista.size()>0)) {
			for(ArticlesModel element:llista){
				this.update(element);
			}
		}
		return 1;
	}

	public int delete(ArticlesModel element){
		String sql = " delete form ARTICLES "+
							"			where ID = :ID ";
		NamedParameterStatement delete;
		try {
			delete = new NamedParameterStatement(this.connexio.getConnection(),sql);
				delete.setInt("ID",element.getId());

			delete.execute();
			delete.close();
		return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		   return 0;
		}
	}

	public int delete(ArticlesLlista llista){
		 if ((llista != null) && (llista.size()>0)) {
			for(ArticlesModel element:llista){
				this.delete(element);
			}
		}
		return 1;
	}

	public void PassaParametres(NamedParameterStatement statement,ArticlesModel element){
		try {
				statement.setInt("ID",element.getId());
				statement.setString("NAME",element.getName());
				statement.setInt("PRICE",element.getPrice());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void OmplirAtributs(ResultSet rs,ArticlesModel element){
		try {
			element.setId(rs.getInt("ID"));
			element.setName(rs.getString("NAME"));
			element.setPrice(rs.getInt("PRICE"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void PassaParametresSelect(NamedParameterStatement statement, ArticlesFiltre filtre){
		try {
			if (filtre.getId() != 0) {
				 statement.setInt("ID",filtre.getId());
			}
			if (filtre.getName() != null) {
				 statement.setString("NAME",filtre.getName());
			}
			if (filtre.getPrice() != 0) {
				 statement.setInt("PRICE",filtre.getPrice());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String GeneraSentenciaSelect(ArticlesFiltre filtre){
		String sql = "select * "+
				"from ARTICLES "+
				"where 1 = 1 ";
		if (filtre.getId() != 0) sql += " and ID = :ID";
		if (filtre.getName() != null) sql += " and NAME = :NAME";
		if (filtre.getPrice() != 0) sql += " and PRICE = :PRICE";
		return sql;
	}

}
