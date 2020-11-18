import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;;

public class Laboratorium4 {

	static final String dbName = "biblioteka";
	static final String uzytkownik = "root";
	static final String haslo = "wilkiga";

public static void main (String[] args)
{

	Scanner scanner = new Scanner(System.in);
	int wybor;
	boolean czy_dziala = true;
	Connection conn = null;
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?serverTimezone=UTC",uzytkownik,haslo);
		Statement st = conn.createStatement();
		st.executeUpdate("CREATE DATABASE IF NOT EXISTS biblioteka");    //utworzenie bazy
		st.executeUpdate("USE"+dbName);  //przejście do bazy
		st.executeUpdate("CREATE TABLE IF NOT EXISTS czytelnicy (id_uzytkownika INT(5) AUTO_INCREMENT,imie VARCHAR(30),nazwisko VARCHAR(30),rok_urodzenia INT(4),miejscowosc VARCHAR(100),PRIMARY KEY(id_uzytkownika));");
	String doosoby1 = "INSERT INTO czytelnicy (imie,nazwisko,rok_urodzenia,miejscowosc) VALUES('Iga','W',1997,'Lublin')";	
		PreparedStatement osoba1= conn.prepareStatement(doosoby1);
		osoba1.execute();
	String doosoby2 = "INSERT INTO czytelnicy (imie,nazwisko,rok_urodzenia,miejscowosc) VALUES('Michal','akacjowy',1678,'Lublin')";	
		PreparedStatement osoba2= conn.prepareStatement(doosoby2);
		osoba2.execute();
	String doosoby3 = "INSERT INTO czytelnicy (imie,nazwisko,rok_urodzenia,miejscowosc) VALUES('Iza','Wolska',2000,'Lublin')";	
		PreparedStatement osoba3= conn.prepareStatement(doosoby3);
		osoba3.execute();
		while(czy_dziala)
		{
			System.out.println("\nMENU");
			System.out.println("1. Dodaj nowego czytelnika");
			System.out.println("2. Wyświetl czytelników");
			System.out.println("3. Usuń czytelnika");
			System.out.println("4. Zamknij aplikację");

			System.out.println("Twój wybór: ");
			wybor = scanner.nextInt();
			scanner.nextLine();
			switch(wybor)
				{
					case 1: 
						{   //Dodawanie czytelnika
							System.out.println("Dodaj nowego czytelnika: ");
							System.out.print("Imie: ");
							String imie = scanner.nextLine();

							System.out.print("Nazwisko: ");
							String nazwisko = scanner.nextLine();

							System.out.print("Rok urodzenia: ");
							Integer rok_ur = scanner.nextInt();
							
							System.out.print("Miejscowosc: ");
							String miejscowosc= scanner.nextLine();
							try{
							String insert1 = "INSERT INTO czytelnicy (imie,nazwisko,rok_urodzenia,miejscowosc) VALUES(?,?,?,?)"; 
							PreparedStatement dane = conn.prepareStatement(insert1);
								System.out.print(imie);
							dane.setString(1, imie);
							dane.setString(2,nazwisko);
							dane.setInt(3,rok_ur);
							dane.setString(4, miejscowosc);
							dane.execute();}
							catch (SQLException e)
							{e.printStackTrace();}
							break;
						}
					case 2: 
						{
							try {
							String select1 = "Select * from czytelnicy";
							Statement st1 = (Statement) conn.createStatement();
							ResultSet rs = st1.executeQuery(select1);
							System.out.println("Dane czytelnikow \n\n\n");
							while (rs.next())
							{
								Integer id_uzytkownika = rs.getInt("id_uzytkownika");
								String imie = rs.getString("imie");
								String nazwisko = rs.getString("nazwisko");
								Integer rok = rs.getInt("rok");
								String miejscowosc = rs.getString("miejscowosc");
								
								System.out.println(id_uzytkownika+" "+imie+" "+nazwisko+ ", "+miejscowosc+" "+rok );} 
								
							}
							catch (SQLException e) {
           					    		   System.out.println("Buuuuuuu");;
               								}break;
						}
					case 3: 
						{
							try{
							System.out.println("Podaj id czytelnika którego chceesz usunac");
							Integer id = scanner.nextInt();
							String delete1 = "DELETE FROM czytelnicy WHERE id_uzytkownika = ?";
							PreparedStatement wartosc = conn.prepareStatement(delete1);
							wartosc.setInt(1,id);
							wartosc.execute(); 
							System.out.println("Usunieto");
							}
							catch (SQLException e) {
               						   e.printStackTrace();
             						  }
							break;
						}
					case 4:
					default:
						   czy_dziala= false;System.exit(0);
						
				}
		}

	}
 	catch (ClassNotFoundException | SQLException e) 
	{
           System.out.println("BRAK polaczenia!!");
    	}

	



}
}
