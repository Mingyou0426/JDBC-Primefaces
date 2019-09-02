package com.sistemes.codi;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import com.sistemes.models.CustomersModel;
import com.sistemes.filtres.CustomersFiltre;
import com.sistemes.llistes.CustomersLlista;
import com.sistemes.NamedParameterStatement;
import com.sistemes.CadenaConnexio;
import com.sistemes.ClassConnexio;

public class CustomersCodi {

		ClassConnexio connexio= null;

		public CustomersCodi(ClassConnexio connexio){
			this.connexio = connexio;
		}

		public CustomersModel select(int id){
			CustomersModel element = null;
			CustomersFiltre filtre = new CustomersFiltre();
			CustomersLlista llista;
			filtre.setId(id);
			llista = this.select(filtre);
			if ((llista != null) && (llista.size()>0)){
				element = llista.get(0);
			}
			return element;
		}

		public CustomersLlista select(CustomersFiltre filtre){
			CustomersLlista llista=null;
			String sql = GeneraSentenciaSelect(filtre);

			NamedParameterStatement select;
			CustomersModel element = null;
			try {
				select = new NamedParameterStatement(this.connexio.getConnection(),sql);
				PassaParametresSelect(select,filtre);
				ResultSet rs = select.executeQuery();
				if (rs != null){
					llista = new CustomersLlista();
					while (rs.next()) {
						element = new CustomersModel();
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

		public int insert(CustomersModel element){
			String sql = " insert into CUSTOMERS "+
						"(ID,NAME,VATNUMBER,LOCATION,CITY)"+
				"values"+
						"(:ID,:NAME,:VATNUMBER,:LOCATION,:CITY)";
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

		public int insert(CustomersLlista llista){
			 if ((llista != null) && (llista.size()>0)) {
				for(CustomersModel element : llista){
					this.insert(element);
				}
			}
			return 1;
		}

		public int update(CustomersModel element){
		String sql = " update CUSTOMERS "+
						"					set ID = :ID "+
						"						, NAME = :NAME "+
						"						, VATNUMBER = :VATNUMBER "+
						"						, LOCATION = :LOCATION "+
						"						, CITY = :CITY "+
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

		public int update(CustomersLlista llista){
			 if ((llista != null) && (llista.size()>0)) {
				for(CustomersModel element:llista){
					this.update(element);
				}
			}
			return 1;
		}

		public int delete(CustomersModel element){
		String sql = " delete form CUSTOMERS "+
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

		public int delete(CustomersLlista llista){
			 if ((llista != null) && (llista.size()>0)) {
				for(CustomersModel element:llista){
					this.delete(element);
				}
			}
			return 1;
		}

		public void PassaParametres(NamedParameterStatement statement,CustomersModel element){
		try {
				statement.setInt("ID",element.getId());
				statement.setString("NAME",element.getName());
				statement.setInt("VATNUMBER",element.getVatnumber());
				statement.setString("LOCATION",element.getLocation());
				statement.setString("CITY",element.getCity());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}

		public void OmplirAtributs(ResultSet rs,CustomersModel element){
			try {
				element.setId(rs.getInt("ID"));
				element.setName(rs.getString("NAME"));
				element.setVatnumber(rs.getInt("VATNUMBER"));
				element.setLocation(rs.getString("LOCATION"));
				element.setCity(rs.getString("CITY"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		private void PassaParametresSelect(NamedParameterStatement statement, CustomersFiltre filtre){
			try {
				if (filtre.getId() != 0) {
					 statement.setInt("ID",filtre.getId());
				}
				if (filtre.getName() != null) {
					 statement.setString("NAME",filtre.getName());
				}
				if (filtre.getVatnumber() != 0) {
					 statement.setInt("VATNUMBER",filtre.getVatnumber());
				}
				if (filtre.getLocation() != null) {
					 statement.setString("LOCATION",filtre.getLocation());
				}
				if (filtre.getCity() != null) {
					 statement.setString("CITY",filtre.getCity());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		private String GeneraSentenciaSelect(CustomersFiltre filtre){
			String sql = "select * "+
					"from CUSTOMERS "+
					"where 1 = 1 ";
			if (filtre.getId() != 0) sql += " and ID = :ID";
			if (filtre.getName() != null) sql += " and NAME = :NAME";
			if (filtre.getVatnumber() != 0) sql += " and VATNUMBER = :VATNUMBER";
			if (filtre.getLocation() != null) sql += " and LOCATION = :LOCATION";
			if (filtre.getCity() != null) sql += " and CITY = :CITY";
			return sql;
		}

}
