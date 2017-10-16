package model.dao;

public class StatusConection {
	
	public static void main(String[] args) {
		ConexaoDataBase.getConexaoMySQL();
		System.out.println(ConexaoDataBase.status);
}

}
