package dataBase;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;


public class DBImgSave {
	public static void inputPictureDB()
	{

		FileInputStream fis = null;
		Connection c = null;
		PreparedStatement st = null;
		try
		{
			File fl = new File("img/1.png"); // imgFile
			fis = new FileInputStream(fl);
			c = DBConnection.connect();

			
			/**  ID  dI_ID  U_ID  imgdata  imgtype          **/
			
			st = c.prepareStatement("UPDATE tb_images set imgdata = ? WHERE ID=1");
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
