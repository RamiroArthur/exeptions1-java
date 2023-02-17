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
		
		if (!checkOut.after(checkIn)) { //Date, tem um methodo chamado after() que testa se uma Data é depois da outra!
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
			
			Date now = new Date(); //Objeto com data atual!
			
			if (checkIn.before(now) || checkOut.before(now)) { //Se a data checkin for antes do agora ou a data de chekout tmb
				System.out.println("Error in reservation: Reservation dates for update must be future dates");
			}
			else if (!checkOut.after(checkIn)) { //Se a data de checkout nao é posterior a data de chekin
				System.out.println("Error in reservation: Check-out date must be after check-in date");
			}
			else {
				reservation.updateDates(checkIn, checkOut);
				System.out.println("Reservation: " + reservation);
			}
		}
		
		sc.close();
	}
}
