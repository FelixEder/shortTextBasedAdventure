# shortTextBasedAdventure
A short text-based adventure that should be about 5-10 minutes long. It should be easy to play and have a simple but intriguing story that pushes the player forward.

The game takes place in the andies, you wake up in immediate danger and have to act.

The game features a short introductory area in order to come to grips with the mechanics and then features three scenarios.
Each features a puzzle that also pushes the story forward. It is a mysterious adventure with inspirations from Indiana Jones and Memento.

It should be available in Swedish, English and German, something that should be chosen before starting the game.

**Testing strategy**

All the lower classes should be tested with JUnit. The bigger ones, like Game, could be tested by play-testing.
This means that a human tests the game and actively tries to break it. Bugs are then recorded and solved.

### **Copy of entire notes written from pre-production (In Swedish)**

**Random tankar:**

Gör ett eget äventyrsspel, lite baserat på The Farmhouse.

Skriv en spännande story, se till att spelet fungerar på både engelska, tyska och svenska. 
Behöver inte vara jättelångt, men jag kan tänka att det bör i alla fall bör vara längre än The Farmhouse.

Gör på samma sätt att man ska gå mellan flera rum. Det bör egentligen inte vara någon action, endast en gripande story. Dock bör det finnas items kan jag nog tycka. Det får dock aldrig bli allt för konstigt att använda dessa items så att man fastnar, utan det ska alltid vara logiskt att använda dem.

Gör någon slags huvudmeny, där du kan välja språk o sådant.

När man vinner kanske några roliga stats kan printas ut.

Huvudsaken är att det ska lära mig hur man gör ett helt eget spel och får det att fungera utanför min IDE.

Se till att manuset är så bra det kan bli innan jag börjar, då det bygger mycket på intressant story och set pieces.

**Setting:**

Någon övergiven plats som ska vara intressant att upptäcka. Det måste även vara något bekant för många, annars kommer det ta för mycket text att förklara miljöer. Så typ något som en gård, ett övergivet nöjesfält, ett berg, en hemsökt herrgård. Något sådant.

Vad sägs om en alpmiljö, med ett mystiskt hus uppe på en bergskant? Du är där på en vandring, blir indragen i något hyfsat kort, men spännande. Allt sker i dagsljus, men ska ändå vara lite creepy. 

**Handling:**

Ska vara lättförståelig och underhållande, men även spännande. Storyn är main-drive för att spela spelet, man vill verkligen veta vad som kommer att hända.

**First draft manus:**

Du är ute på en vandring i Anderna. på upptäcksfärd själv. Spelet börjar med att man är i en knivig, akut situation. Se “Introt”.

Efter det är avklarat så tar man sig tillbaka till sin camp-plats. Här kan man hitta lite items som ska bygga ut förståelsen för vem du är, tex att du är här själv etc. Men lämna lite mystik ändå.
Solen har grytt och man bestämmer sig för att fortsätta sin vandring, så man tar sitt pick och pack och fortsätter. Du kommer du till “scenario 1”.

Dagen som nu sker kommer vara att man försöker att ta sig framåt längs vägen, men att två-tre separata scenarion sker. Alla två-tre är relativt simpla pussel men som antigen introducerar någon mystisk karaktär eller fenomen. 

Efter de två-tre scenariona är avklarade så kommer man förbi ett mystiskt hus, som ser övergivet ut.  Man blir intresserad och upptäcker det. De tre sakerna som introducerade i de tidigare scenariona kommer tillbaka och spelar en stor roll för att lösa pusslet. Tillslut löser du det och kan fortsätta din resa.

Slut

En idé kan vara att först hålla huvudkaraktären hemlig, men sen ju längre handligen går framät utveckla dig mer, tills man kommer fram till något bra i huset i slutet, men kanske är lite over-kill. Tänk över det.

**Ideér**

Introt
Den olyckan som man vaknar upp till skulle kunna vara typ att man faller ner från en bergskant och precis hamnar på en sluttning. Man vet inte vart man har kommit ifrån eller hur man tog sig dit. Det är sedan ett kort pussel om hur man ska ta sig tillbaka upp. Man kan klättra, men det går inte. Det finns en snöhög, man kan leta i den och hittar då ett par is-hackor som man kan ta sig upp för igen. 

