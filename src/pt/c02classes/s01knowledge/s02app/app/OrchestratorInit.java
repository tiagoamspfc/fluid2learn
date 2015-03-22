package pt.c02classes.s01knowledge.s02app.app;

import java.util.Scanner;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerMaze;
import pt.c02classes.s01knowledge.s02app.actors.ResponderMaze;

public class OrchestratorInit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		IEnquirer enq;
		IResponder resp;
		IStatistics stat;		
		IBaseConhecimento base = new BaseConhecimento();
			
		String  mode, argumento;
		
		Scanner entry = new Scanner(System.in);
		
		System.out.println("Deseja jogar animals ou maze?");
		mode = entry.nextLine();
		
		if (mode.equalsIgnoreCase("Animals")) {
			System.out.println("Escolha seu animal a ser advinhado!");
			argumento = entry.nextLine();
			
			base.setScenario ("animals");
			stat = new Statistics();
			resp = new ResponderAnimals(stat, argumento);
			enq = new EnquirerAnimals();
			enq.connect(resp);
			enq.discover();
			
			System.out.println("----------------------------------------------------------------------------------------\n");
		}
		
		else if (mode.equalsIgnoreCase("Maze")) {
			System.out.println("Escolha o nome do labirinto a ser desafiado!");
			argumento = entry.nextLine();
			
			base.setScenario ("maze");
			stat = new Statistics();
			resp = new ResponderMaze(stat, argumento);
			enq = new EnquirerMaze();
			enq.connect(resp);
			enq.discover();
			
			System.out.println("----------------------------------------------------------------------------------------\n");
		}
		
		else {
			System.out.println("Erro: modo de jogo nao encontrado!");	
		}
			
		entry.close();
	}

}
