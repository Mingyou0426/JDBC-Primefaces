package com.sistemes.codi;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import com.sistemes.models.UsersModel;
import com.sistemes.filtres.UsersFiltre;
import com.sistemes.llistes.UsersLlista;
import com.sistemes.NamedParameterStatement;
import com.sistemes.CadenaConnexio;
import com.sistemes.ClassConnexio;

public class UsersCodi {

		ClassConnexio connexio= null;

		public UsersCodi(ClassConnexio connexio){
			this.connexio = connexio;
		}

		public UsersModel select(int id){
			UsersModel element = null;
			UsersFiltre filtre = new UsersFiltre();
			UsersLlista llista;
			filtre.setId(id);
			llista = this.select(filtre);
			if ((llista != null) && (llista.size()>0)){
				element = llista.get(0);
			}
			return element;
		}

		public UsersLlista select(UsersFiltre filtre){
			UsersLlista llista=null;
			String sql = GeneraSentenciaSelect(filtre);

			NamedParameterStatement select;
			UsersModel element = null;
			try {
				select = new NamedParameterStatement(this.connexio.getConnection(),sql);
				PassaParametresSelect(select,filtre);
				ResultSet rs = select.executeQuery();
				if (rs != null){
					llista = new UsersLlista();
					while (rs.next()) {
						element = new UsersModel();
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

		public int insert(UsersModel element){
			String sql = " insert into USERS "+
						"(ID,NAME,USERNAME,PASSWORD)"+
				"values"+
						"(:ID,:NAME,:USERNAME,:PASSWORD)";
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

		public int insert(UsersLlista llista){
			 if ((llista != null) && (llista.size()>0)) {
				for(UsersModel element : llista){
					this.insert(element);
				}
			}
			return 1;
		}

		public int update(UsersModel element){
		String sql = " update USERS "+
						"					set ID = :ID "+
						"						, NAME = :NAME "+
						"						, USERNAME = :USERNAME "+
						"						, PASSWORD = :PASSWORD "+
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

		public int update(UsersLlista llista){
			 if ((llista != null) && (llista.size()>0)) {
				for(UsersModel element:llista){
					this.update(element);
				}
			}
			return 1;
		}

		public int delete(UsersModel element){
		String sql = " delete form USERS "+
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

		public int delete(UsersLlista llista){
			 if ((llista != null) && (llista.size()>0)) {
				for(UsersModel element:llista){
					this.delete(element);
				}
			}
			return 1;
		}

		public void PassaParametres(NamedParameterStatement statement,UsersModel element){
		try {
				statement.setInt("ID",element.getId());
				statement.setString("NAME",element.getName());
				statement.setString("USERNAME",element.getUsername());
				statement.setString("PASSWORD",element.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}

		public void OmplirAtributs(ResultSet rs,UsersModel element){
			try {
				element.setId(rs.getInt("ID"));
				element.setName(rs.getString("NAME"));
				element.setUsername(rs.getString("USERNAME"));
				element.setPassword(rs.getString("PASSWORD"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		private void PassaParametresSelect(NamedParameterStatement statement, UsersFiltre filtre){
			try {
				if (filtre.getId() != 0) {
					 statement.setInt("ID",filtre.getId());
				}
				if (filtre.getName() != null) {
					 statement.setString("NAME",filtre.getName());
				}
				if (filtre.getUsername() != null) {
					 statement.setString("USERNAME",filtre.getUsername());
				}
				if (filtre.getPassword() != null) {
					 statement.setString("PASSWORD",filtre.getPassword());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		private String GeneraSentenciaSelect(UsersFiltre filtre){
			String sql = "select * "+
					"from USERS "+
					"where 1 = 1 ";
			if (filtre.getId() != 0) sql += " and ID = :ID";
			if (filtre.getName() != null) sql += " and NAME = :NAME";
			if (filtre.getUsername() != null) sql += " and USERNAME = :USERNAME";
			if (filtre.getPassword() != null) sql += " and PASSWORD = :PASSWORD";
			return sql;
		}

}