Det viktiga med detta är dels att introducera spelaren att analysera rum, utföra simpla kommandon samt leta efter items och sedan använda dem.

Det ska även introducera spelaren snabbt och spännande, vem är jag? varför har jag ramlat ner här?

**Scenario 1**

Varje scenario ska vara en liten uppsättning rum med ett pussel som man ska lösa för att kunna ta sig vidare. Det första scenariot ska vara att du hittar en mellanstor teknikpryl i ett rum och i något närliggande hittar en kraschad helikopter. Nästa rum kan vara en snöstorm som man ej kan ta sig igenom, utan man råkar alltid gå tillbaka. Man kan ju få en varning först och försöker man gå så skickas man ur igen. Du måste via de två tidigare rummen hitta något som kan guida dig. Men allting måste vara kopplat till huvudstoryn samt inte vara tråkigt att spela. Inte allt för långt heller, men svårare än det tidigare. 

Det ska finnas något på helikoptern som du ska kunna interagera med. Det ska finnas ett starkt skåp som inte går upp, även om du försöker att använda dina is hackor . Ute vid lådan som inte går att få upp kan du slå sönder låset med ditt verktyg. I det hittar du någon slags nyckel samt en bild på tre personer. 

Nyckeln kan användas för att öppna skåpet i helikoptern. Där i hittar du en karta (används för att ta sig igenom snöstormen) samt att du kommer åt en lite audiolog som spelas upp automatiskt, den säger:

“Dag tre på expeditionen, vi börjar närma oss platsen, vindarna blir värre och vädret kallare”

“Vad har du gjort? Lett oss in i en storm?” Mer ljud börjar höras, helikoptern börjar krascha. “Allt är ditt fel, vi två fortsätter härifrån! “ Man hör man skrika och helikoptern fortsätter sedan att krascha. 

Audiolog slut, inget mer kvar här, utan man kan gå igenom snöstormen nu. 

**Scenario 2**

Ska ha någonting att göra med de två andra i expeditionen där en anfaller den andra och du hamnar i mitten på något sätt. 
Du tar dig ur stormen och kommer till ett litet läger. Där ser du två personer. Det ska se ut som att de är vänliga med varandra. När du går närmare så ska de vända på sig och skrika något till dig. Någon sorts konversation uppstår. 
Helt plötsligt så slänger den ena killen sig på dig och den andra skriker. Använd då dina järnitem på honom, han slår tillbaka och ni rullar ner för en kant. Ni kommer ner till en kant där ni båda ställer er upp och kollar på varandra. Väljer du att gå rakt på honom så kommer du inte kunna ta dig vidare, utan du måste göra något pussel för att klara det. Du missar och han håller dig över stupet. 

I sista sekund kommer den andra killen och knuffar ner honom. Han pratar lite med dig och ni bestämmer er för att fortsätta till platsen. 

Viktiga här att man först tror att man själv var den som de två männen knuffade ut. Sedan kommer tvisten när det visade sig att det var den andra ändå. Lägg ut några ledtrådar till det i lägret. 

**Scenario 3**

Du och din kompanjon kommer gående mot ett stort tempel, din kompanjon börjar introducera varför ni åkte hit. Denne personen får vara den som organiserade expeditionen, det är fortfarande lite oklart vad din roll i det hela är. Ni går in i huset och låser dörren efter er. Huset ska vara ett hyfsat stort område där ni försöker ta er in i ett visst rum. Man får göra någon roligt pussel med miljön, gärna lite Tomb Raider-inspirerande. Tillslut löser du det och du och din kompanjon går in i rummet, där finns massa skatt eller whatever. 

Tillslut ska du som spelare förråda din kompanjon och ta allt åt sig själv. Viktigt att spelaren själv faktiskt gör det, men kanske så att man inte inser det. Exempel kan ju vara att plocka upp något från marken och slå hen, eller knuffa ut genom ett fönster. En teknik kan ju vara att helt plötsligt ge spelaren detta kommando och på så sätt tvingar denne att utföra denna ondskedom. Efter det är skett ska din karaktär yttra något som avslöjar att det var din plan hela tiden och sedan gå ut ur rummet med massor av skatt. Lämnar sedan huset och börjar lunka ner för berget.
