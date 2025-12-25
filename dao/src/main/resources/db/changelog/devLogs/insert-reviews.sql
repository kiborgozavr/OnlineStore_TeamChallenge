-- liquibase formatted sql
-- changeset Kate Volianiuk:insert-reviews
insert into reviews(user_id, product_id, text, rate, created_at)
values

------ id = 1
        (2, 1, 'Great', 5, '2023-09-01 12:04:03'),
        (11, 1, 'Mediocre product. It does the job but there are better options at this price point.', 3,
         '2023-11-03 08:22:33'),
        (6, 1, 'Could be better', 3, '2023-12-30 03:46:41'),
        (8, 1, 'Blah blah blah blah', 2, '2024-04-23 01:47:08'),
        (14, 1, 'Not bad, not great. Just okay.', 3, '2024-06-16 09:14:03'),
        (16, 1, 'I love it. Everything works perfectly. No complaints.', 5, '2024-07-16 06:34:51'),
        (17, 1, 'Decent quality and fast delivery. Could use a better manual though.', 4, '2024-07-21 19:06:51'),
        (9, 1, 'I am running out of ideas', 3, '2024-08-10 03:43:45'),
        (4, 1, 'Terrible', 1, '2024-09-16 02:34:22'),
        (3, 1, 'Good', 4, '2024-10-11 01:49:45'),
        (10, 1, 'Exceeded expectations. The build quality is solid and the design is sleek. Would buy again.', 5,
         '2024-12-18 10:47:46'),
        (5, 1, 'Nice', 4, '2024-12-24 23:18:25'),
        (15, 1, 'It broke after two weeks of use. Very disappointed.', 2, '2025-04-03 18:08:47'),
        (13, 1, 'Fantastic value for money. Highly recommended!', 5, '2025-04-07 06:13:00'),
        (12, 1, 'Awful experience. Customer service was no help either.', 1, '2025-05-10 13:20:10'),
        (7, 1, 'I would not recommend to anyone', 2, '2025-07-27 23:02:15'),


------ id = 2
        (2, 2, 'Great', 4, '2023-09-01 12:04:03'),
        (11, 2, 'Mediocre product. It does the job but there are better options at this price point.', 2,
         '2023-11-03 08:22:33'),
        (6, 2, 'Could be better', 4, '2023-12-30 03:46:41'),
        (8, 2, 'Blah blah blah blah', 1, '2024-04-23 01:47:08'),
        (14, 2, 'Not bad, not great. Just okay.', 2, '2024-06-16 09:14:03'),
        (16, 2, 'I love it. Everything works perfectly. No complaints.', 4, '2024-07-16 06:34:51'),
        (17, 2, 'Decent quality and fast delivery. Could use a better manual though.', 4, '2024-07-21 19:06:51'),
        (9, 2, 'I am running out of ideas', 2, '2024-08-10 03:43:45'),
        (4, 2, 'Terrible', 1, '2024-09-16 02:34:22'),
        (3, 2, 'Good', 3, '2024-10-11 01:49:45'),
        (10, 2, 'Exceeded expectations. The build quality is solid and the design is sleek. Would buy again.', 5,
         '2024-12-18 10:47:46'),
        (5, 2, 'Nice', 5, '2024-12-24 23:18:25'),
        (15, 2, 'It broke after two weeks of use. Very disappointed.', 1, '2025-04-03 18:08:47'),
        (13, 2, 'Fantastic value for money. Highly recommended!', 5, '2025-04-07 06:13:00'),
        (12, 2, 'Awful experience. Customer service was no help either.', 2, '2025-05-10 13:20:10'),
        (7, 2, 'I would not recommend to anyone', 1, '2025-07-27 23:02:15'),


-- product_id = 3
    (2, 3, 'Solid product for everyday use.', 4, '2023-08-14 11:22:10'),
    (6, 3, 'Quality is acceptable, but nothing special.', 3, '2024-02-03 16:41:55'),
    (9, 3, 'Stopped working after a month.', 2, '2024-11-19 09:12:44'),
    (13, 3, 'Excellent performance, very satisfied.', 5, '2025-03-02 18:05:27'),

-- product_id = 4
    (11, 4, 'Expected more for this price.', 3, '2023-10-21 07:33:18'),
    (4, 4, 'Terrible build quality.', 1, '2024-01-09 14:58:40'),
    (16, 4, 'Works flawlessly so far.', 5, '2024-07-30 20:16:09'),

-- product_id = 5
    (3, 5, 'Pretty good overall.', 4, '2023-12-02 19:47:01'),
    (8, 5, 'It does what it should.', 3, '2024-04-11 06:52:33'),
    (12, 5, 'Worst purchase I have made this year.', 1, '2025-01-17 12:09:54'),

-- product_id = 6
    (5, 6, 'Nice design and easy to use.', 4, '2024-03-05 22:31:12'),
    (14, 6, 'Average experience, nothing memorable.', 3, '2024-06-29 10:44:08'),
    (7, 6, 'I regret buying this.', 2, '2025-02-14 17:55:41'),

-- product_id = 7
    (10, 7, 'Exceeded my expectations.', 5, '2023-09-18 08:26:59'),
    (6, 7, 'Okay, but has some minor issues.', 3, '2024-05-22 13:37:46'),
    (15, 7, 'Broke very quickly.', 2, '2025-04-01 21:03:15'),

-- product_id = 8
    (17, 8, 'Fast delivery and good quality.', 4, '2024-02-17 09:11:23'),
    (9, 8, 'Not worth the money.', 2, '2024-10-06 15:48:02'),
    (13, 8, 'Absolutely love it!', 5, '2025-06-12 07:19:37'),

-- product_id = 9
    (2, 9, 'Very basic but functional.', 5, '2023-11-27 18:40:55'),
    (12, 9, 'Customer support was awful.', 3, '2024-08-15 11:06:44'),
    (16, 9, 'High quality and reliable.', 5, '2025-03-28 23:51:10'),

-- product_id = 10
    (8, 10, 'Nothing to complain about.', 5, '2024-01-13 05:29:31'),
    (3, 10, 'Mediocre at best.', 5, '2024-09-09 14:14:17'),
    (7, 10, 'I would buy this again.', 5, '2025-07-03 19:58:06'),

-- product_id = 11
    (5, 11, 'Looks good and works well.', 3, '2023-07-08 16:21:42'),
    (11, 11, 'Overpriced for what it offers.', 3, '2024-05-04 10:32:58'),
    (10, 11, 'Fantastic product, highly recommend.', 3, '2025-05-21 08:45:19');

