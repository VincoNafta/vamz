INSERT INTO store (id, storeName, iconName) VALUES
(1, 'Kaufland', 'kaufland'),
(2, 'Tesco', 'tesco'),
(3, 'BILLA', 'billa'),
(4, 'LIDL', 'lidl'),
(5, 'Jednota', 'jednota'),
(6, 'Terno', 'terno');


INSERT INTO category (id, name) VALUES
('Ostatne'),
('Potraviny'),
('Drogeria'),
('Mliečne výrobky'),
('Mäso'),
('Nápoje'),
('Sladkosti'),
('Pečivo'),
('Raňajky'),
('Detské potreby');

INSERT INTO currency (name, symbol, short_name) VALUES
( 'Euro', '€', 'EUR');

INSERT INTO currency (name, symbol, short_name) VALUES
( 'Česká Korun', 'Kč', 'CZK');



insert into price (currencyId, Price) values (1 , 0.60)

insert into items(name, producer, icon, refToStoreItem,refToPrice,refToCategory) values ("Lina", "Sedita", "lina", 3,1, 7);


INSERT INTO price (currencyId, price) VALUES
(1, 0.59),(1, 1.20),(1, 2.50),(1, 3.00),(1, 0.99),
(1, 1.79),(1, 0.89),(1, 4.20),(1, 2.00),(1, 5.50),
(1, 1.10),(1, 0.75),(1, 3.30),(1, 2.20),(1, 4.10),
(1, 0.65),(1, 1.50),(1, 2.80),(1, 3.60),(1, 1.25),
(1, 0.90),(1, 2.00),(1, 3.10),(1, 1.95),(1, 2.75),
(1, 1.05),(1, 0.55),(1, 2.40),(1, 3.20),(1, 4.50),
(1, 0.85),(1, 1.35),(1, 2.90),(1, 3.70),(1, 1.60),
(1, 0.95),(1, 2.10),(1, 3.40),(1, 4.60),(1, 1.15),
(1, 0.70),(1, 2.60),(1, 3.50),(1, 1.85),(1, 2.95),
(1, 1.00),(1, 0.60),(1, 2.30),(1, 3.15),(1, 4.80);

INSERT INTO items (name, producer, icon, refToCategory) VALUES
("Lina", "Sedita", "lina", 7),
("Mlieko čerstvé", "Rajo", "mliekocerstve", 4),
("Chlieb celozrnný", "Pekáreň Tesco", "chlieb", 8),
("Jablkový džús", "Cappy", "jablkovydzus", 6),
("Tekuté mydlo", "Palmolive", "tekutemydlo", 3),
("Kuracie prsia", "Nitra", "kuracieprsia", 5),
("Ovsené vločky", "Semix", "ovsenevlocky", 9),
("Detské plienky", "Pampers", "detskeplienky", 10),
("Cereálie müsli", "Nestlé", "cerealiemusli", 9),
("Zubná pasta", "Colgate", "zubnapasta", 3),
("Vajcia veľké", "Slovenské vajcia", "vajcia", 9),
("Syr eidam", "Lactalis", "syreidam", 4),
("Paradajky čerstvé", "Zelenina.sk", "paradajkyc", 2),
("Jahody čerstvé", "FruitFarm", "jahodyc", 2),
("Kefír biely", "Rajo", "kefirbiely", 4),
("Parfém Daisy", "Dior", "parfemdaisy", 3),
("Banány zrelé", "Dole", "banany", 2),
("Syr mozzarela", "Galbani", "syrmozzarela", 4),
("Čokoláda mliečna", "Milka", "cokoladamiliecna", 7),
("Múka hladká", "Pekárne Zemník", "muka", 8),
("Jogurt jahodový", "Zora", "jogurtjahodovy", 4),
("Rúž na pery", "Maybelline", "ruznapery", 3),
("Mrkva čerstvá", "Zelenina.sk", "mrkvac", 2),
("Káva mletá", "Jacobs", "kavamleta", 6),
("Čaj zelený", "Lipton", "cajzeleny", 6),
("Syr camembert", "Président", "syrcamembert", 4),
("Šunka varená", "Tesco", "sunkavaren", 5),
("Ovocný čaj", "Pickwick", "ovocnycaj", 6),
("Kondenzované mlieko", "Rajo", "kondenzovnemlieko", 4),
("Olivový olej", "Borges", "olivovyolej", 3),
("Sušienky kakaové", "Opavia", "susienky", 7),
("Minerálna voda", "Rajec", "mineralnavoda", 6),
("Toaletný papier", "Regina", "toaletnypapier", 3),
("Mandarínky", "FruitFarm", "mandarinky", 2),
("Rohliky", "Pekáreň Kaufland", "rohlik", 8),
("Tvaroh jemný", "Lactalis", "tvarohjemny", 4),
("Granola", "Semix", "granola", 9),
("Dezodorant", "Nivea", "dezodorant", 3),
("Syr parmezán", "Galbani", "syrparmezan", 4),
("Cukor kryštál", "Kukuričný cukor", "cukor", 8),
("Mrkvové šťavy", "Cappy", "mrkvovestavy", 6),
("Čierny chlieb", "Pekáreň Tesco", "ciernychlieb", 8),
("Cestoviny špagety", "Barilla", "cestovinyspagety", 9),
("Hroznové víno", "Vitis", "hroznovevino", 2),
("Balzam na pery", "Nivea", "balzamnapery", 3),
("Med", "Včelpo", "med", 2),
("Káva instantná", "Nescafé", "kavainstantna", 6),
("Tofu prírodné", "TofuCo", "tofuprirodne", 9),
("Papierové utierky", "Regina", "papieroveutierky", 3),
("Hrozienka", "Opavia", "hrozienka", 7);


