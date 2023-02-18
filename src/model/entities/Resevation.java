package model.entities;	

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//--Lógica de Validação DELEGADA para a classe [Reservartion] "tratamento da excessão"--
public class Resevation {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  //Para formatar um tipo Date!
	//statico para que não seja instanciado um novo objeto SimpleDateFormat para cada Objeto Reservation que minha aplicação tiver!
	//Vou precisar de apenas 1, por isso coloquei como static!
	
	private Integer roomNumber;
	private Date checkIn;		//Obs: Data trabalha com milissegundos
	private Date checkOut;
	
	//Sem nos preocuparmos ainda com excessões!
	
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

	//Não temos setCheckout e o setCheckin pois não vou dexar que as datas sejam alteradas arbitariamente!
	//Temos um metodo para isso  updateDates()
	
	public long duration() { //Usaremos um macete para diferença entre datas que me retorna um valor long
		
		long diff = checkOut.getTime() - checkIn.getTime();//Diferença entre as duas Datas em milissegundos!
		
		//Agora como converto esse milissegundos para dias??? 
		//Vou usar uma Classe do JAVA chamada TimeUnit -- Na verdade ela é um tipo ENUMERADO complexo que tem operações! Ñ classe
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); //Isso converte o valor da var: diff que estava em milisseconds para Dias!	
	}
	
	public String updateDates(Date checkIn, Date checkOut) {
		Date now = new Date(); //Objeto com data atual!
		if (checkIn.before(now) || checkOut.before(now)) { //Se a data checkin for antes do agora ou a data de chekout tmb
			return "Reservation dates for update must be future dates"; //Retorna essa String
		}
		if (!checkOut.after(checkIn)) { //Se a data de checkout nao é posterior a data de chekin
			return "Check-out date must be after check-in date"; //Retorna essa String
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		return null; //Para indicar que não teve nenhum erro, mando retornar NULL
		//null é o critério para dizer que minha operação não deu nenhum erro!
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
