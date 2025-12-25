-- liquibase formatted sql
-- changeset Kate Volianiuk:insert-products
insert into products (id, short_desc, category_id, name, description, price, quantity, created_at, brand_id, code,
                      color)
values
    (8, 'Apple iPhone 13 128Gb Starlight', 1, 'Apple iPhone 13 128Gb Starlight', 'Apple iPhone 13 128Gb Starlight',
     23000, 100, '2021-01-01', 1, '8888881', 'WHITE'),
    (24, 'Apple iPhone 13 128Gb RED', 1, 'Apple iPhone 13 128Gb RED', 'Apple iPhone 13 128Gb Starlight',
     23000, 100, '2021-01-01', 1, '8888882', 'RED'),
    (25, 'Apple iPhone 13 128Gb Midnight', 1, 'Apple iPhone 13 128Gb Midnight', 'Apple iPhone 13 128Gb Starlight',
     23000, 100, '2021-01-01', 1, '8888883', 'BLACK'),
    (26, 'Apple iPhone 13 128Gb PINK', 1, 'Apple iPhone 13 128Gb PINK', 'Apple iPhone 13 128Gb Starlight',
     23000, 100, '2021-01-01', 1, '8888884', 'PINK'),




    (6, 'Apple iPhone 15 128GB Green', 1, 'Apple iPhone 15 128GB Green', 'Apple iPhone 15 128GB Green', 29500, 100,
     '2021-01-01', 1, '666666', 'GREEN'),
    (7, 'Apple iPhone 15 128GB Pink', 1, 'Apple iPhone 15 128GB Pink', 'Apple iPhone 15 128GB Pink', 28300, 1,
     '2021-01-01', 1, '777777', 'PINK'),

    (21, 'Apple iPhone 15 256Gb Blue', 1, 'Apple iPhone 15 256Gb Blue', 'Apple iPhone 15 256Gb Blue', 37600, 100,
     '2021-01-01', 1, '030303', 'BLUE'),

    (22, 'Apple iPhone 15 Pro 256Gb Natural Titanium', 1, 'Apple iPhone 15 Pro 256Gb Natural Titanium',
     'Apple iPhone 15 Pro 256Gb Natural Titanium', 47000, 1, '2021-01-01', 1, '040404', 'GREY'),
    (23, 'Apple iPhone 15 Pro 512Gb Natural Titanium', 1, 'Apple iPhone 15 Pro 512Gb Natural Titanium',
     'Apple iPhone 15 Pro 512Gb Natural Titanium', 58000, 10, '2021-01-01', 1, '050505', 'GREY'),

    (1, 'Apple iPhone 15 Pro Max 256Gb Natural Titanium', 1, 'Apple iPhone 15 Pro Max 256Gb Natural Titanium',
        'Apple iPhone 15 Pro Max 256Gb Natural Titanium', 53500, 100, '2021-01-01', 1, '111111', 'GREY'),
    (4, 'Apple iPhone 15 Pro 256Gb Blue Titanium', 1, 'Apple iPhone 15 Pro 256Gb Blue Titanium',
     'Apple iPhone 15 Pro 256Gb Blue Titanium', 55500, 100, '2021-01-01', 1, '444444', 'BLUE'),









    (2, 'Samsung Galaxy A24 6/128Gb Black', 1, 'Samsung Galaxy A24 6/128Gb Black',
        'Samsung Galaxy A24 6/128Gb Black', 5100, 100, '2021-01-01', 2, '222222', 'BLACK'),
    (3, 'Samsung Galaxy S24 Ultra 12/512Gb', 1, 'Samsung Galaxy S24 Ultra 12/512Gb',
        'Samsung Galaxy S24 Ultra 12/512Gb', 43400, 100, '2021-01-01', 2, '333333', 'BLACK'),

    (5, 'Samsung Galaxy S23 FE 5G 256Gb Mint Global', 1, 'Samsung Galaxy S23 FE 5G 256Gb Mint Global',
        'Samsung Galaxy S23 FE 5G 256Gb Mint Global', 20000, 100, '2021-01-01', 2, '555555', 'GREEN'),

    (9, 'Lenovo IdeaPad 1 15ALC7', 2, 'Lenovo IdeaPad 1 15ALC7', 'Lenovo IdeaPad 1 15ALC7', 23000, 100, '2021-01-01',
        3, '999999', 'GREY'),

    (10, 'Asus TUF Gaming A15', 2, 'Asus TUF Gaming A15', 'Asus TUF Gaming A15', 33700, 100, '2021-01-01', 4,
        '101010', 'GREY'),

    (11, 'Apple MacBook Air 15.3 M3 8GB 256GB', 2, 'Apple MacBook Air 15.3 M3 8GB 256GB',
        'Apple MacBook Air 15.3 M3 8GB 256GB', 47000, 100, '2021-01-01', 1, '202020', 'GREY'),

    (12, 'Nikon Coolpix P950 Black', 3, 'Nikon Coolpix P950 Black', 'Nikon Coolpix P950 Black', 36000, 100,
        '2021-01-01', 5, '303030', 'BLACK'),

    (13, 'Fujifilm Instax Mini 12 Lilac/Violet', 3, 'Fujifilm Instax Mini 12 Lilac/Violet',
        'Fujifilm Instax Mini 12 Lilac/Violet', 4000, 100, '2021-01-01', 6, '404040', 'VIOLET'),

    (14, 'Canon EOS 6D Mark II', 3, 'Canon EOS 6D Mark II', 'Canon EOS 6D Mark II', 58800, 100, '2021-01-01', 7,
        '505050', 'BLACK'),

    (15, 'Apple MacBook Air 13" M3 256GB/8GB/8GPU Midnight', 2, 'Apple MacBook Air 13" M3 256GB/8GB/8GPU Midnight',
        'Apple MacBook Air 13" M3 256GB/8GB/8GPU Midnight', 42000, 100, '2022-01-01', 1, '606060', 'GREY'),

    (16, 'JBL TUNE 720BT Purple', 4, 'JBL TUNE 720BT Purple', 'JBL TUNE 720BT Purple', 2900, 50, '2022-01-02', 8,
        '707070', 'VIOLET'),

    (17, 'Dell Vostro 3520', 2, 'Dell Vostro 3520', 'Dell Vostro 3520', 23000, 10, '2020-01-01', 9, '808080',
        'BLACK'),

    (18, 'Asus X515KA', 2, 'Asus X515KA', 'Asus X515KA', 23000, 100, '2022-01-01', 4, '909090', 'GREY'),

    (19, 'Asus TUF Gaming F17', 2, 'Asus TUF Gaming F17', 'Asus TUF Gaming F17', 39000, 2, '2023-01-01', 4, '010101',
        'GREY'),

    (20, 'Lenovo LOQ 15IRH8', 2, 'Lenovo LOQ 15IRH8', 'Lenovo LOQ 15IRH8', 37800, 100, '2023-01-01', 3, '020202',
        'GREY')

    ;
--next id = 27