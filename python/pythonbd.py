# by Julian

# Import
import random
import pymysql

# connexion database 
conn = pymysql.connect(host='localhost', user='root', password=' ', database='saejava')
cursor = conn.cursor()

# Noms, prénoms, sexe, pays et sport aléatoires
noms_fr = ["Martin", "Bernard", "Dubois", "Thomas", "Robert", "Richard", "Petit", "Durand", "Leroy", "Moreau", "Simon", "Laurent", "Lefebvre", "Michel", "Garcia", "Fournier", "Lambert", "Rousseau", "Vincent", "Muller", "Lefevre", "Guerin", "Dupont", "Roux", "Fontaine", "Blanc", "Girard", "Barbier", "Robin", "Perez", "Morel", "Giraud", "Mercier", "Faure"]
noms_jp = ["Sato", "Suzuki", "Takahashi", "Tanaka", "Watanabe", "Ito", "Yamamoto", "Nakamura", "Kobayashi", "Kato", "Yoshida", "Yamada", "Sasaki", "Yamaguchi", "Saito", "Matsumoto", "Inoue", "Kimura", "Shimizu", "Hayashi", "Shibata", "Sakamoto", "Mori", "Ishikawa", "Maeda", "Fujita", "Ogawa", "Goto", "Hasegawa", "Murakami", "Kondo", "Ishii", "Saito", "Sakai"]
noms_cn = ["Wang", "Li", "Zhang", "Liu", "Chen", "Yang", "Huang", "Zhao", "Wu", "Zhou", "Xu", "Sun", "Ma", "Zhu", "Hu", "Guo", "He", "Gao", "Lin", "Wang", "Li", "Zhang", "Liu", "Chen", "Yang", "Huang", "Zhao", "Wu", "Zhou", "Xu", "Sun", "Ma", "Zhu", "Hu", "Guo", "He", "Gao", "Lin"]
noms_de = ["Müller", "Schmidt", "Schneider", "Fischer", "Weber", "Meyer", "Wagner", "Becker", "Schulz", "Hoffmann", "Schäfer", "Koch", "Bauer", "Richter", "Klein", "Wolf", "Schröder", "Neumann", "Schwarz", "Zimmermann", "Braun", "Krüger", "Hofmann", "Hartmann", "Lange", "Schmitt", "Werner", "Schmitz", "Krause", "Meier", "Lehmann", "Schmid", "Schulze", "Maier", "Köhler", "Herrmann"]
noms_ma = ["Zouhair", "Omar", "Hassan", "Said", "Fatima", "Hafid", "Yasmine", "Ahmed", "Amal", "Karim", "Malika", "Mustapha", "Nadia", "Rachid", "Samira", "Sofiane", "Nawal", "Mohammed", "Amina", "Khalid", "Siham", "Anas", "Loubna", "Younes", "Sara", "Yassin", "Fatiha", "Youssef", "Aicha", "Brahim", "Naima", "Mehdi", "Khadija", "Adil", "Latifa", "Omar"]

