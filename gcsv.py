import csv
import random

# Noms et prénoms aléatoires
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
pays = ["USA", "Chine", "Japon", "Kenya", "France", "Maroc", "Allemagne", "Australie", "Brésil", "Turquie"]
epreuves = ["Natation 100 brasse", "Natation relais libre", "Handball", "Volley-Ball", "Escrime fleuret", "Escrime épée", "Athétisme 110 haies", "Athlétisme relais 400m"]

# Génération des enregistrements
records = []
for _ in range(400):
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
    pays_choice = random.choice(pays)
    epreuve = random.choice(epreuves)
    force = random.randint(1, 20)
    endurance = random.randint(1, 20)
    agilite = random.randint(1, 20)
    records.append([nom, prenom, sexe, pays_choice, epreuve, force,endurance, agilite])

# Écriture des données dans un fichier CSV
with open('donnees.csv', 'w', newline='', encoding='utf-8') as file:
    writer = csv.writer(file)
    # Écriture de l'en-tête
    writer.writerow(["Nom", "Prénom", "Sexe", "Pays", "Épreuve","Force","Endurance","agilite"])
    # Écriture des enregistrements
    writer.writerows(records)

print("Fichier CSV généré avec succès.")
