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
	/*	if (9 != args.length)
		{
			System.out.println("Usage:\n" + "  java DbImgStore drv url usr pwd tbl keyColumn keyValue imgColumn imgFile\n"
							+ "e.g.\n"
							+ "  java DbImgStore com.mysql.jdbc.Driver jdbc:mysql://localhost:3306/MeineDb"
							+ " root mysqlpwd MeineAdressen Name Torsten Foto img/me.jpg");
			System.exit(1);
		}
		*/
		FileInputStream fis = null;
		Connection c = null;
		PreparedStatement st = null;
		try
		{
			File fl = new File("img/owncloud_logo.png"); // imgFile
			fis = new FileInputStream(fl);
			c = DBConnection.connect();
			//Class.forName(args[0]); // drv
			//cn = DriverManager.getConnection(args[1], args[2], args[3]); // url, usr, pwd
			// update tbl set imgColumn = 'imgFile?' where keyColumn = 'keyValue?':
			//                        1   2   3   4     5        6          7        8 
			//"  java DbImgStore drv url usr pwd tbl keyColumn keyValue imgColumn imgFile\n"
			//st = c.prepareStatement("update " + args[4] + " set " + args[7] + " = ? where " + args[5] + " = ?");
			//st.setBinaryStream(1, fis, (int) fl.length()); // imgFile
			//st.setString(2, args[6]); // keyValue
			
			/**  ID  dI_ID  U_ID  imgdata  imgtype          **/
			//st = c.prepareStatement("UPDATE tb_images set imgdata = ?");
			st = c.prepareStatement("UPDATE testimage set imgdata = ?");
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
