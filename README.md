# Platformă de Admitere - Spring Boot

Această aplicație este o platformă de admitere construită folosind Spring Boot. 
Ea facilitează procesul de admitere, oferind funcționalități esențiale pentru gestionarea candidaților, documentelor și rezultatelor.

## Descriere

Platforma de admitere este creată pentru a simplifica și eficientiza procesul de admitere într-o instituție de învățământ superior sau context similar. 
Ea oferă o interfață intuitivă și securizată pentru candidați, secretari și administrație, facilitând înregistrarea, autentificarea, încărcarea documentelor și vizualizarea rezultatelor admiterii.

## Caracteristici Principale

### Autentificare și Autorizare

- Autentificare și autorizare folosind JWT pentru trei tipuri de utilizatori: candidat, secretar și admin.

### Gestionare Conturi și Documente

- Înregistrarea și gestionarea conturilor candidaților.
- Serviciu de trimitere email-uri pentru activarea contului.
- Încărcarea documentelor necesare admiterii.
- Validarea documentelor de către secretari.

### Stocare Documente și Rezultate

- Stocare documente într-un blob storage oferit de minIO.
- Afișare și gestionare a rezultatelor admiterii.

### Integrare cu Bază de Date

- Integrare cu o bază de date Microsoft SQL Server pentru stocarea și gestionarea datelor.
