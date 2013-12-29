package dataBase;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;

public class DBImgSave {
	public static void inputPictureDB()
	//public static void main(String[] args)
	{

		FileInputStream fis = null;
		Connection c = null;
		PreparedStatement st = null;
		try
		{
			File fl = new File("img/me.jpg"); // imgFile
			fis = new FileInputStream(fl);
			c = DBConnection.connect();

			
			/**  ID  dI_ID  U_ID  imgdata  imgtype          **/
			
			st = c.prepareStatement("UPDATE testimage set imgdata = ? WHERE ID=2");
			//String SQL = "INSERT INTO tb_user VALUES (null,'"+ txtVorname.getText() + "', '"+ txtNachname.getText() + "', '"+ txtEmail.getText() + "', '"+ txtUserName.getText() + "', '"+ txtPw.getText() + "', '" + timestamp+ "')";
			//st = c.prepareStatement("INSERT INTO tb_images VALUES (null,'3','2',' imgdata = ?','jpg')");
			st.setBinaryStream(1, fis, (int) fl.length()); // imgFile
			
			st.executeUpdate();
			
			System.out.println(st.toString());
			System.out.println(fl.length() + " Bytes successfully loaded.");
		} catch (Exception ex)
		{
			System.out.println(ex);
		} finally
		{
		      try { if( null != st  ) st.close();  } catch( Exception ex ) {/*ok*/}
		      try { if( null != c   )  c.close();  } catch( Exception ex ) {/*ok*/}
		      try { if( null != fis ) fis.close(); } catch( Exception ex ) {/*ok*/}
		}
	}
}
