package adrar.jcvd.riskinspace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adrar.jcvd.riskinspace.repositories.PlanetRepository;
import adrar.jcvd.riskinspace.repositories.SpeciesRepository;

@Service
public class RiskInSpaceService {
	
	@Autowired
	SpeciesRepository speciesRepo;
	@Autowired
	PlanetRepository planetRepo;
	//insertion des joueurs
	public ArrayList<Player> insertPlayer() {
		ArrayList<Player> players = new ArrayList<Player>();
		Scanner sc = new Scanner(System.in);
		System.out.println("rentre le nbr de joueurs");
		int numberPlayer = 2;//sc.nextInt();
		while(numberPlayer > 0) {
			//Scanner sc2 = new Scanner(System.in);
			System.out.println("rentre le nom du joueur");
			String playerName = sc.nextLine();
			players.add(new Player(1,playerName,speciesRepo.findById(1)));
			numberPlayer--;
		}
		System.out.println(players.toString());
		return players;
	}
	
	public ArrayList<Player> orderPlayerTurn(ArrayList<Player> players) {
		 Collections.shuffle(players);
		 System.out.println(players.toString());
		 return players;
	}
	
	public void renamePlanets(List<Planet> planetList) {
		String []planetNames = {"Mercure","Venus","Terre","Mars","Neptune","Pluton","Naboo","Tatooine","Endor","Coruscant","Hoth","Magrathea","Kakrafoon Kappa","Krikket","Bételgeuse","P3X-888","Lantea","Dakara","Celestis","Talos IV","Risa","Qo'noS","Acamar III","Raxacoricofallapatorius","Metebelis III","Alfava Metraxis","Gallifrey","Kobol","Caprica","Gemenon","Leonis","Alpha Corvus","Meirrion","Troy","Solaria","Pandora","Krypton","Alderaan","Aldebaran","Babel","Betazed","Khitomer","Ligon II","Corellia","Dagobah","Mustafar","Yavin IV"};
		List<String> pla = Arrays.asList(planetNames);
		Collections.shuffle(pla);
		for(int i =0; i < pla.size();i++) {
			planetList.get(i).setPlanetName(pla.get(i));
			planetRepo.save(planetList.get(i));
		}
	}
	
	
	
	public void placeShip(List<Planet> planetList, Optional<Player> player1, Optional<Player> player2) {
		
		
		ArrayList<Planet> planetListPlayer1 = new ArrayList<Planet>();
		for(int i = 0; i < 15; i++) {
			int randomIndex = (int) Math.ceil(Math.random()*planetList.size()-1);
			 Planet randomPlanet = planetList.get(randomIndex);
			 randomPlanet.setPlanetOwner(player1);
			 planetRepo.save(randomPlanet);
			 planetListPlayer1.add(randomPlanet);
			 planetList.remove(randomPlanet);
		}
		ArrayList<Planet> planetListPlayer2 = new ArrayList<Planet>();
		planetListPlayer2.addAll(planetList);
		for(int j = 0; j<planetListPlayer2.size(); j++) {
			Planet pla = planetListPlayer2.get(j);
			pla.setPlanetOwner(player2);
			planetRepo.save(pla);
		}
		//System.out.println(planetList.toString());
		System.out.println(planetListPlayer1);
		System.out.println(planetListPlayer2);
		
		
	}
	
	
}