prenoms_fr = ["Camille", "Lucas", "Emma", "Louis", "Chloé", "Gabriel", "Inès", "Nathan", "Léa", "Arthur", "Jade", "Hugo", "Louise", "Mathis", "Alice", "Raphaël", "Manon", "Paul", "Sarah", "Nolan", "Juliette", "Ethan", "Maëlys", "Gabin", "Lola", "Sacha", "Anna", "Tom", "Emma", "Noé", "Léna", "Jules", "Clara", "Théo", "Zoé"]
prenoms_jp = ["Hiroshi", "Takeshi", "Akira", "Yuki", "Naoki", "Haruto", "Rina", "Yui", "Aoi", "Kaito", "Ren", "Sora", "Hinata", "Asuka", "Ryota", "Sakura", "Yuma", "Haruka", "Riku", "Mio", "Yuto", "Shiori", "Ryota", "Riko", "Yuna", "Sosuke", "Rin", "Daiki", "Nana", "Takumi", "Saki", "Yuki", "Kokoro", "Kazuki", "Hana", "Yusuke", "Yuna"]
prenoms_cn = ["Yiming", "Hui", "Chen", "Wei", "Jing", "Hong", "Min", "Ling", "Fang", "Xin", "Xue", "Yan", "Qian", "Jun", "Lan", "Yan", "Ting", "Xiang", "Shuang", "Xia", "Jing", "Li", "Zhang", "Wang", "Liu", "Chen", "Yang", "Huang", "Zhao", "Wu", "Zhou", "Xu", "Sun", "Ma", "Zhu", "Hu", "Guo", "He"]
prenoms_de = ["Lena", "Paul", "Hannah", "Leon", "Sarah", "Maximilian", "Lea", "Lukas", "Lina", "Jonas", "Marie", "Finn", "Sophie", "Ben", "Emilia", "Julian", "Mia", "Tim", "Emma", "Niklas", "Maja", "Moritz", "Emily", "Tom", "Laura", "Felix", "Maja", "Simon", "Johanna", "David", "Clara", "Jakob", "Luisa", "Luca", "Anna", "Elias"]
prenoms_ma = ["Youssef", "Aya", "Imane", "Mehdi", "Nour", "Hamza", "Fatima", "Ayoub", "Nada", "Omar", "Sara", "Khalid", "Salma", "Mohamed", "Ines", "Ibrahim", "Nora", "Othmane", "Amina", "Omar", "Houda", "Ali", "Rania", "Yassin", "Soukaina", "Ayoub", "Amina", "Adil", "Meryem", "Abdellah", "Hajar", "Amir", "Ibtissam", "Ahmed", "Khadija"]

sexes = ["M", "F"]
payslist = [( 0, "USA"), ( 1, "Chine"), ( 2, "Japon"), ( 3, "Kenya"), ( 4, "France"), ( 5, "Maroc"), ( 6, "Allemagne"), ( 7, "Australie"), ( 8, "Brésil"), ( 9, "Turquie")]
# (idSport, idEpreuve,nomSport, typeEpreuve, description, nbParEquipe)
natation100 = 0
natationRelaisLibre =2
handball = 3
escrimeFleuret = 4
escrimeepee = 5
volleyball = 6
athletisme110 = 7
athletisme400 =  8
nbParEquipe_natation = 1
nbParEquipe_handball = 7
nbParEquipe_escrime = 1
nbParEquipe_volleyball = 6
nbParEquipe_athletisme = 1 
epreuves = [(natation100 ,0, "Natation 100 brasse", "solo", "description : blablabla", nbParEquipe_natation), 
            
            (handball,1, "Handball","equipe", "description : blablabla", nbParEquipe_handball),
            (natationRelaisLibre,1, "Natation relais libre", "solo", "description : blablabla", nbParEquipe_natation),
            (volleyball ,2,"Volley-Ball", "equipe", "description : blablabla", nbParEquipe_volleyball),
            (escrimeFleuret,3,  "Escrime fleuret", "solo", "description : blablabla", nbParEquipe_escrime),
            (escrimeepee,4,  "Escrime épée", "solo", "description : blablabla", nbParEquipe_escrime), 
            (athletisme110 ,5,"Athétisme 110 haies", "solo", "description : blablabla", nbParEquipe_athletisme), 
            (athletisme400,6, "Athlétisme relais 400m","solo", "description : blablabla", nbParEquipe_athletisme)]

