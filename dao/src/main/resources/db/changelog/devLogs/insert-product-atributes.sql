-- liquibase formatted sql
insert into product_attributes (id, product_id, attribute_value_id)
values
    -- Apple iPhone 15 Pro Max
    (1, 1, 2),  -- Memory ROM: 256 Gb
    (2, 1, 6),  -- Memory RAM: 8 Gb
    (3, 1, 8),  -- Color: Gray
    (4, 1, 20), -- Display diagonal: 6.69 inches
    (5, 1, 26), -- Screen resolution: 2796 x 1290 pixels
    (6, 1, 32), -- Screen type: Super Retina XDR OLED
    (7, 1, 35), -- Screen refresh rate: 120 Hz
    (8, 1, 110), -- Glass protection technology: Ceramic Shield
    (9, 1, 42), -- Communication standards: 5G
    (10, 1, 45), -- Number of SIM cards: 1
    (11, 1, 46), -- SIM card size: Nano-SIM
    (12, 1, 48), -- Operating system: iOS
    (13, 1, 50), -- Processor frequency: 3.0 GHz
    (14, 1, 52), -- Number of processor cores: Octa-core
    (15, 1, 109), -- Processor model: A17 Pro
    (16, 1, 57), -- Flesh card: No microSD support
    (17, 1, 111), -- Camera resolution: 48 MP

    -- Samsung Galaxy A24
    (18, 2, 1),  -- Memory ROM: 128 Gb
    (19, 2, 4),  -- Memory RAM: 4 Gb
    (20, 2, 92), -- Color: Carbon Black
    (21, 2, 21), -- Display diagonal: 6.5 inches
    (22, 2, 27), -- Screen resolution: 1080 x 2340 pixels
    (23, 2, 33), -- Screen type: Super AMOLED
    (24, 2, 37), -- Screen refresh rate: 90 Hz
    (25, 2, 43), -- Communication standards: 4G LTE
    (26, 2, 45), -- Number of SIM cards: 1
    (27, 2, 46), -- SIM card size: Nano-SIM
    (28, 2, 49), -- Operating system: Android
    (29, 2, 124), -- Processor frequency: 2.2 GHz
    (30, 2, 52), -- Number of processor cores: Octa-core
    (31, 2, 123), -- Processor model: MediaTek Helio G99
    (32, 2, 56), -- Flesh card: Supports microSD
    (33, 2, 59), -- Sensor resolution: 50 MP

    -- Samsung Galaxy S24 Ultra
    (34, 3, 3),  -- Memory ROM: 512 Gb
    (35, 3, 7),  -- Memory RAM: 12 Gb
    (36, 3, 92), -- Color: carbon Black
    (37, 3, 22), -- Display diagonal: 6.8 inches
    (38, 3, 28), -- Screen resolution: 1440 x 3120 pixels
    (39, 3, 34), -- Screen type: Dynamic AMOLED 2X
    (40, 3, 35), -- Screen refresh rate: 120 Hz
    (41, 3, 116), -- Glass protection technology: Gorilla Glass Armor
    (42, 3, 42), -- Communication standards: 5G
    (43, 3, 44), -- Number of SIM cards: 2
    (44, 3, 46), -- SIM card size: Nano-SIM
    (45, 3, 49), -- Operating system: Android
    (46, 3, 104), -- Processor frequency: 3.3 GHz
    (47, 3, 52), -- Number of processor cores: Octa-core
    (48, 3, 113), -- Processor model: Snapdragon 8 Gen 3
    (49, 3, 57), -- Flesh card: Doesn't Support microSD
    (50, 3, 115), -- Main camera resolution: 200 MP

    -- Apple iPhone 15 Pro 256 Gb Blue Titanium
    (51, 4, 2),   -- Memory ROM: 256 Gb
    (52, 4, 5),   -- Memory RAM: 6 Gb
    (53, 4, 9),   -- Color: Dark Blue
    (54, 4, 24),  -- Display diagonal: 6.12 inches
    (55, 4, 30),  -- Screen resolution: 2556 x 1179 pixels
    (56, 4, 32),  -- Screen type: Super Retina XDR OLED
    (57, 4, 35),  -- Screen refresh rate: 120 Hz
    (58, 4, 110),  -- Glass protection technology: Ceramic Shield
    (59, 4, 43),  -- Communication standards: 4G LTE
    (60, 4, 45),  -- Number of SIM cards: 1
    (61, 4, 46),  -- SIM card size: Nano-SIM
    (62, 4, 48),  -- Operating system: iOS
    (63, 4, 50),  -- Processor frequency: 3.0 GHz
    (64, 4, 52),  -- Number of processor cores: Octa-core
    (65, 4, 109),  -- Processor model: A17 Pro
    (66, 4, 57),  -- Flesh card: No microSD support
    (67, 4, 111),  -- Sensor resolution: 48 MP

    -- Samsung Galaxy S23 FE 5G 256Gb Mint Global
    (68, 5, 2),   -- Memory ROM: 256 Gb
    (69, 5, 6),   -- Memory RAM: 8 Gb
    (70, 5, 12),  -- Color: Light Blue
    (71, 5, 117),  -- Display diagonal: 6.4 inches
    (72, 5, 29),  -- Screen resolution: 1080 x 2350 pixels
    (73, 5, 34),  -- Screen type: Dynamic AMOLED 2X
    (74, 5, 35),  -- Screen refresh rate: 120 Hz
    (75, 5, 43),  -- Communication standards: 4G LTE
    (76, 5, 44),  -- Number of SIM cards: 2
    (77, 5, 46),  -- SIM card size: Nano-SIM
    (78, 5, 49),  -- Operating system: Android
    (79, 5, 98),  -- Processor frequency: 2.8 GHz
    (80, 5, 52),  -- Number of processor cores: Octa-core
    (81, 5, 118),  -- Processor model: Snapdragon 8 Gen 1
    (82, 5, 57),  -- Flesh card: No microSD support
    (83, 5, 119),  -- Sensor resolution: 50 MP

    -- Apple iPhone 15 128GB Green
    (84, 6, 1),   -- Memory ROM: 128 Gb
    (85, 6, 5),   -- Memory RAM: 6 Gb
    (86, 6, 11),  -- Color: Light Green
    (87, 6, 24),  -- Display diagonal: 6.12 inches
    (88, 6, 30),  -- Screen resolution: 2556 x 1179 pixels
    (89, 6, 32),  -- Screen type: Super Retina XDR OLED
    (90, 6, 36),  -- Screen refresh rate: 60 Hz
    (91, 6, 110),  -- Glass protection technology: Ceramic Shield
    (92, 6, 43),  -- Communication standards: 4G LTE
    (93, 6, 45),  -- Number of SIM cards: 1
    (94, 6, 46),  -- SIM card size: Nano-SIM
    (95, 6, 48),  -- Operating system: iOS
    (96, 6, 50),  -- Processor frequency: 3.0 GHz
    (97, 6, 52),  -- Number of processor cores: Octa-core
    (98, 6, 120),  -- Processor model: A16 Bionic
    (99, 6, 57),  -- Flesh card: No microSD support
    (100, 6, 59), -- Sensor resolution: 12 MP

    -- Apple iPhone 15 128GB Pink
    (101, 7, 1),   -- Memory ROM: 128 Gb
    (102, 7, 5),   -- Memory RAM: 6 Gb
    (103, 7, 13),  -- Color: Powder Pink
    (104, 7, 24),  -- Display diagonal: 6.12 inches
    (105, 7, 30),  -- Screen resolution: 2556 x 1179 pixels
    (106, 7, 32),  -- Screen type: Super Retina XDR OLED
    (107, 7, 36),  -- Screen refresh rate: 60 Hz
    (108, 7, 40),  -- Glass protection technology: Gorilla Glass 5
    (109, 7, 43),  -- Communication standards: 4G LTE
    (110, 7, 45),  -- Number of SIM cards: 1
    (111, 7, 46),  -- SIM card size: Nano-SIM
    (112, 7, 48),  -- Operating system: iOS
    (113, 7, 50),  -- Processor frequency: 3.0 GHz
    (114, 7, 52),  -- Number of processor cores: Octa-core
    (115, 7, 54),  -- Processor model: A14 Bionic
    (116, 7, 57),  -- Flesh card: No microSD support
    (117, 7, 59),  -- Sensor resolution: 12 MP

    -- Apple iPhone 13 128Gb Starlight
    (118, 8, 1),   -- Memory ROM: 128 Gb
    (119, 8, 5),   -- Memory RAM: 6 Gb
    (120, 8, 16),  -- Color: White
    (121, 8, 25),  -- Display diagonal: 6.06 inches
    (122, 8, 31),  -- Screen resolution: 2532 x 1170 pixels
    (123, 8, 32),  -- Screen type: Super Retina XDR OLED
    (124, 8, 36),  -- Screen refresh rate: 60 Hz
    (125, 8, 41),  -- Glass protection technology: Gorilla Glass 3
    (126, 8, 43),  -- Communication standards: 4G LTE
    (127, 8, 45),  -- Number of SIM cards: 1
    (128, 8, 46),  -- SIM card size: Nano-SIM
    (129, 8, 48),  -- Operating system: iOS
    (130, 8, 50),  -- Processor frequency: 3.0 GHz
    (131, 8, 52),  -- Number of processor cores: Octa-core
    (132, 8, 112),  -- Processor model: A15 Bionic
    (133, 8, 57),  -- Flesh card: No microSD support
    (134, 8, 59),  -- Sensor resolution: 12 MP

    -- Lenovo IdeaPad 1 15ALC7
    (135, 9, 2),    -- Memory ROM: 256 Gb
    (136, 9, 6),    -- Memory RAM: 8 Gb
    (137, 9, 8),   -- Color: Gray
    (138, 9, 61),   -- Display diagonal: 15.6 inches
    (139, 9, 67),   -- Screen resolution: 1920 x 1080 pixels
    (140, 9, 68),   -- Screen type: IPS
    (141, 9, 36),   -- Screen refresh rate: 60 Hz
    (142, 9, 71),   -- Processor model: AMD Ryzen 5 5500U
    (143, 9, 72),   -- Processor frequency: 2.1 GHz
    (144, 9, 73),   -- Number of processor cores: Hexa-core
    (145, 9, 74),   -- Operating system: Windows 11
    (146, 9, 76),   -- Video card: Integrated

    -- Asus TUF Gaming A15
    (147, 10, 3),    -- Memory ROM: 512 Gb
    (148, 10, 7),    -- Memory RAM: 8 Gb
    (149, 10, 10),   -- Color: Dark Grey
    (150, 10, 64),   -- Display diagonal: 15.6 inches
    (151, 10, 66),   -- Screen resolution: 2560 x 1600 pixels
    (152, 10, 68),   -- Screen type: IPS
    (153, 10, 125),   -- Screen refresh rate: 144 Hz
    (154, 10, 102),   -- Processor model: Intel Core i5-12500H
    (155, 10, 78),   -- Processor frequency: 2.1 GHz
    (156, 10, 73),   -- Number of processor cores: Hexa-core
    (157, 10, 74),   -- Operating system: Windows 11
    (158, 10, 61),   -- Video card: Integrated

    -- Apple MacBook Air 15.3 M3 8GB 256GB
    (161, 11, 2),   -- Memory ROM: 256 Gb
    (162, 11, 6),   -- Memory RAM: 8 Gb
    (163, 11, 14),  -- Color: Medium Gray
    (164, 11, 127),  -- Display diagonal: 15.3 inches
    (165, 11, 79),  -- Screen resolution: 2880 x 1864 pixels
    (166, 11, 80),  -- Screen type: Liquid Retina
    (167, 11, 35),  -- Screen refresh rate: 120 Hz
    (168, 11, 81),  -- Processor model: Apple M3
    (169, 11, 82),  -- Processor frequency: 3.6 GHz
    (170, 11, 52),  -- Number of processor cores: Octa-core
    (171, 11, 84),  -- Operating system: MacOS
    (172, 11, 76),  -- Video card: Integrated

    -- Nikon Coolpix P950 Black
    (173, 12, 122),  -- Camera resolution: 16 MP
    (174, 12, 8),   -- Color: Gray
    (175, 12, 121),  -- Supports SD/SDHC/SDXC

    -- Fujifilm Instax Mini 12
    (176, 13, 128),  -- Sensor resolution: No sensor/Analog camera
    (177, 13, 19),   -- Color: Light Purple
    (178, 13, 57),  -- Doesn't Support microSD

    -- Canon EOS 6D Mark II
    (179, 14, 126),  -- Sensor resolution: 26,2 MP
    (180, 14, 10),   -- Color: Dark Gray
    (181, 14, 121),  -- Supports SD/SDHC/SDXC

    --Apple MacBook Air 13" M3 256GB/8GB/8GPU
    (182, 15, 2),   -- Memory ROM: 256 Gb
    (183, 15, 6),   -- Memory RAM: 8 Gb
    (184, 15, 85),  -- Color: Midnight
    (185, 15, 86),  -- Display diagonal: 13.6 inches
    (186, 15, 87),  -- Screen resolution: 2560 x 1664 pixels
    (187, 15, 80),  -- Screen type: Liquid Retina
    (188, 15, 81),  -- Processor model: Apple M3
    (189, 15, 52),  -- Number of processor cores: Octa-core
    (190, 15, 84),  -- Operating system: MacOS
    (191, 15, 76),  -- Video card: Integrated

    --JBL TUNE 720BT Purple
    (192, 16, 88), -- Bluetooth version 5.3
    (193, 16, 89), --Weight 200g
    (194, 16, 90), --Max play time 76h

    --Dell Vostro 3520
    (195, 17, 3), -- Memory ROM: 512 Gb
    (196, 17, 91), --Memory RAM: 16 Gb
    (197, 17, 92), --Color: Carbon Black
    (198, 17, 64), --Display diagonal: 15.6 inches
    (199, 17, 67), --Screen resolution: 1920 x 1080 pixels
    (200, 17, 93), -- Processor model: Intel Core i5-1235U
    (201, 17, 94), --Processor frequency: 4.4 GHz
    (202, 17, 52), -- Number of processor cores: Octa-core
    (203, 17, 83), --Operating system: Linux
    (204, 17, 76), --Video card: Integrated

    --Asus X515KA
    (205, 18, 3), -- Memory ROM: 512 Gb
    (206, 18, 6), --Memory RAM: 8 Gb
    (207, 18, 96), --Color: Silver
    (208, 18, 64), --Display diagonal: 15.6 inches
    (209, 18, 67), --Screen resolution: 1920 x 1080 pixels
    (210, 18, 97), -- Processor model: Intel Celeron N4500
    (211, 18, 98), --Processor frequency: 2.8 GHz
    (212, 18, 99), -- Number of processor cores: Dual-core
    (213, 18, 95), --Operating system: FreeDOS
    (214, 18, 76), --Video card: Integrated

    --Asus TUF Gaming F17
    (215, 19, 3), -- Memory ROM: 512 Gb
    (216, 19, 91), --Memory RAM: 16 Gb
    (217, 19, 10), --Color: Dark Grey
    (218, 19, 103), --Display diagonal: 17.3 inches
    (219, 19, 67), --Screen resolution: 1920 x 1080 pixels
    (220, 19, 102), -- Processor model: Intel Core i5-12500H
    (221, 19, 101), --Processor frequency: 4.5 GHz
    (222, 19, 52), -- Number of processor cores: Octa-core
    (223, 19, 95), --Operating system: FreeDOS
    (224, 19, 100), --Video card: NVIDIA GeForce RTX 3050

    --Lenovo LOQ 15IRH8
    (225, 20, 3), -- Memory ROM: 512 Gb
    (226, 20, 6), --Memory RAM: 8 Gb
    (227, 20, 8), --Color: Grey
    (228, 20, 64), --Display diagonal: 15.6 inches
    (229, 20, 67), --Screen resolution: 1920 x 1080 pixels
    (230, 20, 104), -- Processor model: Intel Core i5-12450H
    (231, 20, 94), --Processor frequency: 4.4 GHz
    (232, 20, 52), -- Number of processor cores: Octa-core
    (233, 20, 95), --Operating system: FreeDOS
    (234, 20, 105), --Video card: NVIDIA GeForce RTX 2050

    --Apple iPhone 15 256Gb Blue

    (235, 21, 2),   -- Memory ROM: 256 Gb
    (236, 21, 5),   -- Memory RAM: 6 Gb
    (237, 21, 12),  -- Color: Light Blue
    (238, 21, 24),  -- Display diagonal: 6.12 inches
    (239, 21, 30),  -- Screen resolution: 2556 x 1179 pixels
    (240, 21, 32),  -- Screen type: Super Retina XDR OLED
    (241, 21, 36),  -- Screen refresh rate: 60 Hz
    (242, 21, 110),  -- Glass protection technology: Ceramic Shield
    (243, 21, 43),  -- Communication standards: 4G LTE
    (244, 21, 45),  -- Number of SIM cards: 1
    (245, 21, 46),  -- SIM card size: Nano-SIM
    (246, 21, 48),  -- Operating system: iOS
    (247, 21, 50),  -- Processor frequency: 3.0 GHz
    (248, 21, 52),  -- Number of processor cores: Octa-core
    (249, 21, 120),  -- Processor model: A16 Bionic
    (250, 21, 57),  -- Flesh card: No microSD support
    (251, 21, 59), -- Sensor resolution: 12 MP

    --Apple iPhone 15 Pro 256Gb Natural Titanium

    (252, 22, 2),   -- Memory ROM: 256 Gb
    (253, 22, 6),   -- Memory RAM: 8 Gb
    (254, 22, 8),   -- Color: Gray
    (255, 22, 24),  -- Display diagonal: 6.12 inches
    (256, 22, 30),  -- Screen resolution: 2556 x 1179 pixels
    (257, 22, 32),  -- Screen type: Super Retina XDR OLED
    (258, 22, 36),  -- Screen refresh rate: 60 Hz
    (259, 22, 40),  -- Glass protection technology: Gorilla Glass 5
    (260, 22, 43),  -- Communication standards: 4G LTE
    (261, 22, 45),  -- Number of SIM cards: 1
    (262, 22, 46),  -- SIM card size: Nano-SIM
    (263, 22, 48),  -- Operating system: iOS
    (264, 22, 50),  -- Processor frequency: 3.0 GHz
    (265, 22, 52),  -- Number of processor cores: Octa-core
    (266, 22, 54),  -- Processor model: A14 Bionic
    (267, 22, 57),  -- Flesh card: No microSD support
    (268, 22, 59), -- Sensor resolution: 12 MP

    --Apple iPhone 15 Pro 512Gb Natural Titanium

    (269, 23, 3),   -- Memory ROM: 512 Gb
    (270, 23, 6),   -- Memory RAM: 8 Gb
    (271, 23, 8),   -- Color: Gray
    (272, 23, 24),  -- Display diagonal: 6.12 inches
    (273, 23, 30),  -- Screen resolution: 2556 x 1179 pixels
    (274, 23, 32),  -- Screen type: Super Retina XDR OLED
    (275, 23, 35),  -- Screen refresh rate: 120 Hz
    (276, 23, 110),  -- Glass protection technology: Ceramic Shield
    (277, 23, 43),  -- Communication standards: 4G LTE
    (278, 23, 45),  -- Number of SIM cards: 1
    (279, 23, 46),  -- SIM card size: Nano-SIM
    (280, 23, 48),  -- Operating system: iOS
    (281, 23, 50),  -- Processor frequency: 3.0 GHz
    (282, 23, 52),  -- Number of processor cores: Octa-core
    (283, 23, 109),  -- Processor model: A17 Pro
    (284, 23, 57),  -- Flesh card: No microSD support
    (285, 23, 111); -- Sensor resolution: 48 MP
