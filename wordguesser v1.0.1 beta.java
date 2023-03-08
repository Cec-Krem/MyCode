import java.util.Scanner;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;

/* @author Krem
 * TODO :
 * Booster l'esthétique si possible
 * Mettre les listes dans des .txt */

class WordGuesser {
    private String word;
    private String hiddenWord;
    private boolean solved;
    private boolean lost;
    private HashSet<Character> usedLetters;
    private int compteur;
    private int compteur2;
    
    public WordGuesser(String word) {
        this.word = word.toUpperCase();
        this.hiddenWord = new String(new char[word.length()]).replace("\0", "_");
        this.solved = false;
        this.lost = false;
        this.usedLetters = new HashSet<Character>();
        this.compteur = 0;
        this.compteur2 = 0;
    }

    public void guess(String input, String user) {
        if (input.length() == 0) {
            System.out.println("");
            return;
        }

        char letter = input.charAt(0);
        if(usedLetters.contains(letter)){
            System.out.println(RED_BOLD_BRIGHT + "Vous avez déjà tenté cette lettre, choisissez en une autre s'il vous plaît.\n " + ANSI_RESET);
            return;
        }

        usedLetters.add(letter);
        boolean correctGuess = false;
        StringBuilder sb = new StringBuilder(hiddenWord);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                sb.setCharAt(i, letter);
                correctGuess = true;
                compteur2 += 1;
            }
        }
        
        if (input.equals("^")) {
            System.out.println(RED_BOLD_BRIGHT+"/!\\ ====== Stopping ... ====== /!\\"+ANSI_RESET);
            hiddenWord.equals(word);
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Eleve\\Desktop\\Classes\\MyFirstClass\\src\\resources\\historique.txt", true)));
                out.println("|\n|"+user+" - STOPPED\n|-------------------------------------------------------------------------");
                out.close();
            } catch (IOException e) {
            	System.out.println(ANSI_RED+"An error occurred."+ANSI_RESET);
        		e.printStackTrace();
        	}
            correctGuess = true;
            solved = true;
            }

        hiddenWord = sb.toString();
            if (!correctGuess) {
                System.out.println(ANSI_RED + "La lettre n'est pas présente dans le mot. Vous avez pour l'instant trouvé : "+ ANSI_RESET + ANSI_CYAN + hiddenWord + ANSI_RESET);
                compteur += 1;
                
                System.out.println(ANSI_CYAN + "Plus que "+(11-compteur)+" essai(s)." + ANSI_RESET);
                if (compteur == 1) {
                    System.out.println("\n \n \n \n \n \n \n \n═════╩═════");
                }
                if (compteur == 2) {
                    System.out.println("\n     ╔\n     ║\n     ║\n     ║\n     ║\n     ║\n     ║\n═════╩═════");
                }
                if (compteur == 3) {
                    System.out.println("\n     ╔═════╗\n     ║\n     ║\n     ║\n     ║\n     ║\n     ║\n═════╩═════");
                }
                if (compteur == 4) {
                    System.out.println("\n     ╔═╦═══╗\n     ╠═╝\n     ║\n     ║\n     ║\n     ║\n     ║\n═════╩═════");
                }
                if (compteur == 5) {
                    System.out.println("\n     ╔═╦═══╗\n     ╠═╝   ║\n     ║     |\n     ║\n     ║\n     ║\n     ║\n═════╩═════");
                }
                if (compteur == 6) {
                    System.out.println("\n     ╔═╦═══╗\n     ╠═╝   ║\n     ║     |\n     ║    °o°\n     ║\n     ║\n     ║\n═════╩═════");
                }
                if (compteur == 7) {
                    System.out.println("\n     ╔═╦═══╗\n     ╠═╝   ║\n     ║     │\n     ║    °o°\n     ║     │\n     ║\n     ║\n═════╩═════");
                }
                if (compteur == 8) {
                    System.out.println("\n     ╔═╦═══╗\n     ╠═╝   ║\n     ║     |\n     ║    °o°\n     ║     |\n     ║    / \n     ║\n═════╩═════");
                }
                if (compteur == 9) {
                    System.out.println("\n     ╔═╦═══╗\n     ╠═╝   ║\n     ║     |\n     ║    °o°\n     ║     │\n     ║    / \\\n     ║\n═════╩═════");
                }
                if (compteur == 10) {
                    System.out.println("\n     ╔═╦═══╗\n     ╠═╝   ║\n     ║     |\n     ║    °o°\n     ║    /│\n     ║    / \\\n     ║\n═════╩═════");
                }
                if (compteur == 11) {
                    System.out.println("\n     ╔═╦═══╗\n     ╠═╝   ║\n     ║     |\n     ║    °o^   Pas grave !\n     ║    /│\\\n     ║    / \\\n     ║\n═════╩═════\n");
                    System.out.println(ANSI_RED + "Perdu ! Le mot était "+ RED_BOLD_BRIGHT + word + ANSI_RESET);
                    lost = true;
                    if (lost == true) {
                    	try {
                            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Eleve\\Desktop\\Classes\\MyFirstClass\\src\\resources\\historique.txt", true)));
                            out.println("|\n|"+user+" - PERDU ( "+compteur+" Erreur(s) / "+compteur2+" Bonnes Réponse(s) ) - ["+word+"]\n|---------------------------------------------------------------");
                            out.close();
                        } catch (IOException e) {
                        	System.out.println(ANSI_RED+"An error occurred."+ANSI_RESET);
                    		e.printStackTrace();
                    	}
                    }
                }
            } else if (hiddenWord.equals(word)) {
                    System.out.println(ANSI_YELLOW + "Bravo, vous avez deviné le mot : " + YELLOW_BOLD_BRIGHT + word + ANSI_RESET + ANSI_YELLOW + " avec ["+  compteur + "] erreur(s) !" + ANSI_RESET);
                    solved = true;
                    if (solved == true) {
                    try {
                        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Eleve\\Desktop\\Classes\\MyFirstClass\\src\\resources\\historique.txt", true)));
                        out.println("|\n|"+user+" - GAGNÉ ( "+compteur+" Erreur(s) / "+compteur2+" Bonnes Réponse(s) ) - ["+word+"]\n|---------------------------------------------------------------");
                        out.close();
                    } catch (IOException e) {
                    	System.out.println(ANSI_RED+"An error occurred."+ANSI_RESET);
                		e.printStackTrace();
                	}
                    }
                } else {
                    System.out.println(ANSI_CYAN + "Mot actuel : " + hiddenWord + "\n " + ANSI_RESET);
                }
    }
            
    public boolean isSolved() {
        return this.solved;
    }
    public boolean isLost() {
    	return this.lost;
    }

    // Texte normal
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Gras clair
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m";
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m";
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m";
    
    public static void manageScores(String action) {
        if (action.equals("view")) {
        	for (int i = 0;i<100;i++) {
				System.out.println(" \n ");
			}
			try {
		      File myObj = new File("C:\\Users\\Eleve\\Desktop\\Classes\\MyFirstClass\\src\\resources\\historique.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println(ANSI_RED+"Fichier introuvable. Vérifiez l'emplacement MyFirstClass\\src\\resources\\historique.txt"+ANSI_RESET);
		    }
        } else if (action.equals("erase")) {
        	for (int i = 0;i<100;i++) {
				System.out.println(" \n ");
			}
			File myObj = new File("C:\\Users\\Eleve\\Desktop\\Classes\\MyFirstClass\\src\\resources\\historique.txt");
			if (myObj.delete()) {
				System.out.println(ANSI_GREEN+"Stats reset avec succès."+ANSI_RESET);
			} else {
				System.out.println(ANSI_RED+"Impossible de reset les stats. Vérifiez l'emplacement MyFirstClass\\src\\resources\\historique.txt"+ANSI_RESET);
			}
        } else if (action.equals("guide")) {
        	for (int i = 0;i<100;i++) {
				System.out.println(" \n ");
			}
        	System.out.println(CYAN_BOLD_BRIGHT+"Conseils/Guides :");
        	System.out.println("\n"+YELLOW_BOLD_BRIGHT+"Bienvenue dans le guide !\n Ici, vous aurez quelques conseils afin d'optimiser vos parties et maximiser vos chances de trouver le mot caché !");
        	System.out.println(PURPLE_BOLD_BRIGHT+"1. Tenter des voyelles :"+ANSI_RESET+ANSI_WHITE+" En français, beaucoup de mots comportent la lettre \"E\". Un des meilleurs moyens est de tester E, I, O, A, puis U.\n Il y a énormément de chances d'avoir déjà trouvé une grande partie du mot après ça.");
        	System.out.println(PURPLE_BOLD_BRIGHT+"2. Penser à la cohérence des lettres :"+ANSI_RESET+ANSI_WHITE+" Imaginons que vous trouvez H au milieu du mot. Si c'est le cas, il faut réfléchir avec logique !\n Par exemple, il n'y aura jamais de K avant un H, mais plutôt un C ou un P. Ou en avant dernière place, un O aura beaucoup de chances de suivre un N.");
        } else if (action.equals("exit")) {
        	for (int i = 0;i<100;i++) {
				System.out.println(" \n ");
			}
        	System.out.println(YELLOW_BOLD_BRIGHT+"OK"+ANSI_RESET+ANSI_YELLOW+", au revoir!");
        	return;
        }
    }
    
    /********************************************************************************************************************************************************/

    
    public static void main(String[] args) {
    	int mode = 0;
        int diff = 0;
        int replay = 0;
        int choosed = 0;
        String space = " ";
        
        String[] listerandom = {"baguette","chateau","dynamite","bouteille","crayon","ordinateur","table","feutre","tableau","sol","europe","marguerite","fleur","moustique","triangle","maison","photographie","forgeron","ruisseau","poisson","oeuf","kiwi","boisson","sucre","ananas","concombre","docteur","agriculteur","pharmacien","programmeur","jardinier","boulanger","comedie","policier","professeur","pilote","architecte","dentiste","thon","buffle","chat","chien","pingouin","corbeau","kangourou","moineau","crabe","crane","chimie","physique","mathematiques","journal","entreprise","train","route","avion","helicoptere","velo","motoneige","voiture","moto","bateau","courrier","television","magazine","cercle","rectangle","parallelogramme","losange","pentagone","france","allemagne","italie","espagne","pologne","russie","australie","angleterre","ukraine","etoile","chameau","serpent","briquet","brique","chalumeau","tortue","silicone","cidre","retroviseur","miel","saucisson","dirigeable","monstre","louche","silhouette","telesiege","locomotive","chariot","wagon","canard","clown","indication","rayonnement","olympiques","poing","incendie","mousse","courants","amande","croissant","beignet","griffe","percussion","pantoufles","articulation","temps","poutre","malice","batterie","bulle","saxophone","baryton","nenuphar","pissenlit","saucisse","cinema","argent","forteresse","universite","nuage","trou","galaxie","univers","favoris","controle","attraper","public","pull","cheveux","trousse","colle","ciseaux","hache","flamme","cahier","fauteuil","bottes","pantalon","manchot","feuille","encre","calculatrice","picot","pochette","voltmetre","lumiere","glace","neige","pluie","orage","devoir","maison","surveillance","carreau","braquage","liquide","solide","gaz","gazeux","manteau","roue","ecran","affiche","perroquet","lion","tigre","zebre","mouton","cafard","fontaine","pilier","piller","poulie","vriller","usine","pirate","livre","mobile","immobile","salon","chambre","janvier","fevrier","mars","avril","mai","juin","juillet","aout","septembre","octobre","novembre","decembre","phasme","azote","argon","propane","butane","acetone","fer","acier","camion","diamant","emeraude","saphir","amethyste","rubis","charbon","or","lingot","balle","spherique"};
        String[] listepays = {"australie","france","belgique","russie","croatie","chine","etats-unis","angleterre","allemagne","espagne","listenbourg","ukraine","mexique","somalie","botswana","finlande","fidgi","ghana","grece","inde","italie","islande","suede","norvege","israel","japon","jordanie","kazakhstan","kenya","luxembourg","suisse","liechtenstein","lituanie","mali","madagascar","monaco","mongolie","syrie","turquie","tunisie","thailande","tchad"};
        String[] listeanimaux = {"tortue","chien","chat","oiseau","coccinelle","chameau","vache","boeuf","phasme","crevette","poisson","rat","souris","loup","crocodile","kangourou","baleine","ornithorynque"};
        String[] listetech = {"clavier","souris","peripherique","sortie","ROM","RAM","BIOS","SSD","disque","microprocesseur","CPU","GPU","processus","nanocircuit","circuit","processeur","puces","transitor","CPI","radiateur","ventilateur","ventirad","watercooling","horloge","virtuel","numerique","fichier","dossier","ASCII","encodage","codage","ordinateur","ecran"};
        String[] listetransports = {"voiture","moto","velo","avion","helicoptere","camion","scooter","bus","train","automobile","autobus","vehicule","bateau","canoe","pedalo","aeroglisseur","trotinette","skateboard","overboard","cargo"};
        String[] specialnoel = {"calendrier","cadeau","tintinnabuler","lutin","sapin","fete","avent","reveillon","sapin","nativite","cadeau","buche","decembre","houx","epiphanie","creche","jouet","cantique","celebration","joyeux","renne","chant","messe","veille","feter","scrooge","celebrer","minuit","solstice","dinde","festif","tradition","naissance","Bethleem","Christ","illumination","calendrier","etrennes","vacance","liturgique","traineau","decorer","Yule","chanson","traditionnel","veillée","annonciation","marche","repas","santon","paien","bapteme","chretien","conte","féerie","guirlande","Jesus","lutin","boules","cheminee","décoration","soiree","tarte","boudin","chanter","colinde","coutume","festivite","saturnale"};
        String[] specialpaques = {"avril","brioche","chocolat","lapin","gateau","oeuf","vacances","communion","jeudi","poule","toussaint","jesus","jeune","celebration","pentecote","resurrection","cloches","agneau"};
        String[] specialhalloween = {"citrouille","horreur","nuit","sang","effrayant","vampire","squelette","clown","tronconneuse","deguisement","celte","soir","bonbon","sort","brouillard","fantome","zombie","sorciere","masque","poignarder","sequestrer","torturer","friandise"};
        
        Scanner sc = new Scanner(System.in);
        Random Mot = new Random();
        do {
    		mode = 0;
    		choosed = 0;
    		diff = 0;
    		replay = 0;
    		for (int i = 0;i<100;i++) {
                System.out.println(" \n ");
            }
    		// Code sélection user
    		System.out.println("Entrez un pseudo...\n"+RED_BOLD_BRIGHT+"/!\\ Ne prend pas en compte les espaces"+ANSI_RESET);
    		String user = sc.nextLine();
    		while (user.length() == 0 | user.length() >= 17 | user.contains(space/*Regex voulait pas*/)) {
    			System.out.println(ANSI_RED+"Merci de choisir un nom entre 1 et 16 caractères."+ANSI_RESET);
    			user = sc.next();
    		}
    		for (int i = 0; i<100 ; i++) {
    			System.out.println(" \n ");
    		}
    		System.out.println(ANSI_YELLOW+"Amuse-toi bien, "+YELLOW_BOLD_BRIGHT+user+ANSI_RESET+ANSI_YELLOW+"!"+ANSI_RESET);
    		// Fin code sélection user
    		do {
    			do {
    			System.out.println(GREEN_BOLD_BRIGHT+"Que voulez-vous faire, "+YELLOW_BOLD_BRIGHT+user+GREEN_BOLD_BRIGHT+" ?\n"+ANSI_RESET+ANSI_GREEN+"[1] : Jouer\n"+ANSI_RESET+ANSI_YELLOW+"[2] : Voir scores\n"+ANSI_RESET+ANSI_RED+"[3] : Reset scores\n"+ANSI_RESET+ANSI_CYAN+"[4] : Guide/Astuces\n"+ANSI_RESET+RED_BOLD_BRIGHT+"[5] : Quitter"+ANSI_RESET);
    			try {
    				while (!sc.hasNextInt()) {
    					System.out.println(ANSI_RED+"Entrez 1, 2 ou 3 s'il vous plaît : "+ANSI_RESET);
    					sc.nextLine();
    				}
    			} catch (InputMismatchException exception) {
    				System.out.println(ANSI_RED+"Entrez 1, 2 ou 3 s'il vous plaît : "+ANSI_RESET);
    				sc.next();
    			}
    			choosed = sc.nextInt();
    			// Trouver un moyen de refaire choisir choosed après qu'il soit =2 ou =3
    			switch (choosed) {
    			case 1:
    	      	      do {
    	      	    	for (int i = 0;i<100;i++) {
    	    				System.out.println(" \n ");
    	    			}
    	      			System.out.println(GREEN_BOLD_BRIGHT + "                                                 _|                            \n   _|_|_|     _|_|     _|           _|_|         _|             _|_|     _|_|_|\n _|         _|    _|   _|         _|    _|       _|           _|    _|   _|    _| \n   _|_|     _|    _|   _|         _|    _|       _|               _|     _|_|_|    \n       _|   _|    _|   _|         _|    _|       _|             _|       _|        \n _|_|_|       _|_|     _|_|_|_|     _|_|         _|           _|_|_|_|   _| \n                                                 _|                         \n                                                 _|                       \n"+YELLOW_BOLD_BRIGHT+"                                                 .:                              \n                      .::                        .:                .:::.: \n                       .::                       .:               .:    .:\n                       .::                                            .::\n                       .::                       .:                 .::\n                       .::                       .:               .::\n                      .::::                      .:               .:::::::"+ANSI_RESET);
    	    			System.out.println(ANSI_RED+"Entrez 1 ou 2 s'il vous plaît : "+ANSI_RESET);
    	    			try {
    	    				while (!sc.hasNextInt()) {
    	    					System.out.println(ANSI_RED+"Entrez 1 ou 2 s'il vous plaît : "+ANSI_RESET);
    	    					sc.nextLine();
    	    				}
    	    			} catch (InputMismatchException exception) {
    	    				System.out.println(ANSI_RED+"Entrez 1 ou 2 s'il vous plaît : "+ANSI_RESET);
    	    				sc.next();
    	    			}
    	    			mode = sc.nextInt();

    	    			if (mode == 1) {
    	                // Code 1 joueur (depuis base)
    	    				System.out.println(YELLOW_BOLD_BRIGHT+"OK!"+ANSI_RESET);

    	    				for (int i = 0;i<100;i++) {
    	    					System.out.println(" \n ");
    	    				}

    	    				System.out.println(GREEN_BOLD_BRIGHT + "Mode solo sélectionné. Les mots seront TOUJOURS sans aucun espace et sans accents." + ANSI_RESET);
    	    				do {
    	    					System.out.println(YELLOW_BOLD_BRIGHT+"Thèmes :\n"+PURPLE_BOLD_BRIGHT+"1. Aléatoire\n"+ANSI_CYAN+"2. Pays\n"+GREEN_BOLD_BRIGHT+"3. Animaux\n"+BLUE_BOLD_BRIGHT+"4. Technologies\n"+ANSI_WHITE+"5. Transports\n"+RED_BOLD_BRIGHT+"6. ["+ANSI_WHITE+"Spécial"+RED_BOLD_BRIGHT+"] Noël\n"+GREEN_BOLD_BRIGHT+"7. ["+BLUE_BOLD_BRIGHT+"Spécial"+GREEN_BOLD_BRIGHT+"] Pâques\n"+YELLOW_BOLD_BRIGHT+"8. ["+RED_BOLD_BRIGHT+"Spécial"+ANSI_RESET+YELLOW_BOLD_BRIGHT+"] Halloween"+ANSI_RESET);
    	    					System.out.println(ANSI_RED+"Choisissez le thème des mots s'il vous plaît.\n"+ANSI_RESET);
    	    					try {
    	    						while(!sc.hasNextInt()) {
    	    							System.out.println(ANSI_RED+"Choisissez le thème des mots s'il vous plaît.\n"+ANSI_RESET);
    	    							sc.nextLine();
    	    						}
    	    					} catch (InputMismatchException exception) {
    	    						sc.next();
    	    					}
    	    					diff = sc.nextInt();

    	    					if (diff == 1) {
    	    						for (int i = 0;i<100;i++) {
    	    							System.out.println(" \n ");
    	    						}
    	                        
    	    						int r = Mot.nextInt(listerandom.length);
    	    						String wordToGuess1 = listerandom[r];

    	    						// System.out.println(RED_BOLD_BRIGHT + listerandom.length + " mots" + ANSI_RESET);
    	                    
    	    						WordGuesser game1 = new WordGuesser(wordToGuess1);
    	                    
    	    						System.out.println(PURPLE_BOLD_BRIGHT+"\nThème sélectionné : Aléatoire.\nCe mode regroupe plus de 200 mots différents dont beaucoup ne sont pas recensés dans les autres catégories. Certains mots seront durs à trouver.\n"+ANSI_RESET+ANSI_RED+"Amusez-vous bien!\n"+ANSI_RESET);
    	                        
    	    						sc.nextLine(); // Consommation de tampon vide
    	    						while (!game1.isSolved() && !game1.isLost()) {
    	    							System.out.print(BLUE_BOLD_BRIGHT + "Devinez une lettre : " + ANSI_RESET);
    	    							String letter = sc.nextLine();
    	    							game1.guess(letter.toUpperCase(),user);
    	    						}
    	    					} else if (diff == 2) {
    	    						for (int i = 0;i<100;i++) {
    	    							System.out.println(" \n ");
    	    						}
    	    						int r = Mot.nextInt(listepays.length);
    	    						String wordToGuess1 = listepays[r];

    	    						// System.out.println(RED_BOLD_BRIGHT + diff + " " + liste.length + " mots" + ANSI_RESET);
    	                    
    	    						WordGuesser game1 = new WordGuesser(wordToGuess1);
    	                    
    	    						System.out.println(CYAN_BOLD_BRIGHT+"\nThème sélectionné : Pays.\nCe mode regroupe une grande quantité de pays, dont des pays rarement mentionnés dans la vie quotidienne.\n"+ANSI_GREEN+"Amusez-vous bien!\n"+ANSI_RESET);

    	    						sc.nextLine(); // Consommation de tampon vide
    	    						while (!game1.isSolved() && !game1.isLost()) {
    	    							System.out.print(BLUE_BOLD_BRIGHT + "Devinez une lettre : " + ANSI_RESET);
    	    							String letter = sc.nextLine();
    	    							game1.guess(letter.toUpperCase(),user);
    	    						}
    	    					} else if (diff == 3) {
    	    						for (int i = 0;i<100;i++) {
    	    							System.out.println(" \n ");
    	    						}
    	    						int r = Mot.nextInt(listeanimaux.length);
    	    						String wordToGuess1 = listeanimaux[r];

    	    						// System.out.println(RED_BOLD_BRIGHT + diff + " " + liste.length + " mots" + ANSI_RESET);
    	                    
    	    						WordGuesser game1 = new WordGuesser(wordToGuess1);
    	                    
    	    						System.out.println(GREEN_BOLD_BRIGHT+"\nThème sélectionné : Animaux.\nCe mode regroupe une variété d'animaux existants, mammifères, reptiles, insectes, etc. Certains peuvent être compliqués à trouver.\n"+ANSI_RESET+ANSI_GREEN+"Amusez-vous bien!\n"+ANSI_RESET);

    	    						sc.nextLine(); // Consommation de tampon vide
    	    						while (!game1.isSolved() && !game1.isLost()) {
    	    							System.out.print(BLUE_BOLD_BRIGHT + "Devinez une lettre : " + ANSI_RESET);
    	    							String letter = sc.nextLine();
    	    							game1.guess(letter.toUpperCase(),user);
    	    						}
    	    					} else if (diff == 4) {
    	    						for (int i = 0;i<100;i++) {
    	    							System.out.println(" \n ");
    	    						}
    	    						int r = Mot.nextInt(listetech.length);
    	    						String wordToGuess1 = listetech[r];

    	    						// System.out.println(RED_BOLD_BRIGHT + diff + " " + liste.length + " mots" + ANSI_RESET);
    	                    
    	    						WordGuesser game1 = new WordGuesser(wordToGuess1);
    	                    
    	    						System.out.println(BLUE_BOLD_BRIGHT+"\nThème sélectionné : Technologies.\n"+RED_BOLD_BRIGHT+"Ce mode peut être complexe pour les non connaisseurs de l'informatique en général et de détails techniques sur des ordinateurs.\n"+ANSI_RESET+ANSI_GREEN+"Amusez-vous bien!\n"+ANSI_RESET);
    	                        
    	    						sc.nextLine(); // Consommation de tampon vide
    	    						while (!game1.isSolved() && !game1.isLost()) {
    	    							System.out.print(BLUE_BOLD_BRIGHT + "Devinez une lettre : " + ANSI_RESET);
    	    							String letter = sc.nextLine();
    	    							game1.guess(letter.toUpperCase(),user);
    	    						}
    	    					} else if (diff == 5) {
    	    						for (int i = 0;i<100;i++) {
    	    							System.out.println(" \n ");
    	    						}
    	    						int r = Mot.nextInt(listetransports.length);
    	    						String wordToGuess1 = listetransports[r];

    	    						// System.out.println(RED_BOLD_BRIGHT + diff + " " + liste.length + " mots" + ANSI_RESET);
    	                    
    	    						WordGuesser game1 = new WordGuesser(wordToGuess1);
    	                    
    	    						System.out.println(WHITE_BOLD_BRIGHT+"\nThème sélectionné : Transports.\nCe mode est relativement simple si vous connaissez des véhicules sans pour autant sortir dans l'extraordinaire.\n"+ANSI_RESET);

    	    						sc.nextLine(); // Consommation de tampon vide
    	    						while (!game1.isSolved() && !game1.isLost()) {
    	    							System.out.print(BLUE_BOLD_BRIGHT + "Devinez une lettre : " + ANSI_RESET);
    	    							String letter = sc.nextLine();
    	    							game1.guess(letter.toUpperCase(),user);
    	    						}
    	    					} else if (diff == 6) {
    	    						for (int i = 0;i<100;i++) {
    	    							System.out.println(" \n ");
    	    						}
    	    						int r = Mot.nextInt(specialnoel.length);
    	    						String wordToGuess1 = specialnoel[r];

    	    						// System.out.println(RED_BOLD_BRIGHT + diff + " " + liste.length + " mots" + ANSI_RESET);
    	                    
    	    						WordGuesser game1 = new WordGuesser(wordToGuess1);
    	                    
    	    						System.out.println(RED_BOLD_BRIGHT+"\nThème sélectionné : Noël.\nCe mode dipose d'une multitude de mots à difficulté variée.\n"+ANSI_RESET);

    	    						sc.nextLine(); // Consommation de tampon vide
    	    						while (!game1.isSolved() && !game1.isLost()) {
    	    							System.out.print(WHITE_BOLD_BRIGHT+"D"+RED_BOLD_BRIGHT+"e"+WHITE_BOLD_BRIGHT+"v"+RED_BOLD_BRIGHT+"i"+WHITE_BOLD_BRIGHT+"n"+RED_BOLD_BRIGHT+"e"+WHITE_BOLD_BRIGHT+"z"+RED_BOLD_BRIGHT+" "+WHITE_BOLD_BRIGHT+"u"+RED_BOLD_BRIGHT+"n"+WHITE_BOLD_BRIGHT+"e"+RED_BOLD_BRIGHT+" l"+WHITE_BOLD_BRIGHT+"e"+RED_BOLD_BRIGHT+"t"+WHITE_BOLD_BRIGHT+"t"+RED_BOLD_BRIGHT+"r"+WHITE_BOLD_BRIGHT+"e"+RED_BOLD_BRIGHT+" : " + ANSI_RESET);
    	    							String letter = sc.nextLine();
    	    							game1.guess(letter.toUpperCase(),user);
    	    						}
    	    					} else if (diff == 7) {
    	    						for (int i = 0;i<100;i++) {
    	    							System.out.println(" \n ");
    	    						}
    	    						int r = Mot.nextInt(specialpaques.length);
    	    						String wordToGuess1 = specialpaques[r];

    	    						// System.out.println(RED_BOLD_BRIGHT + diff + " " + liste.length + " mots" + ANSI_RESET);
    	                    
    	    						WordGuesser game1 = new WordGuesser(wordToGuess1);
    	                    
    	    						System.out.println(GREEN_BOLD_BRIGHT+"\nThème sélectionné : Pâques.\nCe mode dipose d'une multitude de mots à difficulté variée.\n"+ANSI_RESET);

    	    						sc.nextLine(); // Consommation de tampon vide
    	    						while (!game1.isSolved() && !game1.isLost()) {
    	    							System.out.print(GREEN_BOLD_BRIGHT+"D"+BLUE_BOLD_BRIGHT+"e"+YELLOW_BOLD_BRIGHT+"v"+GREEN_BOLD_BRIGHT+"i"+BLUE_BOLD_BRIGHT+"n"+YELLOW_BOLD_BRIGHT+"e"+GREEN_BOLD_BRIGHT+"z"+BLUE_BOLD_BRIGHT+" "+YELLOW_BOLD_BRIGHT+"u"+GREEN_BOLD_BRIGHT+"n"+BLUE_BOLD_BRIGHT+"e"+YELLOW_BOLD_BRIGHT+" l"+GREEN_BOLD_BRIGHT+"e"+BLUE_BOLD_BRIGHT+"t"+YELLOW_BOLD_BRIGHT+"t"+GREEN_BOLD_BRIGHT+"r"+BLUE_BOLD_BRIGHT+"e"+YELLOW_BOLD_BRIGHT+" : " + ANSI_RESET);
    	    							String letter = sc.nextLine();
    	    							game1.guess(letter.toUpperCase(),user);
    	    						}
    	    					} else if (diff == 8) {
    	    						for (int i = 0;i<100;i++) {
    	    							System.out.println(" \n ");
    	    						}
    	    						int r = Mot.nextInt(specialhalloween.length);
    	    						String wordToGuess1 = specialhalloween[r];

    	    						// System.out.println(RED_BOLD_BRIGHT + diff + " " + liste.length + " mots" + ANSI_RESET);
    	                    
    	    						WordGuesser game1 = new WordGuesser(wordToGuess1);
    	                    
    	    						System.out.println(RED_BOLD_BRIGHT+"\nThème sélectionné : "+YELLOW_BOLD_BRIGHT+"Halloween"+RED_BOLD_BRIGHT+".\nCe mode dipose d'une multitude de mots à difficulté variée.\n"+ANSI_RESET);

    	    						sc.nextLine(); // Consommation de tampon vide
    	    						while (!game1.isSolved() && !game1.isLost()) {
    	    							System.out.print(YELLOW_BOLD_BRIGHT+"D"+RED_BOLD_BRIGHT+"e"+YELLOW_BOLD_BRIGHT+"v"+RED_BOLD_BRIGHT+"i"+YELLOW_BOLD_BRIGHT+"n"+RED_BOLD_BRIGHT+"e"+YELLOW_BOLD_BRIGHT+"z"+RED_BOLD_BRIGHT+" "+YELLOW_BOLD_BRIGHT+"u"+RED_BOLD_BRIGHT+"n"+YELLOW_BOLD_BRIGHT+"e"+RED_BOLD_BRIGHT+" l"+YELLOW_BOLD_BRIGHT+"e"+RED_BOLD_BRIGHT+"t"+YELLOW_BOLD_BRIGHT+"t"+RED_BOLD_BRIGHT+"r"+YELLOW_BOLD_BRIGHT+"e"+RED_BOLD_BRIGHT+" : " + ANSI_RESET);
    	    							String letter = sc.nextLine();
    	    							game1.guess(letter.toUpperCase(),user);
    	    						}
    	    					}
    	    				} while (diff != 1 && diff != 2 && diff != 3 && diff != 4 && diff != 5 && diff != 6 && diff != 7 && diff != 8); // Et c'est reparti pour un tour (sélection de thème)
    	    			} else if (mode == 2) {
    	    				// Code 2 joueurs (base)
    	    				System.out.println(GREEN_BOLD_BRIGHT + "Mode 2 joueurs sélectionné. Un joueur entre un mot, et l'autre doit le trouver. Evitez les accents, nombres et espaces pour une meilleure jouabilité.");
    	    				System.out.println("Entrez un mot à deviner :" + ANSI_RESET);
    	    				String wordToGuess = sc.nextLine().trim();

    	    				while(wordToGuess.length() == 0) {
    	    					wordToGuess = sc.nextLine().trim();
    	    				}

    	    				WordGuesser game2 = new WordGuesser(wordToGuess);
    	    				System.out.println(YELLOW_BOLD_BRIGHT+"OK!"+ANSI_RESET);

    	    				for (int i = 0;i<100;i++) {
    	    					System.out.println(" \n ");
    	    				}

    	    				while (!game2.isSolved() && !game2.isLost()) {
    	    					System.out.print(BLUE_BOLD_BRIGHT + "Devinez une lettre : " + ANSI_RESET);
    	    					String letter = sc.nextLine();
    	    					game2.guess(letter.toUpperCase(),user);
    	    				}
    	    			}
    	    		} while (mode != 1 && mode != 2); // Si ni 1 ni 2 on revient au départ
    	    		do {
    	    			System.out.println(ANSI_CYAN+"\nQue faire ?\n"+GREEN_BOLD_BRIGHT+"1 : [RESTART]\n"+RED_BOLD_BRIGHT+"2 : [STOP]"+ANSI_RESET);
    	    			try {
    	    				while (!sc.hasNextInt()) {
    	    					System.out.println(ANSI_RED+"Entrez 1 ou 2 s'il vous plaît."+ANSI_RESET);
    	    					System.out.println(ANSI_CYAN+"\nQue faire ?\n"+GREEN_BOLD_BRIGHT+"1 : [RESTART]\n"+RED_BOLD_BRIGHT+"2 : [STOP]"+ANSI_RESET);
    	    					sc.nextLine();
    	    				}
    	    			} catch(InputMismatchException exception) {
    	    				System.out.println(ANSI_RED+"Entrez 1 ou 2 s'il vous plaît."+ANSI_RESET);
    	    				System.out.println(ANSI_CYAN+"\nQue faire ?\n"+GREEN_BOLD_BRIGHT+"1 : [RESTART]\n"+RED_BOLD_BRIGHT+"2 : [STOP]"+ANSI_RESET);
    	    				sc.next();
    	    			}
    	    			replay = sc.nextInt();
    	    			if (replay == 1) {
    	        			System.out.println(YELLOW_BOLD_BRIGHT+"OK!"+ANSI_RESET);
    	        		} else if (replay == 2) {
    	        			System.out.println(YELLOW_BOLD_BRIGHT+"OK"+ANSI_RESET + ANSI_YELLOW+", merci d'avoir joué "+YELLOW_BOLD_BRIGHT+user+ANSI_RESET+ANSI_YELLOW+" !");
    	        			sc.close();
    	        			return;
    	        		}
    	    		} while (replay != 1 && replay != 2); // Pas le bon replay : on reboucle
    			case 2:
    				manageScores("view");
    				System.out.println(" \n"+ANSI_YELLOW+"Appuyez sur [ENTRÉE] pour revenir au menu principal."+ANSI_RESET);
    		        sc.nextLine(); // Ignorer la nouvelle ligne restante dans le buffer
    		        sc.nextLine(); // Attendre que l'utilisateur appuie sur une touche
    		        break;
    			case 3:
    				manageScores("erase");
    				System.out.println(" \n"+ANSI_YELLOW+"Appuyez sur [ENTRÉE] pour revenir au menu principal."+ANSI_RESET);
    		        sc.nextLine(); // Ignorer la nouvelle ligne restante dans le buffer
    		        sc.nextLine(); // Attendre que l'utilisateur appuie sur une touche
    				break;
    			case 4:
    				manageScores("guide");
    				System.out.println(" \n"+ANSI_YELLOW+"Appuyez sur [ENTRÉE] pour revenir au menu principal."+ANSI_RESET);
    		        sc.nextLine(); // Ignorer la nouvelle ligne restante dans le buffer
    		        sc.nextLine(); // Attendre que l'utilisateur appuie sur une touche
    				break;
    			case 5:
    				manageScores("exit");
    				sc.close();
    				return;
    			}
    			} while (choosed != 1);
    	} while (choosed != 1 && choosed != 2 && choosed != 3); // Pas le bon mode au menu : on reboucle
    } while (replay == 1); // Décide de rejouer : on reboucle.
        sc.close();
}
}
