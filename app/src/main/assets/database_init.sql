INSERT INTO lieu (nom) VALUES ('1 - la défaite de 1870');
INSERT INTO lieu (nom) VALUES ('2 - la place forte de Maubeuge');
INSERT INTO lieu (nom) VALUES ('3 - Le fort de Leveau');
INSERT INTO lieu (nom) VALUES ('4 - La bataille de Maubeuge');
INSERT INTO lieu (nom) VALUES ('5 - Le tunnel des emmurés');
INSERT INTO lieu (nom) VALUES ('6 - Le soldat français de 1914');
INSERT INTO lieu (nom) VALUES ('7 - La chambrée');
INSERT INTO lieu (nom) VALUES ('8 - L''armée bleu horizon');
INSERT INTO lieu (nom) VALUES ('9 - Le corps expéditionnaire américain');
INSERT INTO lieu (nom) VALUES ('10 - Le service de santé');
INSERT INTO lieu (nom) VALUES ('11 - Les prisonniers de guerre');
INSERT INTO lieu (nom) VALUES ('12 - L''artillerie');
INSERT INTO lieu (nom) VALUES ('13 - Le tunnel central');
INSERT INTO lieu (nom) VALUES ('14 - Le ravitaillement et l''alimentation');
INSERT INTO lieu (nom) VALUES ('15 - Le monde des tranchées');
INSERT INTO lieu (nom) VALUES ('16 - Le stockage des munitions');
INSERT INTO lieu (nom) VALUES ('17 - La simple caponière');
INSERT INTO lieu (nom) VALUES ('18 - La double caponière');
INSERT INTO lieu (nom) VALUES ('19 - Le massif bétonné');
INSERT INTO lieu (nom) VALUES ('20 - La stèle commémorative');
INSERT INTO lieu (nom) VALUES ('21 - La matinée de 7 septembre');
INSERT INTO lieu (nom) VALUES ('22 - Le premier obus');
INSERT INTO lieu (nom) VALUES ('23 - Les chambrées effondrées');
INSERT INTO lieu (nom) VALUES ('24 - La chute du fort');
INSERT INTO lieu (nom) VALUES ('A - Feignies et le bassin de la Sambre durant le second conflit mondial');
INSERT INTO lieu (nom) VALUES ('B - Mémorial W.W PATTON');



INSERT INTO image (chemin, date, description) VALUES ('/android_asset/drawable-mdpi/app_img_games_gallery_11.jpg', 1914,'général Raymond Adolphe Séré de Rivières');
INSERT INTO image (chemin, date, description) VALUES ('/android_asset/drawable-mdpi/app_img_games_gallery_37.jpg', 1915,'canon 420mm');
INSERT INTO image (chemin, date, description) VALUES ('/android_asset/drawable-mdpi/app_img_games_gallery_38.jpg', 1915,'illustration prise des forts de Maubeuge');


INSERT INTO mot_cle (libelle) VALUES ('Artillerie');
INSERT INTO mot_cle (libelle) VALUES ('Allemagne');
INSERT INTO mot_cle (libelle) VALUES ('Personnalité');


INSERT INTO ImageLieuCrossRef (imageId, lieuId) VALUES (1, 1);
INSERT INTO ImageLieuCrossRef (imageId, lieuId) VALUES (2, 4);
INSERT INTO ImageLieuCrossRef (imageId, lieuId) VALUES (3, 4);


INSERT INTO ImageMotCleCrossRef (imageId, motCleId) VALUES (1, 3);
INSERT INTO ImageMotCleCrossRef (imageId, motCleId) VALUES (2, 1);
INSERT INTO ImageMotCleCrossRef (imageId, motCleId) VALUES (2, 2);
INSERT INTO ImageMotCleCrossRef (imageId, motCleId) VALUES (3, 2);

