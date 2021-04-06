/*SITE_CONFIGURATION*/
INSERT INTO site_configuration (name, `value`) VALUES ('site-title', 'RMadrid Basket');
INSERT INTO site_configuration (name, `value`) VALUES ('site-description', 'Toda la actualidad sobre el Real Madrid de Baloncesto');
INSERT INTO site_configuration (name, `value`) VALUES ('site-keywords', 'real madrid, baloncesto, madrid, real, basket, basketball, acb, euroleague, euroliga, liga, endesa');
INSERT INTO site_configuration (name, `value`) VALUES ('site-author', 'rmadridbasket.es');
INSERT INTO site_configuration (name, `value`) VALUES ('site-copyright', 'rmadridbasket.es');

INSERT INTO site_configuration (name, `value`) VALUES ('contact-whoweare', 'rmadridbasket.es es una p&aacute;gina web que ha nacido para poder seguir toda la actualidad de la secci&oactute;n de Baloncesto del Real Madrid Club de F&uacute;tbol. Esta es una p&aacute;gina sin &aacute;nimo de lucro creada con el &uacute;nico objetivo de informar lo mejor posible. Dado que esta p&aacute;gina no es un medio de comunicación profesional, toda la informaci&oacute;n que podr&aacute;s encontrar aqu&iacute; es extraida de otros medios de comunicaci&oacute;n. Nuestra intenci&oacute;n es mencionar siempre a las fuentes de donde sacamos la informaci&oacute;n, por lo que le pedimos que si encuentra alguna anomal&iacute;a en ese aspecto nos informe para poder subsanarla lo antes posible.');
INSERT INTO site_configuration (name, `value`) VALUES ('contact-mail', 'esanchez.devel@gmail.com');

/*MENUS*/
INSERT INTO menus (menu, item, link, item_order) VALUES ('site-menu', 'Resultados', '/results', 1);
INSERT INTO menus (menu, item, link, item_order) VALUES ('site-menu', 'Clasificaciones', '/standings', 2);
INSERT INTO menus (menu, item, link, item_order) VALUES ('site-menu', 'Plantilla', '/roster', 3);
INSERT INTO menus (menu, item, link, item_order) VALUES ('site-menu', 'Palmar&eacute;s', '/trophies', 4);
INSERT INTO menus (menu, item, link, item_order) VALUES ('site-menu', 'Contacta', '/contact', 5);

/*TOURNAMENTS*/
INSERT INTO tournaments (name, season) VALUES ('Liga Endesa', '2020/21');
INSERT INTO tournaments (name, season) VALUES ('Euroleague', '2020/21');
INSERT INTO tournaments (name, season) VALUES ('Copa del Rey', '2021');