INSERT INTO price (currencyId, price) VALUES
(1, 0.80),(1, 1.40),(1, 2.00),(1, 3.25),(1, 1.10),
(1, 1.60),(1, 0.95),(1, 4.00),(1, 2.15),(1, 5.25),
(1, 1.30),(1, 0.85),(1, 3.50),(1, 2.35),(1, 4.45),
(1, 0.70),(1, 1.75),(1, 2.60),(1, 3.85),(1, 1.40),
(1, 0.98),(1, 2.25),(1, 3.45),(1, 1.70),(1, 2.85),
(1, 1.20),(1, 0.65),(1, 2.50),(1, 3.55),(1, 4.75),
(1, 0.88),(1, 1.45),(1, 3.10),(1, 3.90),(1, 1.65),
(1, 0.92),(1, 2.15),(1, 3.65),(1, 4.70),(1, 1.25),
(1, 0.72),(1, 2.70),(1, 3.80),(1, 1.95),(1, 3.05),
(1, 1.10),(1, 0.62),(1, 2.40),(1, 3.20),(1, 4.85),
(1, 0.90),(1, 1.50),(1, 2.70),(1, 3.75),(1, 1.35),
(1, 1.05),(1, 0.78),(1, 3.00),(1, 3.60),(1, 4.90),
(1, 0.83),(1, 1.55),(1, 2.95),(1, 3.85),(1, 1.45),
(1, 0.97),(1, 2.10),(1, 3.55),(1, 4.65),(1, 1.30),
(1, 0.69),(1, 2.55),(1, 3.95),(1, 1.85),(1, 3.25),
(1, 1.15),(1, 0.68),(1, 2.35),(1, 3.45),(1, 4.55),
(1, 0.89),(1, 1.60),(1, 2.80),(1, 3.95),(1, 1.50),
(1, 1.00),(1, 0.80),(1, 3.20),(1, 3.75),(1, 4.60);

INSERT INTO ItemStorePrice (refToStore, refToPrice, refToItem) VALUES
(3, 1, 1),
(1, 2, 2),
(2, 3, 3),
(4, 4, 4),
(5, 5, 5),
(2, 6, 6),
(4, 7, 7),
(6, 8, 8),
(3, 9, 9),
(1, 10, 10),
(2, 11, 11),
(3, 12, 12),
(4, 13, 13),
(5, 14, 14),
(1, 15, 15),
(6, 16, 16),
(4, 17, 17),
(3, 18, 18),
(2, 19, 19),
(5, 20, 20),
(1, 21, 21),
(6, 22, 22),
(4, 23, 23),
(2, 24, 24),
(3, 25, 25),
(1, 26, 26),
(2, 27, 27),
(4, 28, 28),
(3, 29, 29),
(5, 30, 30),
(6, 31, 31),
(1, 32, 32),
(5, 33, 33),
(4, 34, 34),
(3, 35, 35),
(2, 36, 36),
(4, 37, 37),
(5, 38, 38),
(1, 39, 39),
(2, 40, 40),
(3, 41, 41),
(4, 42, 42),
(5, 43, 43),
(6, 44, 44),
(1, 45, 45),
(2, 46, 46),
(3, 47, 47),
(4, 48, 48),
(5, 49, 49),
(6, 50, 50);







