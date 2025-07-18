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
INSERT INTO image (chemin, date, description) VALUES ('/android_asset/drawable-mdpi/app_img_games_gallery_13.jpg', 1914,'Façade principale du fort du Bourdiau');
INSERT INTO image (chemin, date, description) VALUES ('/android_asset/drawable-mdpi/app_img_scenes_1_1.jpg', 1914,'Louis Napoléon Bonaparte');
INSERT INTO image (chemin, date, description) VALUES ('/android_asset/drawable-mdpi/app_img_scenes_1_2.jpg', 1914,'Bismarck');
INSERT INTO image (chemin, date, description) VALUES ('/android_asset/drawable-mdpi/app_img_scenes_2_1.jpg', 1914,'Maubeuge en 1914');
INSERT INTO image (chemin, date, description) VALUES ('/android_asset/drawable-mdpi/app_img_scenes_2_2.jpg', 1914,'Porte de France');

INSERT INTO mot_cle (libelle) VALUES ('Artillerie');
INSERT INTO mot_cle (libelle) VALUES ('Allemagne');
INSERT INTO mot_cle (libelle) VALUES ('Personnalité');

INSERT INTO ImageLieuCrossRef (imageId, lieuId) VALUES (1, 1);
INSERT INTO ImageLieuCrossRef (imageId, lieuId) VALUES (2, 4);
INSERT INTO ImageLieuCrossRef (imageId, lieuId) VALUES (3, 4);
INSERT INTO ImageLieuCrossRef (imageId, lieuId) VALUES (4, 2);
INSERT INTO ImageLieuCrossRef (imageId, lieuId) VALUES (5, 1);
INSERT INTO ImageLieuCrossRef (imageId, lieuId) VALUES (6, 1);
INSERT INTO ImageLieuCrossRef (imageId, lieuId) VALUES (7, 2);
INSERT INTO ImageLieuCrossRef (imageId, lieuId) VALUES (8, 2);

INSERT INTO ImageMotCleCrossRef (imageId, motCleId) VALUES (1, 3);
INSERT INTO ImageMotCleCrossRef (imageId, motCleId) VALUES (2, 1);
INSERT INTO ImageMotCleCrossRef (imageId, motCleId) VALUES (2, 2);
INSERT INTO ImageMotCleCrossRef (imageId, motCleId) VALUES (3, 2);

INSERT INTO theme (nom) VALUES ('Réunion');
INSERT INTO theme (nom) VALUES ('Histoire');
INSERT INTO theme (nom) VALUES ('Guerre');

INSERT INTO document (titre, uri, theme) VALUES ('réunion fortif n°3.pdf', '/android_asset/réunion/réunion fortif n°3.pdf', 'Réunion');
INSERT INTO document (titre, uri, theme) VALUES ('réunion fortif n°4.pdf', '/android_asset/réunion/Réunion fortif n°4.pdf', 'Réunion');
INSERT INTO document (titre, uri, theme) VALUES ('réunion fortif n°5.pdf', '/android_asset/réunion/réunion fortif n°5.pdf', 'Histoire');
INSERT INTO document (titre, uri, theme) VALUES ('réunion fortif n°6.pdf', '/android_asset/réunion/réunion fortif n°6.pdf', 'Histoire');
INSERT INTO document (titre, uri, theme) VALUES ('réunion fortif n°7.pdf', '/android_asset/réunion/réunion fortif n°7.pdf', 'Guerre');
INSERT INTO document (titre, uri, theme) VALUES ('réunion fortif n°8.pdf', '/android_asset/réunion/réunion fortif n°8.pdf', 'Guerre');
INSERT INTO document (titre, uri, theme) VALUES ('réunion fortif n°9.pdf', '/android_asset/réunion/réunion fortif n°9.pdf', 'Réunion');
INSERT INTO document (titre, uri, theme) VALUES ('réunion n°1.pdf', '/android_asset/réunion/réunion n°1.pdf', 'Histoire');
INSERT INTO document (titre, uri, theme) VALUES ('réunion n°2.pdf', '/android_asset/réunion/réunion n°2.pdf', 'Histoire');
INSERT INTO document (titre, uri, theme) VALUES ('réunions n°10-11.pdf', '/android_asset/réunion/réunions n°10-11.pdf', 'Guerre');

INSERT INTO DocumentThemeCrossRef (documentId, themeId) VALUES (1, 1);
INSERT INTO DocumentThemeCrossRef (documentId, themeId) VALUES (2, 1);
INSERT INTO DocumentThemeCrossRef (documentId, themeId) VALUES (3, 2);
INSERT INTO DocumentThemeCrossRef (documentId, themeId) VALUES (4, 2);
INSERT INTO DocumentThemeCrossRef (documentId, themeId) VALUES (5, 3);
INSERT INTO DocumentThemeCrossRef (documentId, themeId) VALUES (6, 3);
INSERT INTO DocumentThemeCrossRef (documentId, themeId) VALUES (7, 1);
INSERT INTO DocumentThemeCrossRef (documentId, themeId) VALUES (8, 2);
INSERT INTO DocumentThemeCrossRef (documentId, themeId) VALUES (9, 1);
INSERT INTO DocumentThemeCrossRef (documentId, themeId) VALUES (10, 3);
