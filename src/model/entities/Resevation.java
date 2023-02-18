package model.entities;	

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//--L�gica de Valida��o DELEGADA para a classe [Reservartion] "tratamento da excess�o"--
public class Resevation {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  //Para formatar um tipo Date!
	//statico para que n�o seja instanciado um novo objeto SimpleDateFormat para cada Objeto Reservation que minha aplica��o tiver!
	//Vou precisar de apenas 1, por isso coloquei como static!
	
	private Integer roomNumber;
	private Date checkIn;		//Obs: Data trabalha com milissegundos
	private Date checkOut;
	
	//Sem nos preocuparmos ainda com excess�es!
	
	public Resevation() {
	}

	public Resevation(Integer roomNumber, Date checkIn, Date checkOut) {
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkIn;
	}

	public Date getCheckout() {
		return checkOut;
	}

	//N�o temos setCheckout e o setCheckin pois n�o vou dexar que as datas sejam alteradas arbitariamente!
	//Temos um metodo para isso  updateDates()
	
	public long duration() { //Usaremos um macete para diferen�a entre datas que me retorna um valor long
		
		long diff = checkOut.getTime() - checkIn.getTime();//Diferen�a entre as duas Datas em milissegundos!
		
		//Agora como converto esse milissegundos para dias??? 
		//Vou usar uma Classe do JAVA chamada TimeUnit -- Na verdade ela � um tipo ENUMERADO complexo que tem opera��es! � classe
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); //Isso converte o valor da var: diff que estava em milisseconds para Dias!	
	}
	
	public String updateDates(Date checkIn, Date checkOut) {
		Date now = new Date(); //Objeto com data atual!
		if (checkIn.before(now) || checkOut.before(now)) { //Se a data checkin for antes do agora ou a data de chekout tmb
			return "Reservation dates for update must be future dates"; //Retorna essa String
		}
		if (!checkOut.after(checkIn)) { //Se a data de checkout nao � posterior a data de chekin
			return "Check-out date must be after check-in date"; //Retorna essa String
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		return null; //Para indicar que n�o teve nenhum erro, mando retornar NULL
		//null � o crit�rio para dizer que minha opera��o n�o deu nenhum erro!
	}
	
	@Override
	public String toString() {
		
		return "Room " 
				+ getRoomNumber() 
				+ ", check-in: " 
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}
}
