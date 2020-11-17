import java.sql.*;
import java.util.Scanner;

public class Laboratorium4 {

	static final string dbName = "biblioteka";
	static final String uzytkownik = "IWilk";
	static final String haslo = "wilkiga";

public static void main (String[] args)
{

	Scanner scanner = new Scanner(System.in);
	int wybor;
	boolean czy_działa = true;
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?serverTimezone=UTC",uzytkownik,haslo);
		Statement st = conn.createStatement();
		st.executeUpdate("CREATE DATABASE IF NOT EXISTS biblioteka");    //utworzenie bazy
		st.executeUpdate("USE"+dbName);  //przejście do bazy
		st.executeUpdate("CREATE TABLE IF NOT EXISTS czytelnicy (id_uzytkownika INT(5) AUTO_INCREMENT,imie VARCHAR(30),nazwisko VARCHAR(30),rok_urodzenia INT(4),miejscowosc VARCHAR(100),PRIMARY KEY(id_uzytkownika))");
	String doosoby1 = "INSERT INTO czytelnicy (imie,nazwisko,rok_urodzenia,miejscowosc) VALUES('Iga','W','1997','Lublin')";	
		PreparedStatement osoba1= conn.prepareStatement(doosoby1);
		osoba1.execute();
	String doosoby2 = "INSERT INTO czytelnicy (imie,nazwisko,rok_urodzenia,miejscowosc) VALUES('Michal','akacjowy',1678,'Lublin')";	
		PreparedStatement osoba2= conn.prepareStatement(doosoby2);
		osoba2.execute();
	String doosoby3 = "INSERT INTO czytelnicy (imie,nazwisko,rok_urodzenia,miejscowosc) VALUES('Iza','Wolska',2000,'Lublin')";	
		PreparedStatement osoba1= conn.prepareStatement(doosoby3);
		osoba3.execute();
	}
 	catch (ClassNotFoundException | SQLException e) 
	{
           e.printStackTrace();
    	}

	while(run)
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
							Int rok_ur = scanner.nextInt();
							
							System.out.print("Miejscowosc: ");
							String miejscowosc= scanner.nextLine();
							
							String insert1 = "INSERT INTO czytelnicy (imie,nazwisko,rok_urodzenia,miejscowosc) VALUES(?,?,?,?)"; 
							PreparedStatement dane = conn.prepareStatement(insert1);
							dane.setString(1, imie);
							dane.setString(2,nazwisko);
							dane.setString(3,rok_ur);
							dane.setString(4, miejscowosc);
							dane.execute();
							break;
						}
					case 2: 
						{
							Statemenent st (Statement) conn.createStatement();
							String select1 = "Select * from czytelnicy";
							ResultSet rs = st.executeQuery(select1);
							System.out.println("Dane czytelnikow \n\n\n");
							while (rs.next())
							{
								int id_uzytkownika = rs.getInt("id_uzytkownika");
								String imie = rs.getString("imie");
								String nazwisko = rs.getString("nazwisko");
								int rok = rs.getInt("rok");
								String miejscowosc = rs.getString("miejscowosc");
								
								System.out.printl(id+" "+imie+" "+nazwisko+ ", "+miejscowosc+" "+rok );break;
							}
						}
					case 3: 
						{
							System.out.println("Podaj id czytelnika którego chceesz usunac");
							Int id = scanner.nextint();
							String delete1 = "DELETE FROM czytelnicy WHERE id_uzytkownika = ?";
							PreparedStatement wartosc = conn.prepareStatement(query);
							wartosc.setInt(1,id);
							wartosc.execute(); 
							System.out.println("Usunieto);break;
						}
					case 4:
					default:
						run = false;
						System.exit(0);
				}
		}




}
}
