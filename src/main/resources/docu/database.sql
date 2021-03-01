/*********************
 * MIGRATION SCRIPT
 *********************/

CREATE DATABASE rmadridbasket;

USE rmadridbasket;

/*SITE_CONFIGURATION*/

INSERT INTO site_configuration (name, `value`) VALUES ('admin-title', 'RMadrid Basket - Admin Panel');
INSERT INTO site_configuration (name, `value`) VALUES ('admin-description', 'Admin Panel');
INSERT INTO site_configuration (name, `value`) VALUES ('admin-keywords', 'real madrid, baloncesto, madrid, real, basket, basketball, acb, euroleague, euroliga, liga, endesa');
INSERT INTO site_configuration (name, `value`) VALUES ('admin-author', 'rmadridbasket.es');
INSERT INTO site_configuration (name, `value`) VALUES ('admin-copyright', 'rmadridbasket.es');


/*MENUS*/

INSERT INTO menus (menu, item, link, item_order) VALUES ('admin-menu', 'Resultados', '/Admin/Results', 1);
INSERT INTO menus (menu, item, link, item_order) VALUES ('admin-menu', 'Clasificaciones', '#', 2);
INSERT INTO menus (menu, item, link, item_order) VALUES ('admin-menu', 'Plantilla', '#', 3);
INSERT INTO menus (menu, item, link, item_order) VALUES ('admin-menu', 'Palmar&eacute;s', '#', 4);