def cree_athlete(nbAthlete):
    """permet de cree un nombre definie d'athlete

        Parameters: 
            nbAthlete (int): nombre d'athelete a généré
        
        Returns:
            records (list): liste des athletes générés
    
    """ 

    sql_query = """
        select max(idAthlete) from ATHLETE;
    """    
    cursor.execute(sql_query)
    results = cursor.fetchall()
    # print(results)
    records = []

    if results[0][0] is None:
        id = 0
    id = results[0][0]    
    
    for _ in range(nbAthlete):
        id+=1
        if random.random() < 0.25:
            nom = random.choice(noms_fr)
            prenom = random.choice(prenoms_fr)
        elif random.random() < 0.5:
            nom = random.choice(noms_jp)
            prenom = random.choice(prenoms_jp)
        elif random.random() < 0.75:
            nom = random.choice(noms_cn)
            prenom = random.choice(prenoms_cn)
        else:
            nom = random.choice(noms_de)
            prenom = random.choice(prenoms_de)
        sexe = random.choice(sexes)
        pays_choice = random.choice(payslist)
        epreuve = random.choice(epreuves)
        force = random.randint(1, 20)
        endurance = random.randint(1, 20)
        agilite = random.randint(1, 20)
        records.append([id, nom, prenom, sexe, pays_choice, epreuve, force,endurance, agilite])

    return records
# print(cree_athlete())


def add_pays_db(listeDesPays):
    """permet d'ajouter des pays à la base de donnée

        Parameters: 
            listeDesPays (list): liste des differents pays sous la forme (IDpays, nomPays)
        
        Returns:
            None
    
    """ 
    
    for pays in listeDesPays:
        sql = "INSERT INTO PAYS (idPays, nomPays) VALUES (%s, %s)"

        donnees_athlete = (pays[0], pays[1])
        print(donnees_athlete)
        cursor.execute(sql, donnees_athlete)
                          

def add_athlete_bd(nbAthlete):
    """permet d'ajouter des pays à la base de donnée

        Parameters: 
            nbAthlete (int): nombre d'athelete a généré
        
        Returns:
            None
    
    """ 

    liste_athlete = cree_athlete(nbAthlete)
    for athlete in liste_athlete:
        cursor = conn.cursor()

        sql = "INSERT INTO athlete (idAthlete, nomAthlete, prenomAthlete, sexe, capaciteForce, endurance, agilite, idpays) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)"
        
        donnees_athlete = (athlete[0],athlete[1],athlete[2],athlete[3],athlete[6],athlete[7],athlete[8],athlete[4][0])
        print(donnees_athlete)
        cursor.execute(sql, donnees_athlete)

def add_sport_db(listeDessports):
    """permet d'ajouter des sports à la base de donnée

        Parameters: 
            listeDesSports (list): liste des differents sports sous la forme (idSport, idEpreuve,nomSPort, typeEpreuve, description, nbParEquipe)
        
        Returns:
            None

    """ 
    
    for sport in listeDessports:
        sql = "INSERT INTO SPORT (idSport,nomSport, nbParEquipe) VALUES (%s, %s, %s)"

        # (idSport, idEpreuve,nomSPort, typeEpreuve, description, nbParEquipe)
        donnees_athlete = (sport[0],sport[2],sport[5])
        print(donnees_athlete)
        cursor.execute(sql, donnees_athlete)

def add_epreuve_db(listeDesEpreuves):
    """permet d'ajouter des Epreuves à la base de donnée

        Parameters: 
            listeDesEpreuves (list): liste des differents Epreuves sous la forme         # (idSport, idEpreuve,nomSPort, typeEpreuve, description, nbParEquipe)
        
        Returns:
            None
    
    """ 

    for epreuve in listeDesEpreuves:
        sql = "INSERT INTO EPREUVE (idEpreuve,descriptionEpreuve, typeEpreuve, idSport) VALUES (%s, %s, %s, %s)"

        donnees_athlete = (epreuve[1],epreuve[3],epreuve[2],epreuve[0])
        print(donnees_athlete)
        cursor.execute(sql, donnees_athlete)
                              

nbAthlete = 1
# add_pays_db(payslist)
add_athlete_bd(nbAthlete)
# add_epreuve_db(epreuves)
# add_sport_db(epreuves)
# cree_athlete(nbAthlete)

conn.commit()
cursor.close()
conn.close()    



