INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

INSERT INTO prevoznik (adresa, naziv, pib) VALUES ('Balzakova 11', 'Balzak', 'A111');
INSERT INTO prevoznik (adresa, naziv, pib) VALUES ('Puskinova 22', 'Puskin', 'B222');
INSERT INTO prevoznik (adresa, naziv, pib) VALUES ('Bulevar 33', 'Bul', 'C333');

INSERT INTO linija (broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id) VALUES (55, 150, 'Popovica', '20:00', 2);
INSERT INTO linija (broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id) VALUES (55, 450, 'Nis', '13:00', 2);
INSERT INTO linija (broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id) VALUES (55, 330, 'Beograd', '06:00', 3);
INSERT INTO linija (broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id) VALUES (55, 990, 'Bukurest', '16:00', 1);