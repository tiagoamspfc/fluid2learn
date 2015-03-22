package pt.c02classes.s01knowledge.s02app.actors;


import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean discover() {
		
		String N = responder.ask("norte");/*Strings Atualizadas com as posicoes em todas as direcoes*/
		String S = responder.ask("sul");
		String L = responder.ask("leste");
		String O = responder.ask("oeste");
		String A = responder.ask("aqui");
		int cord=1;/*Variavel coordenada, utilizada para indicar o sentido de deslocamento, NORTE = 1 , Sul =2 ,Leste=3 , Oeste=4 */
		
		/*Caso a entrada seja Aberta em mais de um bloco de passagem ex -->(##E  ###)*/
		while( L == "passagem"){
			responder.move("leste");
			L = responder.ask("leste");
			N = responder.ask("norte");
			S = responder.ask("sul");
			O = responder.ask("oeste");
			A = responder.ask("aqui");
		}
		
		/*No caso em que que se atingiu a o ultimo bloco ao leste,verifica se o norte esta disponivel e o move*/
		if(S == "mundo" && N != "parede"){
			responder.move("norte");
			L = responder.ask("leste");
			N = responder.ask("norte");
			S = responder.ask("sul");
			O = responder.ask("oeste");
			A = responder.ask("aqui");
		}else if(S == "mundo" && N == "parede" ){/*Caso contrario, move para oeste*/
			responder.move("oeste");
			L = responder.ask("leste");
			N = responder.ask("norte");
			S = responder.ask("sul");
			O = responder.ask("oeste");
			A = responder.ask("aqui");
			cord = 4;
		}
		

		while(A != "saida"){
			/*Enquanto Nao encontrar a Saida*/
			
			/*Sentido atual Norte*/
			if( cord == 1){
				if(L != "parede"){

					responder.move("leste");
					cord = 3;

				}
				else if( L == "parede" && N != "parede"){

					responder.move("norte");
					cord =1;

				}
				else if( L == "parede" && N == "parede" && O != "parede"){
					responder.move("oeste");
					cord =4;
					
				}
				else if(L == "parede" && N == "parede" && O == "parede" && S !="parede" ){
					responder.move("sul");
					cord = 2;
					
				}
			}
			else if( cord == 2){			/*Sentido atual Sul*/

				
				if(O != "parede"){
					responder.move("oeste");
					cord = 4;
				}
				else if( O == "parede" && S != "parede"){
					responder.move("sul");
					cord = 2;
				}
				else if( O == "parede" && S == "parede" && L != "parede"){
					responder.move("leste");
					cord = 3;
					
				}
				else if(O == "parede" && S == "parede" && L == "parede" && N !="parede" ){
					responder.move("norte");
					cord = 1;
				}
			}
			else if( cord == 3){			/*Sentido atual Leste*/

				
				if(S != "parede"){
					responder.move("sul");
					cord = 2;
				}
				else if( S == "parede" && L != "parede"){
					responder.move("leste");
					cord =3;
					
				}
				else if( S == "parede" && L == "parede" && N != "parede"){
					responder.move("norte");
					cord = 1;
					
				}
				else if(S == "parede" && L == "parede" &&  N== "parede" && O !="parede" ){
					responder.move("oeste");
					cord = 4;
				}
			}else if( cord == 4){			/*Sentido atual Oeste*/

				
				if(N != "parede"){
					responder.move("norte");
					cord = 1;
					
				}
				else if( N == "parede" && O != "parede"){
					responder.move("oeste");
					cord = 4;
				}
				else if( N == "parede" && O == "parede" && S != "parede"){
					responder.move("sul");
					cord = 2;
					
				}
				else if(N == "parede" && L == "parede" && S == "parede" && L !="parede" ){
					responder.move("leste");
					cord = 3;
					
				}
			
			}
			
			
			/*Atualiza , a cada laco , o que existe em todas as direcoes*/
			N = responder.ask("norte");
			S = responder.ask("sul");
			L = responder.ask("leste");
			O = responder.ask("oeste");
			A = responder.ask("aqui");;

		}
		
		if (responder.finalAnswer("cheguei"))
			System.out.println("Voce encontrou a saida!");
		else
			System.out.println("Fuem fuem fuem!");
		
		
		return true;
	}
	
}
