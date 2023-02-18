package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Resevation;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Room number: ");
		int roomNumber = sc.nextInt();
		System.out.print("Check-in date (dd/MM/yyyy): ");
		Date checkIn = sdf.parse(sc.next());
		System.out.print("Check-out date (dd/MM/yyyy): ");
		Date checkOut = sdf.parse(sc.next());
		
		//Ainda temos essa valida��o feita no programa principal, que � na hora de INSTANCIAR o objeto!
		//Ainda n�o vamos tirar essa valida��o daqui pq ela tenque ser uma valida��o no CONSTRUTOR!! ja que � na INSTANCIA��O do objeto
		//Por�m, o CONSTRUTOR n�o tem como eu colocar ele para retornar um String!
		if (!checkOut.after(checkIn)) { 
			System.out.println("Error in reservation: Check-out date must be after check-in date");
		}
		else {
			Resevation reservation = new Resevation(roomNumber, checkIn, checkOut); //Instanciei minha reserva
			System.out.println("Reservation: " + reservation);
		
			System.out.println();
		
			System.out.println("Enter data to update the reservation: ");
			System.out.print("Check-in date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());
			System.out.print("Check-out date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());
			
			String error = reservation.updateDates(checkIn, checkOut); 
			//Agora a fun��o reservarion() do objt reservation vai retornar uma String, que vamos guardar!	
			if (error != null) {
				System.out.println("Error in reservations: " + error);
			}
			else {
				System.out.println("Reservation: " + reservation);
			}
		}
	sc.close();
	}
}

